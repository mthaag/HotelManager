package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.reservas.Reservas;
import br.com.matheus.hotelmanager.model.reservas.ReservasDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    @FXML
    public TableView tabela;
    @FXML
    public TableColumn clienteColumn;
    @FXML
    public TableColumn cpfColumn;
    @FXML
    public TableColumn dtEntradaColumn;
    @FXML
    public TableColumn dtSaidaColumn;
    @FXML
    public TableColumn nuQuartoColumn;
    @FXML
    public TableColumn idColumn;

    public void logout(MouseEvent mouseEvent) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/LoginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void chamaClientes(MouseEvent mouseEvent) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/ClientesView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.show();
    }

    public void chamaReserva(MouseEvent mouseEvent) throws IOException, SQLException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/ReservasView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reservas");
        stage.setScene(scene);
        stage.show();
    }

    public void chamaUsuarios(MouseEvent mouseEvent) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/UsuariosView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Usuários");
        stage.setScene(scene);
        stage.show();
    }

    public void chamaQuartos(MouseEvent mouseEvent) throws IOException {
        URL url = new File("src/main/java/br/com/matheus/hotelmanager/view/QuartosView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Quartos");
        stage.setScene(scene);
        stage.show();
    }

    void getReservasTable() throws SQLException {
        ReservasDAOimpl buscaReserva = new ReservasDAOimpl();
        ResultSet getReservas = buscaReserva.mostraReserva();
        while (getReservas.next()){
            Reservas r = new Reservas();
            r.setId_reserva(getReservas.getInt("id"));
            r.setNome(getReservas.getString("nome"));
            r.setCpf(getReservas.getString("cpf"));
            r.setDt_entrada(getReservas.getString("dt_entrada"));
            r.setDt_saida(getReservas.getString("dt_saida"));
            r.setId_quarto(getReservas.getInt("id_quarto"));
            tabela.getItems().add(r);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Reservas, Integer>("id_reserva"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<Reservas,String>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<Reservas,String>("cpf"));
        dtEntradaColumn.setCellValueFactory(new PropertyValueFactory<Reservas, String>("dt_entrada"));
        dtSaidaColumn.setCellValueFactory(new PropertyValueFactory<Reservas, String>("dt_saida"));
        nuQuartoColumn.setCellValueFactory(new PropertyValueFactory<Reservas, Integer>("id_quarto"));
        try {
            getReservasTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
