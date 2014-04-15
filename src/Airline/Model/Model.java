package Airline.Model;

import Airline.Model.DataStore.FileDataStore;

public class Model extends java.util.Observable {

    private int counter;
    public static FileDataStore dataStore = new FileDataStore("/tmp/data.ser");

    public Model() {
        setValue(0);
    }

    public void setValue(int value) {
        this.counter = value;
        System.out.println("Model init: counter = " + counter);
        setChanged();
        notifyObservers(this.counter);
    }

    public void incrementValue() {
        ++this.counter;
        System.out.println("Model     : counter = " + counter);
        setChanged();
        notifyObservers(counter);
    }
}
