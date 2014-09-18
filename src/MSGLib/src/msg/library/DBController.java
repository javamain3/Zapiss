/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alex-user
 */

public class DBController {
    
    
    private static DBController instance;

    private DBController () {
    }

    public static DBController getInstance() {
        if (instance == null) { 
            instance = new DBController(); 
        }
        return instance; 
    }

    public UserList getUserFromDb ( int id)
    {
        String userId = String.valueOf( id );
        MyDBConnector connector = MyDBConnector.getInstance();
        UserList ul = new UserList();

        if( connector != null)
        {
            try
            {
                ResultSet rs = connector.exec( String.format( "SELECT LOGIN, PASSWORD, USER_ID, STATE from USERS WHERE USER_ID = %s", userId));

                if( rs == null )
                {
                    return null;
                }

                User u;
                while( rs.next() == true)
                {
//                  System.out.println  (
//                      String.format( "%s %s %s %s",
//                      rs.getString( "USER_ID"),
//                      rs.getString( "LOGIN"),
//                      rs.getString( "PASSWORD"),
//                      rs.getString( "STATE"))
//                                        );
                    u = new User(
                                    rs.getString( "USER_ID"),
                                    rs.getString( "LOGIN"),
                                    rs.getString( "PASSWORD"),
                                    rs.getString( "STATE")
                                );

                    ul.addUser( u);
                }
                return ul;
            } catch( SQLException e)
            {
                System.err.println( "Error: text: " + e.toString() + " ." );
            }
        } else
        {
            System.err.println( "Error: db connection error." );
        }
        return null;
    }

    public UserList getUserFromDb ( String Login, String Password )
    {
        MyDBConnector connector = MyDBConnector.getInstance();
        UserList ul = new UserList();

        if( connector != null)
        {
            try
            {
                ResultSet rs = connector.exec( String.format( "SELECT LOGIN, PASSWORD, USER_ID, STATE FROM USERS WHERE LOGIN=%s AND PASSWORD=%s", Login, Password) );
                User u;
                while( rs.next() == true )
                {

//                  System.out.println    (
//                    String.format( "%s %s %s %s",    
//                          rs.getString( "USER_ID"),
//                          rs.getString( "LOGIN"),
//                          rs.getString( "PASSWORD"),
//                          rs.getString( "STATE"))
//                                          );

                    u = new User(   rs.getString( "USER_ID" ),
                                    rs.getString( "LOGIN" ),
                                    rs.getString( "PASSWORD" ),
                                    rs.getString( "STATE" ) );
                    ul.addUser( u);
                }
                return ul;
            } catch( SQLException e)
            {
                System.err.println( "Error: text: " + e.toString() + " ." );
            }
        } else
        {
            System.err.println( "Error: db connection error." );
        }
        return null;
    }

    public UserList getFrendListFromDb ( User user )
    {
        MyDBConnector connector = MyDBConnector.getInstance();
        if( connector != null)
        {
            try
            {
                UserList ul = new UserList(); 

                String id=user.getUserId();
                ResultSet rs = 
                connector.exec( String.format( " SELECT PASSWORD, LOGIN, STATE, USER_ID FROM USERS WHERE USER_ID IN (SELECT FREND_ID FROM FREND_LIST WHERE USER_ID = %s)", id ));

                if( rs == null ) 
                {
                    System.out.println( "Error: ResultSet is null. "); 
                } else 
                {
                    System.out.println( "IsFirst = " + rs.isFirst() + " ."); 
                }

                User u;
                while( rs.next())
                {
                    u = new User(
                                    rs.getString("USER_ID"),
                                    rs.getString("LOGIN"),
                                    rs.getString("PASSWORD"),
                                    rs.getString("STATE")
                                );
                    ul.addUser( u);
                }
                return ul;
            } catch( SQLException e ) {
                System.err.println( "Error: text: " + e.toString() + " ." );
            }
        } else
        {
            System.err.println( "Error: db connection error." );
        }
        return null; 
    }

    public boolean insertUserToDb ( User user)
    {
        MyDBConnector connector = MyDBConnector.getInstance();
        if( connector != null )
        {
            ResultSet rs =  connector.exec  (   String.format(  "INSERT INTO USERS ( USER_ID, LOGIN, PASSWORD, STATE ) VALUES ( %s, '%s', '%s', '%s');",
                                                                user.getUserId(), user.getLogin(), user.getPassword(), user.getState()) );

            try
            {
                if ( rs  == null || ( rs.first() == false ) )
                {
                    return false;
                }
            } catch( SQLException e)
            {
                System.err.println( "Error: err " + e.toString() + ".");
                return false;
            }
        }
        return true;
    }

    public boolean insertFrendListToDb( User user, UserList ul )
    {
        MyDBConnector connector = MyDBConnector.getInstance();
        if( connector != null )
        {
            for( int i = 0; i < ul.size(); i++)
            {
                ResultSet rs =  connector.exec ( String.format( "INSERT INTO FREND_LIST ( USER_ID, FREND_ID ) VALUES ( '%s', '%s' );", 
                                                                 user.getUserId(), ul.user_list.get(i) )); 
                try 
                {
                    if ( rs  == null || ( rs.first() == false ) ) 
                    {
                        return false; 
                    } 
                } catch( SQLException e) 
                { 
                    System.err.println( "Error: err " + e.toString() + "."); 
                    return false; 
                } 
            }
        }
        return true;
    }
};