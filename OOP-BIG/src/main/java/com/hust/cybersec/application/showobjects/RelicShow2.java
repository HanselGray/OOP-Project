package com.hust.cybersec.application.showobjects;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.hust.cybersec.application.Main;
import com.hust.cybersec.application.ObjectController;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RelicShow2 {

    public RelicShow2(Relic curSelect) throws IOException {
        try {
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/objectScene.fxml"));
        	Parent root = loader.load();
//        	Main.setRoot("/objectScene");
        	
        	// set background
        	root.setStyle(
					"-fx-background-image:url('https://cdn.discordapp.com/attachments/985587859866148970/1127681782670696529/download-background-lich-su-dep.png?width=900&height=786')"
					+ ";-fx-background-size : 100% 100%");
        	
            // Get the controller associated with the FXML file
            ObjectController controller = loader.getController();
            
            // Scene title
            controller.setSceneTitle("Di tích lịch sử");
            
            // Update the label text using the controller's methods
            controller.setLabel1("Tên di tích: " + curSelect.getName());
            controller.setLabel2("Địa điểm: " + curSelect.getLocation());
            controller.setLabel3("Kiểu di tích: " + curSelect.getType());
            controller.setLabel4("Cấp: " + curSelect.getRank());
            
            HBox moreInforContainer = new HBox();
            String strTrieuDai = "";
            String strKings = "";
            String strFigures = "";
            if (!(curSelect.getDynastys() == null)) {
            	for (int i = 0; i < curSelect.getDynastys().size(); i++) {
            		strTrieuDai += curSelect.getDynastys().get(i).getName() + ", ";
            	
                    final int index = i;
                    Button moreInfoButton = new Button("" + curSelect.getDynastys().get(index).getName());
                    moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                        try {
    						new DynastyShow2(curSelect.getDynastys().get(index));
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                    });
                    moreInforContainer.getChildren().add(moreInfoButton);
                }
            }
            if (!(curSelect.getKings() == null)) {
	            for (int i = 0; i < curSelect.getKings().size(); i++) {
	                strKings += curSelect.getKings().get(i).getName() + ", ";
	            
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
            if (!(curSelect.getFigures() == null)) {
	            for (int i = 0; i < curSelect.getFigures().size(); i++) {
	                strFigures += curSelect.getFigures().get(i).getName() + ", ";
	            
	                final int index = i;
	                Button moreInfoButton = new Button("" + curSelect.getFigures().get(index).getName());
	                moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                    try {
							new FigureShow2(curSelect.getFigures().get(index));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                });
	                moreInforContainer.getChildren().add(moreInfoButton);
	            }
            }
            controller.setLabel5("Triều đại: " + strTrieuDai);
            controller.setLabel6("Vua: " +  strKings);
            controller.setLabel7("Nhân vật lịch sử: " + strFigures);
            
            // Remove the "Nội Dung" label from the bottom VBox
            controller.setNoteLabel("Thông tin di tích: " + curSelect.getDesc());
            controller.addHboxBottom(moreInforContainer);
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
