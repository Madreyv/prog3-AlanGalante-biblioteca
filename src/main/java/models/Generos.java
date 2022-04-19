package models;

public enum Generos {
    acao("Livro de Ação"),
    comedia("Livro de Comédia"),
    terror("Livro de Terror"),
    suspense("Livro de Suspense"),
    ficcao("Livro de Ficção Científica"),
    fantasia("Livro de Fantasia"),
    distopia("Livro de Distopia"),
    ficçãoPolicial("Livro de Ficção Policial"),
    romance("Livro de Ficção Romance"),
    graphicNovel("Livro de Graphic Novel");

    private String nome;

    Generos(String nome) {
        this.nome = nome;
    }
}
