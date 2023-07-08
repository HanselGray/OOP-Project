package com.hust.cybersec.objects;

import java.util.ArrayList;


public class Relic{
	private String location;
	private String type;
	private String rank;
	private String desc;
	private ArrayList<Figure> figures;
	private ArrayList<King> kings;
	private ArrayList<Dynasty> dynastys;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Figure> getFigures() {
		return figures;
	}

	public void setFigures(ArrayList<Figure> figures) {
		this.figures = figures;
	}

	public ArrayList<King> getKings() {
		return kings;
	}

	public void setKings(ArrayList<King> kings) {
		this.kings = kings;
	}

	public String getType() {
		return type;
	}

	public String getRank() {
		return rank;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList<Dynasty> getDynastys() {
		return dynastys;
	}

	public void setDynastys(ArrayList<Dynasty> dynastys) {
		this.dynastys = dynastys;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Relic(String name, String location, String type, String rank, String desc, ArrayList<Figure> figures,
			ArrayList<King> kings, ArrayList<Dynasty> dynastys) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.desc = desc;
		this.figures = figures;
		this.kings = kings;
		this.dynastys = dynastys;
	}
}
