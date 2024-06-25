package br.com.matheus.hotelmanager.model.quarto;

import java.sql.ResultSet;

public interface quartoDAO {

    public void novoQuarto(
            String tipo_quarto,
            int nu_quarto,
            int andar,
            Float preco
    );
    //public void buscaClienteCPF(String cpf);
    public ResultSet listaQuartos();

}
