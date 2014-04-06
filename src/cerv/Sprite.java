/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Graphics;

/**
 * Sprite
 *
 * @author jonny
 */
public class Sprite{
    private Animation animation;
    private DrawableObject drawable;
    

    public Sprite(Animation animation,DrawableObject drawable){
        this.animation = animation;
        this.drawable = drawable;
    }
    
    public boolean isDone(){
        return animation.isDone();
    }
    
    
    public void draw(Graphics g){
        AnimationFrame fr = animation.getCurrentFrame();
        if(fr != null && fr.getImage() != null && g != null){
            g.drawImage(fr.getImage(), drawable.getX(), drawable.getY(),null);
        }
    }

    
}
