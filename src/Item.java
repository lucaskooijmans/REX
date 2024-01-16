public class Item {
	// Instance variables
	private String name;
	private String usageText;

	/**
	 * Constructor
	 * 
	 * @param _name      the item's name
	 * @param _usageText the item's usage text
	 */
	public Item(String _name, String _usageText) {
		name = _name;
		usageText = _usageText;
	}

	public String getName() {
		return name;
	}

	public String getUsageText() {
		return usageText;
	}
}
