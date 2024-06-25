package br.com.matheus.hotelmanager.model.quarto;

public class Quarto {

    private int id;
    private String tipo_quarto;
    private int nu_quarto;
    private int andar;
    private Float preco;

    public Quarto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getTipo_quarto() {
        return tipo_quarto;
    }

    public void setTipo_quarto(String tipo_quarto) {
        this.tipo_quarto = tipo_quarto;
    }

    public int getNu_quarto() {
        return nu_quarto;
    }

    public void setNu_quarto(int nu_quarto) {
        this.nu_quarto = nu_quarto;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

}
