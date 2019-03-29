/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author wz
 */
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;  

public class MyWebViewSample extends Application {
	 public static final String defaultURL="http://www.baidu.com";  
    @Override  
    public void start(Stage primaryStage) {  
        init( primaryStage);  
        primaryStage.show();  
    }  
    private void init(Stage primaryStage){  
        final Stage stage=primaryStage;  
        Group group=new Group();//作为根节点，也就是root  
        primaryStage.setScene(new Scene(group));  
          
        WebView webView=new WebView();  
        final WebEngine engine=  webView.getEngine();  
        engine.load(defaultURL);  
          
        final TextField textField=new TextField(defaultURL);  
        /**修改输入栏的地址，也就是访问那个网站，这个地址栏显示那个网站的地址 
         * locationProperty()是获得当前页面的url封装好的ReadOnlyStringProperty对象 
         */  
        engine.locationProperty().addListener(new ChangeListener<String>(){  
            @Override  
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {  
                textField.setText(newValue);  
            }  
        });  
        /** 
         * 设置标题栏为当前访问页面的标题。 
         */  
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>(){  
            @Override  
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {  
                if(newValue==Worker.State.SUCCEEDED){  
                    stage.setTitle(engine.getTitle());  
                }  
            }  
        });  
          
          
       /** 
        * 测试能否获得javascript上面的交互内容。 
        * 可以自己写一个包含window.alert("neirong")的html进行测试。 
        * 返回的是neirong 
        */  
        engine.setOnAlert(new javafx.event.EventHandler<WebEvent<String>>() { 
  
          
            public void handle(WebEvent<String> event) {  
                System.out.println("this is event"+event);  
            }  
        });  
  
        //加载新的地址  
       final javafx.event.EventHandler<ActionEvent> handler = new javafx.event.EventHandler<ActionEvent>() {  
            @Override  
            public void handle(ActionEvent event) {  
            engine.load(textField.getText().startsWith("http://")?textField.getText().trim():"http://"+textField.getText().trim());  
            }  
        };  
          
        textField.setOnAction(handler);  
          
        Button okButton=new Button("go");  
        okButton.setDefaultButton(true);  
        okButton.setOnAction(handler);  
          
        HBox hbox=new HBox();  
        hbox.getChildren().addAll(textField,okButton);  
        HBox.setHgrow(textField, Priority.ALWAYS);  
          
        VBox vBox=new VBox();  
        vBox.getChildren().addAll(hbox,webView);  
        VBox.setVgrow(webView, Priority.ALWAYS);  
          
        group.getChildren().add(vBox);  
    }  
    /** 
     * 
     * @param args 
     */  
    public static void main(String[] args) {  
        launch(args);  
    }  
}

