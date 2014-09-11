/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testproject1;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Resource;
import javax.jms.*;





/**
 *
 * @author alex-user
 */
public class Main {

    static public class MyTest{
        public MyTest()
        {
        }
        @Resource(lookup = "jms/ConnectionFactory", type=javax.jms.ConnectionFactory.class)
        public ConnectionFactory connectionFactory;
        @Resource(lookup = "jms/Queue", type=javax.jms.Queue.class)
        public Queue queue;
        @Resource(lookup = "jms/Topic", type=javax.jms.Topic.class)
        public Topic topic;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a=3;
        //MyTest test= new MyTest();
      
        final int NUM_MSGS;
        String destType = " ";//args[0];
        System.out.println("Destination type is " + destType);
        if ( ! ( destType.equals("queue") || destType.equals("topic") ) ) { 
            System.err.println("Argument must be \"queue\" or " + "\"topic\"");
            System.exit(1);
        }
        if (args.length == 2){
            NUM_MSGS = (new Integer("4")).intValue();
        } 
        else { 
            NUM_MSGS = 1;
        }
        Destination dest = null;
        try { 
            if (destType.equals("queue")) { 
                dest = (Destination) queue;
            } else { 
                dest = (Destination) topic;
            }
        } 
        catch (Exception e) {
            System.err.println("Error setting destination: " + e.toString()); 
            e.printStackTrace(); 
            System.exit(1);
        }
     }
}
