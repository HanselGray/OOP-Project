package com.hust.cybersec.datacrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datalinker.LinkData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;
import com.hust.cybersec.datacrawler.interfaces.WriteToJSON;

public class ScrapeFromDiTichCombine implements DataCombine, WriteToJSON {

    private ArrayList<Relic> relics;

    public ScrapeFromDiTichCombine() throws IOException {
        relics = new ArrayList<Relic>();
    }

    @Override
    public void writeJSon() throws JsonIOException, IOException {
        String filePath = "D:\\relic_new.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(relics, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws JsonIOException, IOException {
        ScrapeFromDiTichCombine rd = new ScrapeFromDiTichCombine();
        rd.combine();
        rd.writeJSon();
    }

    public ArrayList<Relic> getRelics() {
        return relics;
    }

    @Override
    public void combine() throws IOException {
        LinkData linkRelic = new LinkData();

        String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
        for (int i = 1865; i <= 6139; i++) {

            String url = baseUrl + Integer.toString(i);
            ScrapeFromDiTichOnePage r = new ScrapeFromDiTichOnePage(url);
            r.scraping();
            if (r.getName().strip() != "") {
                System.out.println(i);
                System.out.println(r.getName());
                System.out.println(r.getAddress());
                System.out.println(r.getPerson());
                String tenNguoiTho = r.getPerson();

                linkRelic.genLink(tenNguoiTho);
                List<HistoricalFigure> historicalFigures = linkRelic.getFigures();
                List<King> kings = linkRelic.getKings();
                List<Dynasty> dynastys = linkRelic.getDynastys();

                Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType(), r.getRank(), tenNguoiTho, historicalFigures, kings,
                        dynastys);
                relics.add(r1);
            }

        }

    }
}
