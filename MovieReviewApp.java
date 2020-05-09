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

public class MovieReviewApp implements ActionListener {   // does not implement yet

		public ReviewHandler rh = new ReviewHandler();

	 	public JFrame posNegFrame;
	 	public JFrame movieReviewFrame;
	 	public JFrame loadReviewFrame;
	 	public JFrame deleteReviewFrame;
	 	public JFrame searchByIdFrame;
	 	public JFrame searchBySubstringFrame;

		public JLabel pos;
		public JLabel neg;
		public JLabel pathResult;  // displays if paths exist
		public JLabel welcomeMessage;
		public JLabel reviewPathLable;
		public JLabel reviewScoreLable;
		public JLabel loadResult;
		public JLabel deletePrompt;
		public JLabel deleteResult;
		public JLabel idSearchPrompt;
		public JLabel idSearchMessage;
		public JLabel stringSearchPrompt;
		public JLabel stringSearchMessage;

		public JTextArea idOutputArea;
		public JTextArea strOutputArea;

		public JScrollPane idPane;
		public JScrollPane substringPane;

		public JTextField posPath;
		public JTextField negPath;
		public JTextField reviewPath;
		public JTextField actualScore;
		public JTextField reviewToDelete;
		public JTextField idToSearch;
		public JTextField substringSearch;

		public JButton testPaths, loadReviews, deleteReviews, searchById, searchBySubstring,
					loadReviewsButton, deleteButton, idSearchButton, substringSearchButton;

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
		testPaths.addActionListener(this);

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


		posNegFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		posNegFrame.pack();
		posNegFrame.setVisible(true);

/***************** movieReviewFrame code ******************/
// Need to implement JTable

		movieReviewFrame = new JFrame("Movie Reviews");
		welcomeMessage = new JLabel("Choose one of the following options:");
		loadReviews = new JButton("Load new movie review collection (given a folder or a file path)");
		deleteReviews = new JButton("Delete movie review from database (given its ID)");
		searchById = new JButton("Search movie reviews in database by ID");
		searchBySubstring = new JButton("Search movie reviews by matching a substring");

		movieReviewFrame.setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();

		loadReviews.addActionListener(this);
		deleteReviews.addActionListener(this);
		searchById.addActionListener(this);
		searchBySubstring.addActionListener(this);

		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(welcomeMessage, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(loadReviews, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(deleteReviews, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(searchById, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10, 10,10,10);
		movieReviewFrame.add(searchBySubstring, layoutConst);

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
		loadReviewsButton.addActionListener(this);

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

		loadReviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loadReviewFrame.pack();
		loadReviewFrame.setVisible(false);

/***************** deleteReviewFrame code ******************/

		deleteReviewFrame = new JFrame("Delete Review");
		deletePrompt = new JLabel("Review ID:");
		deleteResult = new JLabel("");
		reviewToDelete = new JTextField(15);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);

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

 	public void actionPerformed (ActionEvent event) {
 		// ReviewHandler rh = new ReviewHandler();

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

 		// Furthur implementation of loadReviews
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

 			} else {
 				stringSearchMessage.setText("Substring can't be found.");
 			}

 		}


 	}


		public static void main (String [] args) {

			// ./material/data/positive-words.txt
			// ./material/data/negavite-words.txt
			// ./material/data/Movie-review/pos 

			new MovieReviewApp();
		}
}

