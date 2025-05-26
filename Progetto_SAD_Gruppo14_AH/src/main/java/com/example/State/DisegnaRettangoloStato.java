package com.example.State;

import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.RettangoloFactory;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DisegnaRettangoloStato implements Stato {

    private double x1, y1;
    private Rectangle figuraTemporanea;
    private AnchorPane lavagna;
    private LavagnaModel model;
    private ColorPicker strokeColor, fillColor;
    private Group figureInserite;


    public DisegnaRettangoloStato(LavagnaView lavagnaView, LavagnaModel model, ColorPicker strokeColor, ColorPicker fillColor) {
        this.lavagna = lavagna;
        this.model = model;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.figureInserite = lavagnaView.getFigureZoomabili();
    }
    @Override

    public void onMousePressed(MouseEvent event) {

        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        x1 = punto.getX();
        y1 = punto.getY();


        figuraTemporanea = new Rectangle(x1, y1, 0, 0);
        figuraTemporanea.setStroke(strokeColor.getValue());
        figuraTemporanea.setFill(fillColor.getValue().deriveColor(1, 1, 1, 0.4));
        figureInserite.getChildren().add((figuraTemporanea));
    }

    @Override
    public void onMouseDragged(MouseEvent e) {

        Point2D punto = figureInserite.sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figuraTemporanea.setX(Math.min(x1, x2));
        figuraTemporanea.setY(Math.min(y1, y2));
        figuraTemporanea.setWidth(Math.abs(x2 - x1));
        figuraTemporanea.setHeight(Math.abs(y2 - y1));
    }


    @Override
    public void onMouseReleased(MouseEvent e) {
        Point2D punto = figureInserite.sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figureInserite.getChildren().remove((figuraTemporanea));

        // Usa Command se vuoi supportare Undo
        Command cmd = new AggiungiFiguraCommand(model, new RettangoloFactory(), x1, y1, x2, y2, strokeColor.getValue(), fillColor.getValue());
        Invoker.getInstance().executeCommand(cmd);

        figuraTemporanea = null;
    }

}
