
package msg.library;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class FrendList {

    MyDBConnector connector;

    private List< String> frendIdList;
    private String userId;

    public FrendList()
    {
        frendIdList = new ArrayList();
        userId = new String();
    }
    
    public void getFromDBFrendList( String user_id )
    {
        userId = user_id;
        connector = MyDBConnector.getInstance();
        if( connector != null)
        {
            try
            {
                if( frendIdList == null)
                {
                    frendIdList = new ArrayList();
                }

                ResultSet rs = connector.exec( String.format( "SELECT FREND_ID, USER_ID FROM \"FREND_LIST\" WHERE USER_ID ='%s';", userId ));
                userId = rs.getString( "USER_ID" );
                while( rs.next() )
                {
                    frendIdList.add( rs.getString( "FREND_ID" ));
                }

            } catch( SQLException e)
            {
                System.err.println( "Error: text: " + e.toString() + " ." );
            }
        } else
        {
            System.err.println( "Error: db connection error." );
        }
    }

    public String getUserId() 
    {
        return this.userId;
    }

    public List<String> getListId() 
    {
        return this.frendIdList;
    }

    public boolean InserToDB( String frend_id, String user_id)
    {
        if( this.frendIdList == null || this.frendIdList.size() == 0 )
            return false;

        connector = MyDBConnector.getInstance();
        if( connector != null)
        {
            for(int i=0; i< this.frendIdList.size(); i++)
            {
                ResultSet rs = connector.exec   
                            (
                String.format   (
                                "INSERT INTO FREND_LIST ( USER_ID, FREND_ID) VALUES ( '%s', '%s');",
                                this.userId,
                                this.frendIdList.get( i)
                                )
                            );
            }
        }
        return true;
    }
}
