package Airline.Model.Person;

import java.io.Serializable;

abstract public class Person implements Serializable {

    public String name;

    //constructor
    public Person(String name) {
        this.name = name;
    }
}
