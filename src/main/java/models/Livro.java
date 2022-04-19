package models;

import java.util.List;
import java.util.Random;

public class Livro {
    private Number id;
    private String titulo;
    private Autor autor;
    private Number ano;
    private Generos genero;
    private static final Random r = new Random();

    public Livro(String titulo, Autor autor, Number ano, Generos genero) {
        int a = 1;
        int b = 99999;
        this.id = r.nextInt((b - a) + 1) + a;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.genero = genero;
    }

    public Livro(Number id, String titulo, Autor autor, Number ano, Generos genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.genero = genero;
    }

    public Livro(){ }


    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Number getAno() {
        return ano;
    }

    public void setAno(Number ano) {
        this.ano = ano;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Id : " + id +
                ", titulo : " + titulo;
    }
}
