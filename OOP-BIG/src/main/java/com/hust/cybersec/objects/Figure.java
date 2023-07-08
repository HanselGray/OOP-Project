package com.hust.cybersec.objects;

import java.util.ArrayList;



// import javafx.scene.chart.Axis.TickMark;


public class Figure extends HistoricalFigure {
	private String Hometown;
	private String Ethnicity;
	private String namNhapNgu;
	private String notes;
	private String namDoTrangNguyen;
	private String otherAliases;
	private King doiVua;

	private List<Dynasty> dynasty = new ArrayList<>();

	public void setOtherAliases(String otherAliases) {
		this.otherAliases = otherAliases;
	}

	public String getOtherAliases() {
		return otherAliases;
	}

	public Figure(String ten, String namSinh, String namMat, String Hometown, String Ethnicity, String namNhapNgu,
			String notes, String namDoTrangNguyen) {
		super(ten, namSinh, namMat);
		this.Hometown = Hometown;
		this.Ethnicity = Ethnicity;
		this.namNhapNgu = namNhapNgu;
		this.notes = notes;
		this.namDoTrangNguyen = namDoTrangNguyen;

	}

	public Figure(String ten, String Hometown, String Ethnicity, String namNhapNgu, String notes,
			String namDoTrangNguyen) {
		super(ten);
		this.Hometown = Hometown;
		this.Ethnicity = Ethnicity;
		this.namNhapNgu = namNhapNgu;
		this.notes = notes;
		this.namDoTrangNguyen = namDoTrangNguyen;
	}

	public Figure(String ten, String namSinh, String namMat, String Hometown, String notes, String otherAliases,
			ArrayList<Dynasty> dynasty) {
		super(ten, namSinh, namMat);
		this.Hometown = Hometown;
		this.notes = notes;
		this.otherAliases = otherAliases;
		this.dynasty = dynasty;
	}

	public Figure() {
	}

	public String getNamDoTrangNguyen() {
		return namDoTrangNguyen;
	}

	public void setNamDoTrangNguyen(String namDoTrangNguyen) {
		this.namDoTrangNguyen = namDoTrangNguyen;
	}

	public King getDoiVua() {
		return doiVua;
	}

	public void setDoiVua(King doiVua) {
		this.doiVua = doiVua;
	}

	public Figure(String ten) {
		super(ten);
	}

	public Figure(String ten, String namSinh, String namMat) {
		super(ten, namSinh, namMat);
	}

	public String getHometown() {
		return Hometown;
	}

	public void setHometown(String Hometown) {
		this.Hometown = Hometown;
	}

	public String getEthnicity() {
		return Ethnicity;
	}

	public void setEthnicity(String Ethnicity) {
		this.Ethnicity = Ethnicity;
	}

	public String getNamNhapNgu() {
		return namNhapNgu;
	}

	public void setNamNhapNgu(String namPhongChuc) {
		this.namNhapNgu = namPhongChuc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Dynasty> getDynasty() {
		return dynasty;
	}

	public void setDynasty(List<Dynasty> dynasty) {
		this.dynasty = dynasty;
	}

}
