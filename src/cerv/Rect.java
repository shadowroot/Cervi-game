/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

/**
 *
 * @author jonny
 */
public class Rect {
    private double x;
    private double y;
    private double wx;
    private double hy;

    public Rect(double x, double y, double wx, double hy) {
        this.x = x;
        this.y = y;
        this.wx = wx;
        this.hy = hy;
    }

    
    
    public boolean isInside(double x,double y){
        if(x < this.x + wx && x > this.x && y < this.y + hy && y > this.y){
            return true;
        }
        return false;
    }
    
}
