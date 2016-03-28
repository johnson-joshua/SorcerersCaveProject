/**File Name: myGUI.java
 * Date: March 26, 2016
 * Author: Joshua M. Johnson
 * Purpose: The myGUI class extends the JFrame class; that is to say, the myGUI class IS a JFrame.
 * This is the main window that I use to display the output of the multi-tree data structure.
 * I create a JPanel for each party and add the party's creatures, treasures, and artifacts
 * to it. Then I add the JPanel to this class in order to display it in the main window.
 * This class is also used to search for the input that the user types in the textBox. Once it
 * finds the given input in the tree structure, it displays it in a modal message box.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class myGUI extends JFrame
{
	public myGUI()
	{
		//Use the dimension to set the JFrame to the size of the user's monitor
		Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void print(ArrayList<Party> parties)
	{	
		//This loop makes a new panel for each party and adds party's tostring method to panel
		for(int i = 0; i < parties.size(); i++)	//loops through each of the parties in the arraylist
		{
			//Set the layout as a GridLayout with a cell for each partyPanel and one extra for searchBox
			this.getContentPane().setLayout(new GridLayout(parties.size() +1, 0, 3, 3)); 
			JPanel partyPanel = new JPanel();
			partyPanel.setLayout(new GridLayout(0,1));
			partyPanel.add(new JTextArea(parties.get(i).toString()));
			ArrayList<Creature> tempCreaturesArrayList = parties.get(i).getCreatures();
			//Loop through creatures in each party and add tostring method to party's panel
			for(int j = 0; j < tempCreaturesArrayList.size(); j++)
			{
				JTextArea textBox = new JTextArea();
				textBox.setText(tempCreaturesArrayList.get(j).toString());
				textBox.setLineWrap(true);
				partyPanel.add(textBox);
				ArrayList<Treasure> creatureTreasureArrayList = tempCreaturesArrayList.get(j).getTreasure();
				//Loop through treasures of each creature and add tostring method to party's panel
				for(Treasure t : creatureTreasureArrayList)
				{
					JTextArea treasureBox = new JTextArea();
					treasureBox.setText(t.toString());
					treasureBox.setLineWrap(true);
					partyPanel.add(treasureBox);
				}
				ArrayList<Artifact> creatureArtifactArrayList = tempCreaturesArrayList.get(j).getArtifacts();
				//Loop through artifacts of each creature and add tostring method to party's panel
				for(Artifact a : creatureArtifactArrayList)
				{
					JTextArea artifactBox = new JTextArea();
					artifactBox.setText(a.toString());
					artifactBox.setLineWrap(true);
					partyPanel.add(artifactBox);
				}
			}
			//Makes each party panel scrollable and adds panel to the game window
			JScrollPane scrollPane = new JScrollPane(partyPanel);
			scrollPane.setBorder(BorderFactory.createTitledBorder("PARTY NAME: " +parties.get(i).getName()));
			this.add(scrollPane);
		}//End of outer for loop
		//This is where I set up a panel to hold textBox and button for searching purposes
		JPanel searchPanel = new JPanel();
		JTextArea searchBox = new JTextArea();
		JButton searchButton = new JButton("Search");
		searchPanel.setLayout(new GridLayout(1,0));
		searchPanel.setSize(50,50);
		searchPanel.add(searchBox);
		searchBox.setSize(50, 50);
		searchPanel.add(searchButton);
		this.add(searchPanel);
		this.setVisible(true);
		//This is the action listener for the button. When it is clicked, code in here exectues.
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//Search for criteria in all parties and clear the textbox
				search(searchBox.getText(), parties);
				searchBox.setText("");
			}
		});//End of button's actionLister
	}//End of print method

	//MESSAGE: I was unsure if this search method should actually be done in this class.
	//Since this class is used to create the GUI, I wasn't sure if 
	//I should call a search method in another class or if I should just search from here.
	public void search(String input, ArrayList<Party> parties)
	{
		String tempMessage = "";
		//We must be able to search by index, name, or type
		//Adds all creatures and items with matching types or indexes or names to the dialog box
		if(!input.isEmpty())
		{
			JOptionPane optionPane = new JOptionPane();
			for(int i = 0; i < parties.size(); i++) //loops through all parties
			{
				if(input.equalsIgnoreCase(parties.get(i).getName()) || input.equalsIgnoreCase(Double.toString(parties.get(i).getIndex())))
				{
					tempMessage += parties.get(i).toString() +"\n";
				}
				ArrayList<Creature> creatures = parties.get(i).getCreatures();
				for(int j = 0; j < creatures.size(); j++)	//loops through all creatures
				{	//Searches through creatures to see if input matches type, name or index
					if(input.equalsIgnoreCase(creatures.get(j).getType()) || input.equalsIgnoreCase(creatures.get(j).getName()) || input.equalsIgnoreCase(Double.toString(creatures.get(j).getIndex())))
					{
						tempMessage += creatures.get(j).toString() +"\n";
					}

					ArrayList<Treasure> treasure = creatures.get(j).getTreasure();
					for(int k = 0; k < treasure.size(); k++)
					{	//Searches through treasure to see if input matches type or index
						if( input.equalsIgnoreCase(treasure.get(k).getType()) || input.equalsIgnoreCase(Double.toString(treasure.get(k).getIndex())))
						{
							tempMessage += treasure.get(k).toString() +"\n";
						}
					}
					ArrayList<Artifact> artifacts = creatures.get(j).getArtifacts();
					for(int l = 0; l < artifacts.size(); l++)
					{ //Searches through artifacts to see if input matches type or index
						if(input.equalsIgnoreCase(artifacts.get(l).getType()) || input.equalsIgnoreCase(Double.toString(artifacts.get(l).getIndex())) || input.equalsIgnoreCase(artifacts.get(l).getName()))
						{
							tempMessage += artifacts.get(l).toString() +"\n";
						}
					}
				}
			}//End outer for loop
			optionPane.setMessageType(NORMAL);
			optionPane.setBorder(BorderFactory.createTitledBorder("SEARCH RESULTS:"));
			optionPane.setMessage(tempMessage);
			if(optionPane.getMessage() != "")
				JOptionPane.showMessageDialog(this.getContentPane(), optionPane.getMessage());
			else
				JOptionPane.showMessageDialog(this.getContentPane(), "No results found for search criteria.");
		}//End if statement
		if(input.isEmpty())
		{
			JOptionPane.showMessageDialog(this.getContentPane(), "No results found for search criteria.");
		}
	}//End search method
}//End class
