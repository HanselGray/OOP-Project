package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Event;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.King;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DynastyShow2 {

    public DynastyShow2(Dynasty curSelect) throws IOException {
        try {
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
        	ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Triều đại lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên triều đại: " + curSelect.getName());
            controller.setLabel2("Năm bắt đầu - kết thúc: " + curSelect.getStartYear());
            controller.setLabel3("Năm kết thúc: " +  curSelect.getStartYear());
            controller.setLabel4("Thủ đô: " +  curSelect.getCapital());
            controller.setLabel5("Người sáng lập: " +  curSelect.getFounder());
            controller.setLabel6("");
            controller.setLabel7("");
            // Remove the "Nội Dung" label from the bottom VBox
            controller.removeNoidungLabel();
            
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
