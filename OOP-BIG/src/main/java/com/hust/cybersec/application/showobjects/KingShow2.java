package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.King;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KingShow2 {

    public KingShow2(King curSelect) throws IOException {
    		Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
        	
            // Get the controller associated with the FXML file
            ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Vua");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên Vua: " + curSelect.getName());
            controller.setLabel2("Năm sinh - năm mất: " + curSelect.getDob() + curSelect.getDod());
            controller.setLabel3("Trị vì: " + curSelect.getReignBegin() + "-" + curSelect.getReignEnd());
            controller.setLabel4("Tên gọi khác: " + curSelect.getAliases());
            controller.setLabel5("Link bài báo tìm hiểu thêm: " + curSelect.getPaperURL());
            controller.setLabel6("");
            controller.setLabel7("");
            
            // Remove the "Nội Dung" label from the bottom VBox
            controller.removeNoidungLabel();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();

    }

}
