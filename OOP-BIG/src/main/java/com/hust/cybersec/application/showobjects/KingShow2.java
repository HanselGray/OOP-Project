package com.hust.cybersec.application.showobjects;

import java.io.IOException;

import com.hust.cybersec.application.Main;
import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KingShow2 {

    public KingShow2(King curSelect) throws IOException {
    		Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(KingShow2.class.getResource("/objectScene.fxml"));
        	Parent root = loader.load();
//        	Main.setRoot("/objectScene");
        	// set background
        	root.setStyle(
					"-fx-background-image:url('https://cdn.discordapp.com/attachments/985587859866148970/1127681782670696529/download-background-lich-su-dep.png?width=900&height=786')"
					+ ";-fx-background-size : 100% 100%");
            // Get the controller associated with the FXML file
            ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Vua");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên Vua: " + curSelect.getName());
            controller.setLabel2("Năm sinh - năm mất: " + curSelect.getDob() +" - "+ curSelect.getDod());
            controller.setLabel3("Trị vì: " + curSelect.getReignBegin() + " - " + curSelect.getReignEnd());
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
