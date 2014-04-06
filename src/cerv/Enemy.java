/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;



/**
 * Determines state and do action.
 *
 * @author jonny
 */
public class Enemy extends DrawableObject{
   
    /*
    * State:
    * 0 - respawn,determine direction
    * 1 - moving
    * 2 - diing
    * 3 - to ground
    */
    private DirectionEnum direction;
    private int maxSteps = 0;
    private int currentSteps = 0;
    private int speed = 1;
    /*
     * straight running.
     */
    private boolean running = false;
    private boolean disapearing = false;
    private boolean appearing = false;
    private Map map;
   

    public Enemy(Map map,int speed) {
        this.map = map;
        this.speed = speed;
        try {
            initTexture();
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        guessPosition();
        appearing = true;
    }
    
   
    public void moveEnemy(){
        if(alive){
            if(running){
                if(currentSteps >= maxSteps){
                    running = false;
                    disapearing = true;
                    currentSteps = 0;
                }
                else{
                    moveDirection();
                }
            }
            if(appearing){
                guessDirection();
                guessDistance();
                appearing = false;
                running = true;
            }
            if(disapearing){
                running = false;
                disapearing = false;
                guessPosition();
                appearing = true;
            }
        }
    }
    
    
    private void guessDirection(){
        int direction = 0;
        Random rand = new Random();
        direction = rand.nextInt(3);
        switch(direction){
            case 0:
                this.direction = DirectionEnum.FW;
                current_sprite = fw;
                current_image = fw_stand;
                break;
            case 1:
                this.direction = DirectionEnum.BW;
                current_sprite = bw;
                current_image = bw_stand;
                break;
            case 2:
                this.direction = DirectionEnum.LW;
                current_sprite = lw;
                current_image = lw_stand;
                break;
            case 3:
                this.direction = DirectionEnum.RW;
                current_sprite = rw;
                current_image = rw_stand;
                break;
        }
    }
    
    private void moveDirection(){
        if(direction == DirectionEnum.FW){
            y += speed;
        }
        else if(direction == DirectionEnum.BW){
            y -= speed;
        }
        else if(direction == DirectionEnum.LW){
            x -= speed;
        }
        else{
            x += speed;
        }
        currentSteps += speed;
    }
    
    private void guessDistance(){
        Random rand = new Random();
        maxSteps = rand.nextInt(100);
        if(maxSteps < 50){
            maxSteps = 50;
        }
    }
    
    
    private void initTexture() throws IOException{
        BufferedImage image_to_clip = ImageIO.read(getClass().getResource("images/worms.png"));
        
        Animation fw_anim = new Animation();
        Animation bw_anim = new Animation();
        Animation lw_anim = new Animation();
        Animation rw_anim = new Animation();
        
        for(int u = 0 ; u < 4; u++){
            fw_anim.addFrame(new AnimationFrame(image_to_clip.getSubimage(u*20, 0, 20, 20)));
        }
        
        for(int u = 0 ; u < 4; u++){
            lw_anim.addFrame(new AnimationFrame(image_to_clip.getSubimage(u*20, 20, 20, 20)));
        }
        
        for(int u = 0 ; u < 4; u++){
            rw_anim.addFrame(new AnimationFrame(image_to_clip.getSubimage(u*20, 40, 20, 20)));
        }
        
        for(int u = 0 ; u < 4; u++){
            bw_anim.addFrame(new AnimationFrame(image_to_clip.getSubimage(u*20, 60, 20, 20)));
        }
        
        
        fw = new Sprite(fw_anim, this);
        bw = new Sprite(bw_anim, this);
        lw = new Sprite(lw_anim, this);
        rw = new Sprite(rw_anim, this);
        current_sprite = fw;
        active = true;
    }
    
    public void reset(){
        guessPosition();
    }

    private void guessPosition() {
        Random rand = new Random();
        x = rand.nextInt(Game.WIN_WIDTH - BOX_WIDTH);
        y = rand.nextInt(Game.WIN_HEIGHT - BOX_HEIGHT);
    }
    
    
}
