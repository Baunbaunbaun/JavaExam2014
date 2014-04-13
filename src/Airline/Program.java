package Airline;

import Airline.DataStore.FileDataStore;
import Airline.Models.Aircrafts.Aircraft;
import Airline.Models.Aircrafts.Concorde;
import Airline.Models.Flight;

import java.util.concurrent.locks.Condition;

public class Program {

    public static FileDataStore ds = FileDataStore.load("/Users/baunbaun/Desktop/data.ser");


    public static void main(String[] args) {

        Aircraft concorde = new Concorde("MyAirPlaneConcorde");

        Flight flight = new Flight(335, concorde);

        flight.save();

        System.out.println(ds.flights.size());

    }
}
