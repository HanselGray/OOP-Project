package com.hust.cybersec.datacrawler.interfaces;

import java.io.IOException;

import com.google.gson.JsonIOException;

public interface WriteToJSON {
	public void writeJSon() throws JsonIOException, IOException;
}
