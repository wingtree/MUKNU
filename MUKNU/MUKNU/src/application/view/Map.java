package application.view;

import java.io.IOException;
import java.net.URL;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class Map extends Pane {
	double lat, lon;

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    String restaurant;


    public Map() {
        final URL urlGoogleMaps = getClass().getResource("map.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> e) {
                restaurant = e.getData();
            }
        });
        getChildren().add(webView);
        
        Button northGate = new Button("North");
        Button eastGate = new Button("East");
        Button mainGate = new Button("Main");
        Button westGate = new Button("West");
        Button choice = new Button("PICK!");
        Text instruction = new Text("     지도에서 식당을 고르고 PICK! 버튼을 눌러주세요");
        
        northGate.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		webEngine.executeScript("document.goToLocation(35.892436, 128.609272)");
        	}
        });
        
        eastGate.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		webEngine.executeScript("document.goToLocation(35.888525, 128.616400)");
        	}
        });
        
        mainGate.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		webEngine.executeScript("document.goToLocation(35.886161, 128.612948)");
        	}
        });
        
        westGate.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		webEngine.executeScript("document.goToLocation(35.888395, 128.603888)");
        	}
        });
        
        choice.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {        		
        		Restaurant r = new Restaurant();     		
        		try {
					r.Array();
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
				instruction.setText(r.show(restaurant));
        	}
        });

        HBox toolBar = new HBox();
        toolBar.getChildren().addAll(northGate, eastGate, mainGate, westGate, choice, instruction);
        
        getChildren().addAll(toolBar);
    }
}
