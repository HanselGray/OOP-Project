package com.hust.cybersec.objects;

public abstract class HistoricalFigure {
	protected int id;
	protected String dob;
	protected String dod;
	protected String name;

	protected HistoricalFigure(String name, String dob, String dod) {
		this.dod = dod;
		this.dob = dob;
		this.name = name;
	}

	public HistoricalFigure() {
	}

	public HistoricalFigure(String name) {
		this.name = name;
	}

	public String getDob() {
		return (dob == null || dob.strip().equals("...") || dob.strip().equals("…")) ? "Chưa rõ" : dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDod() {
		return (dod == null || dod.strip().equals("...") || dod.strip().equals("…")) ? "Chưa rõ" : dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}
