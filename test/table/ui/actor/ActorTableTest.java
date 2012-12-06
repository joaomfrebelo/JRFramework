/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table.ui.actor;

import info.joaorebelo.JRjavaUtils.table.JRtable;
import info.joaorebelo.JRjavaUtils.table.JRtableDataResult;
import java.awt.event.ActionEvent;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joao
 */
public class ActorTableTest {
    
    public ActorTableTest() {
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

    /**
     * Test of getActoresTable method, of class ActorTable.
     */
    @Test
    public void testGetActoresTable() {
        System.out.println("getActoresTable");
        ActorTable instance = null;
        JRtable expResult = null;
        JRtable result = instance.getActoresTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJRtable method, of class ActorTable.
     */
    @Test
    public void testCreateJRtable() {
        System.out.println("createJRtable");
        ActorTable instance = null;
        instance.createJRtable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalRecords method, of class ActorTable.
     */
    @Test
    public void testGetTotalRecords() {
        System.out.println("getTotalRecords");
        ActorTable instance = null;
        Integer expResult = null;
        Integer result = instance.getTotalRecords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bind method, of class ActorTable.
     */
    @Test
    public void testBind() {
        System.out.println("bind");
        Integer page = null;
        Integer numRecords = null;
        String orderBy = "";
        String orderType = "";
        ActorTable instance = null;
        JRtableDataResult expResult = null;
        JRtableDataResult result = instance.bind(page, numRecords, orderBy, orderType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddRecordButoanAction method, of class ActorTable.
     */
    @Test
    public void testAddRecordButoanAction() {
        System.out.println("AddRecordButoanAction");
        ActorTable instance = null;
        instance.AddRecordButoanAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRecordButoanAction method, of class ActorTable.
     */
    @Test
    public void testUpdateRecordButoanAction() {
        System.out.println("updateRecordButoanAction");
        ActorTable instance = null;
        instance.updateRecordButoanAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delRecordButoanAction method, of class ActorTable.
     */
    @Test
    public void testDelRecordButoanAction() {
        System.out.println("delRecordButoanAction");
        ActorTable instance = null;
        instance.delRecordButoanAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnsName method, of class ActorTable.
     */
    @Test
    public void testGetColumnsName() {
        System.out.println("getColumnsName");
        ActorTable instance = null;
        Vector expResult = null;
        Vector result = instance.getColumnsName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnsNameI18n method, of class ActorTable.
     */
    @Test
    public void testGetColumnsNameI18n() {
        System.out.println("getColumnsNameI18n");
        ActorTable instance = null;
        Vector expResult = null;
        Vector result = instance.getColumnsNameI18n();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableTitle method, of class ActorTable.
     */
    @Test
    public void testGetTableTitle() {
        System.out.println("getTableTitle");
        ActorTable instance = null;
        String expResult = "";
        String result = instance.getTableTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class ActorTable.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        ActorTable instance = null;
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecord method, of class ActorTable.
     */
    @Test
    public void testAddRecord() {
        System.out.println("addRecord");
        ActorTable instance = null;
        boolean expResult = false;
        boolean result = instance.addRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRecord method, of class ActorTable.
     */
    @Test
    public void testUpdateRecord() {
        System.out.println("updateRecord");
        ActorTable instance = null;
        boolean expResult = false;
        boolean result = instance.updateRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delRecord method, of class ActorTable.
     */
    @Test
    public void testDelRecord() {
        System.out.println("delRecord");
        ActorTable instance = null;
        boolean expResult = false;
        boolean result = instance.delRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchButtonAction method, of class ActorTable.
     */
    @Test
    public void testSearchButtonAction() {
        System.out.println("searchButtonAction");
        ActorTable instance = null;
        instance.searchButtonAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJRtable method, of class ActorTable.
     */
    @Test
    public void testGetJRtable() {
        System.out.println("getJRtable");
        ActorTable instance = null;
        JRtable expResult = null;
        JRtable result = instance.getJRtable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultCellRender method, of class ActorTable.
     */
    @Test
    public void testSetDefaultCellRender() {
        System.out.println("setDefaultCellRender");
        ActorTable instance = null;
        instance.setDefaultCellRender();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
