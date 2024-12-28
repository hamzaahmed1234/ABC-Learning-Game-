/*
Name: Hamza Ahmed
Final Project
Submission Date: 10:00 pm, tues (12/8)
Brief Description: : Use GUI, graphics, colors, sounds, animations or images, event handling, exception handling, Layout 
managers, file I/O and other techniques in Java to develop alphabet-learning program as an educational
and attractive game for pre-school or first grade kids. The game should be interactive, colorful with sounds,
fun to play for kids and able to display player’s scores.
abcGame class has most code for the game. class constructor contains main application of the game
*/


package abcLearningGame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.io.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class abcGame 

{

// Declares container and component objects
boolean isGameOver;

JFrame jf;//main frame

JFrame bFrame;//scoreboard frame

JFrame hToPFrame;//how to play frame

JPanel jpI1,jpI2,jpI3,jpI4,jpI5,jpI6,jp1, jp2, jp3, mainP;//panels for main frame

JPanel p1, p2, p3, p4,p5, mP, tP; //scoreboard panels for player info

JLabel mainL, numberL, resultL;//labels for main panels

JLabel l1,l2,l3,l4,l5,l6,l7,l8,tL;//labels used for both main and scoreboard

JPanel hToPP;//how to play panel

JLabel hToPL;//how to play label


JTextField resultT;//user input textfield

JButton calculateB, nextB, scoreB;//buttons to be displayed in the game frame

//variables for audio clip
Long currentFrame;
Clip clip;

// Random class object declared
Random rand;

// To store current level of the game
int level;

// To store first, second random number
String first, second;

// To store the calculate result and user current score
String result;

// To count number of questions asked
int counter, score;

// To store user name
String name;

//mutator method
public void setIsGameOver(boolean isGameOver) {
	this.isGameOver = isGameOver;
}
public boolean getIsGameOver() {
	return isGameOver;
}



abcGame()

{

	//code for start menu
	JFrame frame;
	JPanel imageP;
	JPanel mainImageP;

	JLabel displayField;
	ImageIcon image;
	
	frame = new JFrame("Start Menu");
	mainImageP = new JPanel();


	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	try {
	image = new ImageIcon("abcg.png");
	displayField = new JLabel(image);
	mainImageP.add(displayField);
	//frame.add(displayField);	
	}catch(Exception e) {
		System.out.println("Image Cannot be Found");
		
	}
	//size of start frame
	frame.setSize(1111,777);	
	frame.setVisible(true);

	JButton button;
	//image icons for how to play and play buttons
	Icon icon = new ImageIcon("rsz_2start.png");
	Icon icon1 = new ImageIcon("rsz_1htp (1).png");
    button = new JButton(icon);
    button.setPreferredSize(new Dimension(300, 150));
	JButton button2;
    button2 = new JButton(icon1);
    button2.setPreferredSize(new Dimension(300, 150));
    mainImageP.add(button);
    mainImageP.add(button2);
    mainImageP.setBackground(Color.red);
    frame.add(mainImageP);
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);	
    
    
	//How to Play button action listen
	button2.addActionListener(new ActionListener()
	{
	// Overrides the actionPerformed() method of ActionListener interface
	public void actionPerformed(ActionEvent ae)

	{
		hToPFrame = new JFrame("How to Play!");
		
		hToPP = new JPanel();
		
		hToPL = new JLabel("<html>This game is designed to test students alphabet knowledge.<br/>"
				+ "		How to play: Their will be two alphabets displayed in order, player must enter the alphabet that follows."
				+ "		<br/>Example: A,B,__. Player enters C as that is the letter that follows<br/>Each time player enters the correct letter they get 1 point."
				+ " You can check your answer with the show result button<html>");
		
		hToPL.setFont(new Font("ALGERIAN", Font.PLAIN, 18));
		hToPL.setForeground(Color.blue);
		hToPP.add(hToPL);
		hToPFrame.add(hToPP);
		hToPFrame.setVisible(true);
		// Set the size of the frame to width 400 and height 200
		hToPFrame.setSize(1500, 200);
		hToPP.setBackground(Color.gray);
		// Sets the frame to center
		hToPFrame.setLocationRelativeTo(null);
	
	}// End of method

	});// End of anonymous class
	
    
	//Start Game button action listen
	button.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent ae)

	{
		//start menu frame not visible now
		frame.setVisible(false);
		
		// Calls the method to play the music
		try {
			playAudio();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		// Initializes level to 1

		level = 1;

		// Initializes score to 0

		score = 0;

		// Initializes question counter to 1

		counter = 1;

		// Creates Random class object

		rand = new Random();

		// Creates a frame

		jf = new JFrame("Alphabet Game");
		
		// Creates a panels

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		jpI1 = new JPanel();
		jpI2 = new JPanel();
		jpI3 = new JPanel();
		jpI4 = new JPanel();
		jpI5 = new JPanel();
		jpI6 = new JPanel();

		mainP = new JPanel();

		// Creates a labels

		mainL = new JLabel("Level - " + level);
		mainL.setFont(new Font("ALGERIAN", Font.PLAIN, 40));
		mainL.setForeground(Color.blue);
		
		numberL = new JLabel();

		resultL = new JLabel();
		
		//image icon objects for game panels
		ImageIcon imageIcon = new ImageIcon("jresized1.gif");
		ImageIcon imageIcon1 = new ImageIcon("aresized2.gif");
		ImageIcon imageIcon2 = new ImageIcon("vresized11.gif");
		ImageIcon imageIcon3 = new ImageIcon("areized16.gif");

		
		l1 = new JLabel(imageIcon);
		l2 = new JLabel(imageIcon1);
		l3 = new JLabel(imageIcon2);
		l4 = new JLabel(imageIcon3);
		
		//Creates a text field
		//create new Font
		resultT = new JTextField(3);
		Font font = new Font("ALGERIAN", Font.BOLD,44);
		resultT.setBackground(Color.gray);		 //set font for JTextField
		resultT.setFont(font);
		resultT.setPreferredSize( new Dimension(33, 66));
		
		// Creates a buttons
		calculateB = new JButton("Check Answer");
		nextB = new JButton("Next Question");
		scoreB = new JButton("Show Scoreboard");

		// Adds main level to panel 1
		jp1.add(mainL);
		//add labels to panels
		jpI1.add(l1);
		jpI2.add(l3);
		jpI5.add(l2);
		jpI6.add(l4);
		jp2.add(numberL);
		jp2.add(resultT);
		jp2.add(resultL);
		// Adds buttons to panel 3
		jp3.add(calculateB);
		jp3.add(nextB);
		jp3.add(scoreB);
		

		// Adds panels main panel
		mainP.add(jpI1);
		mainP.add(jp1);//posistion 2
		mainP.add(jpI2);
		mainP.add(jpI3);
		mainP.add(jp2);//posistion 5
		mainP.add(jpI4);
		mainP.add(jpI5);
		mainP.add(jp3);//posistion 8
		mainP.add(jpI6);

		jpI1.setBackground(Color.blue);
		jpI2.setBackground(Color.blue);
		jpI3.setBackground(Color.blue);
		jpI4.setBackground(Color.blue);
		jpI5.setBackground(Color.blue);
		jpI6.setBackground(Color.blue);

		jp1.setBackground(Color.magenta);
		jp2.setBackground(Color.gray);
		jp3.setBackground(Color.magenta);

		mainP.setBackground(Color.black);
		
		// Sets the layout of the main panel to 3 rows 3 column
		mainP.setLayout(new GridLayout(3, 3));

		// Adds the main panel to frame
		jf.add(mainP);

		// Sets the frame visible property to true
		jf.setVisible(true);

		// Set the size of the frame to width 400 and height 200
		jf.setSize(1000, 600);


		// Sets the frame to center
		jf.setLocationRelativeTo(null);

		// Accepts user name
		name = JOptionPane.showInputDialog("Enter your name: ");

		// Calls the method to generate question
		generateRandomABC();

		//Scoreboard button action listener
		scoreB.addActionListener(new ActionListener()

		{

		// Overrides the actionPerformed() method of ActionListener interface

		public void actionPerformed(ActionEvent ae)

		{
			
		
			String fileLocation = "scores2222.txt";
			
			File scoreFile =new File(fileLocation);
			
			Scanner scoreScan = null;
			try {
				scoreScan = new Scanner(scoreFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Image Cannot be Found");
			}
			
			String nextLine = scoreScan.nextLine();
			
			//comma delimited list is split into seperate components
			String[] scoreboard = nextLine.split(",");
			
			//create scoreboard jframe
			bFrame = new JFrame("Scoreboard");
			
		//	bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//create scoreboard panels
			p1 = new JPanel();

			p2 = new JPanel();

			p3 = new JPanel();

			p4 = new JPanel();

			p5 = new JPanel();
			
			mP = new JPanel();
			
			tP = new JPanel();

			//add panels to main panel
		    mP.add(tP);

		    mP.add(p1);

		    mP.add(p2);

		    mP.add(p3);
		    
		    mP.add(p4);
		    
		    mP.add(p5);
		    
		    //using colors for panels
			tP.setBackground(Color.red);
			p1.setBackground(Color.magenta);
			p2.setBackground(Color.gray);
			p3.setBackground(Color.red);	
			p4.setBackground(Color.magenta);
			p5.setBackground(Color.gray);
			mP.setBackground(Color.black);
			
			// Sets the layout of the main panel to 6 rows 1 column
			mP.setLayout(new GridLayout(6, 1));

			// Adds the main panel to frame
			bFrame.add(mP);

			// Sets the frame visible property to true
			bFrame.setVisible(true);

			// Set the size of the frame to width 600 and height 600
			bFrame.setSize(600, 600);
					    
			
			//outputting all scores
			tL = new JLabel("Top 5 Player " + " | " + " Score");
			
			l1 = new JLabel(scoreboard[0]+ " | " + scoreboard[1]);

			l2 = new JLabel(scoreboard[2]+ " | " + scoreboard[3]);
			
			l3 = new JLabel(scoreboard[4]+ " | " + scoreboard[5]);
			
			l4 = new JLabel(scoreboard[6]+ " | " + scoreboard[7]);

			l5 = new JLabel(scoreboard[8]+ " | " + scoreboard[9]);
			
			//fonts to make look more appealing
			tL.setFont(new Font("ALGERIAN", Font.PLAIN, 20));

			l1.setFont(new Font("ALGERIAN", Font.PLAIN, 20));

			l2.setFont(new Font("ALGERIAN", Font.PLAIN, 20));

			l3.setFont(new Font("ALGERIAN", Font.PLAIN, 20));

			l4.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
			
			l5.setFont(new Font("ALGERIAN", Font.PLAIN, 20));

			//add labels to panels
			tP.add(tL);
			
			p1.add(l1);

			p2.add(l2);

			p3.add(l3);
			    
			p4.add(l4);
			    
			p5.add(l5);

		// Sets the frame to center
		bFrame.setLocationRelativeTo(null);
		  
		}// End of method

		});// End of anonymous class
		

		// Registers action listener to next question button using anonymous class
		nextB.addActionListener(new ActionListener()

		{

		// Overrides the actionPerformed() method of ActionListener interface
		public void actionPerformed(ActionEvent ae)

		{
			//reset panels that were changed when user selects check answer button
			jpI3.removeAll();
			jpI4.removeAll();
			jpI1.setBackground(Color.blue);
			jpI2.setBackground(Color.blue);
			jpI3.setBackground(Color.blue);
			jpI4.setBackground(Color.blue);
			jpI5.setBackground(Color.blue);
			jpI6.setBackground(Color.blue);
			
		// Clears the contents of result label and text field
		resultT.setText("");
		resultL.setText("");
		
		// Increase the question counter by one
		counter++;

		// Calls the method to generate alphabets
		generateRandomABC();

		//10 round game
		if(counter <= 10) {

		level = counter;

		}
		//condition for end of 10 rounds
		else if(counter > 10)
			
		{
			
		// Reset the level to 1
		isGameOver = true;
		setIsGameOver(isGameOver);

		level = 1;

		// Reset the question counter to 0
		counter = 1;

		// Displays error message
		JOptionPane.showMessageDialog(jf,  "Your Score: " + score, "" , JOptionPane.ERROR_MESSAGE);
		updateFile(name,score);


		// Reset the score to 0
		score = 0;
		

		//to stop music
		
		try {
			stop();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//display game over menu by invoking gameOverFrame class constructor
		gameOverFrame ex = new gameOverFrame();
        ex.setVisible(true);
        
		}// End of else if condition
		
		mainL.setText("Level - " + level);
		

		}// End of method

		});// End of anonymous class

		
		// Registers action listener to calculateB button using anonymous class
		calculateB.addActionListener(new ActionListener()

		{

		// Overrides the actionPerformed() method of ActionListener interface

		public void actionPerformed(ActionEvent ae)

		{

		String tempR = resultT.getText();
		tempR = tempR.replaceAll("\\s", "");
		tempR = tempR.toLowerCase();
		result = result.replaceAll("\\s", "");

		if(result.equals(tempR)) {
			
			ImageIcon imageIcon4 = new ImageIcon("sponge.gif");
			l5 = new JLabel(imageIcon4);
			jpI3.add(l5);
			
			ImageIcon imageIcon5 = new ImageIcon("mickey.gif");
			l6 = new JLabel(imageIcon5);
			jpI4.add(l6);
			//if result is right panels change to green
			jpI1.setBackground(Color.green);
			jpI2.setBackground(Color.green);
			jpI3.setBackground(Color.green);
			jpI4.setBackground(Color.green);
			jpI5.setBackground(Color.green);
			jpI6.setBackground(Color.green);

		// Increase the score by one
		score++;

		// Sets the congratulation message in result label
		resultL.setText("<html>Great Job! <br/>" + result + " is Correct!<html>");
		resultL.setFont(new Font("ALGERIAN", Font.PLAIN, 25));
		resultL.setForeground(Color.blue);

		}// End of if condition

		// Otherwise wrong answer
		else {
			
			ImageIcon imageIcon4 = new ImageIcon("wrong3.gif");
			l5 = new JLabel(imageIcon4);
			jpI3.add(l5);
			
			ImageIcon imageIcon5 = new ImageIcon("wrong3.gif");
			l6 = new JLabel(imageIcon5);
			jpI4.add(l6);
			//if result is wrong panels change to red
			jpI1.setBackground(Color.red);
			jpI2.setBackground(Color.red);
			jpI3.setBackground(Color.red);
			jpI4.setBackground(Color.red);
			jpI5.setBackground(Color.red);
			jpI6.setBackground(Color.red);

		// Sets the wrong message in result label and displays the corrent answer
		resultL.setText("<html>Incorrect!" + ""
				+ ""
				+ "<br/> Correct Answer is: <html>" + result);
		resultL.setFont(new Font("ALGERIAN", Font.PLAIN, 25));
		resultL.setForeground(Color.red);
		}

		}// End of method

		});// End of anonymous class
		
			
	}// End of method
	});
	
}// End of constructor


