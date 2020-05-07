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


	 	public JFrame posNegFrame;
	 	public JFrame movieReviewFrame;

		public JLabel pos;
		public JLabel neg;
		public JLabel pathResult;  // displays if paths exist
		public JLabel welcomeMessage;

		public JTextField posPath;
		public JTextField negPath;

		public JButton testPaths, loadReviews, deleteReviews, searchById, searchBySubstring;

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


		}

 	public void actionPerformed (ActionEvent event) {
 		ReviewHandler hr = new ReviewHandler();

 		File posFilePath = new File(posPath.getText());
 		File negFilePath = new File(negPath.getText());

 		if (event.getSource() == testPaths) {
 			try {
 				hr.loadPosNegWords(posPath.getText(), negPath.getText());
 				pathResult.setText("Paths accepted");
 				movieReviewFrame.setVisible(true);
 				posNegFrame.dispose();
 			} catch (IOException ex) {
 				pathResult.setText("Invalid path name(s), re-enter");
 			}
 		}

 	}


		public static void main (String [] args) {
			new MovieReviewApp();
		}
}