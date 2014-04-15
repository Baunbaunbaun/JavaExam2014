package Airline.View;


import Airline.Model.Aircraft.Concorde;
import Airline.Model.Airport;
import Airline.Model.Flight;

import javax.swing.*;

public class FlightDetails extends JPanel {

    String[] labels = {"Id: ", "yolo: "};

    public FlightDetails(Flight flight) {
        super(new SpringLayout());
        JLabel idLabel = new JLabel("Id: ", JLabel.TRAILING);
        this.add(idLabel);
        JTextField idTextField = new JTextField(10);
        idLabel.setLabelFor(idTextField);
        this.add(idTextField);
        SpringUtilities.makeCompactGrid(this, 1, 2, 5, 5, 5, 5);
    }

    public static void main(String s[]) throws Flight.FlightNameException {

        Flight flight = new Flight("EB3454", new Concorde("Concorde"), Airport.CAMP_DARBY, Airport.FORT_BENNING, 1);
        JFrame frame = new JFrame("Flight Detail Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new FlightDetails(flight));
        frame.pack();
        frame.setVisible(true);
    }
}
