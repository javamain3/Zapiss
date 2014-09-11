/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import msg.library.FrendList;

import msg.library.InitLib;
import msg.library.User;
import msg.library.UserList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author alex-user
 */
public class NewEmptyJUnitTest 
{
    public NewEmptyJUnitTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {

    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {

    }

    @Test
    public void func1()
    {
        InitLib.Init();
        
        
        User us = new User( "1", "user1", "user", "123321", "1" );
        
        FrendList ul=new FrendList();
        ul.addUser(us);
        ul.size();
        
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
