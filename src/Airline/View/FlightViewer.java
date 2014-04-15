package Airline.View;

import Airline.Model.Flight;
import Airline.Model.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashSet;

public class FlightViewer extends JPanel {

    String label[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"};

    JCheckBox checks[] = new JCheckBox[label.length];

    JList list;

    public FlightViewer() {
        setLayout(new BorderLayout());
        HashSet<Flight> t = Model.dataStore.getAllFlights();
        String[] s = new String[t.size()];
        int k = 0;
        for (Flight f : t) {
            s[k] = f.id;
            k++;
        }


        list = new JList(s);
        JScrollPane pane = new JScrollPane(list);

        //  Format the list and the buttons in a vertical box
        Box rightBox = new Box(BoxLayout.Y_AXIS);
        Box leftBox = new Box(BoxLayout.Y_AXIS);
        Box northBox = new Box(BoxLayout.X_AXIS);

        //  Monitor all list selections
        list.addListSelectionListener(new RadioUpdater());

        for (int i = 0; i < label.length; i++) {
            checks[i] = new JCheckBox("Selection " + i);
            checks[i].setEnabled(false);
            rightBox.add(checks[i]);
        }
        leftBox.add(pane);
        add(rightBox, BorderLayout.CENTER);
        add(leftBox, BorderLayout.WEST);

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        JButton exit = new JButton("Schedule New Flight");
        tb.add(exit);
        northBox.add(tb);
        add(northBox, BorderLayout.NORTH);

    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("Flight Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new FlightViewer());
        frame.pack();
        frame.setVisible(true);
    }

    // Inner class that responds to selection events to update the buttons
    class RadioUpdater implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            //  If either of these are true, the event can be ignored.
            if ((!e.getValueIsAdjusting()) || (e.getFirstIndex() == -1))
                return;

            //  Change the radio button to match the current selection state
            //  for each list item that reported a change.
            for (int i = e.getFirstIndex(); i <= e.getLastIndex(); i++) {
                checks[i].setSelected(((JList) e.getSource())
                        .isSelectedIndex(i));
            }
        }
    }
}
