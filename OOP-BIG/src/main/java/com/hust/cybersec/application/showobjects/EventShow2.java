package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.Main;
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
        	
        	// set background
        	root.setStyle(
					"-fx-background-image:url('https://cdn.discordapp.com/attachments/985587859866148970/1127681782670696529/download-background-lich-su-dep.png?width=900&height=786')"
					+ ";-fx-background-size : 100% 100%");
        	
            // Get the controller associated with the FXML file
        	ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Sự kiện lịch sử");
            
            // Update the label text using the controller's methods
            String factions = "";
            if (!(curSelect.getFactions() == null)) {
	            for(int i=0; i < curSelect.getFactions().size(); i++) {
	            	factions += curSelect.getFactions().get(i) + ", ";
	            }
            }
            
            controller.setLabel1("Tên sự kiện: " + curSelect.getName());
            controller.setLabel2("Thời gian diễn ra: " + curSelect.getTime());
            controller.setLabel3("Địa điểm: " + curSelect.getPlaces());
            controller.setLabel4("Bên tham chiến: " + factions );
            controller.setLabel5("Kết quả: ");
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
