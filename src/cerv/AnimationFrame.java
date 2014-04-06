/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author jonny
 */
public class AnimationFrame {
    private Image image;

    public AnimationFrame(String filename) {
        this.image = loadImage(filename);
    }
    
    public AnimationFrame(Image image){
        this.image = image;
    }
    
    public Image getImage(){
        return image;
    }
    
    
    public Image loadImage(String filename){
        Image img = (new ImageIcon(getClass().getResource("images/"+filename))).getImage();
        return img;
    }
    
    
    
    
}
