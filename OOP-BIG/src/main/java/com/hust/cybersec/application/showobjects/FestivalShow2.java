package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Festival;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FestivalShow2 {

    public FestivalShow2(Festival curSelect) throws IOException {
        try {
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
        	ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Lễ hội văn hóa");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên lễ hội: " + curSelect.getName());
            controller.setLabel2("Địa điểm: " + curSelect.getPlace());
            controller.setLabel3("Thời gian: " + curSelect.getTime());
            controller.setLabel4("Nhân vật liên quan: " + curSelect.getFigure());
            controller.setLabel5("");
            controller.setLabel6("");
            controller.setLabel7("");
            // Remove the "Nội Dung" label from the bottom VBox
            controller.setNoteLabel("Nội dung:" + curSelect.getDescription());
            	
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
