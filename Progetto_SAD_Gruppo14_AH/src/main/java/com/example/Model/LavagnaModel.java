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

    public void rimuoviFigura(Figura figura){
        figure.remove(figura);
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

    public void cambiaColoreBordo(Figura figura, Color colore){
        int index = figure.indexOf(figura);
        figure.get(index).setStrokeColor(colore);
        notificaOsservatori();

    }
    public void cambiaColoreInterno(Figura figura, Color colore){
        int index = figure.indexOf(figura);
        figure.get(index).setFillColor(colore);
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

    public void svuotaLavagna() {
        figure.clear();
        notificaOsservatori();
    }

    public void caricaFigure(List <Figura> tempList){
        figure.addAll(tempList);
        notificaOsservatori();
    }

    public void spostaSopra(Figura figura){
        int index = figure.indexOf(figura);
        Figura upper = figure.get(index + 1);
        figure.set(index, upper);
        figure.set(index + 1, figura);
        notificaOsservatori();

    }
    public void spostaSotto(Figura figura){
        int index = figure.indexOf(figura);
        Figura lower = figure.get(index - 1);
        figure.set(index, lower);
        figure.set(index - 1, figura);
        notificaOsservatori();

    }





}
