package Airline.Model.DataStore;

import Airline.Model.Flight;
import Airline.Model.Person.Person;

import java.util.HashSet;

public interface IDataStorage {

    public boolean saveFlight(Flight flight);

    public HashSet<Flight> getAllFlights();

    public HashSet<Flight> getAllFlights(Person person);

    public boolean deleteFlight(Flight flight);

    public String getAllFlightAsString();

}