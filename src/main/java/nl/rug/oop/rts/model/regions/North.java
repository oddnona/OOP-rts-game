package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.StarkSoldier;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the north region.
 */
public class North extends Region{

    /**
     * constructor for the region.
     */
    public North() {
        graph = new Graph();
        name = "The North";
        graph.erasePreviousGraph();
        setupGraph();
        Army army = new Army(new StarkSoldier(), 350);
        army.setHouse(house);
        armies.add(army);
    }

    /**
     * sets up the graph.
     */
    private void setupGraph() {
        Point p = new Point(-5400, -4140);
        Throne highgarden = new Throne("Winterfell", new Point(5142 + p.x, 2996 + p.y));
        highgarden.setHouse(house);
        graph.addNode(highgarden);
        java.util.List<ArmyCamp> armyCampList = new ArrayList<>();
        createArmyCamps(armyCampList, p);
        for(ArmyCamp armyCamp : armyCampList) {
            armyCamp.setHouse(house);
            graph.addNode(armyCamp);
        }
        String name = "Wolfswood road";
        graph.connectNodes(name, highgarden, armyCampList.get(0));
        graph.connectNodes(name, highgarden, armyCampList.get(1));
        graph.connectNodes(name, highgarden, armyCampList.get(2));
        graph.connectNodes(name, highgarden, armyCampList.get(3));
        graph.connectNodes(name, highgarden, armyCampList.get(4));
        graph.connectNodes(name, highgarden, armyCampList.get(5));
        graph.connectNodes(name, highgarden, armyCampList.get(6));
        graph.connectNodes(name, highgarden, armyCampList.get(7));
        graph.connectNodes(name, highgarden, armyCampList.get(8));
        graph.connectNodes(name, highgarden, armyCampList.get(9));
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
    private void connectNodes(java.util.List<ArmyCamp> armyCampList) {
        String road = "Wolfswood road";
        graph.connectNodes(road, armyCampList.get(2), armyCampList.get(1));
        graph.connectNodes(road, armyCampList.get(3), armyCampList.get(4));
        graph.connectNodes(road, armyCampList.get(5), armyCampList.get(4));
        graph.connectNodes(road, armyCampList.get(5), armyCampList.get(6));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(8), armyCampList.get(9));
    }

    /**
     * adds armies on the army camps.
     * @param armyCamps the list of army camps.
     */
    private void addArmies(java.util.List<ArmyCamp> armyCamps) {
        armyCamps.get(0).deployTroops(new Army(new StarkSoldier(),100));
        armyCamps.get(1).deployTroops(new Army(new StarkSoldier(),15));
        armyCamps.get(2).deployTroops(new Army(new StarkSoldier(),20));
        armyCamps.get(3).deployTroops(new Army(new StarkSoldier(),15));
        armyCamps.get(4).deployTroops(new Army(new StarkSoldier(),10));
        armyCamps.get(5).deployTroops(new Army(new StarkSoldier(),10));
        armyCamps.get(6).deployTroops(new Army(new StarkSoldier(),10));
        armyCamps.get(7).deployTroops(new Army(new StarkSoldier(),15));
        armyCamps.get(8).deployTroops(new Army(new StarkSoldier(),20));
        armyCamps.get(9).deployTroops(new Army(new StarkSoldier(),15));
    }

    /**
     * creates the army camps.
     * @param armyCamps the list of army camps.
     * @param p the position relative to the panel.
     */
    private void createArmyCamps(List<ArmyCamp> armyCamps, Point p) {
        String name = "Stark Camp";
        armyCamps.add(new ArmyCamp(name, new Point(5745 + p.x, 3184 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4932 + p.x, 4282 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5386 + p.x, 3794 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5508 + p.x, 5156 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5765 + p.x, 4668 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(6314 + p.x, 5277 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(6118 + p.x, 4586 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(6470 + p.x, 4735 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5779 + p.x, 3746 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(6240 + p.x, 4017 + p.y)));
    }
}