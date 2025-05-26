package com.example.Model;

import com.example.State.FiguraSelezionataManager;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Ellisse extends Figura {

    public Ellisse(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor); // salva tutte le coordinate nella superclasse
    }

    @Override
    public Node creaNodoJavaFX() {
        // Calcola centro e raggi
        double centerX = (x1 + x2) / 2;
        double centerY = (y1 + y2) / 2;
        double radiusX = Math.abs(x2 - x1) / 2;
        double radiusY = Math.abs(y2 - y1) / 2;

        Ellipse e = new Ellipse(centerX, centerY, radiusX, radiusY);

        this.setNodo(e);

        e.setStrokeWidth(1);
        e.setStroke(strokeColor);
        e.setFill(fillColor);
        e.setUserData(this);


        if (FiguraSelezionataManager.getInstance().get() == this) {
            e.setEffect(new DropShadow(20, Color.GREY));
        }

        return e;
    }
}
