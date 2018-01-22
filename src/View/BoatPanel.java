/**
 * 
 */
package View;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author mashichao
 *
 */
public class BoatPanel extends JPanel{
	
	private BufferedImage boatPic;
	 
    public BoatPanel() {
        try {
            boatPic = ImageIO.read(new File("src/pic/boat.png"));
        } catch (IOException ex) {
            System.out.println("Couldn't read in the pic");
        }
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boatPic, 0, 0, this);
	}
}
