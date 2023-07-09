package com.hust.cybersec.application.datareader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hust.cybersec.objects.Event;
import com.hust.cybersec.objects.Festival;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;



public class ReadData<T> {

    public static <T> List<T> readJson(String filePath, Class<T> type) {
        Gson gson = new Gson();
        List<T> objects = null;
        try {
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            objects = gson.fromJson(new FileReader(filePath), listType);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON file: " + e.getMessage());
        }
        return objects;
    }

    public List<T> FromJsonToArray(String relativefilePath, Class<T> objectType) throws IOException {
        String filePath = new File(System.getProperty("user.dir")).getParent() + relativefilePath;
        Gson gson = new Gson();
        List<T> result;
        Type listType = TypeToken.getParameterized(List.class, objectType).getType();
        result = gson.fromJson(new FileReader(filePath), listType);       
        return result;
    }
    
    // demo reader     
    public static void main(String[] args) throws IOException {
//        ReadData<Festival> reader = new ReadData<>();
//        List<Festival> list = reader.FromJsonToArray("/OOP-BIG/src/main/data/festivals.json", Festival.class);
//        ObservableList<Festival> listObservablesFestival = FXCollections.observableList(list);
//        for(Festival e : listObservablesFestival){
//            System.out.println(e);
//        }
    }
}
