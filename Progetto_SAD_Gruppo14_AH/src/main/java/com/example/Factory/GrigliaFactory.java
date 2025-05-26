package com.example.Factory;

import com.example.Model.Figura;
import com.example.Model.Griglia;
import javafx.scene.paint.Color;

public class GrigliaFactory implements FiguraFactory{

    public Figura creaFigura(double nRighe, double nColonne, double x2, double y2, Color strokeColor, Color fillColor){
        return new Griglia(nRighe, nColonne, x2, y2,strokeColor, fillColor = null);
    }

}
