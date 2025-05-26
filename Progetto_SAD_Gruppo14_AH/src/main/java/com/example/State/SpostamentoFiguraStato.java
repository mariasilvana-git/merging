package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.SpostamentoFiguraCommand;
import com.example.Model.LavagnaModel;
import javafx.scene.input.MouseEvent;

public class SpostamentoFiguraStato implements Stato {

    FiguraSelezionataManager figuraSelezionataManager = FiguraSelezionataManager.getInstance();

    @Override
    public void onMousePressed(MouseEvent event){

    }

    @Override
    public void onMouseDragged(MouseEvent event){

    }

    @Override
    public void onMouseReleased(MouseEvent event){

        Command cmd = new SpostamentoFiguraCommand(figuraSelezionataManager.get(),event.getX(),event.getY());
        Invoker.getInstance().executeCommand(cmd);
        StatoManager.getInstance().setStato(new SelezionaFiguraStato());


    }
}
