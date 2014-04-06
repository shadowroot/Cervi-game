/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author jonny
 */
public class Bomb extends DrawableObject{
    
    private long timeToExplode = 0;
    private long startCountdown = 0;
    private int explodeRadius = 50;
    private boolean exploded = false;
    private Rectangle explodeRect;
    
    public Bomb(int x,int y,long timeToExplode) {
        startCountdown = (new Date()).getTime();
        this.timeToExplode = timeToExplode;
        this.x = x;
        this.y = y;
        try {
            initTexture();
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Bomb(int x,int y) {
        startCountdown = (new Date()).getTime();
        this.timeToExplode = 5000; //< default 5000ms
        this.x = x;
        this.y = y;
        try {
            initTexture();
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean Exploded(){
        if(exploded){
            return true;
        }
        if(timeToExplode+startCountdown >= (new Date()).getTime()){
            exploded = true;
            explodeRect = new Rectangle(x-explodeRadius/2, y-explodeRadius/2, explodeRadius, explodeRadius);
            return true;
        }
        return false;
    }
    
    public Rectangle getRect(){
        return explodeRect;
    }
    
    public void initTexture() throws IOException{
        fw_stand = new ImageIcon(getClass().getResource("images/bomb.png")).getImage();
        /*
         * 20x20
         */
        BufferedImage image_to_clip = ImageIO.read(getClass().getResource("images/explode.png"));
        
        Animation useAnimation = new Animation();
        for(int u = 0 ; u < 2; u++){
            for(int v = 0; v < 5; v++){
                useAnimation.addFrame(new AnimationFrame(image_to_clip.getSubimage(v*20, u*20, 20, 20)));
            }
        }
        current_image = fw_stand;
        active = false;
        dw = new Sprite(useAnimation, this);
    }
    
    public boolean BombKill(DrawableObject obj){
        if(getRect().intersects(obj.getRect())){
            return true;
        }
        return false;
    }
    
    
}
