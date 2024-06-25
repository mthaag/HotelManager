package br.com.matheus.hotelmanager.model.cliente;

import java.sql.ResultSet;

public interface ClienteDAO {

    public void novoCliente(
            String nome,
            String sobrenome,
            String cpf,
            String endereco,
            String formPag,
            String telefone,
            String email
    );
    public void buscaClienteCPF(String cpf);
    public ResultSet listaClientes();

}
