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

public class BigEventWiki extends BasicDataCrawler  {
	private ArrayList<Event> WikiEvents = new ArrayList<Event>();

	public ArrayList<Event> getList() {
		return WikiEvents;
	}

	BigEventWiki() {
		this.url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";		
		connect();
	}

	private String containDigits(String rawData) {
		char[] chars = rawData.toCharArray();
		for (char c : chars) {
			if (Character.isDigit(c))
				return rawData;
		}
		return "";
	}

	private String crawlTimeStamp(String rawData) {
		rawData = rawData.replaceAll("[^0-9]", "#");
		String[] arr = rawData.split("#");

		boolean LaMotNam = false;
		int SoNamTrongDuLieu = 0;
		StringBuilder ThoiGianTraVe = new StringBuilder();

		for (String s : arr) {
			if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$"))
				LaMotNam = true;
			SoNamTrongDuLieu++;
			if (SoNamTrongDuLieu > 1)
				ThoiGianTraVe.append(" - ");
			ThoiGianTraVe.append(s);
		}

		if (!LaMotNam)
			return "";
		return ThoiGianTraVe.toString();
	}

	
	private String crawlPlaces(String rawData, String tobeDelete) {
		// StringBuilder s = new StringBuilder();
		final String[] keywords = new String[] { "Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Văn hóa " };
		boolean needModification = false;
		rawData = rawData.replace(tobeDelete, "");

		for (int i = 0; i < keywords.length; i++) {
			if (rawData.contains(keywords[i])) {
				needModification = true;
				rawData = rawData.replace(keywords[i], "");
				rawData = rawData.replaceAll("[-()]", "");
			}
		}
		if (needModification)
			return rawData;
		return "";
	}

	private String crawlFigure(String rawData, String tobeDelete) {
		if (rawData.contains("Dẹp Loạn")) {
			rawData = rawData.replace("Dẹp Loạn ", "");
			rawData = rawData.replace(tobeDelete, "");
			return rawData;
		}
		return "";
	}

	public void scraping() {
		String TimeStamp = "";
		Element main_content = this.doc.getElementById("bodyContent");
		// Elements thoi_ki = main_content.select("h3");
		Elements su_kien = main_content.select("p, dd");
		for (Element e : su_kien) {
			// mot so su kien ghi ro rang ngay thang nam o tag con thap hon
			String name = e.select("a").text();// day
			if (name.length() == 0) {
				TimeStamp = e.select("b").text();
				TimeStamp = containDigits(TimeStamp);
				continue;
			}
			String time = e.select("b").text();
			if (containDigits(time) == "")
				continue;
			if (crawlTimeStamp(time) == "")
				time = time.concat(" " + TimeStamp);
			Event s = new Event();
			s.setName(name);
			s.setTime(time);
			s.setPlaces(crawlPlaces(name, time));			
			// s.setNien_dai(CaoTrieuDai(time));
			WikiEvents.add(s);
			System.out.println(time + ": " + name);
		}
	}

	public static void main(String args[]) {
		BigEventWiki events = new BigEventWiki();
		events.scraping();		
		String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/events.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));			
			gson.toJson(events.getList(), writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}