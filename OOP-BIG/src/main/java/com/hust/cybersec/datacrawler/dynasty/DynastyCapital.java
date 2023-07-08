package com.hust.cybersec.datacrawler.dynasty;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DynastyCapital extends BasicDataCrawler {

    private String capital;
    private String dynastyName;

    public DynastyCapital(String dynastyName) {
        this.dynastyName = dynastyName;
        this.url = "https://vi.wikipedia.org/wiki/Th%E1%BB%A7_%C4%91%C3%B4_Vi%E1%BB%87t_Nam";
        connect();
    }

    public void scraping() {
        Elements capitals = this.getDoc()
                .select("#mw-content-text > div.mw-parser-output > table.wikitable > tbody > tr");
        if (this.dynastyName.contains("Bắc thuộc") || this.dynastyName.equals("Tự chủ")) {
            this.capital = "Không";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else if (this.dynastyName.equals("Nhà Tiền Lê") || this.dynastyName.equals("Nhà Lý")) {
            this.capital = "Hoa Lư";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else if (this.dynastyName.equals("Nhà Trần")) {
            this.capital = "Thăng Long";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else if (this.dynastyName.equals("Nhà Lê sơ") || this.dynastyName.equals("Nhà Mạc")
                || this.dynastyName.equals("Nhà Lê trung hưng") || this.dynastyName.equals("Chúa Trịnh")) {
            this.capital = "Đông Kinh";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else if (this.dynastyName.equals("Quốc gia Việt Nam") || this.dynastyName.equals("Việt Nam Cộng hòa")) {
            this.capital = "Sài Gòn";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else if (this.dynastyName.equals("Thời tiền sử")) {
            this.capital = "Không";
            System.out.println(this.dynastyName + "-" + this.capital);
        } else {
            for (Element e : capitals) {
                String name0 = e.select("td:nth-child(3)  a:nth-child(1)").text();
                String name1 = e.select("td:nth-child(3)  a:nth-child(2)").text();
                String name2 = e.select("td:nth-child(2)  a:nth-child(1)").text();
                String name3 = e.select("td:nth-child(2)  a:nth-child(2)").text();
                if (name0.toUpperCase().equals(this.dynastyName.toUpperCase())) {

                    this.capital = e.select("td:nth-child(1) a:nth-child(1)").text();
                    System.out.println(name0 + "-" + this.capital);
                    break;
                }
                if (name1.toUpperCase().equals(this.dynastyName.toUpperCase())) {
                    this.capital = e.select("td:nth-child(1) a:nth-child(1)").text();
                    System.out.println(name1 + "-" + this.capital);
                    break;
                }
                if (name2.toUpperCase().equals(this.dynastyName.toUpperCase())) {

                    this.capital = e.select("td:nth-child(1) a:nth-child(1)").text();
                    System.out.println(name2 + "-" + this.capital);
                    break;
                }
                if (name3.toUpperCase().equals(this.dynastyName.toUpperCase())) {
                    this.capital = e.select("td:nth-child(1) a:nth-child(1)").text();
                    System.out.println(name3 + "-" + this.capital);
                    break;
                }
            }
        }

    }

    public String getCapital() {
        return capital;
    }

    public static void main(String[] args) {
        DynastyName names = new DynastyName();
        names.scraping();
        for (String e : names.getDynasty_names()) {
            // System.out.println("*"+e+"*");
            DynastyCapital c = new DynastyCapital(e);
            c.scraping();
            // Dynasty dynasty = new Dynasty(y.getBeginYear(), y.getEndYear(),
            // y.getdynastyName());
        }
        System.out.println(names.getDynasty_names().size());
    }
}
