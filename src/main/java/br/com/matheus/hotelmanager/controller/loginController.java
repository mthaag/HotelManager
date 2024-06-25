package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.usuario.UsuarioDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Hyperlink cadastro;

    @FXML
    private TextField senha;

    @FXML
    private TextField usuario;

    private void entraSistema(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/MainPageView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Página Principal");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        String usr, pass;
        boolean verifica_login;
        usr = usuario.getText();
        pass = senha.getText();
        UsuarioDAOimpl login = new UsuarioDAOimpl();
        verifica_login = login.login(usr, pass);
        if (verifica_login){
            entraSistema(event);
        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(
                    "Login Inválido!\n" +
                            "Verifique se os dados estão corretos."
            );
            a.show();
        }
    }

    @FXML
    void chamaCadastro(ActionEvent event) throws IOException {

        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/CadastroUsrView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
