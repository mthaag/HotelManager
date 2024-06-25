package br.com.matheus.hotelmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {

    private Connection Conn;
    private Statement statement;
    private String sql;
    private ResultSet resultset;

    public DBConnect(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection Conexao = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/teste",
                    "root",
                    "123456"
            );
            this.setConn(Conexao);
            this.setStatement(getConn().createStatement());

            System.out.println("Conectado ao Banco de Dados");
        }catch (Exception e){
            System.err.println(
                    "Não foi possível conectar com o banco de dados."
            );
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return Conn;
    }

    public void setConn(Connection conn) {
        Conn = conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }

}
