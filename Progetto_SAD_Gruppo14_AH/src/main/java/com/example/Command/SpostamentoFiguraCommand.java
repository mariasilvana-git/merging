package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;

public class SpostamentoFiguraCommand implements Command {

    private Figura figura;
    private double x1;
    private double y1;

    public SpostamentoFiguraCommand(Figura figura, double x1, double y1) {
        this.figura = figura;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override

    public void execute() {

        LavagnaModel.getInstance().spostaFigura(figura, x1, y1);


    }
}
