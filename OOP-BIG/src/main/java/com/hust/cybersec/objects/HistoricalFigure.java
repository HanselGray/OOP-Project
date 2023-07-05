package com.hust.cybersec.objects;

public abstract class HistoricalFigure {
	protected int id;
	protected String namSinh;
	protected String namMat;
	protected String name;

	protected HistoricalFigure(String ten, String namSinh, String namMat) {
		this.namMat = namMat;
		this.namSinh = namSinh;
		this.name = ten;
	}

	public HistoricalFigure() {
	}

	public HistoricalFigure(String ten) {
		this.name = ten;
	}

	public String getNamSinh() {
		return (namSinh == null || namSinh.strip().equals("...") || namSinh.strip().equals("…")) ? "Chưa rõ" : namSinh;
	}

	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}

	public String getNamMat() {
		return (namMat == null || namMat.strip().equals("...") || namMat.strip().equals("…")) ? "Chưa rõ" : namMat;
	}

	public void setNamMat(String namMat) {
		this.namMat = namMat;
	}

	public String getName() {
		return name;
	}

	public void setName(String ten) {
		this.name = ten;
	}

	public int getId() {
		return id;
	}

}
