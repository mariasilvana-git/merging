package com.example.progetto_sad_gruppo14_ah;

import com.example.Command.*;
import com.example.Factory.*;
import com.example.Model.LavagnaModel;
import com.example.State.DisegnaRettangoloStato;
import com.example.View.LavagnaView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.example.State.*;
import javafx.scene.paint.Color;


public class HelloController{

    @FXML
    private AnchorPane lavagna;
    @FXML
    private TextField nRighe;
    @FXML
    private TextField nColonne;
    @FXML
    private ToggleButton zoom_in;
    @FXML
    private ToggleButton zoom_out;
    @FXML
    private Button resetZoomButton;
    @FXML
    private ToggleButton segmentoButton;
    @FXML
    private ToggleButton ellisseButton;
    @FXML
    private ToggleButton rettangoloButton;
    @FXML
    private ToggleButton grigliaButton;
    @FXML
    private MenuItem salvaConNome;
    @FXML
    private MenuItem caricaFile;

    @FXML
    private ToggleButton selezioneButton;

    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private ColorPicker fillColorPicker;


    private LavagnaModel lavagnaModel;
    private LavagnaView lavagnaView;

    private StatoManager statoManager = StatoManager.getInstance();




    @FXML
    private void initialize() {

        lavagnaModel = LavagnaModel.getInstance();
        lavagnaView = LavagnaView.getInstance(lavagna);

        Invoker.getInstance().executeCommand(new RimuoviGrigliaCommand(new GrigliaFactory(), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY));


        lavagna.heightProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(lavagnaModel, new GrigliaFactory(),Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), (double) newValue, Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                cmd.execute();}
        });

        lavagna.widthProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(lavagnaModel, new GrigliaFactory(),Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), (double) newValue, lavagna.getHeight(), Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                cmd.execute();}
        });

        nRighe.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nRighe = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand(lavagnaModel, new GrigliaFactory(),nRighe, Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    cmd.execute();}
            } catch (NumberFormatException e) {
                System.out.println("Valore non numerico");
            }
        });

        nColonne.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nColonne = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand(lavagnaModel, new GrigliaFactory(), Integer.parseInt(nRighe.getText()), nColonne, lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    cmd.execute();}
            } catch (NumberFormatException e) {
                System.out.println("Valore non numerico");
            }
        });

        zoom_in.setOnAction((e) -> {
            if (zoom_in.isSelected())
                statoManager.setStato(new ZoomInStato(lavagnaView));
            else
                statoManager.setStato(new IdleStato());
        });

        zoom_out.setOnAction((e) -> {
            if (zoom_out.isSelected())
                statoManager.setStato(new ZoomOutStato(lavagnaView));
            else
                statoManager.setStato(new IdleStato());
        });

        resetZoomButton.setOnAction((event) -> {
            Invoker.getInstance().executeCommand(new ResetZoomCommand(lavagnaView));
        });

        rettangoloButton.setOnAction(e -> {
            if (rettangoloButton.isSelected()) {
                statoManager.setStato(new DisegnaRettangoloStato(lavagnaView, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {

                statoManager.setStato(new IdleStato());
            }
        });


        segmentoButton.setOnAction(e -> {
            if (segmentoButton.isSelected()) {
                statoManager.setStato(new DisegnaSegmentoStato(lavagnaView, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {

                statoManager.setStato(new IdleStato());
            }
        });


        ellisseButton.setOnAction(e -> {
            if (ellisseButton.isSelected()) {
                statoManager.setStato(new DisegnaEllisseStato(lavagnaView, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {
                System.out.println("ho deselezionato ellisse button");
                statoManager.setStato(new IdleStato());;
            }
        });

        grigliaButton.setOnAction(e -> {
            if(grigliaButton.isSelected())
                Invoker.getInstance().executeCommand(new AggiungiGrigliaCommand(lavagnaModel, new GrigliaFactory(),Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY));
            else
                Invoker.getInstance().executeCommand(new RimuoviGrigliaCommand(new GrigliaFactory(), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY));
            });


        lavagna.setOnMousePressed(event ->{
            statoManager.getStato().onMousePressed(event);
        });

        lavagna.setOnMouseDragged(event ->{
            statoManager.getStato().onMouseDragged(event);

        });

        lavagna.setOnMouseReleased(event ->{
            statoManager.getStato().onMouseReleased(event);

        });

        salvaConNome.setOnAction(e ->{

            Command cmd = new SalvaFiguraCommand(salvaConNome, lavagnaModel);

            Invoker.getInstance().executeCommand(cmd);

            System.out.println("FIGURA SALVATA");

        });

        caricaFile.setOnAction(e->{
            Command cmd = new CaricaCommand(lavagnaModel, caricaFile);

            Invoker.getInstance().executeCommand(cmd);

            System.out.println("FILE CARICATO");
        });


    }

}