package Airline.Controller;

import Airline.Model.Model;
import Airline.View.View;


public class Controller implements java.awt.event.ActionListener {


    Model model;
    View view;

    public Controller() {
        System.out.println("Controller()");
    } //Controller()


    public void actionPerformed(java.awt.event.ActionEvent e) {

        System.out.println("Controller: acting on Model");
        model.incrementValue();
    } //actionPerformed()


    public void addModel(Model m) {
        this.model = m;
    }

    public void addView(View v) {
        System.out.println("Controller: adding view");
        this.view = v;
    }

    public void initModel(int x) {
        model.setValue(x);
    }

}