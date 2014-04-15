package Airline.DataStore;

import Airline.Model.Flight;
import Airline.Model.Person.Person;

import java.io.*;
import java.util.HashSet;

public class FileDataStore implements IDataStorage, Serializable {

    private String path;

    private HashSet<Flight> flights;

    public FileDataStore(String path) {
        this.path = path;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            FileDataStore loadedDataStore = (FileDataStore) ois.readObject();
            this.flights = loadedDataStore.flights;
        } catch (Exception ex) {
            this.flights = new HashSet<Flight>();

        }
    }

    @Override
    public boolean saveFlight(Flight flight) {

        this.flights.add(flight);
        return this.write();
    }

    @Override
    public HashSet<Flight> getAllFlights() {
        // Make new copy of collection
        return new HashSet<Flight>(this.flights);
    }

    @Override
    public HashSet<Flight> getAllFlights(Person person) {
        HashSet<Flight> flightsWithPerson = new HashSet<Flight>();
        for (Flight f : this.flights) {
            if (f.getBookings().containsKey(person)) {
                flightsWithPerson.add(f);
            }
        }
        return flightsWithPerson;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return this.flights.remove(flight);
    }

    private boolean write() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path));
            oos.writeObject(this);
            oos.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}