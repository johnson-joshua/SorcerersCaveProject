/**File Name: Creature.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: The Creature class is a child of the Party class. Creature is a parent of the
 * Treasure and Artifact classes.  Creature will hold all of the treasures and artifacts
 * that are associated with the creature.  Besides the constructors and the overridden
 * toString(), the only methods here are two add() methods.  One accepts a treasure as a parameter
 * and adds it to the creature's arraylist of treasure.  The other add() method accepts an artifact
 * as a parameter and adds it to creature's arraylist of artifacts.
 */
import java.util.ArrayList;

public class Creature
{
	private String type, name, stringIndex, stringParty;
	private int empathy, fear;
	private double index, party, carrying_capacity;
	private ArrayList<Treasure> treasure = new ArrayList<Treasure>();
	private ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
	
	public ArrayList<Treasure> getTreasure() {
		return treasure;
	}
	public void setTreasure(ArrayList<Treasure> treasure) {
		this.treasure = treasure;
	}
	public ArrayList<Artifact> getArtifacts() {
		return artifacts;
	}
	public void setArtifacts(ArrayList<Artifact> artifacts) {
		this.artifacts = artifacts;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getStringParty() {
		return stringParty;
	}
	public void setStringParty(String stringParty) {
		this.stringParty = stringParty;
	}
	public int getEmpathy() {
		return empathy;
	}
	public void setEmpathy(int empathy) {
		this.empathy = empathy;
	}
	public int getFear() {
		return fear;
	}
	public void setFear(int fear) {
		this.fear = fear;
	}
	public double getIndex() {
		return index;
	}
	public void setIndex(double index) {
		this.index = index;
	}
	public double getParty() {
		return party;
	}
	public void setParty(double party) {
		this.party = party;
	}
	public double getCarrying_capacity() {
		return carrying_capacity;
	}
	public void setCarrying_capacity(double carrying_capacity) {
		this.carrying_capacity = carrying_capacity;
	}

	public Creature()
	{
		this.index = 000;
		this.stringIndex = "000";
		this.party = 000;
		this.type = "Default Type";
		this.name = "Default Name";
		this.empathy = 5;
		this.fear = 5;
		this.carrying_capacity = 10;
		this.stringParty = "000";
	}
	public Creature(double index, String type, String name, double party, int empathy, int fear, double carry_capacity)
	{
		this.index = index;
		this.stringIndex = Double.toString(index);
		this.party = party;
		this.type = type;
		this.name = name;
		this.empathy = empathy;
		this.fear = fear;
		this.carrying_capacity = carry_capacity;
		this.stringParty = Double.toString(party);
	}

	public void add(Treasure t)
	{
		treasure.add(t);
	}

	public void add(Artifact a)
	{
		artifacts.add(a);
	}

	public String toString()
	{
		String stringCreature = "";
		stringCreature += "NAME: " +name +" - "+" PARTY INDEX: " +stringParty +" - " 
				+" INDEX: " +index +" - " +" TYPE: " +type +" - " +" EMP: " +empathy
				+" - " +" FEAR: " +fear +" - " +" CARRY_CAP: " +carrying_capacity;
		return stringCreature;
	}
}
