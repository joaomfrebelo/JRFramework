package info.joaorebelo.JRFramework;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author joao
 */
public class southPanel extends JPanel {

    /**
     * i18n resourses
     */
    protected i18n i18n;
    /**
     * Label to show clock
     */
    public JLabel timeLabel = new JLabel();
    /**
     * time lable width
     */
    private int timeLabelWidth = 100;
    /**
     * Info label
     */
    public JLabel leftLabel = new JLabel();
    /**
     * Labels height
     */
    public static int labelHeight = 20;
    /**
     * Border for labels
     */
    public static Border labelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    /**
     * Label for caps lock sate
     */
    private JLabel capsLockLabel = new JLabel("CAPS LOCK");
    
    /**
     * Width for caps lock label
     */
    private int capsLockLabelWidth = 150;

    /**
     * Get the caps lock state label
     *
     * @return
     */
    public JLabel getCapsLockLabel() {
        return this.capsLockLabel;
    }

    /**
     * Bottom clock
     */
    class setTime extends TimerTask {

        public void run() {
            this.updateHour();
        }


        public setTime updateHour() {
            Date data = new Date();
            SimpleDateFormat format = new SimpleDateFormat(i18n.getString("hour_string"));
            timeLabel.setText(format.format(data));
            return this;
        }
    }

    public southPanel(i18n i18n) {
        this.i18n = i18n;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.createLeftLabel();
        this.createCapsLabel();
        this.createTimeLabel();
    }

    class setCapsLockState extends TimerTask{
        ImageIcon off = new ImageIcon(getClass().getResource("/resources/Extras_16_16/Off.png"));
        ImageIcon on = new ImageIcon(getClass().getResource("/resources/Extras_16_16/On.png"));

        @Override
        public void run() {
            this.set();
        }
        public setCapsLockState set() {
            if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                capsLockLabel.setIcon(on);
                capsLockLabel.setText("CAPS LOCK " + i18n.getString("on").toUpperCase());
            } else {
               capsLockLabel.setIcon(null);
               capsLockLabel.setText("CAPS LOCK " + i18n.getString("off").toUpperCase());
            }
            return this;
        }
    }

    private void createCapsLabel() {
        this.capsLockLabel.setPreferredSize(new Dimension(capsLockLabelWidth, labelHeight));
        this.capsLockLabel.setBorder(labelBorder);
        this.capsLockLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.capsLockLabel);
        Timer t = new Timer("CAPS_LOCK");
        setCapsLockState s = new setCapsLockState();
        t.scheduleAtFixedRate(s.set(), 0, 500);
    }

    /**
     * Create tile label
     */
    private void createTimeLabel() {
        // add time label
        timeLabel.setBorder(labelBorder);
        timeLabel.setPreferredSize(new Dimension(this.timeLabelWidth, labelHeight));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.timeLabel);
        Timer timer1 = new Timer("Hours");
        setTime st = new setTime();
        timer1.scheduleAtFixedRate(st.updateHour(), 0, 1000);
    }

    /**
     * create the left label
     */
    private void createLeftLabel() {
        //add left label
        this.leftLabel.setBorder(labelBorder);
        this.leftLabel.setText("");
        this.add(this.leftLabel);
    }

    /**
     * Resize labels
     *
     * @param c
     */
    public void mekeLabelsSizes() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int w = 0;
        w = tk.getScreenSize().width - 50;
        w -= timeLabelWidth;
        w -= capsLockLabelWidth;
        this.leftLabel.setPreferredSize(new Dimension(w, labelHeight));
    }
}
