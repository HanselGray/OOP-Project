package com.hust.cybersec.datacrawler.relic;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.Relic;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlFromVHHNOnePage extends BasicDataCrawler {

    private LinkedList<Relic> relics;

    public LinkedList<Relic> getRelics() {
        return relics;
    }

    public CrawlFromVHHNOnePage(int i) {
        relics = new LinkedList<Relic>();
        String baseUrl = "http://ditichlichsu-vanhoahanoi.com/category/2dtlsvh/page/";
        this.url = baseUrl + Integer.toString(i) + "/";
        connect();
    }

    public void scraping() throws IOException {
        Elements aData = this.getDoc().select("div#post-wrapper > div > article > a");
        System.out.println(aData.text());
        for (Element e : aData) {
            String boxUrl = e.attr("href");
            System.out.println(boxUrl);
            CrawlFromVHHNOneBox h = new CrawlFromVHHNOneBox(boxUrl);
            h.scraping();
            relics.add(h.getRelic());
        }
    }

    public static void main(String[] args) throws IOException {
        CrawlFromVHHNOnePage r = new CrawlFromVHHNOnePage(1);
        r.scraping();
    }
}
