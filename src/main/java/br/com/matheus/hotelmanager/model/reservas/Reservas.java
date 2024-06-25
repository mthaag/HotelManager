package br.com.matheus.hotelmanager.model.reservas;

public class Reservas {

    private String id_cliente;
    private int id_reserva;
    private String cpf;
    private String nome;
    private int id_quarto;
    private String dt_reserva;
    private String dt_entrada;
    private String dt_saida;
    private Float preco;

    public Reservas(){}

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public String getDt_reserva() {
        return dt_reserva;
    }

    public void setDt_reserva(String dt_reserva) {
        this.dt_reserva = dt_reserva;
    }

    public String getDt_entrada() {
        return dt_entrada;
    }

    public void setDt_entrada(String dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public String getDt_saida() {
        return dt_saida;
    }

    public void setDt_saida(String dt_saida) {
        this.dt_saida = dt_saida;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

}
