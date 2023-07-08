package com.hust.cybersec.datacrawler.dynasty;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import java.util.ArrayList;

import org.jsoup.select.Elements;

import com.hust.cybersec.objects.Dynasty;

public class DynastyYear extends BasicDataCrawler{
	private String dynastyName;
	private String beginYear;
	private String endYear;

	public DynastyYear(String dynastyName) {
		this.url = nameToUrl(dynastyName);
		this.dynastyName = dynastyName;		
		connect();
	}

	private String nameToUrl(String oldDynastyName) {
		String baseUrl = "https://vi.wikipedia.org/wiki/";
		String dynastyName;
		if (oldDynastyName.equals("Bắc thuộc lần I")) {
			dynastyName = "Thời kỳ Bắc thuộc lần thứ nhất";
		}else if (oldDynastyName.equals("Bắc thuộc lần II")){
			dynastyName = "Thời kỳ Bắc thuộc lần thứ hai";
		}else if (oldDynastyName.equals("Bắc thuộc lần III")){
			dynastyName = "Thời kỳ Bắc thuộc lần thứ ba";
		}else if (oldDynastyName.equals("Bắc thuộc lần IV")){
			dynastyName = "Thời kỳ Bắc thuộc lần thứ tư";
		}else {
			dynastyName = oldDynastyName;
		}
		String[] arrOfStr = dynastyName.split(" ");
		StringBuffer b = new StringBuffer();
		b.append(baseUrl);
		for (int i = 0; i < arrOfStr.length; i++) {
			b.append(arrOfStr[i]);
			if (i != arrOfStr.length - 1) {
				b.append("_");
			}
		}
		String url = b.toString();
		return url;
	}

	public String getdynastyName() {
		return dynastyName;
	}

	public String getBeginYear() {
		return beginYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void scraping() {
		// System.out.println(this.url);
		String allYears;
		if (this.dynastyName.equals("Cộng hòa Xã hội Chủ nghĩa Việt Nam")) {
			allYears = "1976–nay";
		} else if (this.dynastyName.equals("Thời tiền sử")) {
			allYears = "đầu–3100 TCN";
		} else if (this.dynastyName.equals("Hai Bà Trưng")) {
			Elements years = this.getDoc().select(
					"#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(4) > td > a");
			beginYear = years.get(0).attr("title");
			endYear = years.get(1).attr("title");
			allYears = beginYear + "–" + endYear;
		} else if (this.dynastyName.equals("Nhà Trần")) {
			Elements years = this.getDoc().select(
					"#mw-content-text > div.mw-parser-output > div.mw-stack.stack-container.stack-right > div:nth-child(1) > table > tbody > tr:nth-child(3) > td");
			allYears = years.text();
		} else if (this.dynastyName.equals("Nhà Hậu Lê")) {
			allYears = "1427–1789";
		} else if (this.dynastyName.equals("Họ Khúc")) {
			allYears = "923–930";
		} else if (this.dynastyName.equals("Hồng Bàng thị")) {
			allYears = "2879 TCN–258 TCN";
		} else if (this.dynastyName.equals("Tự chủ")) {
			allYears = "905–938";
		} else if (this.dynastyName.equals("Nhà Thục")) {
			allYears = "257 TCN–179 TCN";
		} else if (this.dynastyName.equals("Bắc thuộc lần IV")) {
			allYears = "1407–1427";
		} else {
			Elements years = this.getDoc()
					.select("#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(2) > td");
			if (!years.text().matches(".*[0-9].*")) {
				years = this.getDoc().select(
						"#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(3) > td");
			}
			allYears = years.text().split(" [0-9]")[0];
		}
		String[] arrOfStr = allYears.split("–");
		if (arrOfStr.length == 1) {
			beginYear = arrOfStr[0];
			endYear = arrOfStr[0];
		} else {
			beginYear = arrOfStr[0];
			endYear = arrOfStr[1];
		}
		// System.out.println(beginYear);
		// System.out.println(endYear);
	}

	public static void main(String[] args) {
		ArrayList<Dynasty> dynastys = new ArrayList<Dynasty>();
		DynastyName names = new DynastyName();
		names.scraping();
		for (String e : names.getDynasty_names()) {
			DynastyYear y = new DynastyYear(e);
			y.scraping();
			Dynasty dynasty = new Dynasty(y.getBeginYear(), y.getEndYear(), y.getdynastyName());
			dynastys.add(dynasty);
		}
		for (Dynasty d : dynastys) {
			System.out.println(d.getName() + " " + d.getStartYear() + " " + d.getEndYear());
		}

	}
}
