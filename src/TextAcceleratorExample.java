import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TextAcceleratorExample {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception evt) {
        }

        JLabel l;
        JTextField t;
        JButton b;
        JFrame f = new JFrame("Text Accelerator Example");
        Container cp = f.getContentPane();
        cp.setLayout(new GridBagLayout());
        //cp.setBackground(UIManager.getColor("control"));
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(2, 2, 2, 2);
        c.anchor = GridBagConstraints.EAST;

        cp.add(new JLabel("Name:", SwingConstants.RIGHT), c);
        cp.add(l = new JLabel("House/Street:", SwingConstants.RIGHT), c);
        l.setDisplayedMnemonic('h');
        cp.add(l = new JLabel("City:", SwingConstants.RIGHT), c);
        l.setDisplayedMnemonic('c');
        cp.add(l = new JLabel("State/County:", SwingConstants.RIGHT), c);
        l.setDisplayedMnemonic('s');
        cp.add(l = new JLabel("Zip/Post code:", SwingConstants.RIGHT), c);
        l.setDisplayedMnemonic('z');
        cp.add(l = new JLabel("Telephone:", SwingConstants.RIGHT), c);
        l.setDisplayedMnemonic('t');
        cp.add(b = new JButton("Clear"), c);
        b.setMnemonic('l');

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('n');
        c.gridx = 1;
        c.gridy = GridBagConstraints.RELATIVE;
        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('h');
        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('c');
        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('s');
        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('z');
        cp.add(t = new JTextField(35), c);
        t.setFocusAccelerator('t');
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        cp.add(b = new JButton("OK"), c);
        b.setMnemonic('o');

        f.pack();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}
