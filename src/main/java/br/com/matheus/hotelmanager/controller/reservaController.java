package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.reservas.Reservas;
import br.com.matheus.hotelmanager.model.reservas.ReservasDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class reservaController implements Initializable {


    public TextField cpftxt;
    public TextField quartoTxt;
    public DatePicker dt_entradaTxt;
    public DatePicker dt_saidaTxt;
    @FXML
    private TableView<Reservas> tabela;
    @FXML
    private TableColumn<Reservas, String> idTxt;
    @FXML
    private TableColumn<Reservas, String> nomeTxt;
    @FXML
    private TableColumn<Reservas, String> cpfTxt;
    @FXML
    private TableColumn<Reservas, Float> precoTxt;
    @FXML
    private TableColumn<Reservas, String> dtentradaTxt;
    @FXML
    private TableColumn<Reservas, String> dtsaidaTxt;

    public void clear(){
        cpftxt.setText("");
        quartoTxt.setText("");
    }

    @FXML
    void criaReserva() throws ParseException, SQLException {

        ReservasDAOimpl reserva = new ReservasDAOimpl();
        String cpf = cpftxt.getText();
        int quarto = Integer.parseInt(quartoTxt.getText());
        String data_entrada = dt_entradaTxt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String data_saida = dt_saidaTxt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reserva.criaReservaCompleto(quarto, cpf,data_entrada,data_saida);
        getDadosReserva();

    }

    @FXML
    void getDadosReserva() throws SQLException {

        ReservasDAOimpl buscaReserva = new ReservasDAOimpl();
        ResultSet getReservas = buscaReserva.mostraReserva();
        while (getReservas.next()){
            Reservas r = new Reservas();
            r.setNome(getReservas.getString("nome"));
            r.setCpf(getReservas.getString("cpf"));
            r.setId_reserva(getReservas.getInt("id"));
            r.setDt_entrada(getReservas.getString("dt_entrada"));
            r.setDt_saida(getReservas.getString("dt_saida"));
            r.setPreco(getReservas.getFloat("preco"));
            tabela.getItems().add(r);
        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idTxt.setCellValueFactory(new PropertyValueFactory<Reservas, String>("id_reserva"));
        nomeTxt.setCellValueFactory(new PropertyValueFactory<Reservas, String>("nome"));
        cpfTxt.setCellValueFactory(new PropertyValueFactory<Reservas, String>("cpf"));
        precoTxt.setCellValueFactory(new PropertyValueFactory<Reservas, Float>("preco"));
        dtentradaTxt.setCellValueFactory(new PropertyValueFactory<Reservas, String>("dt_entrada"));
        dtsaidaTxt.setCellValueFactory(new PropertyValueFactory<Reservas, String>("dt_saida"));
        try {
            getDadosReserva();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Não foi possível mostrar os dados na tabela!");
        }
    }

}
