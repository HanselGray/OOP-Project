package com.hust.cybersec.datacrawler.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.interfaces.ICombineData;
import com.hust.cybersec.datacrawler.interfaces.IWriteJson;
import com.hust.cybersec.objects.Event;

public class ScrapeEvent implements IWriteJson, ICombineData {

    private LinkedList<Event> list = new LinkedList<>();

    public static void main(String[] args) {
        ScrapeEvent event = new ScrapeEvent();
        event.combine();
        try {
            event.writeJSon();
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void combine() {
        BigEventWiki events = new BigEventWiki();
        events.scraping();
        list.addAll(events.getList());
        
        Battle battle = new Battle();
        battle.scraping();
        list.addAll(battle.getBattleList());
    }

    @Override
    public void writeJSon() throws JsonIOException, IOException {
        String JsonURL = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/events.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(JsonURL));
            gson.toJson(list, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
