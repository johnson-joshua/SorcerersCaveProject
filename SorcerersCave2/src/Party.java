/**File Name: Party.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: The party class is a child of the Cave class.  Party is a parent of the Creature class
 * Party will hold all of the creatures that are associated with the party.
 * Besides the constructors and the overridden toString(), the only method here 
 * is add() which adds a creature to the party's arraylist of creatures.
 */
import java.util.ArrayList;

public class Party 
{
	private double index;
	private String name;
	private String stringIndex;
	private ArrayList<Creature> creatures = new ArrayList<Creature>();
	
	public ArrayList<Creature> getCreatures() {
		return creatures;
	}
	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}
	public double getIndex() {
		return index;
	}
	public void setIndex(double index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStringIndex() {
		return stringIndex;
	}
	public void setStringIndex(String stringIndex) {
		this.stringIndex = stringIndex;
	}

	public Party()
	{
		@SuppressWarnings("unused")
		Party party = new Party(0,"Default Name");
	}
	//Makes new parties with the stats from input and adds to the cave's arraylist of parties
	public Party(double index, String name)
	{
		this.index = index;
		this.name = name;
		this.stringIndex = Double.toString(index);
	}

	public String toString()
	{
		String stringParty = "";
		stringParty += "PARTY NAME: " +name +" - " +" PARTY INDEX: " +index;
		return stringParty;
	}
	public void add(Creature c) 
	{
		creatures.add(c);
	}
}
