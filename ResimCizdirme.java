import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ResimCizdirme{
	BufferedImage image;
	JPanel Cizdir(String resimkonum){
		
			try {
				image = ImageIO.read(new File(resimkonum));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pane.setBackground(Color.decode("#800000")); 
        return pane;
	}
	
}
