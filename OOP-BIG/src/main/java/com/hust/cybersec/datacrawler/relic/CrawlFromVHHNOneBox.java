package com.hust.cybersec.datacrawler.relic;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.datalinker.LinkData;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;
import java.io.IOException;
import java.util.List;

public class CrawlFromVHHNOneBox extends BasicDataCrawler {

    private Relic relic;
    private LinkData linkRelic;

    public Relic getRelic() {
        return relic;
    }

    public CrawlFromVHHNOneBox(String url) throws IOException {
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

        List<HistoricalFigure> historicalFigures = linkRelic.getFigures();
        List<King> kings = linkRelic.getKings();
        List<Dynasty> dynastys = linkRelic.getDynastys();

        relic = new Relic(name, location, type,
                "Unknown", desc, historicalFigures, kings, dynastys);
        System.out.println(name);
        System.out.println(location);
        System.out.println(type);
        System.out.println(desc);

    }

}
