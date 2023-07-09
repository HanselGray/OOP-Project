package com.hust.cybersec.application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.hust.cybersec.application.datareader.ReadData;
import com.hust.cybersec.application.search.Search;
import com.hust.cybersec.application.showobjects.DynastyShow2;
import com.hust.cybersec.application.showobjects.EventShow2;
import com.hust.cybersec.application.showobjects.FestivalShow2;
import com.hust.cybersec.application.showobjects.FigureShow2;
import com.hust.cybersec.application.showobjects.KingShow2;
import com.hust.cybersec.application.showobjects.RelicShow2;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Event;
import com.hust.cybersec.objects.Festival;
import com.hust.cybersec.objects.Figure;
import com.hust.cybersec.objects.HistoricalFigure;
import com.hust.cybersec.objects.King;
import com.hust.cybersec.objects.Relic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchController {
	@FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem figure;

    @FXML
    private MenuButton searchField;

    @FXML
    private MenuItem king;

    @FXML
    private MenuItem dynasty;

    @FXML
    private MenuItem relic;

    @FXML
    private MenuItem festival;

    @FXML
    private TableView<?> tableview;

    @FXML
    private MenuItem historicalevent;

    @FXML
    private TextField textField;

    @FXML
    private Button deletebutton;

    
    @FXML
    void kingoption(ActionEvent event) throws IOException {
    	searchField.setText(king.getText());
    	
    	List<King> listKing = new ReadData<King>()
                .FromJsonToArray("/OOP-BIG/src/main/data/kings.json", King.class);
        ObservableList<King> listObservablesKing = FXCollections.observableList(listKing);
        
        String[] nameColKing = { "Tên Vua", "Năm trị vì", "Năm kết thúc", "Tên gọi khác" };
        String[] kingStr = { "name", "reignBegin", "reignEnd", "aliases" };
        TableView<King> tableKingView = new TableViewCustom<King>().makTableView(
                nameColKing, kingStr);
        
        // showing objectScene
        tableKingView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                King curSelect = tableKingView.getSelectionModel().getSelectedItem();
				try {
					new KingShow2(curSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        // search
        Search<King> searchKing = new Search<King>();
        
        // generate table
        tableKingView.setItems(searchKing.searchList(listObservablesKing, textField, King.class));
        borderPane.setCenter(tableKingView);
    }

    @FXML
    void figureoption(ActionEvent event) throws IOException {
    	searchField.setText(figure.getText());
    	List<HistoricalFigure> listFigure = new ReadData<HistoricalFigure>()
                .FromJsonToArray("/OOP-BIG/src/main/data/figures.json", HistoricalFigure.class);
        ObservableList<HistoricalFigure> listObservablesFigure = FXCollections.observableList(listFigure);
    
        String[] nameColFigure = { "Tên Nhân Vật", "Năm sinh", "Năm mất", "Quê quán"};
        String[] figureStr = { "name", "dob", "dod", "Hometown"};
        TableView<HistoricalFigure> tableFigureView = new TableViewCustom<HistoricalFigure>().makTableView(
                nameColFigure, figureStr);
        tableFigureView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
            	HistoricalFigure curSelect = tableFigureView.getSelectionModel().getSelectedItem();
                try {
					new FigureShow2(curSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        Search<HistoricalFigure> searchFigure = new Search<HistoricalFigure>();
        tableFigureView.setItems(searchFigure.searchList(listObservablesFigure, textField, HistoricalFigure.class));
        borderPane.setCenter(tableFigureView);
    }

    @FXML
    void dynastyoption(ActionEvent event) throws IOException {
    	searchField.setText(dynasty.getText());
    	List<Dynasty> listDynasty = new ReadData<Dynasty>()
                .FromJsonToArray("/OOP-BIG/src/main/data/dynasties.json", Dynasty.class);
        ObservableList<Dynasty> listObservablesDynasty = FXCollections.observableList(listDynasty);

        String[] nameColDynasty = { "Tên Triều đại", "Năm bất đầu", "Năm kết thúc", "Kinh đô" };
        String[] dynastyStr = { "name", "startYear", "endYear", "capital" };
        TableView<Dynasty> tableDynastyView = new TableViewCustom<Dynasty>().makTableView(
                nameColDynasty, dynastyStr);
        tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Dynasty curSelect = tableDynastyView.getSelectionModel().getSelectedItem();
                try {
					new DynastyShow2(curSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        Search<Dynasty> searchDynasty = new Search<Dynasty>();
        tableDynastyView.setItems(searchDynasty.searchList(listObservablesDynasty, textField, Dynasty.class));
        borderPane.setCenter(tableDynastyView);
    }

    @FXML
    void relicoption(ActionEvent event) throws IOException {
    	searchField.setText(relic.getText());
    	List<Relic> listRelic = new ReadData<Relic>()
                .FromJsonToArray("/OOP-BIG/src/main/data/relics.json", Relic.class);
        ObservableList<Relic> listObservablesRelic = FXCollections.observableList(listRelic);

        String[] nameColRelic = { "Tên Di tích", "Địa điểm", "Thể loại", "Xếp hạng" };
        String[] RelicStr = { "name", "location", "type", "rank" };
        TableView<Relic> tableRelicView = new TableViewCustom<Relic>().makTableView(
                nameColRelic, RelicStr);
        tableRelicView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Relic curSelect = tableRelicView.getSelectionModel().getSelectedItem();
                try {
					new RelicShow2(curSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        Search<Relic> searchRelic = new Search<Relic>();
        tableRelicView.setItems(searchRelic.searchList(listObservablesRelic, textField, Relic.class));
        borderPane.setCenter(tableRelicView);
    }

    @FXML
    void eventoption(ActionEvent event) throws IOException {
    	 searchField.setText(historicalevent.getText());
    	 List<Event> listEvent = new ReadData<Event>()
                 .FromJsonToArray("/OOP-BIG/src/main/data/events.json", Event.class);
         ObservableList<Event> listObservablesEvent = FXCollections.observableList(listEvent);

         String[] nameColSuKien = { "Tên sự kiện", "Thời gian diễn ra", "Địa điểm", "Kết quả" };
         String[] SuKienStr = { "name", "time", "places", "result" };
         TableView<Event> tableEventView = new TableViewCustom<Event>().makTableView(
                 nameColSuKien, SuKienStr);
         tableEventView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
             if (e.getClickCount() > 1) {
                 Event curSelect = tableEventView.getSelectionModel().getSelectedItem();
                 try {
						new EventShow2(curSelect);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
             }
         });
         Search<Event> searchEvent = new Search<Event>();
         tableEventView.setItems(searchEvent.searchList(listObservablesEvent, textField, Event.class));
         borderPane.setCenter(tableEventView);
    }

    @FXML
    void festivaloption(ActionEvent event) throws IOException {
    	searchField.setText(festival.getText());
    	List<Festival> listFestival = new ReadData<Festival>()
                .FromJsonToArray("/OOP-BIG/src/main/data/festivals.json", Festival.class);
        ObservableList<Festival> listObservablesFestival = FXCollections.observableList(listFestival);
    
        String[] nameColFestival = { "Tên Lễ hội", "Thời gian", "Địa điểm"};
        String[] festivalStr = { "name", "time", "place"};
        TableView<Festival> tableFestivalView = new TableViewCustom<Festival>().makTableView(
                nameColFestival, festivalStr);
        tableFestivalView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Festival curSelect = tableFestivalView.getSelectionModel().getSelectedItem();
                try {
					new FestivalShow2(curSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        Search<Festival> searchFestival = new Search<Festival>();
        tableFestivalView
                .setItems(searchFestival.searchList(listObservablesFestival, textField, Festival.class));
        borderPane.setCenter(tableFestivalView);
    }


    @FXML
    void deleteclick(ActionEvent event) {
    	textField.clear();
    }

    
}
class TableViewCustom<T> {
    public TableView<T> makTableView(String[] nameColT, String[] TStr) {
        TableView<T> tableTView = new TableView<>();
        tableTView.setPrefWidth(834);
        tableTView.setMaxWidth(834);
        tableTView.setPrefHeight(433);
        tableTView.setMaxHeight(433);
        tableTView.getColumns().clear();

        for (int i = 0; i < TStr.length; i++) {
            TableColumn<T, String> colT = new TableColumn<>(nameColT[i]);
            colT.prefWidthProperty().bind(tableTView.widthProperty().multiply(1.0 / TStr.length));
            colT.setCellValueFactory(new PropertyValueFactory<>(TStr[i]));
            tableTView.getColumns().add(colT);
        }

        return tableTView;
    }
}
