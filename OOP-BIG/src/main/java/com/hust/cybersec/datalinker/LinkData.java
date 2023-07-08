package com.hust.cybersec.datalinker;

import java.io.IOException;
import java.util.LinkedList;
import com.hust.cybersec.application.datareader.ReadData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.King;
import java.util.List;

public class LinkData {

    List<Figure> listObservablesFigure;
    List<King> listObservablesKing;
    private LinkedList<Figure> figures;
    private LinkedList<King> kings;
    private LinkedList<Dynasty> dynastys;
    private LinkedList<String> added;

    public LinkData() throws IOException {
        listObservablesFigure = new ReadData<Figure>()
                .FromJsonToArray("/OOP-BIG/src/main/data/figures.json", Figure.class);

        listObservablesKing = new ReadData<King>().FromJsonToArray("/OOP-BIG/src/main/data/kings.json",
                King.class);
    }

    public void genLink(String relatedFigure) {
        this.figures = new LinkedList<Figure>();
        this.kings = new LinkedList<King>();
        this.dynastys = new LinkedList<Dynasty>();
        this.added = new LinkedList<String>();

        for (King f : listObservablesKing) {

        }
    }

    public LinkedList<Figure> getFigures() {
        return figures;
    }

    public LinkedList<King> getKings() {
        return kings;
    }

    public LinkedList<Dynasty> getDynastys() {
        return dynastys;
    }

}
