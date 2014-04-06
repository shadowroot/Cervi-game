/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cerv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonny
 */
public class DB {
    
    private Connection conn;
    private static int ID = 0;
    
    public DB(){
        try {
            connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<DB_TYPE> getData() throws SQLException{
        ArrayList<DB_TYPE> ret = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM APP.SCORE");
        while(rs.next()){
            ret.add(new DB_TYPE(rs.getString(2), rs.getInt(3)));
        }
        return ret;
    }
    
    public void setData(DB_TYPE data) throws SQLException{
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("INSERT INTO APP.SCORE (ID,NAME,SCORE) VALUES (" + ID +",'"+data.getName()+"',"+data.getScore()+")");
        if(!rs.rowInserted()){
            throw new SQLException("Not inserted DB_TYPE");
        }
        ID++;
    }
    
    public void connect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        String url = "jdbc:derby://localhost:1527/game";
        if(conn == null){
            conn = DriverManager.getConnection(url,"score","score");
        }
        
    }

    public void close() throws SQLException{
        conn.close();
    }
}
