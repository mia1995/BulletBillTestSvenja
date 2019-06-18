package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static sample.Mover.scene;

public class Main extends Application {

    Mover mover;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mover = new Mover();

        //Reibungskoeffizient
        double c = 0.01;

        PVector gravity = new PVector(0,0.981*mover.mass);
        PVector friction = mover.velocity;
        friction.mult(-1);
        friction.normalize();
        friction.mult(c);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

        mover.applyForce(gravity);
        mover.applyForce(friction);
        mover.draw();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