//Method to play audio
public void playAudio()throws UnsupportedAudioFileException,
IOException, LineUnavailableException 
{
      
    // current status of clip
    String status;
      
    AudioInputStream audioInputStream;
    
     String audioFileName = "gameAudio.wav";
  // create AudioInputStream object
     audioInputStream = 
             AudioSystem.getAudioInputStream(new File(audioFileName).getAbsoluteFile());
       
     // create clip reference
     clip = AudioSystem.getClip();
       
     // open audioInputStream to the clip
     clip.open(audioInputStream);
       
     clip.loop(Clip.LOOP_CONTINUOUSLY);
     
   //start the clip
     clip.start();
       
     status = "play";
    
}

//Method to stop the audio
public void stop() throws UnsupportedAudioFileException,
IOException, LineUnavailableException 
{
    currentFrame = 0L;
    clip.stop();
    clip.close();
}

//method to update text file storing top 5 players
void updateFile(String n, int s) {
	
	try {
		

	
	String fileLocation = "scores2222.txt";
	
	File scoreFile =new File(fileLocation);
	
	Scanner scoreScan = new Scanner(scoreFile);
	
	String nextLine = scoreScan.nextLine();
	
	String[] scoreboard = nextLine.split(",");
	
	
	int i,j,k = 0;	

	 int max = scoreboard.length;
	 

	 
	 String temp = scoreboard[1];
	 String temp2 = temp.replaceAll("\\s", "");
	 int min = Integer.parseInt(temp2);
	 
	 String scoreUser =String.valueOf(s);
	 
	 //add new name and score to scoreboard array
	 scoreboard = addX(max,scoreboard,n);
	 scoreboard = addX(max+1,scoreboard,scoreUser);



	 boolean index1 = false;
	 
	 int index = 0;
	 
 if(scoreboard.length>10) {

	for(j=1;j<scoreboard.length;j=j+2) {
		 String t = scoreboard[j];
		 String t2 = t.replaceAll("\\s", "");
		 int tempI = Integer.parseInt(t2);
		 
		 if(tempI<min) {
			 min = tempI;
			 index = j;
			 index1 = true;
		 }
	 }
	//if min stays as first element than remove first name and score
	if (index1 != true) {
			
		 scoreboard = removeTheElement(scoreboard,1);
		 scoreboard = removeTheElement(scoreboard,0);
	}
	//if min is found then remove name and score at that index
	else {
		scoreboard = removeTheElement(scoreboard,index);
		scoreboard = removeTheElement(scoreboard,index-1);
		}
	}

 	try{//to delete contents off txt files

 		FileWriter fw = new FileWriter(fileLocation, false);

 		PrintWriter pw = new PrintWriter(fw, false);

 		pw.flush();

 		pw.close();

 		fw.close();

    }catch(Exception exception){

        System.out.println("Exception have been caught");

    }

 	//reading new top 5 names and scores into a string
 	String name1,name2,name3,name4,name5,score1,score2,score3,score4,score5;

	name1 = scoreboard[0];
	name2 = scoreboard[2];
	name3 = scoreboard[4];
	name4 = scoreboard[6];
	name5 = scoreboard[8];
	
	score1= scoreboard[1];
	score2= scoreboard[3];
	score3= scoreboard[5];
	score4= scoreboard[7];
	score5= scoreboard[9];
	
	String nameScore = name1 + ","  + score1 + "," + name2 + ","  + score2+ "," + name3 + ","  + score3 + "," 
	+ name4 + ","  + score4 + "," + name5 + ","  + score5;
	
	
	
	 System.out.println(nameScore); 
	
	  try {//to write into file
		  
          // Create a FileWriter object
          // to write in the file
          FileWriter fWriter = new FileWriter(fileLocation);

          // Writing name and scores into file
          fWriter.write(nameScore);

          // Closing the file writing connection
          fWriter.close();

          // Display message for successful execution of
          // program on the console
          System.out.println(
              "File is created successfully with the content.");
      }

      // Catch block to handle if exception occurs
      catch (IOException e) {

          // Print the exception
          System.out.print(e.getMessage());
      }
	 

	
	 for(i=0;i<scoreboard.length;i++) {
		 System.out.println(scoreboard[i]); 
	 }
	
	
	}catch(FileNotFoundException ex){
		System.out.println("file not found"); 	
	}

	
	
}//end of update file

