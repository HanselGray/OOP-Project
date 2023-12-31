package com.hust.cybersec.datacrawler.dynasty;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import java.util.LinkedList;
import com.hust.cybersec.objects.King;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class DynastySingleWiki extends BasicDataCrawler {
	private LinkedList<King> kingNames;

	public void scraping() {
		Elements kings = this.getDoc().select("#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr");
		boolean flag = false;
		int cntFlag = 0;
		for (Element e : kings) {
			Elements aData = e.getElementsByTag("a");
			Elements tdData = e.getElementsByTag("td");
			Elements thData = e.getElementsByTag("th");

			// System.out.println(aData.text());
			if ((!aData.isEmpty() && (aData.text().equals("Hoàng đế") || aData.text().equals("Vua")))
					|| thData.text().equals("Hoàng Đế")) {
				// System.out.println("a");
				flag = true;
				continue;
			}
			if (flag && tdData.text().equals("")) {
				cntFlag += 1;
				if (cntFlag == 2) {
					flag = false;
				}

			}
			if (!aData.isEmpty() && flag && !aData.get(aData.size() - 1).text().equals("")
					&& !aData.get(aData.size() - 1).text().matches(".*[0-9].*")) {
				kingNames.add(new King(aData.get(aData.size() - 1).text()));
			}
		}
	}

	public DynastySingleWiki(String url) {
		this.kingNames = new LinkedList<King>();	
                                    this.url = url;
		connect();
	}

	public LinkedList<King> getKingNames() {
		return kingNames;
	}

	public static void main(String[] args) {
		DynastySingleWiki one = new DynastySingleWiki(
				"https://vi.wikipedia.org/wiki/Nh%C3%A0_Nguy%E1%BB%85n");
		one.scraping();
	}
}
