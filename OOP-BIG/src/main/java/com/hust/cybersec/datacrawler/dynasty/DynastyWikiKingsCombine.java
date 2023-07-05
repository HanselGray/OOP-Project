package com.hust.cybersec.datacrawler.dynasty;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hust.cybersec.objects.Dynasty;

public class DynastyWikiKingsCombine extends BasicDataCrawler {

    private LinkedList<String> blackList;
    private LinkedList<Dynasty> dynastys;

    public void scraping() {
        DynastyName names;
        names = new DynastyName();
        names.scraping();

        Elements canCrawlNames = this.getDoc()
                .select("#mw-content-text > div.mw-parser-output > ul:nth-child(6) > li > a");
        for (Element e : canCrawlNames) {
            if (names.getDynasty_names().contains(e.text())) {
                if (!this.blackList.contains(e.text())) {
                    Dynasty dynasty = new Dynasty(e.text());
                    String absHref = e.attr("abs:href");
                    DynastySingleWiki onePage = new DynastySingleWiki(absHref);
                    onePage.scraping();
                    dynasty.setKings(onePage.getKingNames());
                    dynastys.add(dynasty);
                    System.out.println(e.text());
                }
            }
        }
    }

    public LinkedList<Dynasty> getDynastys() {
        return dynastys;
    }

    public DynastyWikiKingsCombine() {
        this.url = "https://vi.wikipedia.org/wiki/Tri%E1%BB%81u_%C4%91%E1%BA%A1i";
        this.blackList = new LinkedList<String>();
        this.blackList.add("Nhà Lý");
        this.blackList.add("Nhà Trần");
        this.blackList.add("Nhà Hậu Lê");
        this.blackList.add("Nhà Nguyễn");

        connect();
        this.dynastys = new LinkedList<Dynasty>();

        // for (String t: this.names.getDynasty_names()) {
        // System.out.println(t);
        // }
        // System.out.println(this.names.getDynasty_names().size());
    }

    public static void main(String[] args) {
        DynastyWikiKingsCombine dynastyScrapeWiki = new DynastyWikiKingsCombine();
        dynastyScrapeWiki.scraping();

    }

}
