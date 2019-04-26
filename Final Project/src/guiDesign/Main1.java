package guiDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import Dictionary.Dictionary;
import Dictionary.Parse;
import Dictionary.Save;

import javax.swing.JLabel;

public class Main1 extends JFrame {
	//GUI components
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panel = new JPanel();
	private final JPanel panelHelp = new JPanel();
	private final JButton btnClear = new JButton("Clear");
	private final JButton btnSave = new JButton("Save");
	private final JButton btnParse = new JButton("Parse");
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnIgnore = new JButton("Ignore");
	private final JButton btnStats = new JButton("Statistics");
	private String dictTextFile;
	private String textFile;
	private ArrayList<String> dictionaryFileArrayList;
	private ArrayList<String> textFileArrayList;
	private Parse dictionaryParser;
	private Parse inputParser;
	private Dictionary dictionary;
	private ArrayList<String> wordsNotFound;
	private int numWordsIgnored = 0;
	private int numLinesInInput;
	private Save saver = new Save();
	private final JTextField textFieldDictionary = new JTextField();
	private final JTextField textFieldInput = new JTextField();
	private final JLabel lblDictionary = new JLabel("Dictionary");
	private final JLabel lblText = new JLabel("Text");
	private final JLabel labelWarningDictionary = new JLabel("");
	private final JLabel labelWarningInput = new JLabel("");
	private final JLabel statsOutput = new JLabel("");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList list = new JList();
	private final JLabel lblTheDictionaryAnd = new JLabel("The Dictionary and Text Files must be in the .txt format");
	private final JLabel lblTheFilesMust = new JLabel("The files must be local files");
	private final JLabel lblAllWordsFrom = new JLabel("All words from the Text file that are not found in the Dictionary will be");
	private final JLabel lblDisplayedInThe = new JLabel("displayed in the scrolling list on the left. Select multiple words at once and either");
	private final JLabel lblAddThemTo = new JLabel("add them to the dictionary by pressing \"Add\" or ignore that words by pressing");
	private final JLabel lblignoreWhenYou = new JLabel("\"Ignore\". When you are done adding new words to the dictionary, you may");
	private final JLabel lblSaveTheUpdated = new JLabel("save the updated dictionary by pressing \"Save\". To compare new files and");
	private final JLabel lblRepeatTheProcess = new JLabel("repeat the process, simply enter the new file names and press \"Parse\" again.");
	private final JLabel lblToClearAll = new JLabel("To clear all the fields press \"Clear\".");

	public Main1() {
		
		initGUI();
	}
	
