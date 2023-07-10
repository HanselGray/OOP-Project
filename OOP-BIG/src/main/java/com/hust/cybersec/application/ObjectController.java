package com.hust.cybersec.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ObjectController {
	@FXML
    private BorderPane borderPane;

    @FXML
    private Label titlelabel;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label notelabel;

    @FXML
    private Button backButton;

    
    public void setLabel1(String labelText) {
        label1.setText(labelText);
    }

    public void setLabel2(String labelText) {
        label2.setText(labelText);
    }

    public void setLabel3(String labelText) {
        label3.setText(labelText);
    }

    public void setLabel4(String labelText) {
        label4.setText(labelText);
    }

    public void setLabel5(String labelText) {
        label5.setText(labelText);
    }

    public void setLabel6(String labelText) {
        label6.setText(labelText);
    }

    public void setLabel7(String labelText) {
        label7.setText(labelText);
    }

    public void setNoteLabel(String labelText) {
        notelabel.setText(labelText);
    }

    public void setSceneTitle(String labelText) {
        titlelabel.setText(labelText);
    }
    
    // 
    public void removeNoidungLabel() {
        notelabel.setText(null);
    }
    

    @FXML
    private void backButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
//    @FXML
//    public void initialize() {
//        // Set up the event handler for the back button
//        backButton.setOnAction(event -> {
//			try {
//				switchToSearchScene(event);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//    }
//
//    public void switchToSearchScene(ActionEvent event) throws IOException {
//        Main.setRoot("/searchScene");
//    }
//    
    public void addHboxBottom(HBox info) throws IOException {
    	borderPane.setBottom(info);
    }
}