//Method to generate question
void generateRandomABC()
{
// Generates random number between 1 and 9 inclusive
	
	String[] alphabet = new String[]{"a","b","c","d","e","f","g","h","i",
			"j","k","l","m","n","o","p","q","r",
			"s","t","u","v","w","x","y","z"};


	int temp = rand.nextInt(23);
	
	first = alphabet[temp];

	second = alphabet[temp+1];

	result = alphabet[temp+2];

	numberL.setFont(new Font("ALGERIAN", Font.PLAIN, 52));
	numberL.setText(first + " , " + second + ", ");
}// End of method

//function to remove string from array and return new array
public static String[] removeTheElement(String[] arr, int index)
{

    // If the array is empty
    // or the index is not in array range
    // return the original array
    if (arr == null || index < 0
        || index >= arr.length) {

        return arr;
    }

    // Create another array of size one less
    String[] anotherArray = new String[arr.length - 1];

    // Copy the elements except the index
    // from original array to the other array
    for (int i = 0, k = 0; i < arr.length; i++) {

        // if the index is
        // the removal element index
        if (i == index) {
            continue;
        }

        // if the index is not
        // the removal element index
        anotherArray[k++] = arr[i];
    }

    // return the resultant array
    return anotherArray;
}

//function to add string into array and return new array
public static String[] addX(int n, String arr[], String x)
{
   int i;

   // create a new array of size n+1
   String newarr[] = new String[n+1];

   // insert the elements from
   // the old array into the new array
   // insert all elements till n
   // then insert x at n+1
   for (i = 0; i < n; i++)
       newarr[i] = arr[i];

   newarr[n] = x;

   return newarr;
}//end of method

}// End of class

