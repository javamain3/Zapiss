
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.library;

import java.sql.*;

/**
 * @author alex-user
 */

public class MyDBConnector  
{ 
    private static MyDBConnector instance; 
    private Connection dbconnection; 

    private MyDBConnector () { 
    } 

    public static MyDBConnector getInstance() { 
        if (instance == null) { 
            instance = new MyDBConnector(); 
        } 
        return instance; 
    } 

    public void connect( String url, String driver_name, String login, String password ) { 
        try
        {
            Class.forName( "org.apache.derby.jdbc.ClientDriver" ); 
            dbconnection = DriverManager.getConnection( "jdbc:derby://localhost:1527/MSGDataBase", "user1", "user1" ); 
            if( !dbconnection.isValid(5) ) 
            { 
                System.out.println("Error: connection to db not valid. "); 
            } 
//            Statement stmt = dbconnection.createStatement   (   
//                ResultSet.TYPE_SCROLL_INSENSITIVE,  
//                ResultSet.CONCUR_UPDATABLE  
//                                                            );  
            if( dbconnection.isValid( 5 ) == false ) 
            { 
                System.err.println( " Connection to db is not valid.  " ); 
            } 
        } catch( ClassNotFoundException ex ) { 
            System.err.println("Error: error " + ex.toString() + " ."); 
        } catch (SQLException ex) {
            System.err.println("Error: error " + ex.toString() + " .");
        }
    } 

    public ResultSet exec( String str) 
    {
        try {
            Statement stmt = null;
            stmt = dbconnection.createStatement (   ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE
                                                );

            String sql = str;
            ResultSet rs = stmt.executeQuery( sql);

            if( rs == null ) {
                System.out.println( "Error: ResultSet is null. " );
            } else {
                System.out.println( "IsFirst = " + rs.isFirst() + " ." );
            }

            if( rs.isFirst() == false) {
                return null;
            }

            return rs;
        } catch( SQLException e) {
            System.err.println( "Error: error " + e.toString() + " in |" + str + "| ." ); 
        }
        return null; 
    }

    public void disconnect() { 
        try
        {
            dbconnection.close(); 
        } catch( SQLException e) {
            System.err.append( "Error: while closing jdbc connection: " + e.toString()+" ."); 
        }
    } 
} 