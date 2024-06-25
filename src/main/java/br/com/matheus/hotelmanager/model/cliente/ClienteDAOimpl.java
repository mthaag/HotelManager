package br.com.matheus.hotelmanager.model.cliente;

import br.com.matheus.hotelmanager.model.DBConnect;

import java.sql.ResultSet;

public class ClienteDAOimpl extends DBConnect implements ClienteDAO{

    public void novoCliente(
            String nome,
            String sobrenome,
            String cpf,
            String endereco,
            String formPag,
            String telefone,
            String email
    ){
        this.setSql(
                "INSERT INTO clientes(" +
                "nome, " +
                "sobrenome, " +
                "cpf, " +
                "endereco, " +
                "forma_pag, " +
                "telefone, " +
                "email" +
                ") VALUES (" +
                "'" + nome + "', " +
                "'" + sobrenome + "', " +
                "'" + cpf + "', " +
                "'" + endereco + "', " +
                "'" + formPag + "', " +
                "'" + telefone + "', " +
                "'" + email +
                "');"
        );
        try{

            this.getStatement().executeQuery(this.getSql());
            System.out.println("Cliente cadastrado com sucesso!");

        }catch (Exception e){
            System.err.println(
                    "Não foi possível cadastrar novo cliente"
            );
            e.printStackTrace();
        }
    }

    public void buscaClienteCPF(String cpf){
        this.setSql(
                "SELECT * " +
                "FROM clientes " +
                "WHERE cpf = '" + cpf + "'"
        );

        try{

            this.setResultset(this.getStatement().executeQuery(this.getSql()));
            while (this.getResultset().next()){
                System.out.println(
                        "Nome Completo: " +
                                this.getResultset().getString("nome") +
                                " " +
                                this.getResultset().getString("sobrenome")
                );
                System.out.println("CPF: " + this.getResultset().getString("cpf"));
                System.out.println("Endereço: " + this.getResultset().getString("endereco"));
                System.out.println("Forma de pagamento: " + this.getResultset().getString("forma_pag"));
            }

        }catch (Exception e){
            System.err.println(
                    "Não foi possível encontrar o cliente desejado"
            );
            e.printStackTrace();
        }

    }

    public ResultSet listaClientes(){
        this.setSql("SELECT * FROM clientes");

        try{
            this.setResultset(this.getStatement().executeQuery(this.getSql()));
            /*while (this.getResultset().next()){
                System.out.println(
                        "Nome Completo: " +
                                this.getResultset().getString("nome") +
                                " " +
                                this.getResultset().getString("sobrenome")
                );
                System.out.println("CPF: " + this.getResultset().getString("cpf"));
                System.out.println("Endereço: " + this.getResultset().getString("endereco"));
                System.out.println("Forma de pagamento: " + this.getResultset().getString("forma_pag"));
                System.out.println("-------------------------------");
            }*/
            return this.getResultset();

        }catch (Exception e){
            System.err.println(
                    "Não foi possível mostrar todos os clientes"
            );
            e.printStackTrace();
        }
        return null;
    }

}
