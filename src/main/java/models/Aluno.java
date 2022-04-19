package models;

public class Aluno implements Usuario {
    private final static String titulo = "Aluno";
    private String cpf;
    private Number prazoDevolucao = 5;
    private String nome;

    public Aluno(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Number getPrazoDevolucao() {
        return null;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPrazoDevolucao(Number prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome: '" + getNome() + '\'' +
                "CPF : '" + getCpf() ;
    }
}
