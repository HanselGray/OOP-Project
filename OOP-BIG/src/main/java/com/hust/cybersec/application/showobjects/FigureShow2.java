package com.hust.cybersec.application.showobjects;

import java.io.IOException;
import java.util.List;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FigureShow2 {

    public FigureShow2(HistoricalFigure curSelect) throws IOException {
			Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
            ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Nhân vật lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên: " + curSelect.getName());
            controller.setLabel2("Năm sinh - năm mất: " + curSelect.getDob() + curSelect.getDod());
            controller.setLabel3("Quê quán: " + curSelect.getHometown());
            controller.setLabel4("Tên gọi khác: " + curSelect.getOtherAliases());
            controller.setLabel5("");
            controller.setLabel6("");
            controller.setLabel7("");
            // Remove the "Nội Dung" label from the bottom VBox
            if (curSelect.getNotes() != null) {
            	controller.setNoteLabel("Ghi chú:" + curSelect.getNotes());
            } else {
            	controller.removeNoidungLabel();
            }
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();

    }


}
