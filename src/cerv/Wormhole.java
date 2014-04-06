/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.io.IOException;

/**
 *
 * @author jonny
 */
public class Wormhole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        try{
            Game game = new Game();
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println("Error occured.");
        }
       
    }
}
