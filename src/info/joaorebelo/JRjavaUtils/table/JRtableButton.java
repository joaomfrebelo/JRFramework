/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.table;


import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author joao
 */
public class JRtableButton extends JButton{
    
    
    static final String ADD = "add";
    
    static final String DEL = "del";
    
    static final String EDIT = "edit";
    
    static final String REFRESH = "refresh";
    
    static final String SEARCH = "search";
    
    static final String FIRST_PAGE = "first";
    
    static final String LAST_PAGE = "last";
    
    static final String NEXT_PAGE = "next";
    
    static final String PREVIOUS_PAGE = "previous";
    
    /**
     * Define the button type
     */
    public String type;
    
    /**
     * constructur
     * @param JRtable 
     */
    public JRtableButton(){
        this.initComponents();
    }
         
    /**
     * Componentes initialization
     */
    private void initComponents(){
        this.setPreferredSize(new Dimension(20, 20));
    }
    
    /**
     * Set 
     * @param icon path for image 16x16
     */
    public void setButtonIcon(String icon){
        ImageIcon i = new ImageIcon(getClass().getResource(icon));
        this.setIcon(i);
    }
    
    
}
