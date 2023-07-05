package com.hust.cybersec.objects;

import java.util.*;


public class King extends HistoricalFigure {
	private String paperURL;
	private String reignBegin;
	private String reignEnd;
	private List<String> aliases = new ArrayList<>();

	public King(String name) {
		super(name);
	}

	public King() {
	}

	public String getPaperURL() {
		return paperURL;
	}

	public void setPaperURL(String paperURL) {
		this.paperURL = paperURL;
	}

	public String getReignBegin() {
		return reignBegin;
	}

	public void setReignBegin(String reignBegin) {
		this.reignBegin = reignBegin;
	}

	public String getReignEnd() {
		return reignEnd;
	}

	public void setReignEnd(String reignEnd) {
		this.reignEnd = reignEnd;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	public void setId(int id) {
		this.id = id;
	}

}
