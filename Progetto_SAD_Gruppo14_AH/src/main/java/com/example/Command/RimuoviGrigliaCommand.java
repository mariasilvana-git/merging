package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Griglia;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.scene.paint.Color;

public class RimuoviGrigliaCommand implements Command{



    public void execute(){
        LavagnaView.getInstance().rimuoviGriglia();
    }


}
