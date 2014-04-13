package Airline.DataStore;

import Airline.Models.Flight;

import java.io.*;
import java.util.HashMap;

public class FileDataStore implements IDataStorage, Serializable {

    private String file;

    private FileDataStore(String file) {
        this.file = file;
    }

    public HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();

    public void createFlight(Flight flight) {

        this.flights.put(flight.id, flight);

        this.write();
    }

    public void write() {

        try {

            FileOutputStream fout = new FileOutputStream(this.file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static FileDataStore load(String file) {

        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            FileDataStore data = (FileDataStore) ois.readObject();
            return data;
        } catch (Exception ex) {
            return new FileDataStore(file);
        }
    }




    /*public Collection<Flight> readFlight() {

        return this.flights.keySet();
    }

    public void updateFlight() {

    }

    public void deleteFlight() {
        flights.clear();
    }*/
}

