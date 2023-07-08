package com.hust.cybersec.datacrawler.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.Event;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Battle extends BasicDataCrawler {

    private ArrayList<Event> battleList = new ArrayList<>();

    public ArrayList<Event> getBattleList() {
        return battleList;
    }

    Battle() {
        this.url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_tr%E1%BA%ADn_%C4%91%C3%A1nh_trong_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        connect();
    }

    // kiem tra xem trong phan element co chua 3 hoac 4 ki tu chu so lien tuc hay khong
    private String filterTimeStamp(String RawData) {
        RawData = RawData.replaceAll("[^0-9]", "#");
        String[] arr = RawData.split("#");

        boolean validYearNum = false;
        int year = 0;
        StringBuilder yearStr = new StringBuilder();

        for (String s : arr) {
            if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$")) {
                validYearNum = true;
                year++;
                if (year > 1) {
                    yearStr.append(" - ");
                }
                yearStr.append(s);
            }
        }

        if (!validYearNum) {
            return "unknown";
        }
        return yearStr.toString();
    }

    private String filterPlaces(String RawData, String toDelete) {
        // StringBuilder s = new StringBuilder();
        final String[] tukhoa = new String[]{"Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Kháng chiến"};
        boolean CanChinhSua = false;
        RawData = RawData.replaceAll("\\(\\d+-\\d+\\)", "");
        RawData = RawData.replaceAll("\\(\\d+\\)", "");

        for (int i = 0; i < tukhoa.length; i++) {
            if (RawData.contains(tukhoa[i])) {
                CanChinhSua = true;
                RawData = RawData.replace(tukhoa[i], "");
            }
        }
        if (CanChinhSua) {
            return RawData;
        }
        return "Việt Nam";
    }

    private List<String> crawlFactions(Elements table, int i) {
        Elements cols = table.select("> tbody > tr:nth-child(" + i + ") > td");
        List<String> temp = new ArrayList<>();
        for (Element col : cols) {
            String faction = col.select("> a").text();
            temp.add(faction);
        }
        return temp;
    }

    private boolean validURL(String name) {
        final String[] tukhoa = new String[]{"Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Kháng chiến"};
        for (String tu : tukhoa) {
            if (name.contains(tu)) {
                return true;
            }
        }
        return false;
    }

    public void scraping() {
        String TimeStamp = "";
        Element main_content = this.doc.getElementById("bodyContent");
        Elements listBattle = main_content.select("li");
        for (Element e : listBattle) {

            Event s = new Event();
            String battleName = e.text();       
            s.setName(battleName);

            if (!e.select("> a").attr("href").isEmpty() && validURL(battleName)) {
                String link = "https://vi.wikipedia.org" + e.select("> a").attr("href");
                try {
                    Document doc = Jsoup.connect(link).get();
                    Elements table = doc.select("#mw-content-text > div.mw-parser-output > table.infobox.vevent");
                    
                    if (!"".equals(table.select("> tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td").text())) {

                        s.setTime(table.select("> tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td").text());

                    } else if (!"".equals(table.select("> tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td").text())) {

                        s.setTime(table.select("> tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td").text());

                    } else {
                        
                        s.setTime(table.select("> tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(1) > td").text());
                        
                    }
                    s.setPlaces(table.select("div.location").text()); //crawl Places
                    s.setResult(table.select("td.status ").text());
                    //get factions 
                    if ("Tham chiến".equals(table.select("> tbody > tr:nth-child(3) > th").text())) {
                        s.setFactions(crawlFactions(table, 4));
                    } else if ("Tham chiến".equals(table.select("> tbody > tr:nth-child(5) > th").text())) {
                        s.setFactions(crawlFactions(table, 6));
                    } else if ("Tham chiến".equals(table.select("> tbody > tr:nth-child(4) > th").text())) {
                        s.setFactions(crawlFactions(table, 5));
                    }
                    battleList.add(s);
                } catch (IOException ex) {
                    Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String time = filterTimeStamp(e.text());
                if (time.length() != 0) {
                    TimeStamp = time;
                }
                s.setTime(TimeStamp);
                s.setPlaces(filterPlaces(battleName, TimeStamp));
                battleList.add(s);
                System.out.println(TimeStamp + ": " + battleName);
            }
            if (battleName.contains("1988")) {
                break;
            }
        }

    }

    public static void main(String args[]) {
        Battle battleCrawl = new Battle();
        battleCrawl.scraping();
        String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/events.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(battleCrawl.getBattleList(), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
