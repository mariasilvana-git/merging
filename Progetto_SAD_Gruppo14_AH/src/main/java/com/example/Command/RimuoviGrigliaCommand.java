package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Griglia;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;

public class RimuoviGrigliaCommand implements Command{

    private double x2,y2;
    private Color strokeColor;
    private FiguraFactory figuraFactory;

    public RimuoviGrigliaCommand(FiguraFactory figuraFactory, double x2, double y2, Color strokeColor) {
        this.figuraFactory = figuraFactory;
        this.strokeColor = strokeColor;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void execute(){
        Griglia griglia = (Griglia) figuraFactory.creaFigura(0, 0, x2 , y2, Color.LIGHTGRAY, null);
        LavagnaModel.getInstance().rimuoviGriglia(griglia);
    }


}
