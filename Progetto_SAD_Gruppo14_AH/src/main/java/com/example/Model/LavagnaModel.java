package com.example.Model;


import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

public class LavagnaModel {

    private static LavagnaModel instance;
    private List<Figura> figure = new ArrayList<>();
    private List<Runnable> osservatori = new ArrayList<>();
    Griglia griglia;
    //private Figura figuraSelezionata;

    public static LavagnaModel getInstance(){
        if(instance == null){
            instance = new LavagnaModel();
        }
        return instance;
    }

    public void selezionaFigura(Figura figura){

        notificaOsservatori();
    }

    public void deselezionaFigura(Figura figura){

        notificaOsservatori();
    }

    public void rimuoviGriglia(){
        this.griglia = null;
        notificaOsservatori();
    }

    public void aggiungiGriglia(Griglia griglia){
        this.griglia = griglia;
        notificaOsservatori();
    }

    public Griglia getGriglia(){
        return griglia;
    }

    public void ridimensionaFigura(Figura figura, double x2, double y2){

        figure.get(figure.indexOf(figura)).setX2(x2);
        figure.get(figure.indexOf(figura)).setY2(y2);

        notificaOsservatori();
    }

    public void spostaFigura(Figura figura, double x1, double y1){
        double x2_diff = figure.get(figure.indexOf(figura)).getX2() - figure.get(figure.indexOf(figura)).getX1();
        double y2_diff = figure.get(figure.indexOf(figura)).getY2() - figure.get(figure.indexOf(figura)).getY1();

        figure.get(figure.indexOf(figura)).setX1(x1);
        figure.get(figure.indexOf(figura)).setY1(y1);
        figure.get(figure.indexOf(figura)).setX2(x1+x2_diff);
        figure.get(figure.indexOf(figura)).setY2(y1+y2_diff);

        notificaOsservatori();


    }

    public void aggiungiFigura(Figura figura){
        figure.add(figura);
        notificaOsservatori();
    }


    public List<Figura> getFigure() {
        return figure;
    }

    public void aggiungiOsservatore(Runnable osservatore){
        osservatori.add(osservatore);
    }

    public void notificaOsservatori(){
        for (Runnable r : osservatori){
            r.run();
        }
    }


}
