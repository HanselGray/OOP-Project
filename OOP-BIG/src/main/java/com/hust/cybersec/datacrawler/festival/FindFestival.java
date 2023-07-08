package com.hust.cybersec.datacrawler.festival;


import com.hust.cybersec.objects.Festival;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.hust.cybersec.datacrawler.interfaces.DataCombine;
import com.hust.cybersec.datacrawler.interfaces.WriteToJSON;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FindFestival implements DataCombine, WriteToJSON {
	private ArrayList<Festival> list = new ArrayList<Festival>();

	public static void main(String[] args) {
		FindFestival fes = new FindFestival();
		fes.combine();
		try {
			fes.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		DaNangFestival daNang = new DaNangFestival();
		daNang.scraping();		
		AnGiangFestival anGiang = new AnGiangFestival();
		anGiang.scraping();
		Wikipedia obj = new Wikipedia();
		obj.scraping();		
		list.addAll(anGiang.getList());
		list.addAll(daNang.getList());
		list.addAll(obj.getList());
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = new File(System.getProperty("user.dir")).getParent() + "/OOP-BIG/src/main/data/festivals.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
