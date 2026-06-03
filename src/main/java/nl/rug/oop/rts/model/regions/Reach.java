package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.KingsGuard;
import nl.rug.oop.rts.model.soldiertypes.LannisterSoldier;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the reach region.
 */
public class Reach extends Region{

    /**
     * constructor for the region.
     */
    public Reach() {
        graph = new Graph();
        house = House.Tyrell;
        name = "The Reach";
        setupGraph();
        Army army = new Army(new TyrellSoldier(), 1500);
        army.setHouse(house);
        armies.add(army);

        Army army2 = new Army(new KingsGuard(), 900);
        army2.setHouse(house);
        armies.add(army2);

        Army army3 = new Army(new LannisterSoldier(), 130);
        army3.setHouse(house);
        armies.add(army3);
    }

    /**
     * sets up the graph.
     */
    private void setupGraph() {
        Point p = new Point(-5063, -4713);
        Settlement theSept = new Settlement("The Sept", new Point(3846 + p.x, 1667 + p.y));
        theSept.setHouse(house);
        graph.addNode(theSept);
        Settlement goldenGrove = new Settlement("Goldengrove", new Point(5101 + p.x, 4967 + p.y));
        goldenGrove.setHouse(house);
        graph.addNode(goldenGrove);
        Settlement ciderHall = new Settlement("Cider Hall", new Point(1821 + p.x, 3622 + p.y));
        ciderHall.setHouse(house);
        graph.addNode(ciderHall);
        Throne highgarden = new Throne("Highgarden", new Point(2200 + p.x, 973 + p.y));
        highgarden.setHouse(house);
        graph.addNode(highgarden);
        List<ArmyCamp> armyCampList = new ArrayList<>();
        createArmyCamps(armyCampList, p);
        for(ArmyCamp armyCamp : armyCampList) {
            armyCamp.setHouse(house);
            graph.addNode(armyCamp);
        }
        String name = "Roseroad";
        graph.connectNodes(name, highgarden, armyCampList.get(0));
        graph.connectNodes(name, theSept, armyCampList.get(2));
        graph.connectNodes(name, ciderHall, armyCampList.get(17));
        graph.connectNodes(name, ciderHall, armyCampList.get(18));
        graph.connectNodes(name, goldenGrove, armyCampList.get(19));
        connectNodes(armyCampList);
        for(Edge edge : graph.getEdges()) {
            edge.setHouse(house);
        }
        graph.erasePreviousGraph();
        addArmies(armyCampList);
        addTraps();
    }

    /**
     * add trap events on random edges.
     */
    private void addTraps(){
        Random random = new Random();
        for(Edge edge : graph.getEdges()) {
            boolean add = random.nextBoolean();
            if(add) {
                edge.addEvent(new TrapedBattlefieldEvent(house));
            }
        }
    }

    /**
     * connects the nodes.
     * @param armyCampList the list of army nodes.
     */
    private void connectNodes(List<ArmyCamp> armyCampList) {
        String road = "Roseroad";
        graph.connectNodes(road, armyCampList.get(0), armyCampList.get(1));
        graph.connectNodes(road, armyCampList.get(1), armyCampList.get(2));
        graph.connectNodes(road, armyCampList.get(2), armyCampList.get(3));
        graph.connectNodes(road, armyCampList.get(3), armyCampList.get(4));
        graph.connectNodes(road, armyCampList.get(3), armyCampList.get(5));
        graph.connectNodes(road, armyCampList.get(4), armyCampList.get(6));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(8));
        graph.connectNodes(road, armyCampList.get(8), armyCampList.get(10));
        graph.connectNodes(road, armyCampList.get(5), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(7), armyCampList.get(9));
        graph.connectNodes(road, armyCampList.get(9), armyCampList.get(11));
        graph.connectNodes(road, armyCampList.get(4), armyCampList.get(5));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(8), armyCampList.get(9));
        graph.connectNodes(road, armyCampList.get(10), armyCampList.get(11));
        graph.connectNodes(road, armyCampList.get(3), armyCampList.get(12));
        graph.connectNodes(road, armyCampList.get(12), armyCampList.get(13));
        graph.connectNodes(road, armyCampList.get(13), armyCampList.get(14));
        graph.connectNodes(road, armyCampList.get(14), armyCampList.get(15));
        graph.connectNodes(road, armyCampList.get(16), armyCampList.get(18));
        graph.connectNodes(road, armyCampList.get(16), armyCampList.get(17));
        graph.connectNodes(road, armyCampList.get(17), armyCampList.get(18));
    }

    /**
     * adds armies on the army camps.
     * @param armyCamps the list of army camps.
     */
    private void addArmies(List<ArmyCamp> armyCamps) {
        armyCamps.get(0).deployTroops(new Army(new TyrellSoldier(),200));
        armyCamps.get(1).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(2).deployTroops(new Army(new TyrellSoldier(),30));
        armyCamps.get(3).deployTroops(new Army(new TyrellSoldier(),15));
        armyCamps.get(4).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(5).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(6).deployTroops(new Army(new TyrellSoldier(),15));
        armyCamps.get(7).deployTroops(new Army(new TyrellSoldier(),15));
        armyCamps.get(8).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(9).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(10).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(11).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(12).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(13).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(14).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(15).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(16).deployTroops(new Army(new TyrellSoldier(),10));
        armyCamps.get(17).deployTroops(new Army(new TyrellSoldier(),20));
        armyCamps.get(18).deployTroops(new Army(new TyrellSoldier(),30));
        armyCamps.get(19).deployTroops(new Army(new TyrellSoldier(),50));
    }

    /**
     * creates the army camps.
     * @param armyCamps the list of army camps.
     * @param p the position relative to the panel.
     */
    private void createArmyCamps(List<ArmyCamp> armyCamps, Point p) {
        String name = "Tyrell Camp";
        armyCamps.add(new ArmyCamp(name, new Point(2529 + p.x, 1317 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2879 + p.x, 1653 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3349 + p.x, 1968 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3531 + p.x, 2711 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3531 + p.x, 2844 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3636 + p.x, 2767 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3797 + p.x, 3258 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3965 + p.x, 3202 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4148 + p.x, 3720 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4246 + p.x, 3636 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4547 + p.x, 3888 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4645 + p.x, 3825 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4407 + p.x, 2774 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5023 + p.x, 2459 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5409 + p.x, 2641 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5717 + p.x, 3041 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(1968 + p.x, 4484 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(1891 + p.x, 4007 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(1380 + p.x, 4218 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5654 + p.x, 4785 + p.y)));
    }
}
