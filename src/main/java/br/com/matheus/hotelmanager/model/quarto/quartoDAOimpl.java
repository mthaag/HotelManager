package br.com.matheus.hotelmanager.model.quarto;

import br.com.matheus.hotelmanager.model.DBConnect;

import java.sql.ResultSet;

public class quartoDAOimpl extends DBConnect implements quartoDAO {

    public void novoQuarto(
            String tipo_quarto,
            int nu_quarto,
            int andar,
            Float preco
    ){
        this.setSql(
                "INSERT INTO quartos(" +
                        "tipo_quarto, " +
                        "nu_quarto, " +
                        "andar, " +
                        "preco " +
                        ") VALUES (" +
                        "'" + tipo_quarto + "', " +
                        "'" + nu_quarto + "', " +
                        "'" + andar + "', " +
                        "'" + preco +
                "');"
        );
        try{

            this.getStatement().executeQuery(this.getSql());
            System.out.println("Quarto cadastrado com sucesso!");

        }catch (Exception e){
            System.err.println(
                    "Não foi possível cadastrar novo Quarto"
            );
            e.printStackTrace();
        }
    }
    public ResultSet listaQuartos(){

        this.setSql("SELECT * FROM quartos");

        try{
            this.setResultset(this.getStatement().executeQuery(this.getSql()));
            /*while (this.getResultset().next()){
                System.out.println(
                        "Tipo do Quato: " +
                        this.getResultset().getString("tipo_quarto")
                );
                System.out.println("Número do quarto: " + this.getResultset().getString("nu_quarto"));
                System.out.println("Andar: " + this.getResultset().getString("andar"));
                System.out.println("-------------------------------");
            }*/
            return this.getResultset();

        }catch (Exception e){
            System.err.println(
                    "Não foi possível mostrar todos os Quartos"
            );
            e.printStackTrace();
        }
        return null;
    }
}
