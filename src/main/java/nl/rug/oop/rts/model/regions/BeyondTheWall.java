package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.House;
import nl.rug.oop.rts.model.events.ArmorEvent;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.events.MilkOfThePoppyEvent;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Beyond the wall region.
 */
public class BeyondTheWall extends Region{

    /**
     * constructor for the region.
     */
    public BeyondTheWall() {
        graph = new Graph();
        name = "Beyond the Wall";
        house = House.FreeFolk;
        Army army = new Army(new FreeFolkWarrior(), 100);
        army.setHouse(house);
        armies.add(army);
        setupGraph();
        Event event = new MilkOfThePoppyEvent(house);
        events.add(event);
        events.add(new ArmorEvent(house));
        graph.erasePreviousGraph();
    }

    /**
     * sets up the graph.
     */
    private void setupGraph() {
        Point p = new Point(-5000, 0);
        Settlement theSept = new Settlement("Storrold's Point", new Point(1883 + p.x, 2229 + p.y));
        theSept.setHouse(house);
        graph.addNode(theSept);
        Settlement goldenGrove = new Settlement("Tents", new Point(5989 + p.x, 3490 + p.y));
        goldenGrove.setHouse(house);
        graph.addNode(goldenGrove);
        Settlement ciderHall = new Settlement("Tents", new Point(4850 + p.x, 2974 + p.y));
        ciderHall.setHouse(house);
        graph.addNode(ciderHall);
        Settlement tent1 = new Settlement("Tent", new Point(2256 + p.x, 3075 + p.y));
        tent1.setHouse(house);
        graph.addNode(tent1);
        Settlement tent2 = new Settlement("Tents", new Point(3164 + p.x, 3150 + p.y));
        tent2.setHouse(house);
        graph.addNode(tent2);
        Settlement tent3 = new Settlement("Tent", new Point(6328 + p.x, 1900 + p.y));
        tent3.setHouse(house);
        graph.addNode(tent3);
        Throne hardhome = new Throne("Hardhome", new Point(3963 + p.x, 4275 + p.y));
        hardhome.setHouse(house);
        graph.addNode(hardhome);
        java.util.List<ArmyCamp> armyCampList = new ArrayList<>();
        createArmyCamps(armyCampList, p);
        for(ArmyCamp armyCamp : armyCampList) {
            armyCamp.setHouse(house);
            graph.addNode(armyCamp);
        }
        String name = "Milkroad";
        graph.connectNodes(name, tent3, armyCampList.get(0));
        graph.connectNodes(name, goldenGrove, armyCampList.get(1));
        graph.connectNodes(name, ciderHall, armyCampList.get(2));
        graph.connectNodes(name, tent2, armyCampList.get(3));
        graph.connectNodes(name, tent1, armyCampList.get(4));
        graph.connectNodes(name, theSept, armyCampList.get(5));
        graph.connectNodes(name, theSept, armyCampList.get(6));
        graph.connectNodes(name, hardhome, armyCampList.get(7));
        graph.connectNodes(name, hardhome, armyCampList.get(8));
        graph.connectNodes(name, hardhome, armyCampList.get(9));
        graph.connectNodes(name, hardhome, armyCampList.get(10));
        graph.connectNodes(name, hardhome, armyCampList.get(11));
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
        String road = "Milkroad";
        graph.connectNodes(road, armyCampList.get(7), armyCampList.get(8));
        graph.connectNodes(road, armyCampList.get(11), armyCampList.get(9));
        graph.connectNodes(road, armyCampList.get(9), armyCampList.get(8));
    }

    /**
     * adds armies on the army camps.
     * @param armyCamps the list of army camps.
     */
    private void addArmies(java.util.List<ArmyCamp> armyCamps) {
        armyCamps.get(0).deployTroops(new Army(new FreeFolkWarrior(),2));
        armyCamps.get(1).deployTroops(new Army(new FreeFolkWarrior(),3));
        armyCamps.get(2).deployTroops(new Army(new FreeFolkWarrior(),7));
        armyCamps.get(3).deployTroops(new Army(new FreeFolkWarrior(),4));
        armyCamps.get(4).deployTroops(new Army(new FreeFolkWarrior(),2));
        armyCamps.get(5).deployTroops(new Army(new FreeFolkWarrior(),10));
        armyCamps.get(6).deployTroops(new Army(new FreeFolkWarrior(),4));
        armyCamps.get(7).deployTroops(new Army(new FreeFolkWarrior(),5));
        armyCamps.get(8).deployTroops(new Army(new FreeFolkWarrior(),5));
        armyCamps.get(9).deployTroops(new Army(new FreeFolkWarrior(),5));
        armyCamps.get(10).deployTroops(new Army(new FreeFolkWarrior(),10));
        armyCamps.get(11).deployTroops(new Army(new FreeFolkWarrior(),5));
    }

    /**
     * creates the army camps.
     * @param armyCamps the list of army camps.
     * @param p the position relative to the panel.
     */
    private void createArmyCamps(List<ArmyCamp> armyCamps, Point p) {
        String name = "Wildling Camp";
        armyCamps.add(new ArmyCamp(name, new Point(6483 + p.x, 2188 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5603 + p.x, 3875 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4261 + p.x, 2967 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3604 + p.x, 2791 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3170 + p.x, 2120 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2710 + p.x, 2479 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3882 + p.x, 3455 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3008 + p.x, 4214 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3482 + p.x, 5088 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4417 + p.x, 5189 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4547 + p.x, 3888 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4857 + p.x, 5223 + p.y)));
    }
}
