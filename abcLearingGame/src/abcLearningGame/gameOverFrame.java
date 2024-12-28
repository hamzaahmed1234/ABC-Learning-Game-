package abcLearningGame;
/*
Name: Hamza Ahmed
Final Project
Submission Date: 10:00 pm, tues (12/8)
Brief Description: : Use GUI, graphics, colors, sounds, animations or images, event handling, exception handling, Layout 
managers, file I/O and other techniques in Java to develop alphabet-learning program as an educational
and attractive game for pre-school or first grade kids. The game should be interactive, colorful with sounds,
fun to play for kids and able to display player’s scores.
gameOverFrame class uses graphics2d to display a frame with shapes and an image
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {
    
    private void doDrawing(Graphics g) {
    	//set shape color to magenta
    	g.setColor(Color.magenta);
    	//object of Graphics2d
        Graphics2D g2d = (Graphics2D) g;
        Image image = null;

        try 
        {
        	//draw all shapes and images at specified locations
        	image = ImageIO.read(new File("game-over.gif"));
            g2d.drawImage(image, 45, 100, this);
            g2d.fill(new Ellipse2D.Double(5, 30, 50, 50));
            g2d.fill(new Ellipse2D.Double(60, 30, 50, 50));
            g2d.fillRect(60, 30, 50, 50);
            g2d.fillRect(180, 30, 50, 50);
            g2d.fillRect(300, 30, 50, 50);
            g2d.fill(new Ellipse2D.Double(120, 30, 50, 50));
            g2d.fill(new Ellipse2D.Double(240, 30, 50, 50));            
            g2d.fill(new Ellipse2D.Double(360, 30, 50, 50));
            g2d.fillRect(420, 30, 50, 50);
            g2d.fill(new Ellipse2D.Double(480, 30, 50, 50));
            g2d.fillRect(540, 30, 50, 50);
            }
        catch (IOException e)
            {e.printStackTrace();}
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        
   } 

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }    
}

public class gameOverFrame extends JFrame {

	//default constructor
    public gameOverFrame() {

        initUI();
    }
    
    //to edit the frame
    private void initUI() {
        add(new Surface());
        setTitle("Close Window");
        setSize(610, 510);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}