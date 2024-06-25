package br.com.matheus.hotelmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.resizableProperty().setValue(Boolean.FALSE);
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/LoginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        /*URL cssURL = getClass().getResource("/src/main/java/br/com/matheus/hotelmanager/view/css/mainPage.css");
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
        }*/
        //scene.getStylesheets().add(getClass().getResource("/src/main/java/br/com/matheus/hotelmanager/view/css/mainPage.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
