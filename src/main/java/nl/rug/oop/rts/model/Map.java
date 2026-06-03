package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.regions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * class tha represents the model of the main map that has multiple regions.
 */
public class Map {
    /**
     * the list of regions.
     */
    private List<Region> regions;
    /**
     * a map for all the regions and their houses.
     */
    private java.util.Map<House, Region> regionMap;
    /**
     * the history log scroll.
     */
    private Scroll scroll;

    /**
     * constructor for the map model.
     */
    public Map() {
        regions = new ArrayList<>();
        regionMap = new HashMap<>();
        scroll = new Scroll();
        setupRegions();
        setRegionMap();
    }

    /**
     * getter for the scroll.
     * @return the scroll.
     */
    public Scroll getScroll() {
        return scroll;
    }

    /**
     * sets up the regions in the map.
     */
    private void setupRegions() {
        regions.add(new BeyondTheWall());
        regions.add(new Braavos());
        regions.add(new Crownlands());
        regions.add(new Dorne());
        regions.add(new DothrakiSea());
        regions.add(new Dragonstone());
        regions.add(new IronIslands());
        regions.add(new North());
        regions.add(new Reach());
        regions.add(new Stormlands());
        regions.add(new Vale());
        regions.add(new Valyria());
        regions.add(new Westerlands());
        regions.add(new Yunkai());
    }

    /**
     * sets the region map with their respective house.
     */
    private void setRegionMap() {
        regionMap.put(House.FreeFolk, regions.get(0));
        regionMap.put(House.FacelessMan, regions.get(1));
        regionMap.put(House.KingsGuard, regions.get(2));
        regionMap.put(House.Martell, regions.get(3));
        regionMap.put(House.Dothraki, regions.get(4));
        regionMap.put(House.Greyjoy, regions.get(6));
        regionMap.put(House.Stark, regions.get(7));
        regionMap.put(House.Tyrell, regions.get(8));
        regionMap.put(House.Baratheon, regions.get(9));
        regionMap.put(House.Arryn, regions.get(10));
        regionMap.put(House.Lannister, regions.get(12));
        regionMap.put(House.Unsullied, regions.get(13));
    }

    /**
     * getter for the region map.
     * @return the region map.
     */
    public java.util.Map<House, Region> getRegionMap() {
        return regionMap;
    }

    /**
     * getter for the regions.
     * @return the list of regions.
     */
    public List<Region> getRegions() {
        return regions;
    }
}
