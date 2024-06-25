package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.usuario.UsuarioDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cadastroController {

    public TextField nm_completo;
    public TextField nm_usuario;
    public TextField email;
    public TextField senha;

    @FXML
    void cadastraUsr(ActionEvent event) throws IOException {

        String nmCompleto, nmUsuario, emailText, senhatext;
        boolean verifica_cadastro;

        nmCompleto = nm_completo.getText();
        nmUsuario = nm_usuario.getText();
        emailText = email.getText();
        senhatext = senha.getText();

        UsuarioDAOimpl usuario = new UsuarioDAOimpl();

        verifica_cadastro = usuario.cadastraUsr(nmCompleto, nmUsuario, emailText, senhatext);

        System.out.println(verifica_cadastro);

        if (verifica_cadastro){
            System.out.println("Cadastro feito");
            URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/LoginView.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(
                    "Cadastro Inválido!\n" +
                            "Verifique se todos os campos estão preenchidos corretamente "
            );
            a.show();
        }

    }

    @FXML
    void cancelaCadastro(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/LoginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }


    public void initialize(URL location, ResourceBundle resources) {

    }

}
