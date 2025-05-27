package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.RidimensionaFiguraCommand;
import com.example.Model.*;
import com.example.Strategy.EllisseTemporaneoStrategy;
import com.example.Strategy.FiguraTemporaneaStrategy;
import com.example.Strategy.RettangoloTemporaneoStrategy;
import com.example.Strategy.SegmentoTemporaneoStrategy;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class RidimensionaFiguraStato implements Stato {

    private double x1, y1;

    Figura figura = FiguraSelezionataManager.getInstance().get();

    double x1_init = FiguraSelezionataManager.getInstance().get().getX1();
    double y1_init = FiguraSelezionataManager.getInstance().get().getY1();

    Node figuraTemporaneaFX = null;
    FiguraTemporaneaStrategy strategy = null;

    private AnchorPane lavagna;



    public RidimensionaFiguraStato() {

        if (figura instanceof Rettangolo) strategy = new RettangoloTemporaneoStrategy();
        else if (figura instanceof Ellisse) strategy = new EllisseTemporaneoStrategy();
        else if (figura instanceof Segmento) strategy = new SegmentoTemporaneoStrategy();
    }

    @Override
    public void onMousePressed(MouseEvent event) {

        Point2D punto = LavagnaView.getInstance().getFigureZoomabili().sceneToLocal(event.getX(), event.getY());
        double x1 = punto.getX();
        double y1 = punto.getY();

        figuraTemporaneaFX = strategy.crea(x1, y1);
        LavagnaView.getInstance().getFigureZoomabili().getChildren().add(figuraTemporaneaFX);
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        Point2D punto = LavagnaView.getInstance().getFigureZoomabili().sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();


        if(x2 > FiguraSelezionataManager.getInstance().get().getX1() && y2 > FiguraSelezionataManager.getInstance().get().getY1()) {
            strategy.aggiorna(figuraTemporaneaFX, x1_init, y1_init, x2, y2);
        }

    }


    @Override
    public void onMouseReleased(MouseEvent e) {

        LavagnaView.getInstance().getFigureZoomabili().getChildren().remove(figuraTemporaneaFX);

        Point2D punto = LavagnaView.getInstance().getFigureZoomabili().sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        Command cmd = new RidimensionaFiguraCommand(x2, y2);
        Invoker.getInstance().executeCommand(cmd);
        StatoManager.getInstance().setStato(new SelezionaFiguraStato());

    }
}
