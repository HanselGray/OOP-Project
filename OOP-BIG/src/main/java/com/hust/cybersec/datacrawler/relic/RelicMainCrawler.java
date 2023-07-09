package com.hust.cybersec.datacrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.objects.Relic;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;
import com.hust.cybersec.datacrawler.interfaces.WriteToJSON;

public class RelicScrapeFull implements DataCombine, WriteToJSON {

    private LinkedList<Relic> relics;

    public RelicScrapeFull() {
        relics = new LinkedList<>();
    }

    @Override
    public void combine() throws IOException {
        ScrapeFromDiTichCombine r_d = new ScrapeFromDiTichCombine();
        r_d.combine();
        relics.addAll(r_d.getRelics());

        ScrapeFromVHHNCombine r_h = new ScrapeFromVHHNCombine();
        r_h.combine();
        relics.addAll(r_h.getRelics());
//        System.out.println(r_h.getLienKetDynasty());
//        System.out.println(r_h.getLienKetFigure());
//        System.out.println(r_h.getLienKetKing());
    }

    @Override
    public void writeJSon() throws JsonIOException, IOException {
        String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/relics.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(relics, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws IOException {
        RelicScrapeFull r_f = new RelicScrapeFull();
        r_f.combine();
        r_f.writeJSon();
    }

}
