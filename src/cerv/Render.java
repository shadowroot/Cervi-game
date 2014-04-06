/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Rendering panel
 * @author jonny
 */
public class Render{
   
    private Graphics g;
    private Map map;
    private Game game;
    private Image backgroud = null;
    private long startTime = 0;
    private final long delay = 40;
    

    public Render(Game game,Map map) {
        g = game.getGamePaneGraphics();
        this.map = map;
        this.game = game;
        if(backgroud == null){
            generateBackground();
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, game.WIN_WIDTH, game.WIN_HEIGHT);
        drawStart(g);
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void frame(Graphics g2d){
        for(DrawableObject dr : map.getAI()){
            if(dr.isAlive()){
                dr.draw(g2d);
            }
        }
        for(DrawableObject dr : map.getBombs()){
            if(dr.isAlive()){
                dr.draw(g2d);
            }
        }
        game.getBomber().draw(g2d);
    }
    
    private void drawStart(Graphics g){
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,60));
        g.setColor(Color.black);
        
        g.drawString("Game started", 100, 200);
        
        g.drawString(game.getPlayerName(), 100, 400);
       
    }
    
    private void drawScore(Graphics g){
        String score = "Score: " + game.getScore();
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        g.drawString(score, 300, 20);
    }
    
    private void drawEndOfGame(Graphics g){
        String cg = "CONGRATULATION";
        String score = "Score: " + game.getScore();
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        g.drawString(cg, 300, 300);
        g.setColor(Color.red);
        g.drawString(score, 300, 400);
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void drawGameOver(Graphics g){
        String cg = "GAME OVER";
        g.setColor(Color.red);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        g.drawString(cg, 300, 300);
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateBackground(){
        backgroud = new BufferedImage(game.WIN_WIDTH,game.WIN_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = backgroud.getGraphics();
        Random rand = new Random();
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, game.WIN_WIDTH, game.WIN_HEIGHT);
        g.setColor(Color.black);
        for(int i = 0; i < 300; i++){
            g.fillRect(rand.nextInt(game.WIN_WIDTH), rand.nextInt(game.WIN_HEIGHT), 1, 1);
        }
    }
    
    private void drawBackgroud(Graphics g){
        g.drawImage(backgroud, 0, 0, null);
    }
    

    public void paint() {
        startTime = new Date().getTime();
        paintOff(g);
        while((new Date()).getTime() < startTime + delay){}
    }
    
    

    private void paintOff(Graphics g) {
        drawBackgroud(g);
        frame(g);
        drawScore(g);
        
        if(game.isGameOver()){
            drawGameOver(g);
        }
        if(game.isEndOfGame()){
            drawEndOfGame(g);
        }
    }
    
}
