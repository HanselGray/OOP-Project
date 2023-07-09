package com.hust.cybersec.datacrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.King;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.hust.cybersec.datacrawler.interfaces.WriteToJSON;

public class KingScrapeMain extends BasicDataCrawler implements WriteToJSON {

    private ArrayList<King> kings = new ArrayList<King>();

    public KingScrapeMain() {
        this.url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
        connect();
    }

    public Document getDoc() {
        return this.doc;
    }

    public ArrayList<King> getKings() {
        return kings;
    }

    public void setKings(ArrayList<King> kings) {
        this.kings = kings;
    }

    public void scraping() {
    	Elements tables = doc.select("#mw-content-text > div.mw-parser-output > table");
		ArrayList<Element> kingTables = new ArrayList<>();
		for (Element table : tables) {
			Elements columns = table.select("tr:nth-child(2) > td");
			if (columns.size()== 10) {
				kingTables.add(table);
			}
		}
		int identifier = 1;
		for (Element table : kingTables) {
			
			//select so hang cua bang
			Elements kingElements = table.select("tr");
			//xu li neu bang khong co cai gi
			if (kingElements.size() == 0) {
				System.out.println("Data not found");
				return;
			}
			for (int i = 1; i < kingElements.size(); i++) {
				Element row = kingElements.get(i);
				King king = new King();
				//set cac attribute cho class king
				Elements columns = row.select("td"); //chon cot
				king.setId(identifier);
				
				String nameText = columns.get(1).text();
				String name = nameText.replaceAll("\\[.*\\]", "").trim();
				king.setName(name);
				
				String reignBeginText = columns.get(7).text();
				String reignBegin = reignBeginText.replaceAll("\\[.*\\]", "").trim();
				king.setReignBegin(reignBegin);
				
				String reignEndText = columns.get(9).text();
				String reignEnd = reignEndText.replaceAll("\\[.*\\]", "").trim();
				king.setReignEnd(reignEnd);
				
				ArrayList<String> aliases = new ArrayList<>();
				ArrayList<String> aliasesNone = new ArrayList<>();
				for (int j = 2; j < 6; j++) {
					String aliasText = columns.get(j).text();
					String alias = aliasText.replaceAll("\\[.*\\]", "").trim();
					if ((alias.equals("không rõ")) || (alias.equals("không có"))) {
						aliasesNone.add(alias);
					} else {
						aliases.add(alias);
					}
				}
				king.setAliases(aliases);
				
				king.setPaperURL("https://wikipedia.org"+columns.get(1).select("> b > a").attr("href"));
				
				//cho phan tu vao list kings
				kings.add(king);
				identifier++;
			}
		}
    }

    public String cleanData(String sample) {
        String data = sample;
        int index = data.indexOf("[");
        while (index != -1) {
            int close = data.indexOf("]");
            String tmp = data.substring(index, close + 1);
            data = data.replace(tmp, "");
            index = data.indexOf("[");
        }
        return data;
    }

    public static void main(String[] args) throws JsonIOException, IOException {
        KingScrapeMain obj = new KingScrapeMain();
//		ArrayList<King> kings = new ArrayList<King>();
        obj.scraping();
        obj.writeJSon();
//		kings.addAll(obj.getKings());
//		System.out.println(kings.size());

    }

    @Override
    public void writeJSon() throws JsonIOException, IOException {
        String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/kings.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(kings, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
