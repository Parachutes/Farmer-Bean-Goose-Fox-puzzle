/**
 * 
 */
package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author mashichao
 *This is a JPanel bases on Water
 */
public class WaterPanel extends JPanel{
	
	private BufferedImage waterPic;
 
    public WaterPanel() {
        try {
            waterPic = ImageIO.read(new File("src/pic/water.png"));
        } catch (IOException ex) {
            System.out.println("Couldn't read in the pic");
        }
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(waterPic, 0, 0, this);
	}
}
