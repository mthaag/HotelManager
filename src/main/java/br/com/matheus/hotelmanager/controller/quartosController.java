package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.quarto.Quarto;
import br.com.matheus.hotelmanager.model.quarto.quartoDAOimpl;
import br.com.matheus.hotelmanager.model.reservas.Reservas;
import br.com.matheus.hotelmanager.model.reservas.ReservasDAOimpl;
import javafx.event.ActionEvent;
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

public class quartosController implements Initializable {
    public TableColumn idColumn;
    public TableColumn tipoQuartoColumn;
    public TableColumn nuColumn;
    public TableColumn andarColumn;
    public TableColumn precoColumn;
    public TableView tabela;
    public TextField tipoTxt;
    public TextField nuTxt;
    public TextField andarTxt;
    public TextField precoTxt;

    void getQuartosTable() throws SQLException {
        quartoDAOimpl buscaQuartos = new quartoDAOimpl();
        ResultSet getQuartos = buscaQuartos.listaQuartos();
        while (getQuartos.next()){
            Quarto q = new Quarto();
            q.setId(getQuartos.getInt("id"));
            q.setTipo_quarto(getQuartos.getString("tipo_quarto"));
            q.setNu_quarto(getQuartos.getInt("nu_quarto"));
            q.setAndar(getQuartos.getInt("andar"));
            q.setPreco(getQuartos.getFloat("preco"));
            tabela.getItems().add(q);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Quarto, Integer>("id"));
        tipoQuartoColumn.setCellValueFactory(new PropertyValueFactory<Quarto, String>("tipo_quarto"));
        nuColumn.setCellValueFactory(new PropertyValueFactory<Quarto, Integer>("nu_quarto"));
        andarColumn.setCellValueFactory(new PropertyValueFactory<Quarto, Integer>("andar"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<Quarto, Float>("preco"));
        try {
            getQuartosTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        tipoTxt.setText("");
        nuTxt.setText("");
        andarTxt.setText("");
        precoTxt.setText("");
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

    public void cadastraQuarto(ActionEvent actionEvent) {
        quartoDAOimpl quarto = new quartoDAOimpl();
        String tipo = tipoTxt.getText();
        int nu_quarto = Integer.parseInt(nuTxt.getText());
        int andar = Integer.parseInt(andarTxt.getText());
        Float preco = Float.parseFloat(precoTxt.getText());
        quarto.novoQuarto(tipo, nu_quarto, andar, preco);
        clear();
    }
}
