package com.hust.cybersec.objects;

import java.util.List;


public class Relic{
	private String location;
	private String type;
	private String rank;
	private String desc;
	private List<HistoricalFigure> historicalFigures;
	private List<King> kings;
	private List<Dynasty> dynastys;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<HistoricalFigure> getFigures() {
		return historicalFigures;
	}

	public void setFigures(List<HistoricalFigure> historicalFigures) {
		this.historicalFigures = historicalFigures;
	}

	public List<King> getKings() {
		return kings;
	}

	public void setKings(List<King> kings) {
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

	public List<Dynasty> getDynastys() {
		return dynastys;
	}

	public void setDynastys(List<Dynasty> dynastys) {
		this.dynastys = dynastys;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Relic(String name, String location, String type, String rank, String desc, List<HistoricalFigure> historicalFigures,
			List<King> kings, List<Dynasty> dynastys) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.desc = desc;
		this.historicalFigures = historicalFigures;
		this.kings = kings;
		this.dynastys = dynastys;
	}
}
