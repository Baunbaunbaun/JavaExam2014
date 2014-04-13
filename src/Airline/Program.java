package Airline;

import Airline.DataStore.FileDataStore;
import Airline.Models.Aircrafts.Aircraft;
import Airline.Models.Aircrafts.Concorde;
import Airline.Models.Flight;

public class Program {

    public static FileDataStore ds = FileDataStore.load("/Users/baunbaun/Desktop/data.ser");

    public static void main(String[] args) {

        Aircraft concorde1 = new Concorde("MyAirPlaneConcorde");

        Flight flight = new Flight(concorde1);

        flight.save();

        System.out.println(ds.flights.size());

    }
}
