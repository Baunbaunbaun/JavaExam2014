package Airline.Model.Person;

import java.io.Serializable;

abstract public class Person implements Serializable{

    public final String name;

    public Person(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return (this.name);
    }
}