	private void initGUI() {
		//set the GUI constraints for the text fields
		textFieldInput.setBounds(380, 112, 150, 20);
		textFieldInput.setColumns(10);
		textFieldDictionary.setBounds(380, 37, 150, 20);
		textFieldDictionary.setColumns(10);
		
		//stop the application when the window is closed and set size for the whole content pane
		setDefaultCloseOperation(Main1.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(700, 400);
		getContentPane().setLayout(null);
		
		//add a tabbed pane to the window
		tabbedPane.setBounds(10, 11, 600, 300);
		getContentPane().add(tabbedPane);
		tabbedPane.addTab("Dictionary", null, panel, null);
		panel.setLayout(null);
		
		
		
		//set action for the clear button
		//clear file fields and scrolling list
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setListData(new String[0]);
				scrollPane.revalidate();
				scrollPane.repaint();
				textFieldDictionary.setText("");
				textFieldInput.setText("");
				labelWarningDictionary.setVisible(false);
				labelWarningInput.setVisible(false);
				statsOutput.setVisible(false);
			}
		});
		btnClear.setBounds(470, 177, 89, 23);
		panel.add(btnClear);
		
		
		
		//set action for the save button
		//saves the added words to the dictionary text file
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saver.saveDictionary(dictionary.getDictionaryHash(), dictTextFile);
			}
		});
		btnSave.setBounds(153, 90, 89, 23);
		panel.add(btnSave);
		
		
		
		//set action for the parse button
		//gets the strings for the names of the text files and parses them into ArrayLists
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dictTextFile = textFieldDictionary.getText();
				textFile = textFieldInput.getText();
				list.setListData(new String[0]);
				scrollPane.revalidate();
				scrollPane.repaint();
				
				//parse dictionary text file
				try {
					dictionaryFileArrayList = dictionaryParser.parse(dictTextFile);
					labelWarningDictionary.setVisible(false);
	
				} catch (FileNotFoundException e1) {
					dictionaryFileArrayList = null;
					labelWarningDictionary.setText("Please enter a .txt file");
					labelWarningDictionary.setVisible(true);
				}
				
				//parse the input text file
				try {
					textFileArrayList = inputParser.parse(textFile);
					numLinesInInput = inputParser.getNumLines(textFile);
					labelWarningInput.setVisible(false);
					
				} catch (IOException e2) {
					textFileArrayList = null;
					labelWarningInput.setText("Please enter a .txt file");
					labelWarningInput.setVisible(true);
				}
				
				//create a dictionary hash map, compare the input text to the dictionary hash map, and create an arraylist of words not found
				try{
						dictionary = new Dictionary(dictionaryFileArrayList);
						wordsNotFound = dictionary.compare(textFileArrayList);
						list.setListData(wordsNotFound.toArray());
						scrollPane.revalidate();
						scrollPane.repaint();
				}
				catch(NullPointerException nullPointer){
				}
			}
		});
		btnParse.setBounds(375, 177, 89, 23);
		panel.add(btnParse);
		
		
		
		//set action for the add button
		//takes selected words from the scrolling list and adds them to the dictionary hash map
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndices().length > 0) {
			 		int[] selectedIndices = list.getSelectedIndices();
			 		
			        for (int iterator = selectedIndices.length-1; iterator >=0; iterator--) {
			        	dictionary.addWord(wordsNotFound.get(selectedIndices[iterator]).toString());
			        	wordsNotFound.remove(selectedIndices[iterator]);
			        } 
			    }
				
				//updating scrolling list after adding words to dictionary
				list.setListData(wordsNotFound.toArray());
				scrollPane.revalidate();
				scrollPane.repaint();
			}
		});
		btnAdd.setBounds(153, 11, 89, 23);
		panel.add(btnAdd);
		
		
		
		//set action for the ignore button
		//removing words from the scrolling list and from words not found after pressing ignore
		btnIgnore.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			 	if(list.getSelectedIndices().length > 0) {
			 		int[] selectedIndices = list.getSelectedIndices();
			 		
			        for (int i = selectedIndices.length-1; i >=0; i--) {
			        	wordsNotFound.remove(selectedIndices[i]);
			        	numWordsIgnored ++;
			        } 
			    }
			 	
			 	//updating the scrolling list after ignoring words
			 	list.setListData(wordsNotFound.toArray());
			 	scrollPane.revalidate();
			 	scrollPane.repaint();
		}
		});
		btnIgnore.setBounds(153, 45, 89, 23);
		panel.add(btnIgnore);
		
		//set action for the Stats button
		//displays the Stats on the screen for the user to see
		btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statsOutput.setText("Words in Input.txt: " + inputParser.getNumWords() + " \n" +
									"Input File Lines: " + numLinesInInput + "\n" +
									"Words Replaced: " + " \n" /*number of words replaced*/ +
									"Words Added to Dictionary: " + dictionary.numWordsAdded + "\n" +
									"Words Ignored: " + numWordsIgnored);
				statsOutput.setVisible(true);
			}
		});
		btnStats.setBounds(153, 130, 89, 23);
		panel.add(btnStats);
		
		//add gui components to the panels
		panel.add(textFieldDictionary);
		panel.add(textFieldInput);
		
		lblDictionary.setBounds(380, 11, 85, 14);
		panel.add(lblDictionary);
		
		lblText.setBounds(380, 87, 89, 14);
		panel.add(lblText);
		
		labelWarningDictionary.setBounds(385, 60, 146, 14);
		labelWarningDictionary.setForeground(Color.RED);
		panel.add(labelWarningDictionary);
		
		labelWarningInput.setBounds(385, 135, 146, 14);
		labelWarningInput.setForeground(Color.RED);
		panel.add(labelWarningInput);
		
		statsOutput.setBounds(153, 120, 146, 140);			//error here
		statsOutput.setForeground(Color.BLUE);				//
		panel.add(statsOutput);								//
		
		scrollPane.setBounds(10, 11, 133, 189);
		panel.add(scrollPane);
		scrollPane.setViewportView(list);
		
		tabbedPane.addTab("Help", null, panelHelp, null);
		panelHelp.setLayout(null);
		lblTheDictionaryAnd.setBounds(10, 11, 600, 189);
		
		panelHelp.add(lblTheDictionaryAnd);
		lblTheDictionaryAnd.setBounds(10, 11, 600, 14);
		
		panelHelp.add(lblTheDictionaryAnd);
		lblTheFilesMust.setBounds(10, 25, 600, 14);
		
		panelHelp.add(lblTheFilesMust);
		lblAllWordsFrom.setBounds(10, 36, 600, 14);
		
		panelHelp.add(lblAllWordsFrom);
		lblDisplayedInThe.setBounds(10, 50, 600, 14);
		
		panelHelp.add(lblDisplayedInThe);
		lblAddThemTo.setBounds(10, 61, 600, 14);
		
		panelHelp.add(lblAddThemTo);
		lblignoreWhenYou.setBounds(10, 75, 600, 14);
		
		panelHelp.add(lblignoreWhenYou);
		lblSaveTheUpdated.setBounds(10, 92, 600, 14);
		
		panelHelp.add(lblSaveTheUpdated);
		lblRepeatTheProcess.setBounds(10, 110, 600, 14);
		
		panelHelp.add(lblRepeatTheProcess);
		lblToClearAll.setBounds(10, 128, 600, 14);
		
		panelHelp.add(lblToClearAll);
	}

	//main function
	public static void main(String[] args) {
		new Main1();
	}
}