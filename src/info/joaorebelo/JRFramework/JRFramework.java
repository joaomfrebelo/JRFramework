/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRFramework;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author joao
 */
public class JRFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        // TODO code application logic here
        i18n i18n = new i18n("en", "US");
        JFrame maneLayout = new mainLayout(i18n);
        // maneLayout.setVisible(true);



    }
}
