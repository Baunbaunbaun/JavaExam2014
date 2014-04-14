package Airline;

import Airline.DataStore.FileDataStore;
import Airline.Models.Aircrafts.Aircraft;
import Airline.Models.Aircrafts.Concorde;
import Airline.Models.Airport;
import Airline.Models.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {

    public static FileDataStore ds = FileDataStore.load("/Users/baunbaun/Desktop/data.ser");

    public static void main(String[] args) {

        Aircraft concorde1 = new Concorde("MyAirPlaneConcorde");

        Date departure = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String departureDay = "01.01.2016";

        try {
            departure = format.parse(departureDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Flight flight = new Flight(departure, concorde1, Airport.DUBLIN, Airport.HOMS);

        flight.save();

        System.out.println(ds.flights.size());

        System.out.println(flight);
    }
}
