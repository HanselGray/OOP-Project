package com.hust.cybersec.objects;

public class Event {
	private String ten;
	private String thoi_gian;
	private String dia_diem;
	private Dynasty nien_dai;
	private Figure nhan_vat_lien_quan;

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getThoi_gian() {
		return thoi_gian;
	}

	public void setThoi_gian(String thoi_gian) {
		this.thoi_gian = thoi_gian;
	}

	public String getDia_diem() {
		return (dia_diem.equals("")) ? "Đang cập nhật" : dia_diem;
	}

	public void setDia_diem(String dia_diem) {
		this.dia_diem = dia_diem;
	}

	public Dynasty getNien_dai() {
		return nien_dai;
	}

	public void setNien_dai(Dynasty nien_dai) {
		this.nien_dai = nien_dai;
	}

	public HistoricalFigure getNhan_vat_lien_quan() {
		return nhan_vat_lien_quan;
	}

	public void setNhan_vat_lien_quan(String ten) {
		Figure fig = new Figure(ten) {
		};
		this.nhan_vat_lien_quan = fig;
	}
}