package Dao.Emprestimo;

import models.Autor;
import models.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDados {
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

    public List<Emprestimo> getEmprestimos(){
        return emprestimos;
    }

    public void setEmptrestimos(List<Emprestimo> emprestimos){
        this.emprestimos  = emprestimos;
    }

}
