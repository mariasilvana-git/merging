package com.example.View;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.*;
//import com.example.State.FiguraSelezionataManager;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class LavagnaView implements Runnable{


    private static LavagnaView instance;
    private AnchorPane lavagna;
    private Circle handle;
    private Circle handle_1;

    public static LavagnaView getInstance(AnchorPane lavagna) {
        if (instance == null) {
            instance = new LavagnaView(lavagna);
        }
        return instance;
    }


    public LavagnaView(AnchorPane lavagna){

        this.lavagna = lavagna;
        LavagnaModel.getInstance().aggiungiOsservatore(this);
    }


    public void aggiornaLavagna(){

        lavagna.getChildren().clear();

        handle = null;
        handle_1 = null;

        for (Figura f : LavagnaModel.getInstance().getFigure()) {

            System.out.println("Sto aggiornando la lavagna.");

            Node nodo = f.creaNodoJavaFX();

            nodo.setOnMouseClicked(event -> {
                FiguraSelezionataManager.getInstance().set(f);

                if(!((StatoManager.getInstance().getStato())instanceof SelezionaFiguraStato))

                StatoManager.getInstance().setStato(new SelezionaFiguraStato());

            });

            lavagna.getChildren().add(nodo);

        }

        // gestione figura selezionata, handle e toFront()
        if (FiguraSelezionataManager.getInstance().get() != null) {

            Figura f = FiguraSelezionataManager.getInstance().get();

                double hx = Math.max(f.getX1(), f.getX2());
                double hy = Math.max(f.getY1(), f.getY2());

                handle = new Circle(hx, hy, 5, Color.BROWN);

                handle.setCursor(Cursor.SE_RESIZE);

                handle.setOnMousePressed(event -> {

                    StatoManager.getInstance().setStato(new RidimensionaFiguraStato(lavagna));
                    System.out.println("inizio a ridimensionare");

                });

                handle.setOnMouseReleased(event -> {
                    System.out.println("ho ridimensionato");

                });

                lavagna.getChildren().add(handle);
                f.getNodo().toFront();
                handle.toFront();

                // gestione handle spostamento

                double hx_1 = Math.min(f.getX1(), f.getX2());
                double hy_1 = Math.min(f.getY1(), f.getY2());

                handle_1 = new Circle(hx_1, hy_1, 5, Color.GRAY);
                handle_1.setCursor(Cursor.MOVE);
                lavagna.getChildren().add(handle_1);
                handle_1.toFront();

                handle_1.setOnMousePressed(event -> {

                    StatoManager.getInstance().setStato(new SpostamentoFiguraStato());

                });





        }

        }



    @Override
    public void run() {
        aggiornaLavagna();
    }


}
