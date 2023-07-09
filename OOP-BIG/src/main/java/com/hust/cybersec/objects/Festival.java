package com.hust.cybersec.objects;

public class Festival {
	private String name;
	private String time;
	private String place;
	private HistoricalFigure historicalFigure;
	private String description;

	public Festival(String name, String time, String place) {
		this.name = name;
		this.time = time;
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public HistoricalFigure getFigure() {
		return historicalFigure;
	}

	public void setFigure(HistoricalFigure historicalFigure) {
		this.historicalFigure = historicalFigure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
