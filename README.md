# RTS

Game of Thrones-themed battle simulator. The idea is to provide the user with an editor where they can create a map consisting of locations and routes between those locations. The user can then add armies and simulate some battles!

The map is resembled by a simple undirected graph. In this graph, the nodes represent important locations, while edges represent routes between those locations. In the world, there are several armies. These armies move from location to location. On any location or route, the armies can encounter events. If enemy armies encounter each other, they will battle it out, until only one team survives. The user to configure these locations, routes, armies and events. Once the user is happy with their configuration, they will be able to simulate the scenario they created.

Uitilised Swing, MVC, Java IO. Implemented the GUI using Java Swing. The program properly follows the MVC pattern.
___


### The Maps

Each map is represented by an undirected, simple, unweighted graph.

- Every node has a unique (integer) id and a name.
- Every node has a list of edges.
- Every edge has a unique (integer) id and a name.
- An edge always connects two nodes.
- The user can add and remove nodes and edges using the buttons in the top menu
- Whenever a node is selected, a side panel pops up with the details of said node. Here, the user can place armies on the node, remove armies from the node, direct the armies towards a certain edge etc.

---

### Armies

The point of the game is to use your army resources to gain enemy territory.

- An army has a number of units.
- A unit has damage, health and a name.
- Armies belong to a certain faction (a Game of Thrones house or tribe)
- Both nodes and edges can contain armies, and fights can happen either on nodes or on edges
- The side menu provides detailed information about each unit's stats, abilities, and history.

### Battles

Battles comprise of a succession of simulation steps.

- Whenever armies of different teams reside on the same node or edge, a battle happens.
- The outcome of which army wins depends on the number of units in each army and how strong the units in the army are (their health and damage)
- Armies that have been defeated are removed from the graph entirely.
- Only the armies of a single team can remain alive after a battle. I.e. only a single team can be the victor of a battle.
- Every time a battle is resolved, the user is notified of the outcome using a simple popup message


### 2.4 Events

Of course, just having some battling armies is not the most interesting thing. As such, we want armies to encounter random events.

- Add something to model a general event. An event affects the army that encounters said event. Some examples of events could be:

	- Reinforcements event: adds units to the army.
	- Natural disaster event: removes a number of units from the army.
	- Hidden weaponry event: improves the weapons of an army, giving their units increased damage.

	However, you are of course encouraged to come up with your own events as well. **You should Implement at least three different kinds of events.**

- Nodes and edges should both be able to have events.
- Allows the user to add/remove events to/from nodes & edges. Once again, you can use a `JOptionPane` to allow the user to select one of the existing events.
- Whenever an army arrives at a node/edge for the first time during a simulation step, it should encounter one of the events present at the said location at random:

	- There should be a chance not to encounter any event at all (e.g. 50%).
	- If the army encounters an event, it should be a random available event from the node/edge the army is on.

- Make sure that the user is somehow aware of what event has happened (and to which army). You can do this by drawing some fancy stuff or using a simple popup message.

### Undo and Saving to JSON

This allows the user to undo their actions. Additionally, the user is able to export the entire state of the simulation to a file in JSON format.

- There is a button in the menu bar that allows the user to save the simulation.
- Pressing this button allows the user to pick a location/file to save to.
- At the start of the game, the user can choose to load and continue playing a presaved game. 


Note that if you have less than 2 points for functionality, the design part is capped at 1.5 points.

For design, we will be paying attention to things such as good use of inheritance, encapsulation, polymorphism etc.
