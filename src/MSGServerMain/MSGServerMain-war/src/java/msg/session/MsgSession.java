/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.session;

/*  *
    *
    * @author alex-user
    */

public class MsgSession 
{
    public static String getTable()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("<table>\n");
        for( int i=0 ; i < 100 ; i++ )
        {
            sb.append("<tr>");
            sb.append("<td>");
                sb.append( i );
            sb.append("</td>");
            sb.append("<td>");
                sb.append( i + "123");
            sb.append("</td>");
            sb.append("</tr>\n");
        }
        
        sb.append("</table>\n");
        return sb.toString();
    }
}
