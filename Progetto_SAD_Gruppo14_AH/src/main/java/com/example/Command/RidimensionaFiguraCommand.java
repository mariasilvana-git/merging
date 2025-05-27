package com.example.Command;

import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;

public class RidimensionaFiguraCommand implements Command {

    double x2, y2;


    public RidimensionaFiguraCommand(double x2, double y2) {
        this.y2 = y2;
        this.x2 = x2;
    }

    double x1 = FiguraSelezionataManager.getInstance().get().getX1();
    double y1 = FiguraSelezionataManager.getInstance().get().getY1();


    public void execute() {

        if(x2<x1){
            x2 = x1+10;
        }

        if (y2<y1){
            y2 = y1+10;
        }
        LavagnaModel.getInstance().ridimensionaFigura(FiguraSelezionataManager.getInstance().get(), x2, y2);
    }

}
