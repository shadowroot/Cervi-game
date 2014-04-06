/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jonny
 */
public class MenuPage extends JPanel implements ActionListener{
    
    private Game game;
    private JLabel msg;
    private JButton bStart;
    private JButton bExit;
    private JButton bShowPlayers;
    private JTextField textLabel;
    
    public MenuPage(Game game) {
        super();
        this.game = game;
        addComponents();
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(new Dimension(Game.WIN_WIDTH, Game.WIN_HEIGHT));
        setOpaque(false);
        revalidate();
        setFocusable(false);
    }
    
    private void addComponents(){
        msg = new JLabel("Zadejte své jméno:");
        msg.setForeground(Color.MAGENTA);
        textLabel = new JTextField();
        textLabel.setMaximumSize(new Dimension(200, 24));
        bStart = new StartButton(game,textLabel);
        bExit = new ExitButton(game);
        bShowPlayers = new JButton("Show scores");
        bShowPlayers.addActionListener(this);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        bStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        bExit . setAlignmentX(Component . CENTER_ALIGNMENT);
        bShowPlayers.setAlignmentX(Component . CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(msg);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(textLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(bStart);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(bShowPlayers);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(bExit);
        bStart.setVisible(true);
        bExit.setVisible(true);
        repaint();
    }
    
    public void clear(){
        remove(msg);
        remove(textLabel);
        remove(bStart);
        remove(bShowPlayers);
        remove(bExit);
        repaint();
    }
    
    public void badName(){
        msg.setText("Jméno nebylo zadáno.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DB db = game.getDB();
        game.getBGContainer().Results();
        removeAll();
        ArrayList<DB_TYPE> datas = null;
        try {
            datas = db.getData();
        } catch (SQLException ex) {
            Logger.getLogger(MenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(datas != null){
            for(DB_TYPE row : datas){
                JLabel rowLabel = new JLabel(row.toString());
                rowLabel.setForeground(Color.MAGENTA);
                add(rowLabel);
            }
        }
        else{
            JLabel empty = new JLabel("Žádné záznamy");
            empty.setForeground(Color.MAGENTA);
            add(empty);
        }
        JButton back = new JButton("Back");
        
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                addComponents();
                revalidate();
                game.repaint();
            }
        });
        
        
        add(back);
        revalidate();
    }
    
    


    
    
}

