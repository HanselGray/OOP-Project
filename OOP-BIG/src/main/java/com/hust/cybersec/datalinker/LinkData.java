package com.hust.cybersec.datalinker;

import java.io.IOException;
import java.util.*;
import com.hust.cybersec.application.datareader.ReadData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import java.util.List;

public class LinkData {

    List<HistoricalFigure> listObservablesHistoricalFigure;
    List<King> listObservablesKing;
    private List<HistoricalFigure> historicalFigures;
    private List<King> kings;
    private List<Dynasty> dynastys;
    private List<String> added;

    public LinkData() throws IOException {
        listObservablesHistoricalFigure = new ReadData<HistoricalFigure>()
                .FromJsonToArray("/OOP-BIG/src/main/data/figures.json", HistoricalFigure.class);

        listObservablesKing = new ReadData<King>().FromJsonToArray("/OOP-BIG/src/main/data/kings.json",
                King.class);
    }

    public void genLink(String relatedFigure) {
        this.historicalFigures = new ArrayList<HistoricalFigure>();
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

        for (HistoricalFigure f : listObservablesHistoricalFigure) {
            if (f.getName() != null && relatedFigure.toLowerCase().contains(f.getName().toLowerCase())) {
                historicalFigures.add(f);
                for (Dynasty d : f.getDynasty()) {
                    if (!added.contains(d.getName())) {
                        added.add(d.getName());
                        dynastys.add(d);
                    }
                }
                continue;
            }
            if (f.getOtherAliases() != null && relatedFigure.toLowerCase().contains(f.getOtherAliases().toLowerCase())) {
                historicalFigures.add(f);
                for (Dynasty d : f.getDynasty()) {
                    if (!added.contains(d.getName())) {
                        added.add(d.getName());
                        dynastys.add(d);
                    }
                }
            }
        }
    }

    public List<HistoricalFigure> getFigures() {
        return historicalFigures;
    }

    public List<King> getKings() {
        return kings;
    }

    public List<Dynasty> getDynastys() {
        return dynastys;
    }

}
