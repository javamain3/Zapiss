/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package secondtestproject;


import java.util.Hashtable;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


        
    public class SecondTestProject 
    {
        Context ctx;
        public SecondTestProject() {
            Hashtable< String, String> properties = new Hashtable<String,String>(2);
            properties.put( Context.PROVIDER_URL, "iiop://127.0.0.1:3700" );
            properties.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.appserv.naming.S1ASCtxFactory");

            try {
                ctx = new InitialContext( properties );
            }

            catch ( NamingException ex) {
                System.err.println("Error: error " + ex.toString()+ " .");
                ex.printStackTrace();
            }
        }

        public Object lookup(String name)
        {
            try {
                return ctx.lookup(name);
            } catch ( NamingException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static void main(String[] args) 
        {
            SecondTestProject client = new SecondTestProject();
            try
            {
                ConnectionFactory connectionFactory = (ConnectionFactory) client.lookup( "jms/myConnectionFactory" );
                Queue queue = (Queue)client.lookup( "jms/myQueue" );
                javax.jms.Connection connection = connectionFactory.createConnection();
                javax.jms.Session session = connection.createSession( false,Session.AUTO_ACKNOWLEDGE );
                MessageProducer messageProducer = session.createProducer( queue );

                for( int i = 0 ; i < 5 ; i++ )
                {
                    TextMessage message = session.createTextMessage( "It is a message from main class " +  ": " + i );
                    System.out.println( "It come from main class:" + message.getText() );
                    messageProducer.send( message );
                }
            }
            catch( Exception ex) {
                System.err.println( "Error: error " + ex.toString() + " .");
                ex.printStackTrace();
            }
        }
    }
