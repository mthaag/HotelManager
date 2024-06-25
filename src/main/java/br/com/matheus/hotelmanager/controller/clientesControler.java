package br.com.matheus.hotelmanager.controller;

import br.com.matheus.hotelmanager.model.cliente.Cliente;
import br.com.matheus.hotelmanager.model.cliente.ClienteDAO;
import br.com.matheus.hotelmanager.model.cliente.ClienteDAOimpl;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class clientesControler implements Initializable {
    
    public TextField nometxt;
    public TextField sobrenometxt;
    public TextField cpftxt;
    public TextField telefonetxt;
    public TextField enderecotxt;
    public TextField emailtxt;
    public TextField formPagtxt;
    public TableColumn idColumn;
    public TableColumn nomeColumn;
    public TableColumn cpfColumn;
    public TableColumn telefoneColumn;
    public TableColumn emailColumn;
    public TableColumn pagmentoColumn;
    public TableView tabela;

    public void clear(){
        nometxt.setText("");
        sobrenometxt.setText("");
        cpftxt.setText("");
        telefonetxt.setText("");
        enderecotxt.setText("");
        emailtxt.setText("");
        formPagtxt.setText("");
    }

    @FXML
    void cadastraCliente(){
        String nome = nometxt.getText();
        String sobrenome = sobrenometxt.getText();
        String cpf = cpftxt.getText();
        String telefone = telefonetxt.getText();
        String endereco = enderecotxt.getText();
        String email = emailtxt.getText();
        String formaPag = formPagtxt.getText();

        ClienteDAOimpl cliente = new ClienteDAOimpl();

        cliente.novoCliente(nome, sobrenome, cpf, endereco, formaPag, telefone, email);

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

    void getClientesTable() throws SQLException {
        ClienteDAOimpl buscaCliente = new ClienteDAOimpl();
        ResultSet getClientes = buscaCliente.listaClientes();
        while (getClientes.next()){
            Cliente c = new Cliente();
            c.setId(getClientes.getInt("id"));
            c.setNome(getClientes.getString("nome"));
            c.setCpf(getClientes.getString("cpf"));
            c.setTelefone(getClientes.getString("telefone"));
            c.setEmail(getClientes.getString("email"));
            c.setFormPagamento(getClientes.getString("forma_pag"));
            tabela.getItems().add(c);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        pagmentoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("formPagamento"));
        try {
            getClientesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
