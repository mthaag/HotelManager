package br.com.matheus.hotelmanager.model.reservas;

import br.com.matheus.hotelmanager.model.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ReservasDAOimpl extends DBConnect implements ReservasDAO {


    public String getPreco(int nu_quarto){
        this.setSql(
                "SELECT * FROM quartos " +
                "WHERE nu_quarto = " + "'" +
                nu_quarto + "'"
        );
        System.out.println(this.getSql());
        String preco = "";
        try{
            this.setResultset(this.getStatement().executeQuery(this.getSql()));
            while (this.getResultset().next()){
                preco = this.getResultset().getString("preco");
                System.out.println(
                        "id e tipo do quarto: " +
                                this.getResultset().getInt("id") +
                                " " +
                                this.getResultset().getString("tipo_quarto")
                );
                System.out.println("numero do quarto: " + this.getResultset().getInt("nu_quarto"));
                System.out.println("andar: " + this.getResultset().getString("andar"));
                //System.out.println("preco: " + this.getResultset().getString("preco"));
                System.out.println("preco_teste: " + preco);
            }

            return preco;

        }catch (Exception e){
            System.err.println(
                    "Não foi possível achar Reserva"
            );
            e.printStackTrace();
        }
        return "0";
    }

    public int getIdQuarto(int nu_quarto){
        this.setSql(
                "SELECT * FROM quartos " +
                        "WHERE nu_quarto = " + "'" +
                        nu_quarto + "'"
        );
        System.out.println(this.getSql());
        int id_quarto = 0;
        try{
            this.setResultset(this.getStatement().executeQuery(this.getSql()));
            while (this.getResultset().next()){
                id_quarto = this.getResultset().getInt("id");
                System.out.println(
                        "id e tipo do quarto: " +
                                id_quarto +
                                " " +
                                this.getResultset().getString("tipo_quarto")
                );
                System.out.println("numero do quarto: " + this.getResultset().getInt("nu_quarto"));
                System.out.println("andar: " + this.getResultset().getString("andar"));
                System.out.println("preco: " + this.getResultset().getString("preco"));
            }

            return id_quarto;

        }catch (Exception e){
            System.err.println(
                    "Não foi possível achar Reserva"
            );
            e.printStackTrace();
        }
        return 0;
    }

    public void criaReservaCompleto(
            int nu_quarto,
            String id_cliente,
            String dt_entrada,
            String dt_saida
    ) throws ParseException {

        float preco = Float.parseFloat(getPreco(nu_quarto));
        int id_quarto = getIdQuarto(nu_quarto);
        System.out.println(preco + " / " +id_quarto);
        if (preco == 0 || id_quarto == 0){
            System.out.println("Reserva não pode ser efetuada! if");
        }else{
            criaReserva(
                    id_cliente,
                    id_quarto,
                    dt_entrada,
                    dt_saida,
                    (double) preco
            );
        }


    }

    public void criaReserva(
            String id_cliente,
            int id_quarto,
            String dt_entrada,
            String dt_saida,
            Double preco
    ) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dt_entrada_final = (Date)formatter.parse(dt_entrada);
        Date dt_saida_final = (Date)formatter.parse(dt_saida);

        long dias = dt_saida_final.getTime() - dt_entrada_final.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(dias, TimeUnit.MILLISECONDS);

        Double preco_final = preco * diffrence;

        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date_start = inputFormat.parse(dt_entrada);
        Date date_end = inputFormat.parse(dt_saida);
        String dt_entrada_formatado = outputFormat.format(date_start);
        String dt_saida_formatado = outputFormat.format(date_end);

        this.setSql(
                "INSERT INTO reservas(" +
                        "id_cliente, " +
                        "id_quarto, " +
                        "dt_reserva, " +
                        "dt_entrada, " +
                        "dt_saida, " +
                        "preco " +
                        ") VALUES (" +
                        "'" + id_cliente + "', " +
                        "'" + id_quarto + "', " +
                        "CURRENT_TIMESTAMP(), " +
                        "STR_TO_DATE('" + dt_entrada_formatado + "', '%d-%m-%Y %H:%i'), " +
                        "STR_TO_DATE('" + dt_saida_formatado + "', '%d-%m-%Y %H:%i'), " +
                        "'" + preco_final +
                        "');"
        );
        try{
            this.getStatement().executeQuery(this.getSql());
            System.out.println("Reserva cadastrada com sucesso!");

        }catch (Exception e){
            System.err.println(
                    "Não foi possível cadastrar nova Reserva"
            );
            e.printStackTrace();
        }
    }

    public ResultSet mostraReserva(){
        this.setSql("SELECT * " +
                " FROM reservas join clientes c " +
                "on id_cliente = c.cpf"
        );
        System.out.println(this.getSql());

        try{
            this.setResultset(
                    this.getStatement().executeQuery(
                            this.getSql()
                    )
            );
            /*while (this.getResultset().next()){
                System.out.println(
                        "Id: " +
                        this.getResultset().getString("id")
                );
                System.out.println("Nome do Cliente: " + this.getResultset().getString("c.nome"));
                System.out.println("Data de Entrada: " + this.getResultset().getString("dt_entrada"));
                System.out.println("Data de Saída: " + this.getResultset().getString("dt_saida"));
                System.out.println("Preco: " + this.getResultset().getString("preco"));
                System.out.println("-------------------------------");
            }*/

            return this.getResultset();

        }catch (Exception e){
            System.err.println(
                    "Não foi possível mostrar todos as Reservas"
            );
            e.printStackTrace();
        }
        return null;
    }

}
