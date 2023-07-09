package com.hust.cybersec.datacrawler.main;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.dynasty.DynastyMainCrawler;
import com.hust.cybersec.datacrawler.event.EventCrawler;
import com.hust.cybersec.datacrawler.festival.FestivalMainCrawler;
import com.hust.cybersec.datacrawler.history_figures.KingMainCrawler;
import com.hust.cybersec.datacrawler.history_figures.FigureCrawler;
import com.hust.cybersec.datacrawler.relic.RelicMainCrawler;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;

public class CrawlerMain implements DataCombine {
	private DynastyMainCrawler dynasty;
	private RelicMainCrawler relic;
	private KingMainCrawler king;
	private FigureCrawler figure;
	private EventCrawler event;
	private FestivalMainCrawler festival;
	
	public CrawlerMain() throws JsonIOException, IOException {
		king = new KingMainCrawler();
		relic = new RelicMainCrawler();
		figure = new FigureCrawler();
		event = new EventCrawler();
		dynasty = new DynastyMainCrawler();
		festival = new FestivalMainCrawler();
	}

	@Override
	public void combine() throws IOException {
		king.scraping();
		king.writeJSon();
		
		figure.combine();
		figure.writeJSon();
		
		dynasty.combine();
		dynasty.writeJSon();
		
		relic.combine();
		relic.writeJSon();
		
		event.combine();
		event.writeJSon();

		festival.combine();
		festival.writeJSon();
	}
	
	public static void main(String[] args) throws JsonIOException, IOException {
		CrawlerMain full = new CrawlerMain();
		full.combine();
	}

}
