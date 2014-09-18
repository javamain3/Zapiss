/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alex-user
 */

public class User 
{
    public enum user_state
    {
        online ,
        off_line ,
        out_work 
    }

    private String login;
    private String password;
    private String userId;
    private String state;
    private FrendList frendList;

    public User (
                String _user_id,
                String _login,
                String _password,
                String _state
                ) 
    {
 
        this.setLogin( login);
        this.setPassword( password);
        this.setState( state);
        this.setUserId( userId);
    }

    public String toString() {
        return String.format(   "(%s, %s, %s, %s)",
                                this.getUserId(),
                                this.getLogin(),
                                this.getPassword(),
                                this.getState()
                            );
    }

    public User() {
    }

    public User( String userId) {
        this.userId = userId;
    }

    public List<User> getFrendList() {
        User user = new User();
        List<User> user_lst = new ArrayList<User>();
        return user_lst;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState( String state) {
        this.state = state;
    }
}