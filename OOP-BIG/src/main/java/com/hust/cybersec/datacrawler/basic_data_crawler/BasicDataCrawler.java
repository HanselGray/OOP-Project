package com.hust.cybersec.datacrawler.basic_data_crawler;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.nodes.Document;

public abstract class BasicDataCrawler {
	protected String url;
	protected Document doc;

	public BasicDataCrawler() {
	}

	public Document getDoc() {
		return doc;
	}

	protected void connect() {
		try {
			doc = Jsoup.connect(this.url).timeout(10*1000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.70").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
