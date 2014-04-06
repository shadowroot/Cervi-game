/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 * 
 * @author jonny
 */
public class BGContainer extends JPanel{

    private boolean result = false;
    private Image img;
    
    public BGContainer(Game game) {
        super();
        setMinimumSize(new Dimension(Game.WIN_WIDTH, Game.WIN_HEIGHT));
        img = new ImageIcon(getClass().getResource("images/background.jpg")).getImage();
        setVisible(true);
        addKeyListener(new KeyController(game));
        setFocusTraversalKeysEnabled(true);
        setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(img, 0, 0, this);
    }
    
    public void Results(){
        result = true;
    }
    
    public void noResults(){
        result = false;
    }

}
