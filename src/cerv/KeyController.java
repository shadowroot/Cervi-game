/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jonny
 */
public class KeyController implements KeyListener, KeyEventDispatcher{

    private Game game;
    
    public KeyController(Game game) {
        super();
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                   game.getBomber().moveLeft();
                   game.getBomber().active = true;
                    break;

                case KeyEvent.VK_RIGHT:
                   game.getBomber().moveRight();
                   game.getBomber().active = true;
                    break;

                case KeyEvent.VK_DOWN:
                   game.getBomber().moveDown();
                   game.getBomber().active = true;
                    break;

                case KeyEvent.VK_UP:
                   game.getBomber().moveUp();
                   game.getBomber().active = true;
                    break;
                case KeyEvent.VK_SPACE:
                    game.getCurrentMap().putBomb();
                    break;
                default:
                    break;
        }
        System.out.println("Pressed:" + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
               game.getBomber().active = false;
                break;
            case KeyEvent.VK_RIGHT:
               game.getBomber().active = false;
                break;

            case KeyEvent.VK_DOWN:
               game.getBomber().active = false;
                break;

            case KeyEvent.VK_UP:
               game.getBomber().active = false;
                break;
            default:
                break;
        }
        System.out.println("Released:" + e.getKeyCode());
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                       game.getBomber().moveLeft();
                       game.getBomber().active = true;
                        break;

                    case KeyEvent.VK_RIGHT:
                       game.getBomber().moveRight();
                       game.getBomber().active = true;
                        break;

                    case KeyEvent.VK_DOWN:
                       game.getBomber().moveDown();
                       game.getBomber().active = true;
                        break;

                    case KeyEvent.VK_UP:
                       game.getBomber().moveUp();
                       game.getBomber().active = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        game.getCurrentMap().putBomb();
                        break;
                    default:
                        break;
            }
        }
        else if(e.getID() == KeyEvent.KEY_RELEASED){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                   game.getBomber().active = false;
                    break;
                case KeyEvent.VK_RIGHT:
                   game.getBomber().active = false;
                    break;

                case KeyEvent.VK_DOWN:
                   game.getBomber().active = false;
                    break;

                case KeyEvent.VK_UP:
                   game.getBomber().active = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Dispatched:" + e.getKeyCode());
        return false;
    }
    
}
