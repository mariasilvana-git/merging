package com.example.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Griglia extends Figura{

    Group griglia = new Group();


    public Griglia(double nRighe, double nColonne, double x2, double y2, Color strokeColor, Color fillColor) {
        super(nRighe, nColonne, x2, y2, strokeColor, fillColor);
    }

    @Override
    public Node creaNodoJavaFX() {
        double nRighe = x1;
        double nColonne = y1;
        double larghezzaLavagna = x2;
        double altezzaLavagna = y2;
        double spazioTraRighe = altezzaLavagna / nRighe;
        double spazioTraColonne = larghezzaLavagna/nColonne;

        //linee verticali
        for (int i = 1; i < nColonne; i++) {
            double x = i * spazioTraColonne;
            Line line = new Line(x, 0, x, altezzaLavagna);
            line.setStroke(strokeColor);
            line.setStrokeWidth(1);
            griglia.getChildren().add(line);
        }

        //linee orizzonatali
        for (int i = 1; i < nRighe; i++) {
            double y = i * spazioTraRighe;
            Line line = new Line(0, y, larghezzaLavagna,y);
            line.setStroke(strokeColor);
            line.setStrokeWidth(1);
            griglia.getChildren().add(line);
        }


        griglia.setUserData(this);

        return griglia;
    }


}
