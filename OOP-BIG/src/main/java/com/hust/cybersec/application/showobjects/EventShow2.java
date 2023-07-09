package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Event;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.King;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventShow2 {

    public EventShow2(Event curSelect) throws IOException {
        try {
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
        	ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Sự kiện lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên sự kiện: " + curSelect.getName());
            controller.setLabel2("Thời gian diễn ra: " + curSelect.getTime());
            controller.setLabel3("Địa điểm: " + curSelect.getPlaces() != null ? curSelect.getPlaces(): "Đang cập nhật");
            controller.setLabel4("");
            controller.setLabel5("");
            controller.setLabel6("");
            controller.setLabel7("");
            
            // Remove the "Nội Dung" label from the bottom VBox
            if (curSelect.getResult() != null) {
            	controller.setNoteLabel("Kết quả:" + curSelect.getResult());
            } else {
            	controller.removeNoidungLabel();
            }
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
