package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class simulates one phase.
 * it moves the enemy and player's armies and starts and resolves battles.
 */
public class Simulation {
    /**
     * the instance of the game.
     */
    private Game game;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the simulation class.
     * @param game the instance of the game.
     */
    public Simulation(Game game) {
        this.game = game;
        sounds = new Sounds();
    }

    /**
     * simulates a phase where the armies battle and move.
     */
    public void simulate() {
        game.getPlayer().getCurrentRegion().getGraph().savePreviousGraph();
        resolveBattles();
        moveEnemyArmies();
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Node node : graph.getNodes()) {
            node.getTimer().start();
            List<Army> armies = new ArrayList<>(node.getArmies());
            for(Army army : armies) {
                if(army.getNextEdge() != null) {
                    node.commandArmy(army, army.getNextEdge());
                    army.setPreviousEdge(army.getNextEdge());
                    army.setMoving(true);
                }
                army.setNextEdge(null);
            }
        }
        for(Edge edge : graph.getEdges()) {
            edge.getTimer().start();
            List<Army> armies = new ArrayList<>(edge.getArmies());
            for(Army army : armies) {
                if(army.getNextNode() != null) {
                    edge.commandArmy(army, army.getNextNode());
                    army.setPreviousNode(army.getNextNode());
                    army.setMoving(true);
                }
                army.setNextNode(null);
            }
        }
        List<AddArmyObserver> copyObservers = new ArrayList<>(game.getAddArmyObservers());
        for(AddArmyObserver observer : copyObservers) {
            observer.onAddedArmy();
        }
        graph.notifyObservers();
        doEvents();
        checkForBattle();
    }

    /**
     * helper for the check for enemies on nodes.
     * @param edge one of the edges the armies are.
     * @param edge2 the other edge where the enemy army would be.
     * @param node the node the player's army is going.
     */
    private void checkForArmies(Edge edge, Edge edge2, Node node) {
        for(Army army : edge.getArmies()) {
            if(army.getHouse() != game.getPlayer().getHouse()) {
                for(Army army2 : edge2.getArmies()) {
                    if(army2.getHouse() == game.getPlayer().getHouse() && army2.getNextNode() == node) {
                        army.setNextNode(node);
                    }
                }
            }
        }
    }

    /**
     * executes events on the entire graph.
     */
    private void doEvents(){
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Node node : graph.getNodes()) {
            List<Event> events = new ArrayList<>(node.getEvents());
            for(Event event : events) {
                event.doEvent(node.getArmies());
                if(event.isEventDone() && event.isOneTimeOnly()) {
                    node.removeEvent(event);
                }
            }
        }
        for(Edge edge : graph.getEdges()) {
            List<Event> events = new ArrayList<>(edge.getEvents());
            for(Event event : events) {
                event.doEvent(edge.getArmies());
                if(event.isEventDone() && event.isOneTimeOnly()) {
                    edge.removeEvent(event);
                }
            }
        }
    }

    /**
     * does a battle on a node.
     * @param army1 the first army in the battle.
     * @param army2 the opposite army in the battle.
     * @param node the node the battle happens on.
     */
    public void battleOnNode(Army army1, Army army2, Node node) {
        Army winner, loser;
        int amountArmy1 = army1.getAmount(), amountArmy2 = army2.getAmount(), winnerAmount;
        battle(army1, army2);
        if(army1.getAmount() > army2.getAmount()) {
            winner = army1;
            loser = army2;
            winnerAmount = amountArmy1;
        } else if(army2.getAmount() > army1.getAmount()) {
            winner = army2;
            loser = army1;
            winnerAmount = amountArmy2;
        } else {
            JOptionPane.showMessageDialog(null, army1 + " and " + army2 + " both lost in the battle");
            node.getArmies().remove(army1);
            node.getArmies().remove(army2);
            return;
        }
        if(winner.getHouse() == game.getPlayer().getHouse()) {
            sounds.playSound("winsound.wav");
        } else {
            sounds.playSound("losesound.wav");
        }
        node.getArmies().remove(loser);
        JOptionPane.showMessageDialog(null, "The " + winner.getSoldier()
                + "s won but they lost " + (winnerAmount - winner.getAmount()) + " soldiers");
        winner.setAmount(winner.getAmount() - loser.getAmount());
    }

    /**
     * decides who wins in a battle based on the stats the army has.
     * @param army1 the first army in the battle.
     * @param army2 the opposite army in the battle.
     */
    private void battle(Army army1, Army army2) {
        int currentHealth1 = army1.getSoldier().getHealth();
        int currentHealth2 = army2.getSoldier().getHealth();
        while(army1.getAmount() > 0 && army2.getAmount() > 0) {
            int nrOfHits1, nrOfHits2;
            if(currentHealth1 % army2.getSoldier().getDamage() == 0) {
                nrOfHits1 = currentHealth2 / army1.getSoldier().getDamage();
            } else {
                nrOfHits1 = currentHealth2 / army1.getSoldier().getDamage() + 1;
            }
            if(currentHealth2 % army1.getSoldier().getDamage() == 0) {
                nrOfHits2 = currentHealth1 / army2.getSoldier().getDamage();
            } else {
                nrOfHits2 = currentHealth1 / army2.getSoldier().getDamage() + 1;
            }
            if(nrOfHits1 < nrOfHits2) {
                currentHealth1 = currentHealth1 - army2.getSoldier().getDamage() * nrOfHits1;
                army2.setAmount(army2.getAmount() - 1);
                currentHealth2 = army2.getSoldier().getHealth();
            } else if(nrOfHits2 < nrOfHits1) {
                currentHealth2 = currentHealth2 - army1.getSoldier().getDamage() * nrOfHits2;
                army1.setAmount(army1.getAmount() - 1);
                currentHealth1 = army1.getSoldier().getHealth();
            } else {
                army1.setAmount(army1.getAmount() - 1);
                army2.setAmount(army2.getAmount() - 1);
                currentHealth2 = army2.getSoldier().getHealth();
                currentHealth1 = army2.getSoldier().getHealth();
            }
        }
    }

    /**
     * does a battle on a node.
     * @param army1 the first army in the battle.
     * @param army2 the opposite army in the battle.
     * @param edge the edge the battle happens on.
     */
    public void battleOnEdge(Army army1, Army army2, Edge edge) {
        Army winner, loser;
        int amountArmy1 = army1.getAmount(), amountArmy2 = army2.getAmount(), winnerAmount;
        battle(army1, army2);
        if(army1.getAmount() > army2.getAmount()) {
            winner = army1;
            loser = army2;
            winnerAmount = amountArmy1;
        } else if(army2.getAmount() > army1.getAmount()) {
            winner = army2;
            loser = army1;
            winnerAmount = amountArmy2;
        } else {
            JOptionPane.showMessageDialog(null, army1 + " and " + army2 + " both lost in the battle");
            edge.getArmies().remove(army1);
            edge.getArmies().remove(army2);
            return;
        }
        if(winner.getHouse() == game.getPlayer().getHouse()) {
            sounds.playSound("winsound.wav");
        } else {
            sounds.playSound("losesound.wav");
        }
        edge.getArmies().remove(loser);
        JOptionPane.showMessageDialog(null, "The " + winner.getSoldier()
                + "s won but they lost " + (winnerAmount - winner.getAmount()) + " soldiers");
        winner.setAmount(winner.getAmount() - loser.getAmount());
    }

    /**
     * function to move enemy armies where the player armies are moving.
     */
    private void moveEnemyArmies() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Edge edge : graph.getEdges()) {
            checkForEnemiesOnEdges(edge.getNode1(), edge.getNode2(), edge);
            checkForEnemiesOnEdges(edge.getNode2(), edge.getNode1(), edge);
        }
        for(Node node : graph.getNodes()) {
            checkForEnemiesOnNodes(node);
        }
    }

    /**
     * check for the battles on the entire graph and shows a popup for each battle started.
     */
    private void checkForBattle(){
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Node node : graph.getNodes()) {
            for(Army army : node.getArmies()) {
                for(Army army1 : node.getArmies()) {
                    if(army.getHouse() != army1.getHouse() && !army.isinBattle() && !army1.isinBattle()) {
                        army.setinBattle(true);
                        army1.setinBattle(true);
                        sounds.playSound("swordsound.wav");
                        JOptionPane.showMessageDialog(null,
                                "A battle has started between: "
                                        + army.toString()
                                        + " and " + army1.toString());
                    }
                }
            }
        }
        for(Edge edge : graph.getEdges()) {
            for(Army army : edge.getArmies()) {
                for(Army army1 : edge.getArmies()) {
                    if(army.getHouse() != army1.getHouse() && !army.isinBattle() && !army1.isinBattle()) {
                        army.setinBattle(true);
                        army1.setinBattle(true);
                        sounds.playSound("swordsound.wav");
                        JOptionPane.showMessageDialog(null,
                                "A battle has started between: "
                                        + army
                                        + " and " + army1);
                    }
                }
            }
        }
        resetBattles();
    }

    /**
     * resolves the battles on the entire graph.
     */
    private void resolveBattles() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Node node : graph.getNodes()) {
            List<Army> armies = new ArrayList<>(node.getArmies());
            for(Army army : armies) {
                for(Army army1 : armies) {
                    if(army.getHouse() != army1.getHouse() && !army.isinBattle() && !army1.isinBattle()) {
                        army.setinBattle(true);
                        army1.setinBattle(true);
                        battleOnNode(army, army1, node);
                    }
                }
            }
        }
        for(Edge edge : graph.getEdges()) {
            List<Army> armies = new ArrayList<>(edge.getArmies());
            for(Army army : armies) {
                for(Army army1 : armies) {
                    if(army.getHouse() != army1.getHouse() && !army.isinBattle() && !army1.isinBattle()) {
                        army.setinBattle(true);
                        army1.setinBattle(true);
                        battleOnEdge(army, army1, edge);
                    }
                }
            }
        }
        resetBattles();
    }

    /**
     * resets the in battle states for each army.
     */
    private void resetBattles() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        for(Node node : graph.getNodes()) {
            for(Army army : node.getArmies()) {
                army.setinBattle(false);
            }
        }
        for(Edge edge : graph.getEdges()) {
            for(Army army : edge.getArmies()) {
                army.setinBattle(false);
            }
        }
    }

    /**
     * function to check for enemies on neighbouring edges.
     * @param node1 first node of the edge.
     * @param node2 second node of the edge.
     * @param edge the current edge.
     */
    private void checkForEnemiesOnEdges(Node node1, Node node2, Edge edge) {
        for(Army army : node1.getArmies()) {
            if(army.getHouse() != game.getPlayer().getHouse()) {
                for(Army army2 : node2.getArmies()) {
                    if(army2.getHouse() == game.getPlayer().getHouse() && army2.getNextEdge() == edge) {
                        army.setNextEdge(edge);
                    }
                }
            }
        }
    }

    /**
     * function to check for enemies on neighbouring nodes.
     * @param node the current node.
     */
    private void checkForEnemiesOnNodes(Node node) {
        for(Edge edge : node.getEdges()) {
            for(Edge edge2 : node.getEdges()) {
                if(edge != edge2) {
                    checkForArmies(edge, edge2, node);
                }
            }
        }
    }
}
