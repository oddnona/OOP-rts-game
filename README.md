# RTS Battle Simulator

A Java real-time strategy battle simulator inspired by the Game of Thrones universe. I can create and edit a map made of locations and routes, place armies and events on the map, and simulate battles between different factions.

The goal of the game is to use army resources strategically, move through different regions, and conquer enemy territory.

## Tech Stack

* Java 17
* Maven
* Java Swing
* Checkstyle

## Key Features

* Choose a faction before starting the game
* Explore a world map with multiple regions
* Edit regional maps using graph-based nodes and edges
* Add, move, select, and remove nodes
* Connect nodes with routes represented by edges
* Place armies and events on nodes and edges
* Move armies between neighbouring nodes and routes
* Simulate battles between armies from different houses
* Trigger events that affect armies during simulation steps
* Use undo to restore the previous graph state
* Save the simulation state using JSON serialization
* Display game updates through GUI panels, popups, logs, animations, and sound effects
* Keep the UI and game logic separated through MVC and observer-based updates

## Gameplay Overview

At the beginning of the game, I choose the house I want to play as. Depending on this decision, the game gives me a starting region, armies, and events.

After choosing a house, I enter the main map. The map contains several regions inspired by the Game of Thrones world, such as the North, Crownlands, Dorne, Dragonstone, Iron Islands, Reach, Stormlands, Vale, Westerlands, and more.

When I visit a region, I can edit that region's graph. Each graph is made of nodes and edges. Nodes represent important locations, and edges represent routes between those locations.

I can place nodes, move selected nodes, connect nodes with edges, and remove existing nodes or edges. I can also place armies and events on nodes and edges.

## Map and Graph System

Each region is represented as an undirected graph.

* A node represents a location.
* An edge represents a route between two locations.
* Each node has a unique ID, a name, a position, connected edges, armies, and events.
* Each edge connects two nodes and can also contain armies and events.
* Nodes and edges can be selected through the GUI.
* Selecting a node or edge opens a side panel with relevant controls and information.

The graph system also supports saving the previous graph state. This makes it possible to undo actions by restoring the previous version of the graph.

## Armies and Movement

Armies belong to a specific house or faction. Each army contains a number of soldiers, and each soldier type has its own health, damage, and name.

Armies can be placed on nodes or edges. I can control my armies by selecting a node or edge and choosing where the army should move next.

If an army is on a node, I can direct it to a neighbouring edge. If an army is on an edge, I can direct it to one of the two connected nodes. If I do not give an army a movement command, it stays in the same place during the next simulation step.

Enemy armies can also react to my movement. If they are close enough, they follow my armies and may start a battle on a neighbouring node or edge.

## Battle System

Battles happen when armies from different houses meet on the same node or edge.

The outcome of a battle depends on:

* the number of soldiers in each army
* the health of the soldiers
* the damage dealt by the soldiers

During battle resolution, armies fight until one side has no soldiers left. If both armies lose all soldiers, both are removed. If one army wins, the defeated army is removed from the graph, and the player is notified of the result.

Battles can happen on both nodes and edges. The game also uses popup messages and sound effects to notify the player when a battle starts or ends.

## Events

Events are actions that trigger when armies pass through the node or edge where the event is located. Each event has a name and can affect armies in a different way.

The project includes event types such as:

* `ArmorEvent`
* `MilkOfThePoppyEvent`
* `TrapedBattlefieldEvent`

Events can affect armies by changing their stats or number of soldiers. Some events can happen multiple times, while others are one-time events and are removed after they are triggered.

## Simulation Step

The simulation step button runs one cycle of the game.

During a simulation step, the game:

1. saves the previous graph state
2. resolves existing battles
3. moves enemy armies
4. moves player armies based on selected commands
5. updates the graph view and army animations
6. triggers events on nodes and edges
7. checks whether new battles will happen in the next cycle

This creates a turn-based simulation loop while still giving the project the feel of a real-time strategy game.

## Saving and Undo

The project includes support for saving the simulation state with JSON serialization. The graph, nodes, edges, armies, soldier data, events, and positions can be converted into JSON-compatible objects.

The game also supports undo. Before major changes, the graph saves a cloned version of the previous state. When I undo an action, the game restores the previous graph and updates the view through observers.

## Project Structure

```text
src/main/java/nl/rug/oop/rts
├── controller/       # Controllers for game state, panels, mouse input, nodes, edges, regions, and settings
├── json/             # Custom JSON classes and serialization helpers
├── model/            # Game state, player, graph, map, simulation, armies, soldiers, houses, and scroll log
│   ├── events/       # Event classes that affect armies
│   ├── nodes/        # Node classes used in region graphs
│   ├── regions/      # Region classes for the world map
│   └── soldiertypes/ # Soldier and archer types for different houses
├── view/             # Swing UI, panels, graph views, buttons, textures, animations, and sounds
└── Main.java         # Application entry point
```

## Architecture

The project follows the Model-View-Controller pattern.

The Model contains the game state and core logic. It stores the player, map, regions, graphs, nodes, edges, armies, soldiers, events, and simulation rules. The Model decides how battles are resolved, how armies move, how events are triggered, and how the graph state is saved.

The View is responsible for displaying the game. It contains the Swing frame, panels, graph rendering, buttons, edit panels, textures, animation views, logs, and sound-related feedback.

The Controller handles user input. It connects button clicks, mouse actions, node selection, edge selection, region selection, menu actions, settings, and scrolling behaviour to changes in the Model.

The Model and View are kept connected through observers. When the Model changes, it notifies observers so the View can redraw the graph or update the current panel. This keeps the game logic from directly controlling specific UI components.

## Design Highlights

This project demonstrates:

* Object-oriented design in Java
* MVC-based desktop application architecture
* Observer-based communication between model and UI
* Graph-based modelling of maps, locations, and routes
* Turn-based simulation logic for army movement and battle resolution
* Event-driven GUI programming with Java Swing
* Custom JSON serialization for game state export
* Undo functionality through cloned graph states
* Use of Maven, Checkstyle, Lombok, and FlatLaf in a Java project

## Getting Started

### Prerequisites

* Java 17
* Maven

### Running the Project

Clone the repository:

```bash
git clone https://github.com/oddnona/OOP-rts-game.git
cd OOP-rts-game
```

Build the project:

```bash
mvn clean package
```

Run the main class:

```bash
mvn exec:java -Dexec.mainClass=nl.rug.oop.rts.Main
```

You can also run the project from an IDE by opening it as a Maven project and running `nl.rug.oop.rts.Main`.

## Current Limitations

* Loading a previously saved JSON game is a planned improvement.
* Some View code could be refactored to reduce duplication.
* Node ID generation can produce very large IDs after repeated graph cloning.
* Deleting a node also removes connected edges, including any armies or events placed on those edges.
* Some UI behaviour, such as scroll animation, could be separated more cleanly from the Controller.

## Future Improvements
I would like to add more complex behaviour. For example, making the other factions attack you aswell. I would also improve the battle logic, for example: to actually have an ending to battles or add more game restrictions like not adding a node all over the map.
:::
