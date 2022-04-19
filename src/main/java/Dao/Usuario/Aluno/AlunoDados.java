package Dao.Usuario.Aluno;


import models.Aluno;
import models.Autor;

import java.util.ArrayList;
import java.util.List;

public class AlunoDados {
    private List<Aluno> alunos = new ArrayList<Aluno>();

    public List<Aluno> getAlunos(){
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos){
        this.alunos  = alunos;
    }

    public Aluno getAlunoByID(String id){
        Aluno aluno = (Aluno) this.alunos.stream()
                .filter(a -> a.getCpf().equals(id));
        return aluno;

    }
}
