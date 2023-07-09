package com.hust.cybersec.datacrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.interfaces.*;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.HistoricalFigure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ScrapeFigure implements DataCombine, WriteToJSON {
	private LinkedList<HistoricalFigure> list = new LinkedList<>();

	public static void main(String[] args) {
		// ArrayList<String> links = new ArrayList<String>();
		ScrapeFigure figure = new ScrapeFigure();
		figure.combine();
		try {
			figure.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String replaceTrieuDai(String original) {
		String trieuDai = "";
		switch (original) {
			case "Bắc thuộc lần 1" ->  {
				trieuDai = trieuDai.concat("Nhà Triệu");
			}
			case "Trưng Nữ Vương" ->  {
				trieuDai = trieuDai.concat("Hai Bà Trưng");
			}
			case "Nhà Tiền Lý, Triệu" ->  {
				trieuDai = trieuDai.concat("Nhà Tiền Lý");
			}
			case "Hậu Trần" ->  {
				trieuDai = trieuDai.concat("Nhà Hậu Trần");
			}
			case "Trịnh - Nguyễn" ->  {
				trieuDai = trieuDai.concat("Nhà Hậu Lê");
			}
			case "Triều Lê Sơ" ->  {
				trieuDai = trieuDai.concat("Nhà Lê sơ");
			}
			case "Nam - Bắc Triều" ->  {
				trieuDai = trieuDai.concat("Nhà Mạc");
			}
			case "Nhà Nguyễn độc lập" ->  {
				trieuDai = trieuDai.concat("Nhà Nguyễn");
			}
			case "Pháp đô hộ" ->  {
				trieuDai = trieuDai.concat("Đế quốc Việt Nam");
			}
			case "Nước Việt Nam mới" ->  {
				trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
			}
			case "Dựng nước" ->  {
				trieuDai = trieuDai.concat("Hồng Bàng thị");
			}
			default -> {
				trieuDai = trieuDai.concat(original);
			}
		}
		return trieuDai;
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/figures.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";

		while (pageIndex <= 800) {
			String url = urlFirstHalf + Integer.toString(pageIndex);
			VanSu vanSu = new VanSu(url);
			vanSu.scraping();
			list.add(vanSu.getFigure());
			pageIndex += 1;
		}

		System.out.println("num of mem: " + list.size());
		for (HistoricalFigure historicalFigure : list) {
			List<Dynasty> dynastyList = historicalFigure.getDynasty();
			for (Dynasty dynasty : dynastyList) {
				String name = dynasty.getName();
				dynasty.setName(replaceTrieuDai(name));
			}
		}
	}
}
