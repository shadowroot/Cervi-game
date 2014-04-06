/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.util.Random;
import javax.swing.ImageIcon;




/**
 * Size of square
 * 
 * @author jonny
 */


public class Bomber extends DrawableObject{
    
    
    private int puttedBombs = 0;
    private int maxPuttedBomb = 4;
    private int step_w = 2;
    private int step_h = 2;
    /**
     *
     * @param map
     */
    public Bomber() {
        initTexture();
        Random rand = new Random();
        x = Game.WIN_WIDTH - rand.nextInt(300);
        y = Game.WIN_HEIGHT - rand.nextInt(300);
    }
    
    public void moveLeft(){
        current_pos = DirectionEnum.LW;
        current_sprite = lw;
        current_image = lw_stand;
    }
    
    public void moveRight(){
        current_pos = DirectionEnum.RW;
        current_sprite = rw;
        current_image = rw_stand;
    }
    
    public void moveDown(){
        current_pos = DirectionEnum.DW;
        current_sprite = bw;
        current_image = bw_stand;
    }
    
    public void moveUp(){
        current_pos = DirectionEnum.FW;
        current_sprite = fw;
        current_image = fw_stand;
    }
    
    public void movement(){
        if(active){
            if(current_pos == DirectionEnum.LW){
                x-=step_w;
            }
            else if(current_pos == DirectionEnum.RW){
                x+=step_w;
            }
            else if(current_pos == DirectionEnum.DW){
                y+=step_h;
            }
            else{
                y-=step_h;
            }
            System.out.println("Moved x=" +x + " y=" + y);
        }
        
    }
    
    public boolean putBomb(){
        if(puttedBombs <= maxPuttedBomb){
            puttedBombs++;
            return true;
        }
        return false;
    }
    
    public void explodedBomb(){
        puttedBombs--;
    }
    
    private void initTexture(){
        fw_stand = new ImageIcon(getClass().getResource("images/bomb_fw_stand.png")).getImage();
        bw_stand = new ImageIcon(getClass().getResource("images/bomb_bw_stand.png")).getImage();
        lw_stand = new ImageIcon(getClass().getResource("images/bomb_lw_stand.png")).getImage();
        rw_stand = new ImageIcon(getClass().getResource("images/bomb_rw_stand.png")).getImage();
        
        Animation fw_anim = new Animation();
        fw_anim.addFrame(new AnimationFrame("bomb_fw_0.png"));
        fw_anim.addFrame(new AnimationFrame("bomb_fw_1.png"));
        fw = new Sprite(fw_anim, this);
        
        Animation bw_anim = new Animation();
        bw_anim.addFrame(new AnimationFrame("bomb_bw_0.png"));
        bw_anim.addFrame(new AnimationFrame("bomb_bw_1.png"));
        bw = new Sprite(bw_anim, this);
        
        Animation lw_anim = new Animation();
        lw_anim.addFrame(new AnimationFrame("bomb_lw_0.png"));
        lw_anim.addFrame(new AnimationFrame("bomb_lw_1.png"));
        lw = new Sprite(lw_anim, this);
        
        Animation rw_anim = new Animation();
        rw_anim.addFrame(new AnimationFrame("bomb_rw_0.png"));
        rw_anim.addFrame(new AnimationFrame("bomb_rw_1.png"));
        rw = new Sprite(rw_anim, this);
        
        
        Animation die_anim = new Animation();
        die_anim.addFrame(new AnimationFrame("bomb_explode_0.png"));
        die_anim.addFrame(new AnimationFrame("bomb_explode_1.png"));
        die_anim.addFrame(new AnimationFrame("bomb_explode_1.png"));
        dw = new Sprite(die_anim, this);
        current_pos = DirectionEnum.FW;
        current_image = fw_stand;
        active = false;
    }
    
    
}
