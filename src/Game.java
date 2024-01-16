public class Game {
	// Instance variables
	private Player m_Player;
	private Room firstRoom;
	private Room lastRoom;
	boolean keepPlaying = true; // To control game loop

	// Constructor
	public Game() {
		// The map is linked together so we only need the starting room, we're able to
		// navigate from there
		firstRoom = generateDungeon();

		// Hand first room over to player constructor
		m_Player = new Player(firstRoom);
	}

	// Main method for the game containing the game loop.
	public void run() {
		// Introduction message
		System.out.println("Welcome to REX: Room EXplorer! \n" + "© 2024 Lucas Kooijmans \n");

		// Variable to save last room in
		lastRoom = null;

		while (keepPlaying) {

			// Check if room changed
			if (m_Player.getCurrentRoom() != lastRoom) {

				// Display current room description
				display();

				// Save current room as new last room
				lastRoom = m_Player.getCurrentRoom();
			}

			// Get user input
			String input = ConsoleIO.readInput();

			// Handle user input
			handleCommand(input);
		}

		// If keepPlaying = false, game has ended
		System.out.println("Game ended");
	}

	/**
	 * Method to handle logic based on user input. Follows OO-patterns by telling
	 * objects to behave in certain ways instead of forcing them
	 * 
	 * @param userInput the string entered by the player
	 */
	private void handleCommand(String userInput) {
		// Check if the input is valid
		if (checkInput(userInput)) {

			// Split the user input string
			String[] inputArray = userInput.split(" ");

			switch (inputArray[0].toLowerCase()) {

			// At this point the two word commands will be executed
			case "go":
				m_Player.go(inputArray[1].toLowerCase());
				break;

			case "use":
				// Check if win condition is met
				if (m_Player.checkWinCondition(inputArray[1])) {

					// If yes, end the game
					System.out.println("Congratulations, you beat the game!");
					keepPlaying = false;
				}

				// Else call the normal use method
				else {
					m_Player.use(inputArray[1].toLowerCase());
				}
				break;

			case "drop":
				m_Player.drop(inputArray[1].toLowerCase());
				break;

			case "get":
				m_Player.get(inputArray[1].toLowerCase());
				break;

			// At this point the one word commands will be executed
			case "look":
				m_Player.look();
				break;

			case "help":
				m_Player.help();
				break;

			case "pack":
				m_Player.pack();
				break;

			case "quit":
				keepPlaying = false;
				break;
			}
		}

		// Wrong input
		else
			System.out.println("Invalid input");
	}

	/**
	 * Method to check if the input is valid for the commmands in the game.
	 * 
	 * The method checks for the validity of user input based on the specified
	 * criteria for one-word and two-word commands. It ensures that the input is not
	 * empty, has the correct number of words, and contains a valid command.
	 * 
	 * @param input the user input to check
	 * @return boolean true, if input is a valid command with the required
	 *         parameters.
	 */
	private boolean checkInput(String input) {
		// If input is empty input is invalid
		if (input.length() == 0) {
			return false;
		}

		String[] inputArray = input.split(" ");
		String command = inputArray[0].toLowerCase();
		// If it's one of the one word commands
		if (inputArray.length == 1) {
			// If it's none of the valid one word commands input is invalid
			if (!(command.equals("look") || command.equals("help") || command.equals("pack") || command.equals("quit")))
				return false;
		}

		// Else if length > 2 -> miss-input
		else if (inputArray.length > 2) {
			return false;
		}

		// At this point the inputArray has a length of 2, we need to check for a valid
		// command
		else {
			if (!(command.equals("go") || command.equals("use") || command.equals("drop") || command.equals("get")))
				return false;
		}

		// If this point in the code is reached, the input is valid
		return true;
	}

	// Method to print the current room description and options
	private void display() {
		// Get the description of the current room you are in
		String description = m_Player.getCurrentRoom().getDescription();

		System.out.println("===========================================================");
		System.out.println("|                                                         |");
		System.out.println("|                                                         |");
		System.out.println("|            You are currently in: " + description + "                 |");
		System.out.println("|                                                         |");
		System.out.println("|                                                         |");
		System.out.println("===========================================================");
		System.out.println("commands: go | use | drop | get | look | help | pack | quit");
	}

	/**
	 * Method to generate the rooms and items of the dungeon
	 * 
	 * @return Room startRoom
	 */
	private Room generateDungeon() {
		// Create rooms
		Room startRoom = new Room("Room 1");
		Room secondRoom = new Room("Room 2");
		Room thirdRoom = new Room("Room 3");
		Room fourthRoom = new Room("Room 4");
		Room fifthRoom = new Room("Room 5");
		Room sixthRoom = new Room("Room 6");
		Room seventhRoom = new Room("Room 7");
		Room eighthRoom = new Room("Room 8");
		Room ninthRoom = new Room("Room 9");
		Room tenthRoom = new Room("Room 10");
		Room eleventhRoom = new Room("Room 11");
		Room twelfthRoom = new Room("Room 12");
		Room thirteenthRoom = new Room("Room 13");

		// Connect the rooms with adjacent rooms
		connectRooms(startRoom, "west", secondRoom);
		connectRooms(startRoom, "south", thirdRoom);
		connectRooms(startRoom, "north", fifthRoom);

		connectRooms(thirdRoom, "east", fourthRoom);

		connectRooms(fifthRoom, "east", sixthRoom);

		connectRooms(sixthRoom, "north", seventhRoom);
		connectRooms(sixthRoom, "east", eighthRoom);

		connectRooms(eighthRoom, "south", ninthRoom);
		connectRooms(eighthRoom, "east", tenthRoom);

		connectRooms(eleventhRoom, "west", ninthRoom);
		connectRooms(eleventhRoom, "north", tenthRoom);
		connectRooms(eleventhRoom, "south", twelfthRoom);

		connectRooms(twelfthRoom, "east", thirteenthRoom);

		// Create items
		Item item1 = new Item("Lantern", "The lantern lights up the room");
		Item item2 = new Item("Gold", "*looks at gold* Wow");
		Item item3 = new Item("Rock", "*throws rock in the air and catches it*");
		Item item4 = new Item("Sword", "*strange sword noises* How does this work?");
		Item winItem = new Item("Delicious_Banana", "Hmmm delicious... \n" + 
								"> Use this in the start room to beat the game!");

		// Add items to different rooms
		secondRoom.addItem(item1);
		fourthRoom.addItem(item2);
		seventhRoom.addItem(item3);
		ninthRoom.addItem(item4);
		thirteenthRoom.addItem(winItem);

		// Return first room
		return startRoom;
	}

	/**
	 * Method to connect the rooms by assigning the appropriate exits to each other
	 * 
	 * @param _first    the first room you want to address
	 * @param direction the direction to connect the following room to, handed in as a string
	 * @param _second   the second room to be connected.
	 */
	private void connectRooms(Room _first, String direction, Room _second) {
		switch (direction.toLowerCase()) {

		case "west":
			_first.setAdjacentRoom("west", _second);
			_second.setAdjacentRoom("east", _first);
			break;

		case "south":
			_first.setAdjacentRoom("south", _second);
			_second.setAdjacentRoom("north", _first);
			break;

		case "east":
			_first.setAdjacentRoom("east", _second);
			_second.setAdjacentRoom("west", _first);
			break;

		case "north":
			_first.setAdjacentRoom("north", _second);
			_second.setAdjacentRoom("south", _first);
			break;

		// Invalid direction input
		default:
			System.out.println("Invalid direction input");
			break;
		}
	}

}
