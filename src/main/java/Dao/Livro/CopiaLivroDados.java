package Dao.Livro;

import models.CopiaLivro;
import models.Livro;

import java.util.ArrayList;
import java.util.List;

public class CopiaLivroDados {
    private List<CopiaLivro> copiaLivros = new ArrayList<CopiaLivro>();

    public List<CopiaLivro> getLivros() { return copiaLivros; }

    public void setAutores(List<CopiaLivro> livros){
        this.copiaLivros  = livros;
    }

    public CopiaLivro getLivroByID(Number id){
        List<CopiaLivro> livrosFiltrados = new ArrayList<CopiaLivro>();

        this.copiaLivros.forEach(livro -> {

            if(livro.getCodigo().toString().equals(id.toString())){

                livrosFiltrados.add(livro);
            }

        });
        return livrosFiltrados.get(0);
    }
}
