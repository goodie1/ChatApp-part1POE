/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp.pkg1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author phulu
 */
public class messageTest {
    
    public messageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMessageId method, of class message.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        message instance = null;
        String expResult = "";
        String result = instance.getMessageId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class message.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        message instance = null;
        String expResult = "";
        String result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class message.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        message instance = null;
        String expResult = "";
        String result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHash method, of class message.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        message instance = null;
        String expResult = "";
        String result = instance.getHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageID method, of class message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        message instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class message.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        message instance = null;
        int expResult = 0;
        int result = instance.checkRecipientCell();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        message instance = null;
        String expResult = "";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class message.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        message message = null;
        String option = "";
        String expResult = "";
        String result = message.sendMessage(message, option);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class message.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        String expResult = "";
        String result = message.printMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 0;
        int result = message.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeMessage method, of class message.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        message message = null;
        message.storeMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
