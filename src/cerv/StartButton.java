/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Button for starting game
 *
 * @author jonny
 */
public class StartButton extends JButton implements ActionListener{

    private Game game;
    private JTextField textLabel;
    
    public StartButton(Game game,JTextField textLabel) {
        super();
        this.setText("START");
        this.game = game;
        this.textLabel = textLabel;
        addActionListener(this);
        setFocusTraversalKeysEnabled(true);
        setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         start();
    }
    
    private void start(){
        if(textLabel.getText() != ""){
            game.setPlayer(textLabel.getText());
            game.startGame();
        }
        else{
            game.badName();
        }
    }
    
}
