package com.hust.cybersec.datacrawler.dynasty;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;

import java.util.ArrayList;
import java.util.LinkedList;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.King;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class DynastyScrapeNKSKings extends BasicDataCrawler{
	LinkedList<Dynasty> dynastys = new LinkedList<>();
	
	public DynastyScrapeNKSKings() {
		this.url = "https://nguoikesu.com/tu-lieu/bang-doi-chieu-cac-trieu-dai-viet-nam-va-cac-trieu-dai-trung-quoc";		
		connect();
	}
	
	
	
	
	public void scraping() {
		ArrayList<String> remained = new ArrayList<String>();
		remained.add("Việt Nam Dân chủ Cộng hòa");
		remained.add("Cộng hòa Xã hội Chủ nghĩa Việt Nam");
		for (String s: remained) {
			Dynasty d = new Dynasty(s);
			dynastys.add(d);
		}
		Elements trData = this.getDoc().select("#content > div.com-content-article.item-page > div.com-content-article__body > table > tbody > tr");
		ArrayList<Element> els = new ArrayList<Element>();
		for (Element e: trData) {
			for (String s: remained) {
				if (e.text().toLowerCase().replace("oà", "òa").contains(s.toLowerCase())) {
					System.out.println(e.text());
					els.add(e);
				}
			}
		}
		
		for (int i=0;i<dynastys.size();i++) {
			LinkedList<King> kings = new LinkedList<King>();
			Element nextE = els.get(i).nextElementSibling();
			while (!nextE.text().contains(".")) {
				
				if (!nextE.select("a:nth-child(1)").text().equals("")) {
					kings.add(new King(nextE.select("a:nth-child(1)").text()));
				}
				
				nextE = nextE.nextElementSibling();
				if (nextE == null) {
					break;
				}
			}
			dynastys.get(i).setKings(kings);
			System.out.println(dynastys.get(i).getKings().size());
		}
		
		System.out.println(this.dynastys.size());
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public static void main(String[] args) {
		DynastyScrapeNKSKings n = new DynastyScrapeNKSKings();
		n.scraping();
	}
	
}
