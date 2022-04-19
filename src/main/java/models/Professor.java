package models;

public class Professor implements Usuario {
    public static final String titulo = "Professor";
    private String cpf;
    private Number prazoDevolucao;
    private String nome;

    public Professor() { }

    public Professor(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public Number getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public void setPrazoDevolucao(Number prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }

    public Number getPrazo(){
        return prazoDevolucao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return " CPF : '" + getCpf() +  "Nome : '" + getNome();
    }
}
