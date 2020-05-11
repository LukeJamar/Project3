package project3;
// package Project3_Testing;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// added Java swing Imports
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


/**
* Class for Movie Review Datablase Implemeted through Swing GUI
* @author Frederick Jamar
*/
public class MovieReviewApp implements ActionListener { 

		public ReviewHandler rh = new ReviewHandler();

	 	public JFrame posNegFrame, movieReviewFrame, loadReviewFrame, deleteReviewFrame,
	 					searchByIdFrame, searchBySubstringFrame;

		public JLabel pos, neg, pathResult, welcomeMessage, menuMessage, reviewPathLable, reviewScoreLable,
						loadResult, accuracyLable, deletePrompt, deleteResult, idSearchPrompt, idSearchMessage,
						stringSearchPrompt, stringSearchMessage, totalLable, loadDBMessage;

		public JTextArea idOutputArea, strOutputArea;

		public JScrollPane idPane, substringPane, mainPane;

		public JTable mainTable;

		public JTextField posPath, negPath, reviewPath, actualScore, reviewToDelete,
							idToSearch, substringSearch;

		public JButton testPaths, loadReviews, deleteReviews, searchById, searchBySubstring,
					loadReviewsButton, deleteButton, idSearchButton, substringSearchButton,
					loadDB, saveDB, exitPosNeg, exitMain, exitload, exitDelete, exitSearchId,
					exitSearchSub;


