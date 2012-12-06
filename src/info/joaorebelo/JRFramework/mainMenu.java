/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRFramework;

import info.joaorebelo.JRjavaUtils.ui.JRString;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * main menu
 * @author joao
 */
public class mainMenu extends JMenuBar{
 
    /**
     * i18n resources
     */
    private i18n _i18n;
    
    /**
     *   mainLayout, the class that have the methods to be invoked
     */
    protected mainLayout ml;
    
    public mainMenu(mainLayout ml, i18n i18n){
        this._i18n = i18n;
        this.ml = ml;
        this.createFileMenu();
        this.createTableMenu();
    }
    
    /**
     * Creat the file menu
     */
    private void createFileMenu(){
        try{
            
            JMenu fileMenu = new JMenu(JRString.ucfirst(this._i18n.getString("file")));            
            fileMenu.setMnemonic(KeyEvent.VK_F);
            
            JMenuItem _exit = new JMenuItem(JRString.ucfirst(this._i18n.getString("exit")), 
                               new ImageIcon(getClass().getResource("/resources/Extras_16_16/Shutdown.png")));
            
            _exit.setMnemonic(KeyEvent.VK_E);
            
            _exit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            
            fileMenu.add(_exit);
            
            this.add(fileMenu);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Create tables menu
     */
    private void createTableMenu(){
        try{
            JMenu tableMenu = new JMenu(JRString.ucfirst(this._i18n.getString("tables")));
            tableMenu.setMnemonic(KeyEvent.VK_T);
            
            JMenuItem actor = new JMenuItem(JRString.ucfirst(this._i18n.getString("actors")),
                                new ImageIcon(getClass().getResource("/resources/Extras_16_16/User.png"))
                                  );
            
            actor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      ml.getTableActor();
                }
            });
            actor.setMnemonic(KeyEvent.VK_A);
            
            tableMenu.add(actor);
            this.add(tableMenu);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
