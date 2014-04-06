/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jonny
 */
public class ExitButton extends JButton implements ActionListener{

    private Game game;
    
    public ExitButton(Game game) {
        super();
        this.game = game;
        this.setText("EXIT");
        addActionListener(this);
        setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        WindowEvent wev = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
        mainFrame.dispose();
        */
        game.destroy();
        System.out.println("Hra ukončena.");
        System.exit(0);
    }
    
    
}
