package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.soldiertypes.KingsGuard;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * dragonstone region.
 */
public class Dragonstone extends Region{

    /**
     * constructor for the region.
     */
    public Dragonstone() {
        graph = new Graph();
        name = "Dragonstone";
        graph.erasePreviousGraph();
        setupGraph();
    }

    /**
     * sets up the graph.
     */
    private void setupGraph() {
        Point p = new Point(-1040, -2600);
        Settlement theSept = new Settlement("Stone Drum", new Point(2344 + p.x, 2277 + p.y));
        theSept.setHouse(house);
        graph.addNode(theSept);
        Settlement goldenGrove = new Settlement("Dragonmont", new Point(2611 + p.x, 3758 + p.y));
        goldenGrove.setHouse(house);
        graph.addNode(goldenGrove);
        Throne highgarden = new Throne("Dragonstone", new Point(3998 + p.x, 1180 + p.y));
        highgarden.setHouse(house);
        graph.addNode(highgarden);
        java.util.List<ArmyCamp> armyCampList = new ArrayList<>();
        createArmyCamps(armyCampList, p);
        for(ArmyCamp armyCamp : armyCampList) {
            armyCamp.setHouse(house);
            graph.addNode(armyCamp);
        }
        String name = "Crackclaw Point";
        graph.connectNodes(name, highgarden, armyCampList.get(5));
        graph.connectNodes(name, highgarden, armyCampList.get(6));
        graph.connectNodes(name, highgarden, armyCampList.get(7));
        graph.connectNodes(name, theSept, armyCampList.get(1));
        graph.connectNodes(name, theSept, armyCampList.get(2));
        graph.connectNodes(name, theSept, armyCampList.get(3));
        graph.connectNodes(name, goldenGrove, armyCampList.get(0));
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
        String road = "Crackclaw Point";
        graph.connectNodes(road, armyCampList.get(4), armyCampList.get(5));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(5), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(5));
    }

    /**
     * adds armies on the army camps.
     * @param armyCamps the list of army camps.
     */
    private void addArmies(java.util.List<ArmyCamp> armyCamps) {
        armyCamps.get(0).deployTroops(new Army(new KingsGuard(),35));
        armyCamps.get(1).deployTroops(new Army(new KingsGuard(),20));
        armyCamps.get(2).deployTroops(new Army(new KingsGuard(),10));
        armyCamps.get(3).deployTroops(new Army(new KingsGuard(),20));
        armyCamps.get(4).deployTroops(new Army(new KingsGuard(),20));
        armyCamps.get(5).deployTroops(new Army(new KingsGuard(),50));
        armyCamps.get(6).deployTroops(new Army(new KingsGuard(),34));
        armyCamps.get(7).deployTroops(new Army(new KingsGuard(),25));
    }

    /**
     * creates the army camps.
     * @param armyCamps the list of army camps.
     * @param p the position relative to the panel.
     */
    private void createArmyCamps(List<ArmyCamp> armyCamps, Point p) {
        String name = "Kingsguard Camp";
        armyCamps.add(new ArmyCamp(name, new Point(2411 + p.x, 4126 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(1592 + p.x, 2956 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2049 + p.x, 2405 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2344 + p.x, 2622 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2895 + p.x, 1497 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3530 + p.x, 1358 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4070 + p.x, 818 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4293 + p.x, 1291 + p.y)));
    }

}
