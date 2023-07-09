package com.hust.cybersec.datacrawler.festival;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;



import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hust.cybersec.objects.Festival;
import com.hust.cybersec.objects.HistoricalFigure;

public class AnGiangFestival extends ScrapeMainFestival {

	public AnGiangFestival() {
		String url = "https://angiangtourist.vn/thoi-gian-va-dia-diem-to-chuc-cac-le-hoi-lon-o-an-giang/#";
		this.url = url;
		connect();
	}

	@Override
	public void scraping() {
		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
		Elements paragraphs = mainContent.getElementsByTag("p");
		for (int i = 6; i <= 13; i++) {
			Element p = paragraphs.get(i);
			Element header = p.getElementsByTag("strong").first();
			// Festival festival = new Festival();
			if (header != null) {
				String content = p.html();
				String fesName = header.text();
				System.out.println(fesName);
				// System.out.println(content);
				int start = content.indexOf("<br>") + 4;
				String mainData = content.substring(start);
				String[] data = mainData.split("<br>");
				// System.out.println(data.length);
				String time = "";
				String place = "";
				String relatedFigure = "";
				String description = "";
				for (String d : data) {
					String[] dataParts = d.split(": ");
					String title = "";
					String mainDescrip = "";
					title = title.concat(dataParts[0]);
					mainDescrip = mainDescrip.concat(dataParts[1]);
					System.out.println(title);
					System.out.println(mainDescrip);
					switch (title) {
						case " Thời gian": {
							time = time.concat(mainDescrip);
							time = time.trim();
							break;
						}
						case " Địa điểm": {
							place = place.concat(mainDescrip);
							place = place.trim();
							break;
						}
						case " Đối tượng suy tôn": {
							if (mainDescrip.contains("Trực")) {
								int index = mainDescrip.indexOf(",");
								mainDescrip = mainDescrip.substring(0, index);
							} else if (mainDescrip.contains("Cảnh")) {
								int index = mainDescrip.indexOf("(");
								mainDescrip = mainDescrip.substring(0, index);
							}
							relatedFigure = relatedFigure.concat(mainDescrip);
							relatedFigure = relatedFigure.trim();
							break;
						}
						case " Đặc điểm": {
							description = description.concat(mainDescrip);
							description = description.trim();
							break;
						}
					}

				}
				Festival festival = new Festival(fesName, time, place);
				festival.setDescription(description);
				HistoricalFigure historicalFigure = new HistoricalFigure(relatedFigure);
				festival.setFigure(historicalFigure);
				list.add(festival);
			}
		}
	}

	public static void main(String[] args) {
		AnGiangFestival anGiang = new AnGiangFestival();
		anGiang.scraping();
		String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/festivals.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Festival> festivals = anGiang.getList();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(festivals, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
