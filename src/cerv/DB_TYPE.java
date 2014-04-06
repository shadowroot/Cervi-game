/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

/**
 *
 * @author jonny
 */
public class DB_TYPE {
    private String name;
    private int score;
    
    public DB_TYPE(String name,int score){
        this.name = name;
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return new String(name + " " + score);
    }
  
}