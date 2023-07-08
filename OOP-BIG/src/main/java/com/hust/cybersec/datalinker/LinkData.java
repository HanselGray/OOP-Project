package com.hust.cybersec.datalinker;

import java.io.IOException;
import java.util.ArrayList;
import com.hust.cybersec.application.datareader.ReadData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.King;
import java.util.List;

public class LinkData {

    List<Figure> listObservablesFigure;
    List<King> listObservablesKing;
    private ArrayList<Figure> figures;
    private ArrayList<King> kings;
    private ArrayList<Dynasty> dynastys;
    private ArrayList<String> added;

    public LinkData() throws IOException {
        listObservablesFigure = new ReadData<Figure>()
                .FromJsonToArray("/OOP-BIG/src/main/data/figures.json", Figure.class);

        listObservablesKing = new ReadData<King>().FromJsonToArray("/OOP-BIG/src/main/data/kings.json",
                King.class);
    }

    public void genLink(String relatedFigure) {
        this.figures = new ArrayList<Figure>();
        this.kings = new ArrayList<King>();
        this.dynastys = new ArrayList<Dynasty>();
        this.added = new ArrayList<String>();

        for (King king : listObservablesKing) {
            if (king.getName() != null && relatedFigure.toLowerCase().contains(king.getName().toLowerCase())) {
                kings.add(king);
                continue;
            }
            for (String allias : king.getAliases()) {
                if (relatedFigure.toLowerCase().contains(allias.toLowerCase())) {
                    kings.add(king);
                    break;
                }
            }
        }

        for (Figure f : listObservablesFigure) {
            if (f.getName() != null && relatedFigure.toLowerCase().contains(f.getName().toLowerCase())) {
                figures.add(f);
                for (Dynasty d : f.getDynasty()) {
                    if (!added.contains(d.getName())) {
                        added.add(d.getName());
                        dynastys.add(d);
                    }
                }
                continue;
            }
            if (f.getOtherAliases() != null && relatedFigure.toLowerCase().contains(f.getOtherAliases().toLowerCase())) {
                figures.add(f);
                for (Dynasty d : f.getDynasty()) {
                    if (!added.contains(d.getName())) {
                        added.add(d.getName());
                        dynastys.add(d);
                    }
                }
            }
        }
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public ArrayList<King> getKings() {
        return kings;
    }

    public ArrayList<Dynasty> getDynastys() {
        return dynastys;
    }

}
