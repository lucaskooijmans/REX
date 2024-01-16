public class Player {
	// Instance variables
	private Room currentRoom; // The current room the player is in
	private Room startRoom; // The room the player started in
	private Inventory inventory;

	/**
	 * Constructor
	 * 
	 * @param mapEntry the starting room
	 */
	public Player(Room mapEntry) {
		currentRoom = mapEntry;
		startRoom = mapEntry; // Save room as start room (for win condition)
		inventory = new Inventory();
	}

	/**
	 * Check if the direction can take us to another room. If so: do it!
	 * 
	 * @param direction the direction to go to
	 */
	public void go(String direction) {
		// Get the adjacent room(s) from the current room
		Room[] adjacentRooms = currentRoom.getAdjacentRooms();

		switch (direction.toLowerCase()) {

		// west = index 0
		case "west":
			if (adjacentRooms[0] != null) {
				currentRoom = adjacentRooms[0];
			} else
				System.out.println("You can't go that way!");
			break;

		// south = index 1
		case "south":
			if (adjacentRooms[1] != null) {
				currentRoom = adjacentRooms[1];
			} else {
				System.out.println("You can't go that way!");
			}
			break;

		// east = index 2
		case "east":
			if (adjacentRooms[2] != null) {
				currentRoom = adjacentRooms[2];
			} else {
				System.out.println("You can't go that way!");
			}
			break;

		// north = index 3
		case "north":
			if (adjacentRooms[3] != null) {
				currentRoom = adjacentRooms[3];
			} else {
				System.out.println("You can't go that way!");
			}
			break;

		default:
			System.out.println("Direction is invalid!");
		}
	}

	/**
	 * Method to try to use an item from either the player's inventory or the
	 * current room
	 * 
	 * @param name the name of the item you're trying to use
	 */
	public void use(String name) {
		// Try and get the item from the inventory
		Item item = inventory.getItem(name);

		// If item != null -> item came back from inventory successfully
		if (item != null) {

			// "Use" it
			System.out.println(item.getUsageText());
		}

		// else check if the item is in the room
		else if (currentRoom.getItem(name) != null) {

			// Get the actual item
			Item roomItem = currentRoom.getItem(name);

			// "Use" it
			System.out.println(roomItem.getUsageText());
		}

		else {

			System.out.println(name + " doesn't exist.");

		}
	}

	// Method to drop an item from the player's inventory
	public void drop(String name) {
		// Check if inventory contains the item that u want to drop
		if (inventory.contains(name)) {

			// Drop the item from your inventory -> it gets removed from your inventory
			Item droppedItem = inventory.drop(name);

			// Add the item you just dropped to the current room that you are in
			currentRoom.addItem(droppedItem);

			System.out.println("You dropped " + droppedItem.getName());
		}

		else {
			System.out.println(name + " is not in your inventory.");
		}

	}

	// Method to pick up an item from the current room and add it to your inventory
	public void get(String name) {
		// Check if rooms contains item
		if (currentRoom.contains(name)) {

			// Check if the item exists in the room that you are in and take it
			Item roomItem = currentRoom.getItem(name);

			// Check if item was taken
			if (roomItem != null) {

				// Remove item from the current room that you are in
				currentRoom.removeItem(roomItem);

				// Add it to inventory
				inventory.add(roomItem);

				System.out.println("You picked up " + roomItem.getName());
			}
		} else {
			System.out.println(name + " doesn't exist.");
		}
	}

	// Method to look at the content of the current room
	public void look() {
		// Get string of room items
		String items = currentRoom.itemsToString();

		// Items: Magic_Orb, Sword, Rock, Coin, Lantern
		if (items != "") {
			System.out.println("Items: " + items);
		}

		// If not print alternative message
		else {
			System.out.println("This room contains no items.");
		}

		// Put an extra line inbetween
		System.out.println();

		// Get the adjacent room(s) from the current room that you are in
		String exits = currentRoom.adjacentRoomsToString();

		// Does not need a check since you always have atleast one door in a room
		// Exits: West | South | East | North
		System.out.println("Exits: " + exits);
	}

	// Method to print out a help list
	public void help() {
		// Print out a list of commands and possible methods of using them
		System.out.println("These are the commands u can use in the game: ");
		System.out.println("'go' + 'direction' ('west','south','east','north'): lets you move from room to room.");
		System.out.println(
				"'get' + 'item name' lets you take the item, puts it in your backpack and removes it from the room.");
		System.out.println(
				"'drop' + 'item name' lets you drop an item from your backpack and puts it in the current room.");
		System.out.println("'use' + 'item name' lets you use the item that is in the current room or in your backpack");
		System.out.println("'pack' shows a list of every item in your inventory.");
		System.out.println("'help' shows a list of commands.");
		System.out.println("'look' shows a list of items and exits in the current room.");
		System.out.println("'quit' quits the game.");

	}

	// Method to print out the content of your inventory
	public void pack() {
		// Get string of inventory items
		String items = inventory.inventoryToString();

		// If item != null -> item came back from inventory successfully
		if (items != "") {
			System.out.println("Inventory: " + items);
		} else {
			System.out.println("Your inventory is empty.");
		}
	}

	/**
	 * Method to check if the win condition is met
	 *
	 * @param itemName the name of the item used for the win condition check
	 * 
	 * @return true, if the winning item is used in the start room.
	 */
	public boolean checkWinCondition(String itemName) {
		// Check if inventory contains the item that u want check
		Item item = inventory.getItem(itemName);

		if (item != null) {
			// If the item u checked equals "Delicious_Banana" and you are in the startRoom,
			// return true
			return (item.getName().equals("Delicious_Banana") && currentRoom == startRoom);
		}

		return false;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

}
