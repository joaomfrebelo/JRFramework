/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author joao
 */
public class leftMenu extends JPanel{
    
    public JPanel menuGrid = new JPanel(new GridLayout(20,1));
    public JPanel menu = new JPanel();
    
    public leftMenu(){
        this.setLayout(new BorderLayout());
        JButton b1 = new JButton("text");
        b1.setPreferredSize(new Dimension(100, 50));
        menuGrid.add(b1);
        menu.add(new JLabel("menu action"));
        menuGrid.add(menu);
        this.add(menuGrid, BorderLayout.CENTER);
        
    }
    
    
    
    
    
}
