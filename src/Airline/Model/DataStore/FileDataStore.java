package Airline.Model.DataStore;

import Airline.Model.Flight;
import Airline.Model.Person.Person;

import java.io.*;
import java.util.HashSet;

public class FileDataStore implements IDataStorage, Serializable {

    private final String path;

    private HashSet<Flight> flights;

    public FileDataStore(final String path) {
        this.path = path;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path));
            FileDataStore loadedDataStore = (FileDataStore) ois.readObject();
            this.flights = loadedDataStore.flights;
        } catch (Exception ex) {
            this.flights = new HashSet<Flight>();

        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    writeToDisk();
                }
            });
        }
    }

    @Override
    public boolean saveFlight(Flight flight) {
        return this.flights.add(flight);
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

    @Override
    public String getAllFlightAsString() {
        return this.flights.toString();
    }

    private boolean writeToDisk() {
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