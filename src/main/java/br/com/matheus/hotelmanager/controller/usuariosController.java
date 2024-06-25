package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.cliente.Cliente;
import br.com.matheus.hotelmanager.model.cliente.ClienteDAOimpl;
import br.com.matheus.hotelmanager.model.usuario.Usuario;
import br.com.matheus.hotelmanager.model.usuario.UsuarioDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class usuariosController implements Initializable {


    public TextField nometxt;
    public TextField nomeUsrTxt;
    public TextField emailtxt;
    public TextField senhatxt;
    @FXML
    public TableView tabela;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nmUsuarioColumn;
    @FXML
    public TableColumn emailColumn;

    public void clear(){
        nometxt.setText("");
        nomeUsrTxt.setText("");
        emailtxt.setText("");
        senhatxt.setText("");
    }

    public void cadastraUsr(ActionEvent actionEvent) {

        UsuarioDAOimpl usuario = new UsuarioDAOimpl();
        usuario.cadastraUsr(
                nometxt.getText(),
                nomeUsrTxt.getText(),
                emailtxt.getText(),
                senhatxt.getText()
        );
        clear();
    }

    public void voltaMainPage(MouseEvent event) throws IOException {

        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/MainPageView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Página Principal");
        stage.setScene(scene);
        stage.show();

    }

    void getUsuariosTable() throws SQLException {
        UsuarioDAOimpl buscaUsuario = new UsuarioDAOimpl();
        ResultSet getUsuarios = buscaUsuario.selectUsuarios();
        while (getUsuarios.next()){
            Usuario u = new Usuario();
            u.setId(getUsuarios.getInt("id"));
            u.setNome_usuario(getUsuarios.getString("nm_usuario"));
            u.setEmail(getUsuarios.getString("email"));
            tabela.getItems().add(u);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id"));
        nmUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome_usuario"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
        try {
            getUsuariosTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
