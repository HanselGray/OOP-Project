package com.hust.cybersec.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/searchScene.fxml"));
			// Group root = new Group();
			// welcome background
			root.setStyle(
					"-fx-background-image:url('https://viettrieu.edu.vn/wp-content/uploads/2023/04/343089046_748889476966305_6068933904509204170_n.jpg?width=900&height=786')"
					+ ";-fx-background-size : 100% 100%");
			// search background
			root.setStyle(
					"-fx-background-image:url('https://cdn.discordapp.com/attachments/985587859866148970/1127681820767567963/hinh-background-lich-su-hoa-tiet-trong-dong.png?width=900&height=786')"
					+ ";-fx-background-size : 100% 100%");
			scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
