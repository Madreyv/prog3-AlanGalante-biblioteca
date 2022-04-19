package Dao.Usuario.Professor;


import models.Aluno;
import models.Autor;
import models.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDados {
    private List<Professor> professores = new ArrayList<Professor>();

    public List<Professor> getProfessores(){
        return professores;
    }

    public void setProfessores(List<Professor> professores){
        this.professores  = professores;
    }

    public Professor getProfessorByID(String id){
        Professor professor = (Professor) this.professores.stream()
                .filter(a -> a.getCpf().equals(id));
        return professor;

    }
}
