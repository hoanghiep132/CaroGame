package caro;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anims {

    public static Node getAtoms1(int number){
        Circle circle = new Circle(30,30f,15);
        circle.setFill(Color.RED);
        circle.setEffect(new Glow());
        Group group = new Group();
        group.getChildren().add(circle);
        return group;
    }


    public static Node getAtoms2(int number){
        Circle circle = new Circle(30,30f,15);
        circle.setFill(Color.BLUE);
        circle.setEffect(new Glow());
        Group group = new Group();
        group.getChildren().add(circle);
        return group;
    }
}
