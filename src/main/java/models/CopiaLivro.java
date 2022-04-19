package models;

import java.util.Random;

public class CopiaLivro{
    private Number codigo;
    private Boolean Disponível;
    private Livro livro;
    private static final Random r = new Random();

    public CopiaLivro(Livro livro) {
        int a = 1;
        int b = 99999;
        this.codigo = r.nextInt((b - a) + 1) + a;
        Disponível = true;
        this.livro = livro;
    }

    public CopiaLivro(Number codigo, Boolean disponível, Livro livro) {
        this.codigo = codigo;
        Disponível = disponível;
        this.livro = livro;
    }

    public Number getCodigo() {
        return codigo;
    }

    public void setCodigo(Number codigo) {
        this.codigo = codigo;
    }

    public Boolean getDisponível() {
        return Disponível;
    }

    public void setDisponível(Boolean disponível) {
        Disponível = disponível;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Codigo=" + codigo +
                ", Disponível=" + Disponível +
                ", livro=" + livro.getTitulo();
    }
}
