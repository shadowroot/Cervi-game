/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *  Holding all informations about map and handles everything on panel.
 * 
 * @author jonny
 */
public class Map{
    private Bomber bomber;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private int MAX_ENEMIES = 5;
    private int MAXX = 0;
    private int MAXY = 0;
    private final int bombKilled = 50;
    private Game game;
    private int level;
    private Rectangle map_rect;
    

    public Map(Game game,int level,int maxX,int maxY){
        super();
        this.game = game;
        this.level = level;
        MAX_ENEMIES = level + 5;
        for(int i = 0; i < MAX_ENEMIES ; i++){
            enemies.add(new Enemy(this,1+level));
        }
        MAXX = maxX;
        MAXY = maxY;
        bomber = new Bomber();
        map_rect = new Rectangle(0, 0, Game.WIN_WIDTH, Game.WIN_HEIGHT);
    }
    
    public int getLevel(){
        return level;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Enemy> getAI() {
        return enemies;
    }
    
    public int getMaxX(){
        return MAXX;
    }
    
    public int getMaxY(){
        return MAXY;
    }
    
    public void removeBomb(Bomb bomb){
        bombs.remove(bomb);
        bomber.explodedBomb();
    }
    /*
     * 
     */
    public void putBomb(){
        if(bomber.putBomb()){
            Bomb bomb = new Bomb(bomber.getX(), bomber.getY());
            bombs.add(bomb);
        }
    }
    
    
    /*
     * Collision to stay.
     */
    public boolean isCollision(DrawableObject obj,Bomb bomb){
        if(bomb.getRect().intersects(obj.getRect())){
            return true;
        }
        return false;
    }
    
    
    public void killEnemy(Enemy enemy){
        enemies.remove(enemy);
        game.addScore(bombKilled);
    }
    
    public boolean insideMap(DrawableObject obj){
        if(map_rect.contains(obj.getRect())){
            return true;
        }
        return false;
    }

    
    
}
