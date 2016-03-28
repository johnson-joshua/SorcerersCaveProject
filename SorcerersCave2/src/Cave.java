/**File Name: Cave.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: The cave is the root of the multi-tree. This class will hold all parties
 * that are associated with the cave.  Cave will accept an input file from the user
 * in order to create the data structure.  As it parses through the input file, it will
 * add parties, creatures, treasures, and artifacts to their respective arraylists.
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Cave 
{
	public ArrayList<Party> parties = new ArrayList<Party>();
	public ArrayList<Treasure> treasure = new ArrayList<Treasure>();
	public myGUI myJFrame = new myGUI();

	public Cave()	
	{
		String currentLine;
		char firstLetter = '/';  //Default to comment to skip line
		try{
			//Ask for input file, only display text files
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(myJFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION )
			{
				//User hit ok, accept input file and begin parse
				File inputFile = chooser.getSelectedFile();
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				currentLine = br.readLine();
				do
				{
					if(currentLine.length() == 0)
						firstLetter = '/';
					else
						firstLetter = currentLine.charAt(0);
					switch(firstLetter)
					{
					//switch on the first letter of each line to decide which kind of object to create
					case 'p':
						parseStatsForParty(currentLine); //Parses and adds party to the arraylist
						break;
					case 'c':	
						parseStatsForCreature(currentLine); //Parses and adds creature to the arraylist
						break;
					case 't':
						parseStatsForTreasure(currentLine); //Parses and adds treasure to the arraylist
						break;
					case 'a':
						parseStatsForArtifact(currentLine); //Parses and adds artifact to the arraylist
						break;
					//Default case will assume a commented line and skip it
					default: break;
					}
					currentLine = br.readLine();
					if(currentLine != null)
						currentLine = currentLine.trim();
				}while(currentLine != null);
				br.close();
			}
			else
			{
				//user clicked cancel
				System.exit(0);
			}
			myJFrame.print(parties);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(myJFrame, "An error has occured.");
		}
	}//End Cave Constructor

	private void parseStatsForCreature(String inputLine)
	{
		double index = 0, carrying_capacity = 0, party = 0;
		int empathy = 0, fear = 0;
		String type = null, name = null;
		String[] words = inputLine.split(":");

		//Will always be in the same format
		//c:<index>:<type>:<name>:<party>:<empathy>:<fear>:<carrying capacity>
		for(int i = 0; i < words.length; i++)
		{
			if(i == 1)
				index = Double.parseDouble(words[i].trim());
			if(i == 2)
				type = words[i].trim();
			if(i == 3)
				name = words[i].trim();
			if(i == 4)
				party = Double.parseDouble(words[i].trim());
			if(i == 5)
				empathy = Integer.parseInt(words[i].trim());
			if(i == 6)
				fear = Integer.parseInt(words[i].trim());
			if(i == 7)
				carrying_capacity = Double.parseDouble(words[i].trim());
		}
		Creature creature = new Creature(index, type, name, party, empathy, fear, carrying_capacity);
		addCreatureToParty(creature);
	}

	private void addCreatureToParty(Creature c)
	{
		//Adds the creature to the party with a matching party index
		for(int i = 0; i < parties.size(); i++)
		{
			if(c.getStringParty().equals(parties.get(i).getStringIndex()))
			{
				parties.get(i).getCreatures().add(c);
				break;
			}
		}
	}

	//Parses fields from input file to create a new instance of the class
	private void parseStatsForTreasure(String inputLine)
	{
		double index = 0, weight = 0, value = 0, creature = 0;
		String type = null;
		String[] words = inputLine.split(":");
		//Will always be in the same format
		//t:<index>:<types>:<creature>:<weight>:<value>
		for(int i = 0; i < words.length; i++)
		{
			if(i == 1)
				index = Double.parseDouble(words[i].trim());
			if(i == 2)
				type = words[i].trim();
			if(i == 3)
				creature = Double.parseDouble(words[i].trim());
			if(i == 4)
				weight = Double.parseDouble(words[i].trim());
			if(i == 5)
				value = Double.parseDouble(words[i].trim());
		}
		Treasure treasure = new Treasure(index, type, creature, weight, value);
		addTreasureToCreature(treasure);
	}

	private void addTreasureToCreature(Treasure t)
	{//Adds the treasure to the creature with a matching creature index
		for(int i = 0; i < parties.size(); i++)
		{//Get each array of creatures
			Object[] tempCreaturesArray = parties.get(i).getCreatures().toArray();
			//Search an array for creature index
			for(int j = 0; j < tempCreaturesArray.length; j++)
			{
				if(t.getStringCreature().equals(((Creature)tempCreaturesArray[j]).getStringIndex()))
				{
					//add treasure to the correct creature's arraylist of treasure
					parties.get(i).getCreatures().get(j).getTreasure().add(t);
					break;
				}

			}
		}
		if(t.getStringCreature().equals(Double.toString(0)))
		{
			//Treasure belongs to no creature, add to Cave's arraylist of treasure
			this.treasure.add(t);
		}
	}

	private void parseStatsForArtifact(String inputLine)
	{
		double index = 0, creature = 0;
		String type = null, name = null;
		String[] words = inputLine.split(":");
		//Will always be in the same format (name is optional)
		//a:<index>:<type>:<creature>[:<name>]
		for(int i = 0; i < words.length; i++)
		{
			if(i == 1)
				index = Double.parseDouble(words[i]);
			if(i == 2)
				type = words[i].trim();
			if(i == 3)
				creature = Double.parseDouble(words[i].trim());
			if(words.length == 5)
				if(i == 4)
					name = words[i].trim();
		}
		Artifact artifact = new Artifact(index, type, creature, name);
		addArtifactToCreature(artifact);
	}

	private void addArtifactToCreature(Artifact a)
	{//Adds the artifact to the creature with a matching creature index
		for(int i = 0; i < parties.size() - 1; i++)
		{//Get each array of creatures
			Object[] tempCreaturesArray = parties.get(i).getCreatures().toArray();
			//Search an array for creature index
			for(int j = 0; j < tempCreaturesArray.length; j++)
			{
				if(a.getStringCreature().equals(((Creature)tempCreaturesArray[j]).getStringIndex()))
				{
					//add artifact to the correct creature's arraylist of artifacts
					parties.get(i).getCreatures().get(j).getArtifacts().add(a);
					break;
				}
			}
		}
	}

	private void parseStatsForParty(String inputLine)
	{
		String name = null, stringIndex;
		double index = 0;

		String[] words = inputLine.split(":");
		//Will always be in the same format
		//p:<index>:<name>
		for(int i = 0; i < words.length; i++)
		{
			if(i == 1)
			{
				stringIndex = words[i].trim();
				index = Double.parseDouble(stringIndex);
			}
			else if(i == 2)
				name = words[i].trim();
		}
		Party party = new Party(index, name);
		parties.add(party);
	}

	public String toString()
	{
		String stringCave = "";
		stringCave += "Cave - Root of the Multi-Tree";
		return stringCave;
	}
}
