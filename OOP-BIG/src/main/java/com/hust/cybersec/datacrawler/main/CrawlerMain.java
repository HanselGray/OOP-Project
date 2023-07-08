package com.hust.cybersec.datacrawler.main;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.dynasty.DynastyScrapeMain;
import com.hust.cybersec.datacrawler.event.ScrapeEvent;
import com.hust.cybersec.datacrawler.history_figures.FigureScrapeMain;
import com.hust.cybersec.datacrawler.history_figures.KingScrapeMain;
import com.hust.cybersec.datacrawler.relic.RelicScrapeFull;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;

public class CrawlerMain implements DataCombine {
	private DynastyScrapeMain dynasty; 
	private RelicScrapeFull relic;
	private KingScrapeMain king;
	private FigureScrapeMain figure;
	private ScrapeEvent event;
	
	public CrawlerMain() throws JsonIOException, IOException {
		king = new KingScrapeMain();
		relic = new RelicScrapeFull();
		figure = new FigureScrapeMain();
		event = new ScrapeEvent();
		dynasty = new DynastyScrapeMain();		
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
	}
	
	public static void main(String[] args) throws JsonIOException, IOException {
		CrawlerMain full = new CrawlerMain();
		full.combine();
	}

}
