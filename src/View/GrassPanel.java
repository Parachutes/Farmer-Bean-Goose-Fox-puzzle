/**
 * 
 */
package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author mashichao
 *This is a JPanel bases on grass
 */
public class GrassPanel extends JPanel{

	private BufferedImage grassPic;
	 
    public GrassPanel() {
        try {
            grassPic = ImageIO.read(new File("src/pic/grass.png"));
        } catch (IOException ex) {
            System.out.println("Couldn't read in the pic");
        }
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grassPic, 0, 0, this);
	}
	
	@Override
	public Dimension preferredSize() {
		return new Dimension(160, 650);
	}  
}
