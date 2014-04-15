package Airline;

import Airline.Controller.Controller;
import Airline.Model.Model;
import Airline.View.View;

public class Application {

    private int start_value = 10;

    public Application() {


        Model myModel = new Model();
        View myView = new View();
        myModel.addObserver(myView);

        Controller myController = new Controller();
        myController.addModel(myModel);
        myController.addView(myView);
        myController.initModel(start_value);
        myView.addController(myController);

    }

    public static void main(String[] args) {

        Application app = new Application();

    }

}
