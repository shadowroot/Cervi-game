/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 *
 * @author jonny
 */
public class Game extends JFrame{
    private Map current_level;
    private int level = 0;
    final static private int MAX_LEVEL = 5;
    private boolean running = false;
    private boolean levelEnd = false;
    private boolean gameOver = false;
    private boolean gameEnd = false;
    private Date levelStart;
    private Updater updater;
    public final static int WIN_WIDTH = 600;
    public final static int WIN_HEIGHT = 600;
    private int score = 0;
    private String name;
    protected JTextField textLabel;
    private Bomber bomber;
    private DB db;
    private BGContainer bgcontainer;
    private MenuPage menu;
    private MainPane mainPane;
    

    public Game() {
        super();
        try {
            db = new DB();
            db.connect();
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        setVisible(true);
        bomber = new Bomber();
        setSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));
        setVisible(true);
        menu = new MenuPage(this);
        bgcontainer = new BGContainer(this);
        bgcontainer.add(menu);
        add(bgcontainer);
        revalidate();
        repaint();
        setFocusable(false);
    }
    
    public DB getDB(){
        return db;
    }
    
    public MainPane getMainPane(){
        return mainPane;
    }
    
    public void destroy(){
        try {
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Map getCurrentMap(){
        return current_level;
    }
    
    public void startGame(){
        //getContentPane().addKeyListener(new KeyController(this));
        //addKeyListener(new KeyController(this));
        mainPane = new MainPane(this);
        mainPane.addKeyListener(new KeyController(this));
        add(mainPane);
        revalidate();
        startLevel();
    }
    
    public Graphics getGamePaneGraphics(){
        return mainPane.getGraphics();
    }
    
    
    public void startLevel(){
        current_level = new Map(this,level,WIN_WIDTH,WIN_HEIGHT);
        running = true;
        levelEnd = false;
        levelStart = new Date();
        updater = new Updater(this,current_level);  
        for(;;){
            updater.run();
            if(!running){
                break;
            }
            if(levelEnd && level == MAX_LEVEL){
                EndOfGame();
                break;
            }
            if(levelEnd){
                nextLevel();
            }
            mainPane.requestFocus();
        }
        write_DB(new DB_TYPE(name, score));
        updater = null;
        revalidate();
    }
    
    private void write_DB(DB_TYPE player){
        try {
            db.setData(player);
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Bomber getBomber(){
        return bomber;
    }
    
    public void GameOver(){
        running = false;
        gameOver = true;
    }
    
    public void EndOfGame(){
        running = false;
        gameEnd = true;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getPlayerName(){
        return name;
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    
    public boolean isGameRunning(){
        return running;
    }
    
    public boolean isEndOfGame(){
        return gameEnd;
    }
    
    public void setPlayer(String name){
        this.name = name;
    }
    
    public void EndOfLevel(){
        nextLevel();
    }
    
    
    public void nextLevel(){
        level++;
        startLevel();
    }
    
    public void LevelDone(){
        levelEnd = true;
    }
    
    public int getMaxX(){
        return WIN_WIDTH;
    }
    
    public int getMaxY(){
        return WIN_HEIGHT;
    }
    
    public void addScore(int points){
        score += points;
    }

    void badName() {
        menu.badName();
    }
    
    public BGContainer getBGContainer(){
        return bgcontainer;
    }
    
}
