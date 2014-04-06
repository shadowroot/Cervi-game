/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import javax.swing.JPanel;

/**
 *
 * @author jonny
 */
public class MainPane extends JPanel{

    private Game game;
    
    public MainPane(Game game) {
        super();
        addKeyListener(new KeyController(game));
        this.game = game;
        setFocusable(true);
        setVisible(true);
        setSize(new Dimension(Game.WIN_WIDTH, Game.WIN_HEIGHT));
        requestFocus();
    }
    
    
}
