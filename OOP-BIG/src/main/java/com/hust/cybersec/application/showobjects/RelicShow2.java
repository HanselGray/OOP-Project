package com.hust.cybersec.application.showobjects;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelicShow2 {

    public RelicShow2(Relic curSelect) throws IOException {
        try {
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
            ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Di tích lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên di tích: " + curSelect.getName());
            controller.setLabel2("Địa điểm: " + curSelect.getLocation());
            controller.setLabel3("Kiểu di tích: " + curSelect.getType());
            controller.setLabel4("Cấp: " + curSelect.getRank());
            controller.setLabel5("");
            controller.setLabel6("");
            controller.setLabel7("");
            
            String strTrieuDai = "";
            String strKings = "";
            String strFigures = "";
            LinkedList<Dynasty> newDynasty = new LinkedList<Dynasty>();
            LinkedList<King> newKings = new LinkedList<King>();
            LinkedList<Figure> newFigures = new LinkedList<Figure>();
//            for (int i = 0; i < curSelect.getDynastys().size(); i++) {
//
//                strTrieuDai += curSelect.getDynastys().get(i).getName() + ",";
//                for (int j = 0; j < listDynasty.size(); j++) {
//
//                    if (curSelect.getDynastys().get(i).getName().toLowerCase()
//                            .indexOf(listDynasty.get(j).getName().toLowerCase()) != -1) {
//                        newDynasty.add(listDynasty.get(j));
//                        System.out.println(listDynasty.get(j).getFounder().getName());
//                    }
//                }
//            }
//            for (int i = 0; i < curSelect.getKings().size(); i++) {
//
//                strKings += curSelect.getKings().get(i).getName() + ",";
//                for (int j = 0; j < listKings.size(); j++) {
//
//                    if (curSelect.getKings().get(i).getName().toLowerCase()
//                            .indexOf(listKings.get(j).getName().toLowerCase()) != -1) {
//                        newKings.add(listKings.get(j));
//
//                    }
//                }
//            }
//            for (int i = 0; i < curSelect.getFigures().size(); i++) {
//
//                strFigures += curSelect.getFigures().get(i).getName() + ",";
//                for (int j = 0; j < listFigures.size(); j++) {
//
//                    if (curSelect.getFigures().get(i).getName().toLowerCase()
//                            .indexOf(listFigures.get(j).getName().toLowerCase()) != -1) {
//                        newFigures.add(listFigures.get(j));
//
//                    }
//                }
//            }
//            
            
            controller.setLabel5("Triều đại: " + strTrieuDai);
            controller.setLabel6("Vua: " +  strKings);
            controller.setLabel7("Nhân vật lịch sử: " + strFigures);
            
            // Remove the "Nội Dung" label from the bottom VBox
            controller.setNoteLabel("Thông tin di tích: thờ" + curSelect.getDesc());
            	
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
