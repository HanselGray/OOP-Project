package com.hust.cybersec.objects;

public abstract class Figure {
	protected int id;
	protected String dob;
	protected String dod;
	protected String name;

	protected Figure(String name, String dob, String dod) {
		this.dod = dod;
		this.dob = dob;
		this.name = name;
	}

	public Figure() {
	}

	public Figure(String name) {
		this.name = name;
	}

	public String getDob() {
		return (dob == null || dob.strip().equals("...") || dob.strip().equals("…")) ? "Unknown" : dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDod() {
		return (dod == null || dod.strip().equals("...") || dod.strip().equals("…")) ? "Unknown" : dod;
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
