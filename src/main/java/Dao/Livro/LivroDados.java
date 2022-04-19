package Dao.Livro;

import models.Autor;
import models.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDados {
    private List<Livro> livros = new ArrayList<Livro>();

    public List<Livro> getLivros() { return livros; }

    public void setLivros(List<Livro> livros){
        this.livros  = livros;
    }

    public Livro getLivroByID(Number id){
       List<Livro> livrosFiltrados = new ArrayList<Livro>();

       this.livros.forEach(livro -> {

           if(livro.getId().toString().equals(id.toString())){

               livrosFiltrados.add(livro);
           }

       });
       return livrosFiltrados.get(0);
    }
}
