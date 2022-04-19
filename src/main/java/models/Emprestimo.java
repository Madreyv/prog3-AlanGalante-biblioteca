package models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Emprestimo {
    private Number id;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private CopiaLivro copiaLivro;
    private static final Random r = new Random();

    public Emprestimo() {
        int a = 1;
        int b = 99999;
        this.id = r.nextInt((b - a) + 1) + a;
    }


    public Number getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public CopiaLivro getCopiaLivro() {
        return copiaLivro;
    }

    public void setCopiaLivro(CopiaLivro copiaLivro) {
        this.copiaLivro = copiaLivro;
    }

    @Override
    public String toString() {
        return  "Id=" + id +
                ", usuario=" + usuario.getCpf() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", copiaLivro=" + copiaLivro.getLivro().getId();
    }
}
