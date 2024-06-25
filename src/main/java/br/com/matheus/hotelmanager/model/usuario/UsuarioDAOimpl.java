package br.com.matheus.hotelmanager.model.usuario;

import br.com.matheus.hotelmanager.model.DBConnect;

import java.sql.ResultSet;

public class UsuarioDAOimpl extends DBConnect implements UsuarioDAO{

    private ResultSet result;

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    @Override
    public boolean cadastraUsr(String nome_completo, String nome_usuario, String email, String senha) {
        this.setSql(
                "INSERT INTO usuarios(" +
                        "nm_completo, " +
                        "nm_usuario, " +
                        "email, " +
                        "senha " +
                        ") VALUES (" +
                        "'" + nome_completo + "', " +
                        "'" + nome_usuario + "', " +
                        "'" + email + "', " +
                        "'" + senha +
                        "');"
        );
        try{
            int linhas = this.getStatement().executeUpdate(this.getSql());
            if (linhas > 0){
                return true;
            }

        }catch (Exception e){
            System.err.println(
                    "Não foi possível cadastrar novo Usuário"
            );
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(String nome_usuario, String senha) {
        this.setSql(
                "SELECT * FROM usuarios" +
                " WHERE nm_usuario = " + "'" + nome_usuario + "'" +
                " AND senha = " + "'" + senha + "'"
        );
        try{

            setResult(this.getStatement().executeQuery(this.getSql()));
            if (getResult().next()) {
                System.out.println("Login efetuado com sucesso!");
                return true;
            }

        }catch (Exception e){
            System.err.println(
                    "Não foi possível logar!"
            );
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet selectUsuarios() {
        this.setSql("SELECT * FROM usuarios");
        try{

            setResult(this.getStatement().executeQuery(this.getSql()));
            return this.getResult();

        }catch (Exception e){
            System.err.println(
                    "Não foi possível buscar os dados de usuários!"
            );
            e.printStackTrace();
        }
        return null;
    }
}
