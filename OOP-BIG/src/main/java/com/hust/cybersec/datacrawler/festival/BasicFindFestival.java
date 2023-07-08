package com.hust.cybersec.datacrawler.festival;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.Festival;
import java.util.ArrayList;

public abstract class BasicFindFestival extends BasicDataCrawler {

    protected ArrayList<Festival> list = new ArrayList<Festival>();

    public ArrayList<Festival> getList() {
        return list;
    }

    public void setList(ArrayList<Festival> list) {
        this.list = list;
    }

    public void scraping() {

    }
}
