/**File Name: Treasure.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: Treasure is a child of the Creature class. Treasure is one of the leaves of the
 * multi-tree data structure that this game implements. Treasure consists of a few attributes,
 * a few constructors, and an overridden toString() method.
 */

public class Treasure 
{
	private double index, weight, value, creature = 0;
	private String type, stringCreature;
	
	public double getIndex() {
		return index;
	}

	public void setIndex(double index) {
		this.index = index;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getCreature() {
		return creature;
	}

	public void setCreature(double creature) {
		this.creature = creature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStringCreature() {
		return stringCreature;
	}

	public void setStringCreature(String stringCreature) {
		this.stringCreature = stringCreature;
	}

	public Treasure()
	{
		this.index = 000;
		this.type = "Default Type";
		this.creature = 000;
		this.stringCreature = "000";
		this.weight = 0;
		this.value = 0;
		@SuppressWarnings("unused")
		Treasure treasure = new Treasure(index, type, creature, weight, value);
	}

	public Treasure(double index, String type, double creature, double weight, double value)
	{
		this.index = index;
		this.type = type;
		this.creature = creature;
		this.stringCreature = Double.toString(creature);
		this.weight = weight;
		this.value = value;
	}

	public String toString()
	{
		String stringTreasure = "";
		stringTreasure = "TREASURE - TYPE: " +type +" - " +" CREATURE: " +creature +" - "  
				+" INDEX: " +index +" - " +" WEIGHT: " +weight +" - " +" VALUE: " +value;
		return stringTreasure;
	}
}//End of Class
