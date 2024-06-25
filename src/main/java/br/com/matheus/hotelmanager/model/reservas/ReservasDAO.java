package br.com.matheus.hotelmanager.model.reservas;

import java.sql.ResultSet;
import java.text.ParseException;

public interface ReservasDAO {

    public void criaReserva(
            String id_cliente,
            int id_quarto,
            String dt_entrada,
            String dt_saida,
            Double preco
    ) throws ParseException;

    public ResultSet mostraReserva();

}
