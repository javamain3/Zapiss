/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.library;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


/**
 * @author alex-user
 */

public class UserList
{
    public List<User> user_list;

    public UserList(){
        user_list = new ArrayList<User>();
    }

    public void addUser(User user) {
        user_list.add(user);
    }

    public void deleteUser(User user)
    {
        user_list.remove(user);
    }

    public int size()
    {
        return user_list.size();
    }

}