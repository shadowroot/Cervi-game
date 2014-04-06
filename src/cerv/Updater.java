/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Updating positions
 * @author jonny
 */
public class Updater{
    
    private Bomber bomber;
    private Game game;
    private Map map;
    private Render render;
    

    public Updater(Game game,Map map) {
        this.game = game;
        this.map = map;
        bomber = game.getBomber();
        render = new Render(game, map);
    }
    
    
    public Render getRender(){
        return render;
    }
    
    public void run(){
        game.getMainPane().requestFocus();
        update();
        render.paint();
        try {
            Thread.sleep(100 - map.getLevel() * 10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void update(){
        
        if(map.getAI().isEmpty()){
            game.LevelDone();
        }
        if(map.getBombs().isEmpty()){
            for(Enemy enemy : map.getAI()){
                if(enemy.getRect().intersects(bomber.getRect())){
                    game.GameOver();
                }
                for(Enemy en : map.getAI()){
                    if(enemy != en){
                        if(!enemy.getRect().intersects(en.getRect())){
                            if(map.insideMap(enemy)){
                               enemy.moveEnemy();
                            }
                            else{
                                enemy.reset();
                            }
                        }
                    }
                }
            }
            if(map.insideMap(bomber)){
                bomber.movement();
            }
        }
        else{
            for(Bomb bomb : map.getBombs()){
                /*
                 * Bomb killing collision checking.
                 */
                if(bomb.Exploded()){
                    if(bomb.BombKill(bomber)){
                        game.GameOver();
                    }

                    for(Enemy enemy : map.getAI()){
                        /*
                         * Has bomber kill?
                         */
                        if(bomb.Exploded()){
                            if(bomb.BombKill(enemy)){
                                enemy.die();
                                map.killEnemy(enemy);
                            }
                        }
                        else{
                            if(!map.isCollision(enemy, bomb) && map.insideMap(enemy)){
                                enemy.moveEnemy();
                            }
                        }
                    }
                    /*
                     * Remove bomb from array.
                     */
                    map.removeBomb(bomb);
                }
                else{
                    /*
                    * Simple step collision. Just stopping.
                    */
                    for(Enemy enemy : map.getAI()){
                        if(!map.isCollision(enemy, bomb)){
                            enemy.moveEnemy();
                        }
                    }
                    if(!map.isCollision(bomber, bomb)){
                        bomber.movement();
                    }
                }
            }
        }
    }
    
    
    
    
}
