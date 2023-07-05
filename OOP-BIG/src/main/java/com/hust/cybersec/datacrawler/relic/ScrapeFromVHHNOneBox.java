package com.hust.cybersec.datacrawler.relic;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.datalinker.LinkData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;
import java.io.IOException;
import java.util.LinkedList;





public class ScrapeFromVHHNOneBox extends BasicDataCrawler {
	private Relic relic;
	private LinkData linkRelic;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetFigure = 0;
	
	public Relic getRelic() {
		return relic;
	}
	

	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getLienKetFigure() {
		return lienKetFigure;
	}



	public ScrapeFromVHHNOneBox(String url) throws IOException {
		this.url = url;
		connect();
		linkRelic = new LinkData();
	}

	public void scraping() {
		
		String title = this.getDoc().select("header > h1").text();
		String name = title.split(" \\(")[0];
		String location;
		String type;
		String desc = this.getDoc().select(".entry-content").text();
		
		if (title.contains("La Khê")) {
			location = "Hà Đông";
		} else if (title.split(" \\(").length > 1) {
			location = title.split(" \\(")[1].replace(")", "");
		} else {
			location = "Hà Nội";
		}
		if (name.contains("Đình")) {
			type = "Đình";
		} else if (name.contains("Chùa")) {
			type = "Chùa";
		} else if (name.contains("Đền")) {
			type = "Đền";
		} else if (name.contains("Miếu")) {
			type = "Miếu";
		} else {
			type = "Nghè";
		}
		
		linkRelic.genLink(desc);
		lienKetDynasty += linkRelic.getLienKetDynasty();
		lienKetKing += linkRelic.getLienKetKing();
		lienKetFigure += linkRelic.getLienKetFigure();
		LinkedList<Figure> figures = linkRelic.getFigures();
		LinkedList<King> kings = linkRelic.getKings();
		LinkedList<Dynasty> dynastys = linkRelic.getDynastys();
		
		relic = new Relic(name, location, type
				, "Unknown", desc, figures, kings, dynastys);
		System.out.println(name);
		System.out.println(location);
		System.out.println(type);
		System.out.println(desc);

	}

}
