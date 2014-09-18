/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.library;

/**
 *
 * @author alex-user
 */
public class InitLib {
    public static void Init()
    {
        MyDBConnector con = MyDBConnector.getInstance();
        // настройка базы данных
        con.connect( "jdbc:derby://localhost:1527/MSGDataBase", "org.apache.derby.jdbc.ClientDriver" ,"user1" ,"user1" );
    }
}
