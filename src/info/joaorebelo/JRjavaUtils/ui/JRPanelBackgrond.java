/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;

import info.joaorebelo.JRjavaUtils.table.JRtableNavigation;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author joao
 */
public class JRPanelBackgrond extends JPanel{
    
    Image img;
    
    /**
     * Create a JPanel with a background image
     * @param filePath path for image file
     */
    public JRPanelBackgrond(String filePath){
        this._setBgImage(filePath);
    }

    /**
     * Create a JPanel with a background image
     * @param filePath path for image file
     * @param layout layout manager
     */
    public JRPanelBackgrond(String filePath, LayoutManager layout){
        this._setBgImage(filePath);
        this.setLayout(layout);
    }
    
    /**
     * Set the Image for background
     * @param filePath 
     */
    private void _setBgImage(String filePath){
                try{
            this.img = ImageIO.read(getClass().getResourceAsStream(JRtableNavigation.ICON_PATH + "Background.png"));
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), this);
    }
    
}
