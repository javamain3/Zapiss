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

    private MyDBConnector ()
    {
    }

    public static MyDBConnector getInstance()
    {
        if (instance == null)
        {
            instance = new MyDBConnector();
        }
        return instance;
    }

    public void connect( String url, String driver_name, String login, String password )
    {
        try
        {
            Class.forName( driver_name );
            dbconnection = DriverManager.getConnection( url, login, password );
        }
        catch( SQLException se )
        {
            System.err.println( "Error: exception " + se.toString() + " ." );
        }
        catch( Exception e)
        {
            System.err.println(" Error: exception " + e.toString() + " .");
        }
    }

    public ResultSet exec( String str)
    {
        try 
        {
            Statement stmt = null;
            stmt = dbconnection.createStatement();
            String sql = str;
            ResultSet rs = stmt.executeQuery( sql);
            return rs;
        }
        catch( SQLException e)
        {
            System.err.println( "Error: error " + e.toString() + " ." );
        }
        return null;
    }

    public void disconnect()
    {
        try
        {
            dbconnection.close();
        } catch( SQLException e)
        {
            System.err.append( "Error: while closing jdbc connection: " + e.toString()+" .");
        }
    }
}



