import java.util.ArrayList;

public class Inventory {
	// Instance variables
	ArrayList<Item> items; // List of items

	// Constructor
	public Inventory() {
		items = new ArrayList<Item>();
	}

	/**
	 * Method to add an item to the inventory
	 * 
	 * @param item the item to add to the inventory
	 */
	public void add(Item item) {
		items.add(item);
	}

	/**
	 * Method to try and get an item from the inventory
	 * 
	 * @param name the name of the requested item
	 * 
	 * @return Item the requested item (null if not found)
	 */
	public Item getItem(String name) {

		// Loop over the items in your Inventory
		for (Item item : items) {
			// Check if the given name matches the item
			if (item.getName().toLowerCase().equals(name))
				return item;
		}
		return null;
	}

	/**
	 * Method to check if the inventory contains a requested item
	 * 
	 * @param name the name of the requested item
	 * 
	 * @return boolean true, if the inventory contains the requested item
	 */
	public boolean contains(String name) {
		// Loop over the items in your Inventory
		for (Item item : items) {
			// Check if the given name matches the item
			if (item.getName().toLowerCase().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * Method to drop an item from the inventory.
	 * 
	 * @param name name of the item you want to drop
	 * 
	 * @return Item the item dropped out of the inventory, returned to add to
	 *         current room
	 */
	public Item drop(String name) {
		// Loop over the items in your Inventory
		for (Item item : items) {
			// Check if the given name matches the item
			if (item.getName().toLowerCase().equals(name)) {
				// If so, remove the item from Inventory
				items.remove(item);
				return item;
			}
		}
		return null;
	}

	/**
	 * Method to return a description of the inventory's content.
	 * 
	 * @return string description
	 */
	public String inventoryToString() {
		String temp = "";

		// https://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string-array
		Item[] itemArray = new Item[items.size()];
		itemArray = items.toArray(itemArray);

		for (int i = 0; i < itemArray.length - 1; i++) {

			temp += itemArray[i].getName() + " | ";
		}

		if (itemArray.length != 0) {
			temp += itemArray[itemArray.length - 1].getName();
		}

		return temp;
	}
}
