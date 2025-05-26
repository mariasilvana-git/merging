package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.Griglia;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;

import java.util.List;

public class AggiungiGrigliaCommand implements Command {

    private LavagnaModel lavagnaModel;
    private FiguraFactory figuraFactory;
    private double x2, y2;
    private int nRighe, nColonne;
    private Color strokeColor;
    private Figura griglia;

    public AggiungiGrigliaCommand(LavagnaModel lavagnaModel, FiguraFactory factory,int nRighe, int nColonne, double x2, double y2, Color strokeColor) {
        this.lavagnaModel = lavagnaModel;
        this.figuraFactory = factory;
        this.nRighe = nRighe;
        this.nColonne = nColonne;
        this.x2 = x2; // larghezza lavagna
        this.y2 = y2; // altezza lavagna
        this.strokeColor= strokeColor;

    }

    public void execute() {
        griglia = figuraFactory.creaFigura(nRighe, nColonne, x2 , y2, strokeColor, null);
        lavagnaModel.aggiungiGriglia((Griglia) griglia);
    }

}
