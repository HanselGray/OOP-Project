package com.hust.cybersec.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {
	private static Scene scene;
	
	@FXML
	private Button startbutton;
	
	public void toSearchScene(ActionEvent event) throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) startbutton.getScene().getWindow();
        // do what you have to do
        stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("/searchScene.fxml"));

		// search background
		root.setStyle(
				"-fx-background-image:url('https://cdn.discordapp.com/attachments/985587859866148970/1127681820767567963/hinh-background-lich-su-hoa-tiet-trong-dong.png?width=900&height=786')"
				+ ";-fx-background-size : 100% 100%");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
