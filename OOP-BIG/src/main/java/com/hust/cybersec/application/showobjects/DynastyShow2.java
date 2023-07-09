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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DynastyShow2 {

    public DynastyShow2(Dynasty curSelect) throws IOException {
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
            controller.setSceneTitle("Triều đại lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên triều đại: " + curSelect.getName());
            controller.setLabel2("Năm bắt đầu - kết thúc: " + curSelect.getStartYear());
            controller.setLabel3("Năm kết thúc: " +  curSelect.getStartYear());
            controller.setLabel4("Thủ đô: " +  curSelect.getCapital());
            controller.setLabel5("Người sáng lập: " +  ((curSelect.getFounder()==null) ? "N/A":curSelect.getFounder().getName()));
            controller.setLabel6("");
            controller.setLabel7("");
            // Remove the "Nội Dung" label from the bottom VBox
            controller.removeNoidungLabel();
            
            HBox moreInforContainer = new HBox();
            if (!(curSelect.getKings() == null)) {
	            for (int i = 0; i < curSelect.getKings().size(); i++) {
	                final int index = i;
	                Button moreInfoButton = new Button("" + curSelect.getKings().get(index).getName());
	                moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                    try {
							new KingShow2(curSelect.getKings().get(index));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                });
	                moreInforContainer.getChildren().add(moreInfoButton);
	            }
            }
            controller.addHboxBottom(moreInforContainer);
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
