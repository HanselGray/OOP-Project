package com.hust.cybersec.datacrawler.relic;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.Relic;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ScrapeFromVHHNOnePage extends BasicDataCrawler{
	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetFigure = 0;
	
	public LinkedList<Relic> getRelics() {
		return relics;
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

	public ScrapeFromVHHNOnePage(int i) {
		relics = new LinkedList<Relic>();
		String baseUrl = "http://ditichlichsu-vanhoahanoi.com/category/2dtlsvh/page/";
		this.url = baseUrl + Integer.toString(i) + "/";		
		connect();
	}

	public void scraping() throws IOException {
		Elements aData = this.getDoc().select("div#post-wrapper > div > article > a");
		System.out.println(aData.text());
		for (Element e: aData) {
			String boxUrl = e.attr("href");
			System.out.println(boxUrl);
			ScrapeFromVHHNOneBox h = new ScrapeFromVHHNOneBox(boxUrl);
			h.scraping();
			lienKetDynasty += h.getLienKetDynasty();
			lienKetKing += h.getLienKetKing();
			lienKetFigure += h.getLienKetFigure();
			relics.add(h.getRelic());
		}
	}
	
	public static void main(String[] args) throws IOException {
		ScrapeFromVHHNOnePage r = new ScrapeFromVHHNOnePage(1);
		r.scraping();
	}
}
