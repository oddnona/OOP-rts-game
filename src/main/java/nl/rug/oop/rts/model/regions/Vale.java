package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.soldiertypes.ArrynSoldier;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the vale of arryn region.
 */
public class Vale extends Region{

    /**
     * constructor for the region.
     */
    public Vale() {
        graph = new Graph();
        name = "The Vale";
        graph.erasePreviousGraph();
        setupGraph();
        Army army = new Army(new ArrynSoldier(), 400);
        army.setHouse(house);
        armies.add(army);
    }

    /**
     * sets up the graph.
     */
    private void setupGraph() {
        Point p = new Point(-720, -4320);
        Throne highgarden = new Throne("The Eyrie", new Point(4370 + p.x, 1890 + p.y));
        highgarden.setHouse(house);
        graph.addNode(highgarden);
        java.util.List<ArmyCamp> armyCampList = new ArrayList<>();
        createArmyCamps(armyCampList, p);
        for(ArmyCamp armyCamp : armyCampList) {
            armyCamp.setHouse(house);
            graph.addNode(armyCamp);
        }
        String name = "Stairs";
        graph.connectNodes(name, highgarden, armyCampList.get(0));
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
        String road = "the Bloody Gate";
        graph.connectNodes(road, armyCampList.get(0), armyCampList.get(1));
        graph.connectNodes(road, armyCampList.get(1), armyCampList.get(2));
        graph.connectNodes(road, armyCampList.get(2), armyCampList.get(3));
        graph.connectNodes(road, armyCampList.get(3), armyCampList.get(4));
        graph.connectNodes(road, armyCampList.get(4), armyCampList.get(5));
        graph.connectNodes(road, armyCampList.get(5), armyCampList.get(6));
        graph.connectNodes(road, armyCampList.get(6), armyCampList.get(7));
        graph.connectNodes(road, armyCampList.get(7), armyCampList.get(8));
        graph.connectNodes(road, armyCampList.get(8), armyCampList.get(9));
        graph.connectNodes(road, armyCampList.get(9), armyCampList.get(10));
    }

    /**
     * adds armies on the army camps.
     * @param armyCamps the list of army camps.
     */
    private void addArmies(java.util.List<ArmyCamp> armyCamps) {
        armyCamps.get(0).deployTroops(new Army(new ArrynSoldier(),2));
        armyCamps.get(1).deployTroops(new Army(new ArrynSoldier(),12));
        armyCamps.get(2).deployTroops(new Army(new ArrynSoldier(),22));
        armyCamps.get(3).deployTroops(new Army(new ArrynSoldier(),32));
        armyCamps.get(4).deployTroops(new Army(new ArrynSoldier(),62));
        armyCamps.get(5).deployTroops(new Army(new ArrynSoldier(),52));
        armyCamps.get(6).deployTroops(new Army(new ArrynSoldier(),42));
        armyCamps.get(7).deployTroops(new Army(new ArrynSoldier(),32));
        armyCamps.get(8).deployTroops(new Army(new ArrynSoldier(),22));
        armyCamps.get(9).deployTroops(new Army(new ArrynSoldier(),12));
        armyCamps.get(10).deployTroops(new Army(new ArrynSoldier(),2));
    }

    /**
     * creates the army camps.
     * @param armyCamps the list of army camps.
     * @param p the position relative to the panel.
     */
    private void createArmyCamps(List<ArmyCamp> armyCamps, Point p) {
        String name = "Arryn Camp";
        armyCamps.add(new ArmyCamp(name, new Point(4390 + p.x, 2350 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4630 + p.x, 2540 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(5020 + p.x, 3200 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4600 + p.x, 3391 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(4072 + p.x, 3500 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3750 + p.x, 3445 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3380 + p.x, 3710 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(3070 + p.x, 3919 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2710 + p.x, 4224 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2357 + p.x, 4475 + p.y)));
        armyCamps.add(new ArmyCamp(name, new Point(2005 + p.x, 4773 + p.y)));
    }
}
