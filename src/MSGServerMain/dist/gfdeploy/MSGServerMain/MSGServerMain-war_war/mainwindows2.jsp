<%-- 
    Document   : mainwindows2
    Created on : 02.09.2014, 16:04:13
    Author     : alex-user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="msg.session.MsgSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <table>
            <tr><td>Логин:</td><td></td></tr>
            <tr><td>Пароль:</td><td></td></tr>
            <tr><td></td><td></td></tr>
            <tr><td></td><td></td></tr>
        </table>

        <%
            
            out.print( MsgSession.getTable() );
            for(int i=0 ; i < 100 ; i++)
            {
                 out.println("<p>A dsf to sadfsa sadfsdf sadfasdf</p>");
            }
        %>

    </body>
</html>
