package com.hust.cybersec.datacrawler.relic;

import com.hust.cybersec.objects.Relic;
import java.io.IOException;
import java.util.LinkedList;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;

public class ScrapeFromVHHNCombine implements DataCombine {

    private LinkedList<Relic> relics;

    public ScrapeFromVHHNCombine() throws IOException {
        relics = new LinkedList<>();
    }

    public LinkedList<Relic> getRelics() {
        return relics;
    }

    public static void main(String[] args) throws IOException {
        ScrapeFromVHHNCombine r = new ScrapeFromVHHNCombine();
        r.combine();
    }

    @Override
    public void combine() throws IOException {
        int cnt = 0;
        for (int i = 1; i <= 44; i++) {
            ScrapeFromVHHNOnePage r_one = new ScrapeFromVHHNOnePage(i);
            r_one.scraping();
            cnt += r_one.getRelics().size();

            relics.addAll(r_one.getRelics());
        }
        System.out.println(cnt);

    }

}
