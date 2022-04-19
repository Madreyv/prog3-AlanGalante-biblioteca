package Dao.Autor;

import models.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDados {
    private List<Autor> autores = new ArrayList<Autor>();

    public List<Autor> getAutores(){
        return autores;
    }

    public void setAutores(List<Autor> autores){
        this.autores  = autores;
    }

    public Autor getAutorbyID(Number id){
        Autor autor = (Autor) this.autores.stream()
                .filter(a -> a.getId() == id);
        return autor;

    }
}
