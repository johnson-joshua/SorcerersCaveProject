/**File Name: Artifact.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: Artifact is a child of the Creature class. Artifact is one of the leaves of the
 * multi-tree data structure that this game implements. Artifact consists of a few attributes,
 * a few constructors, and an overridden toString() method.
 */
public class Artifact
{
	private double index, creature;
	private String type, stringCreature, name;
	
	public double getIndex() {
		return index;
	}
	public void setIndex(double index) {
		this.index = index;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Artifact()
	{
		this.index = 000;
		this.type = "Default Type";
		this.stringCreature = "000";
		this.name = "Defulat Name";
	}
	public Artifact(double index, String type, double creature, String name)
	{
		this.index = index;
		this.type = type;
		this.stringCreature = Double.toString(creature);
		this.name = name;
	}

	public String toString()
	{
		String stringArtifact = "";
		if(this.name != null)
			stringArtifact += "ARTIFACT - NAME: " +name +" -  ";
		stringArtifact += "ARTIFACT - TYPE: " +type +" - " +" CREATURE: " +stringCreature
				+" - "  +" INDEX: " +index;
		return stringArtifact;
	}
}
