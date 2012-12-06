/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;

import javax.swing.Icon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 *
 * @author joao
 */
public class JRtabCloseIcon implements Icon {

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.drawLine(6, 6, getIconWidth() - 7, getIconHeight() - 7);
        g.drawLine(getIconWidth() - 7, 6, 6, getIconHeight() - 7);
    }

    @Override
    public int getIconWidth() {
        return 21;
    }

    @Override
    public int getIconHeight() {
        return 21;
    }
}
