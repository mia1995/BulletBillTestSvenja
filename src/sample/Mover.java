package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Mover {

    PVector location;
    PVector velocity;
    PVector acceleration;

    Bahn element1;

    double mass;
    int radius = 16;
    public static Pane container = new Pane();

    public static Scene scene = new Scene(container, 1000,800);

    double height = 2*radius+2;
    double width = 200.0;

    Pane pane1 = new Pane();
    Pane pane2 = new Pane();
    Circle ball = new Circle(radius, Color.BLUE);


    public Mover(){
        location = new PVector(width - radius,height - radius-1);
        velocity = new PVector(0,0);
        acceleration = new PVector(-0.001,0.01);
        mass = 5;
    }

    public void applyForce(PVector force){
        PVector f = force;
        f.div(mass);
        acceleration.add(f);
    }

    public void draw(){
        element1 = new Bahn(0,20,0,40,0,10);

        pane1.setPrefSize(width,height);
        pane1.setLayoutX(800);
        Rectangle rectangle1 = new Rectangle(width,height, Color.WHITE);
        rectangle1.setStroke(Color.GRAY);
        pane1.getChildren().addAll(rectangle1);

        pane2.setPrefSize(width, height);
        pane2.setLayoutX(650);
        pane2.setLayoutY(150);
        pane2.getTransforms().add(new Rotate(-45,0,0));
        Rectangle rectangle2 = new Rectangle(width,height, Color.RED);
        rectangle2.setStroke(Color.GRAY);
        pane2.getChildren().addAll(rectangle2);

        ball.relocate(location.x, location.y);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                velocity.add(acceleration);
                location.add(velocity);
                acceleration.mult(1);

                ball.setLayoutX(location.x);
                ball.setLayoutY(location.y);

                System.out.println("Location: (" + location.x + "; " + location.y + ")");
                System.out.println("Velocity: (" + velocity.x + "; " + velocity.y + ")");
                System.out.println("Acceleration: (" + acceleration.x + "; " + acceleration.y + ")");
                System.out.println("Bounds: Bahn = (" + width + "; " + height + ")");
                System.out.println("Bounds: Pane2 = (" + pane2.getLayoutX() + "; " + pane2.getLayoutY() + ")" + " || Container = (" + container.getBoundsInLocal().getMaxX() + ", " + container.getBoundsInLocal().getMaxY() + ")");

                checkEdges();
            }
        }));
        timeline.play();

        pane1.getChildren().add(ball);
        container.getChildren().addAll(pane1, pane2);
    }

    public void checkEdges(){
        if(location.x > (width)){
            velocity.x *= -1;
            location.x = width;
        } else if(location.x < radius){
            //velocity.x *= -1;
            //location.x = radius;
            //acceleration.y *= 2;
            location.x = pane2.getLayoutX()+radius;
            pane2.getChildren().add(ball);
        }

        if(location.y > (height - radius)){
            velocity.y *= -0.5;
            location.y = height - radius;
        }
    }
}
