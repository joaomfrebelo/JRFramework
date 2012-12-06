/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 *
 * @author joao
 */
public class JRtabCloseButton extends JPanel implements ActionListener {
    
  public JTabbedPane pane;
  
  public JRtabCloseButton(JTabbedPane pane, int index) {
    this.pane = pane;
    setOpaque(false);
    add(new JLabel(
        pane.getTitleAt(index),
        pane.getIconAt(index),
        JLabel.LEFT));
    Icon closeIcon = new JRtabCloseIcon();
    JButton btClose = new JButton(closeIcon);
    btClose.setPreferredSize(new Dimension(
        closeIcon.getIconWidth(), closeIcon.getIconHeight()));
    add(btClose);
    btClose.addActionListener(this);
    pane.setTabComponentAt(index, this);
  }

    @Override
  public void actionPerformed(ActionEvent e) {
    int i = pane.indexOfTabComponent(this);
    if (i != -1) {
      pane.remove(i);
    }
  }
}
