package models;

import java.util.Random;

public class Autor {
    private String nome;
    private String sobrenome;
    private Number id;
    private static final Random r = new Random();

    public Autor(String nome, String sobrenome) {
        int a = 1;
        int b = 99999;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.id = r.nextInt((b - a) + 1) + a;
    }

    public Autor(String nome, String sobrenome, Number id) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.id = id;
    }

    public Number getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return "Id: '" + id + '\'' +
                "Nome: '" + nome + '\'' +
                ", sobrenome: '" + sobrenome + '\'' +
                '}';
    }
}
