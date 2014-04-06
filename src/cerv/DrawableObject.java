/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author jonny
 */
public class DrawableObject{
    
    protected Sprite fw;
    protected Sprite bw;
    protected Sprite lw;
    protected Sprite rw;
    protected Sprite dw;
    protected Sprite uw;
    protected Image fw_stand;
    protected Image bw_stand;
    protected Image lw_stand;
    protected Image rw_stand;
    
    protected Sprite current_sprite;
    protected Image current_image;
    
    protected int targetX;
    protected int targetY;
    protected boolean active = false;
    protected int x;
    protected int y;
    protected final int BOX_WIDTH = 20;
    protected final int BOX_HEIGHT = 20;
    protected DirectionEnum current_pos;
    protected boolean alive = true;
    

    public DrawableObject() {
    }
    
    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }
   
   public int getX(){
       return x;
   }
   
   public int getY(){
       return y;
   }
   /*
    * Taking rectangle for position rectangle.
    */
   public Rectangle getRect(){
       return new Rectangle(x, y, BOX_WIDTH, BOX_HEIGHT);
   }
    
    /*
     * Draving sprite with active true and with active=false image.
     * 
     */
    public void draw(Graphics g){
        if(active){
            current_sprite.draw(g);
        }
        else{
            g.drawImage(current_image, x, y, null);
        }
    }
    
    public void die(){
        alive = false;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
}