		/**
		* Constructor for MovieReviewApp, where all frames are initialized
		*/
		MovieReviewApp() {

/***************** posNegFrame  code ******************/

 		posNegFrame = null;
		pos = null;
		neg = null;
		pathResult = null;  // displays if paths exist
		posPath = null;
		negPath = null;
		testPaths = null;
		GridBagConstraints layoutConst = null;

		posNegFrame = new JFrame("Load Positive and Negative Words");
		pos = new JLabel("Positive Path:");
		neg = new JLabel("Negetive Path:");

		// Label to message the user of incorrect and correct paths
		pathResult = new JLabel("");

		// Add text feilds to Frame
		posPath = new JTextField(20);
		posPath.setEditable(true);
		posPath.setText("");

		negPath = new JTextField(20);
		negPath.setEditable(true);
		negPath.setText("");

		// initialize compare button
		testPaths = new JButton("Check Paths");
		exitPosNeg = new JButton("Exit");
		testPaths.addActionListener(this);
		exitPosNeg.addActionListener(this);

		// Uses Gridbag Layout for GUI
		posNegFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

     	layoutConst.gridx = 0;
     	layoutConst.gridy = 0;

        layoutConst.insets = new Insets(10, 10, 10, 10);

        posNegFrame.add(pos, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 0;
      	layoutConst.gridy = 1;
      	layoutConst.insets = new Insets(10, 10, 10, 10);
      	posNegFrame.add(neg, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 1;
      	layoutConst.gridy = 0;
      	layoutConst.insets = new Insets(10,10,10,10);
      	posNegFrame.add(posPath, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 1;
      	layoutConst.gridy = 1;
      	layoutConst.insets = new Insets(10,10,10,10);
      	posNegFrame.add(negPath, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 1;
      	layoutConst.gridy = 2;
      	layoutConst.insets = new Insets(10,10,10,10);
      	posNegFrame.add(pathResult, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 0;
      	layoutConst.gridy = 2;
      	layoutConst.insets = new Insets(10,10,10,10);
      	posNegFrame.add(testPaths, layoutConst);

      	layoutConst = new GridBagConstraints();
      	layoutConst.gridx = 2;
      	layoutConst.gridy = 2;
      	layoutConst.insets = new Insets(10,10,10,10);
      	posNegFrame.add(exitPosNeg,layoutConst);


		posNegFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		posNegFrame.pack();
		posNegFrame.setVisible(true);

/***************** movieReviewFrame code ******************/

		movieReviewFrame = new JFrame("Movie Reviews");
		welcomeMessage = new JLabel("Movie Review Database panel:");
		menuMessage = new JLabel("Choose one of the following: (Exit will save database)");
		accuracyLable = new JLabel("Accuracy: ");
		totalLable = new JLabel("total: ");
		loadDBMessage = new JLabel("");
		loadReviews = new JButton("Load reviews");
		deleteReviews = new JButton("Delete Review (given ID)");
		searchById = new JButton("Search reviews by ID");
		searchBySubstring = new JButton("Search reviews by substring");
		loadDB = new JButton("LoadDB");
		saveDB = new JButton("SaveDB");
		exitMain = new JButton("Exit");

		movieReviewFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

		Object [][] tableVals = new Object[2001][4];
		String []columnHeadings = { "ID", "Real Score", "Predicted Score", "Filepath"};
		mainTable = new JTable(tableVals, columnHeadings);
		mainTable.setEnabled(false);

		mainPane = new JScrollPane(mainTable);

		loadReviews.addActionListener(this);
		deleteReviews.addActionListener(this);
		searchById.addActionListener(this);
		searchBySubstring.addActionListener(this);
		loadDB.addActionListener(this);
		saveDB.addActionListener(this);
		exitMain.addActionListener(this);


		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		movieReviewFrame.add(welcomeMessage, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(1, 10,10,10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.gridwidth = 4;
		movieReviewFrame.add(mainTable.getTableHeader(), layoutConst);

		
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10, 10,10,10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridwidth = 4;
		movieReviewFrame.add(mainPane, layoutConst);
		

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		layoutConst.gridwidth = 2;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(menuMessage, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 2;
		layoutConst.gridy = 3;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(accuracyLable, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 3;
		layoutConst.gridy = 3;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(totalLable, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(loadReviews, layoutConst);		

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(deleteReviews, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 2;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(searchById, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 3;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(searchBySubstring, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 4;
		layoutConst.gridy = 3;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(loadDB, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 4;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);	
		movieReviewFrame.add(saveDB, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 5;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);	
		movieReviewFrame.add(exitMain, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 4;
		layoutConst.gridy = 0;
		layoutConst.gridwidth = 2;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(loadDBMessage, layoutConst);	


		movieReviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movieReviewFrame.pack();
		movieReviewFrame.setVisible(false);


/***************** loadReviewFrame code ******************/

		loadReviewFrame = new JFrame("Load Reviews");
		reviewPathLable = new JLabel("Reviews Path:");
		reviewScoreLable = new JLabel("Real score:");
		loadResult = new JLabel("");
		reviewPath = new JTextField(20);
		actualScore = new JTextField(20);
		loadReviewsButton = new JButton("Load");
		exitload = new JButton("Exit");
		loadReviewsButton.addActionListener(this);
		exitload.addActionListener(this);

		loadReviewFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(reviewPathLable, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(reviewScoreLable, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(reviewPath, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(actualScore, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(loadReviewsButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(loadResult, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 2;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
		loadReviewFrame.add(exitload, layoutConst);

		loadReviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loadReviewFrame.pack();
		loadReviewFrame.setVisible(false);

/***************** deleteReviewFrame code ******************/

		deleteReviewFrame = new JFrame("Delete Review");
		deletePrompt = new JLabel("Review ID:");
		deleteResult = new JLabel("");
		reviewToDelete = new JTextField(15);
		deleteButton = new JButton("Delete");
		exitDelete = new JButton("Exit");
		deleteButton.addActionListener(this);
		exitDelete.addActionListener(this);

		deleteReviewFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		deleteReviewFrame.add(deletePrompt, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		deleteReviewFrame.add(reviewToDelete, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		deleteReviewFrame.add(deleteButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		deleteReviewFrame.add(deleteResult, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 2;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		deleteReviewFrame.add(exitDelete, layoutConst);

		deleteReviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		deleteReviewFrame.pack();
		deleteReviewFrame.setVisible(false);


/***************** searchByIdFrame code ******************/

		searchByIdFrame = new JFrame("Search By ID");
		idSearchPrompt = new JLabel("Enter ID:");
		idSearchMessage = new JLabel("");
		idToSearch = new JTextField(15);
		idSearchButton = new JButton("Search");
		idSearchButton.addActionListener(this);

		// JText area and scroll for text area
		idOutputArea = new JTextArea(10,15);
		idPane = new JScrollPane(idOutputArea);
		idOutputArea.setEditable(false);

		searchByIdFrame.setLayout(new GridBagLayout());

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		searchByIdFrame.add(idSearchPrompt, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		searchByIdFrame.add(idToSearch, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		searchByIdFrame.add(idSearchButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		searchByIdFrame.add(idSearchMessage, layoutConst);

		// Setup for Output Area
		layoutConst = new GridBagConstraints();
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
		layoutConst.gridwidth = 2;
		searchByIdFrame.add(idPane, layoutConst);

		searchByIdFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchByIdFrame.pack();
		searchByIdFrame.setVisible(false);

/***************** searchBySubstringFrame code ******************/

		searchBySubstringFrame = new JFrame("Search By Substring");
		stringSearchPrompt = new JLabel("Enter  Substring:");
		stringSearchMessage = new JLabel("");
		substringSearch = new JTextField(20);
		substringSearchButton = new JButton("Search");
		substringSearchButton.addActionListener(this);

		strOutputArea = new JTextArea(10,15);
		substringPane = new JScrollPane(strOutputArea);
		strOutputArea.setVisible(false);

		searchBySubstringFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		searchBySubstringFrame.add(stringSearchPrompt, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		searchBySubstringFrame.add(substringSearch, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		searchBySubstringFrame.add(substringSearchButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		searchBySubstringFrame.add(stringSearchMessage, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridwidth = 4;
		searchBySubstringFrame.add(substringPane, layoutConst);

		searchBySubstringFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchBySubstringFrame.pack();
		searchBySubstringFrame.setVisible(false);


		}

	/**
	* listener function for all buttons & feilds in the interface
	* @param event onject initated from actionListener
	*/
 	public void actionPerformed (ActionEvent event) {

 		if (event.getSource() == testPaths) {
 			try {
 				File posFilePath = new File(posPath.getText());
 				File negFilePath = new File(negPath.getText());

 				rh.loadPosNegWords(posPath.getText(), negPath.getText());
 				pathResult.setText("Paths accepted");
 				movieReviewFrame.setVisible(true);
 				posNegFrame.dispose();
 			} catch (IOException ex) {
 				pathResult.setText("Invalid path name(s), re-enter");
 			}
 		}

 		if (event.getSource() == loadReviews) {
 			loadReviewFrame.setVisible(true);
 		}

 		if (event.getSource() == deleteReviews) {
 			deleteReviewFrame.setVisible(true);
 		}

 		if (event.getSource() == searchById) {
 			searchByIdFrame.setVisible(true);
 		}

 		if (event.getSource() == searchBySubstring) {
 			searchBySubstringFrame.setVisible(true);
 		}

 		if (event.getSource() == loadReviewsButton) {


 			if (actualScore.getText().equals("1") || actualScore.getText().equals("2") ||
 				actualScore.getText().equals("0")) {
 				if (Files.exists(Path.of(reviewPath.getText()))) {
 					rh.loadReviews(reviewPath.getText(), Integer.parseInt(actualScore.getText()));
 					loadResult.setText("Succuessful Load!"); // change to close Frame
 					updateTable();
 					accuracyTest();
 				}
 				else
 					loadResult.setText("Error, path doesn't exists.");
 			}
 			else
 				loadResult.setText("Error, needs to be 0, 1, or 2");
 		}

 		if (event.getSource() == deleteButton) {
 			String idStr = reviewToDelete.getText();


 			if (!idStr.matches("-?(0|[1-9]\\d*)")) {
 				deleteResult.setText("Invalid Input");
 			} 
 			else if (!rh.getDatabase().containsKey(Integer.parseInt(idStr))) {
 				deleteResult.setText("Id " + idStr + " does not exist");
 			}
 			else {
 			 	int id = Integer.parseInt(idStr);
 				rh.deleteReview(id);
 				updateTable();
 				totalLable.setText("Total: " + rh.getDatabase().size());
 				deleteResult.setText("Review " + id + " deleted!");
 			}

 		}

 		if (event.getSource() == idSearchButton) {
 			String idStr = idToSearch.getText();

 			if (!idStr.matches("-?(0|[1-9]\\d*)")) {
 				idSearchMessage.setText("Invalid input");
 			}
 			else if (!rh.getDatabase().containsKey(Integer.parseInt(idStr))) {
 				idSearchMessage.setText("Id " + idStr + " does not exist");
 				idOutputArea.setText("");
 			}
 			else {
 				int id = Integer.parseInt(idStr);
 				MovieReview mr = rh.searchById(id);

 				idOutputArea.setText("");

 				idOutputArea.append("Review Id: " + mr.getId() + "\n" +
 									"Real Score: " + mr.getRealScore() + "\n" +
 									"Predicted Score: " + mr.getPredictedScore() + "\n" +
 									"Text: " + mr.getText().substring(0,35) + "...");
 			}
 		}

 		if (event.getSource() == substringSearchButton) {

 			String substring = substringSearch.getText();
 			List<MovieReview> reivewList = rh.searchBySubstring(substring);

 			if (reivewList != null) {


 				strOutputArea.setText("");
				
 				for ( MovieReview mr: reivewList ) {

 					strOutputArea.append(
 						"Review Id: " + mr.getId() + "\n" +
 						"Real Score: " + mr.getRealScore() + "\n" +
 						"Predicted Score: " + mr.getPredictedScore() + "\n" +
 						"Text: " + mr.getText().substring(0,40) + "..." + "\n\n");
 				}

 			}
 			else {
 				stringSearchMessage.setText("Substring can't be found.");
 				strOutputArea.setText("");
 			}

 		}

 		if (event.getSource() == loadDB) {
        	File dataFile = new File("database.txt");

        	if (!dataFile.exists()){
        		loadDBMessage.setText("No database file found.");
        	}
        	else if (rh.getDatabase().size() != 0) {
        		loadDBMessage.setText("Database contains reviews");
        	}
        	else {
        		try{
        			rh.loadDB();
        			updateTable();
        			accuracyTest();
        	} catch(IOException ex) {
        		loadDBMessage.setText("database file could not load.");
        	}
        	}
 		}

 		if (event.getSource() == saveDB) {
 			try {
 				rh.saveDB();
 				loadDBMessage.setText("Database saved!");
 			}catch (IOException ex) {
 				loadDBMessage.setText("database could not be saved. (IO)");
 		}

 		}

 		if (event.getSource() == exitMain) {
 			try{
 				rh.saveDB();
 				System.exit(0);
 			} catch (IOException ex) {
 				loadDBMessage.setText("database could not be saved. (IO)");
 			}
 		}

 		if (event.getSource() == exitPosNeg) {
 			System.exit(0);
 		}

 		if (event.getSource() == exitload) {
 			loadReviewFrame.dispose();
 		}

 		if (event.getSource() == exitDelete) {
 			deleteReviewFrame.dispose();
 		}


 	}

 	/**
 	* updates the main table in the Movie Review window
 	*/
 	public void updateTable() {
 		final int idCol = 0;
 		final int realScoreCol = 1;
 		final int predictedCol = 2;
 		final int pathCol = 3;
 		int i = 0; // index for loop

		// Replaces alll spaces and sets updated list 		
 		for (MovieReview mr : rh.getDatabase().values()) {
 			mainTable.setValueAt(mr.getId(), i, idCol);
 			mainTable.setValueAt(mr.getRealScore(), i, realScoreCol);
 			mainTable.setValueAt(mr.getPredictedScore(), i, predictedCol);
 			mainTable.setValueAt(mr.getFilePath(), i, pathCol);
 			i++;
		} 
 	}

 	/**
 	* accuracy test to replace that in console output
 	*/
 	public void accuracyTest() {
 		 int counter = 0;
 		 double percent;

 		for (MovieReview mr : rh.getDatabase().values()) {
 			if (mr.getRealScore() != mr.getPredictedScore())
 				if (mr.getPredictedScore() != ReviewScore.UNKNOWN)
 					counter++;
 		}

 		percent = (counter * 100.0) / rh.getDatabase().size();
 		percent = 100 - percent;
 		accuracyLable.setText("Accuracy: " + percent);
 		totalLable.setText("Total: " + rh.getDatabase().size());

 	}
}

