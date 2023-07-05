package com.hust.cybersec.datacrawler.dynasty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.interfaces.ICombineData;
import com.hust.cybersec.datacrawler.interfaces.IWriteJson;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.King;

public class DynastyScrapeFull implements ICombineData, IWriteJson {

    private DynastyName crawlNames;
    private DynastyFounder crawlFounder;
    private DynastyWikiKingsCombine firstKings;
    private LinkedList<Dynasty> dynastys;
    private DynastyRemainedKings remainedKings;

    public DynastyScrapeFull() {
        dynastys = new LinkedList<Dynasty>();
    }

    @Override
    public void writeJSon() throws JsonIOException, IOException {
        String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-Project/OOP-BIG/src/main/data/dynasties.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(dynastys, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws JsonIOException, IOException {
        DynastyScrapeFull f = new DynastyScrapeFull();
        f.combine();
        f.writeJSon();
    }

    @Override
    public void combine() throws IOException {
        firstKings = new DynastyWikiKingsCombine();
        firstKings.scraping();

        crawlNames = new DynastyName();
        crawlNames.scraping();

        crawlFounder = new DynastyFounder();
        crawlFounder.scraping();

        remainedKings = new DynastyRemainedKings();
        remainedKings.scraping();

        LinkedList<String> dynastyNames = crawlNames.getDynasty_names();

        for (String name : dynastyNames) {
            // System.out.println(name);
            Dynasty d = new Dynasty(name);
            dynastys.add(d);
        }
        firstKings.getDynastys().addAll(remainedKings.getDynastys());
        for (Dynasty d_1 : firstKings.getDynastys()) {
            for (Dynasty d_2 : dynastys) {
                if (d_1.getName().equals(d_2.getName())) {
                    // System.out.println("* " + d_1.getName());
                    d_2.setKings(d_1.getKings());
                }
            }
        }

        for (Dynasty d_1 : crawlFounder.getDynastys()) {
            for (Dynasty d_2 : dynastys) {
                if (d_1.getName().equals(d_2.getName())) {
                    d_2.setFounder(d_1.getFounder());
                    System.out.println("* " + d_2.getName() + "-" + d_2.getFounder());
                }
            }
        }

        for (Dynasty d_1 : dynastys) {
            if (d_1.getFounder() == null) {
                d_1.setFounder(new King("Unknown"));
            }
            System.out.println("* " + d_1.getName() + "-" + d_1.getFounder());
        }

        for (Dynasty d : dynastys) {
            if (d.getKings() == null) {
                DynastyWikiWandKings d_w = new DynastyWikiWandKings(d.getName());
                d_w.scraping();

                System.out.println("** " + d.getName());
                System.out.println(d_w.getKings().size());

                d.setKings(d_w.getKings());
            }

            DynastyYear d_y = new DynastyYear(d.getName());
            d_y.scraping();

            d.setStartYear(d_y.getBeginYear());
            d.setEndYear(d_y.getEndYear());
            // System.out.println(d.getName() + " " + d.getStartYear() + " " +
            // d.getEndYear());

            DynastyCapital d_c = new DynastyCapital(d.getName());
            d_c.scraping();

            d.setCapital(d_c.getCapital());

            // System.out.println(d.getName() + " " + d.getCapital());
        }

    }
}
