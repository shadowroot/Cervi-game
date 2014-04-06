/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jonny
 */
public class Animation {
    
    private long startFrame = 0;
    private long delay = 1000;
    private ArrayList<AnimationFrame> frames;
    private int cycle=0;
    private int size=0;
    private boolean done = false;
    
    public Animation() {
        frames = new ArrayList<>();
    }
    
    public void addFrame(AnimationFrame frame){
        frames.add(frame);
        size++;
    }
    
    public boolean isDone(){
        return done;
    }
    
    public AnimationFrame getCurrentFrame(){
        AnimationFrame fr = null;
        if((new Date()).getTime() >= startFrame + delay){
            cycle++;
        }
        if(cycle < size){
            startFrame = (new Date()).getTime();
            done = false;
        }
        else{
            cycle = 0;
            done = true;
        }
        if(!frames.isEmpty()){
            fr = frames.get(cycle);
        }
        return fr;
    }
    
    
}