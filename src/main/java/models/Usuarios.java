package models;

public class Usuarios {
    private Aluno aluno;
    private Professor professor;

    public Usuarios() {
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Usuario getUsuario(){
        if(this.aluno != null){
            return this.getAluno();
        }else{
            return this.getProfessor();
        }
    }

    public String getCFP(){
        if(this.aluno != null){
           return this.aluno.getCpf();
        }else{
            return this.professor.getCpf();
        }
    }

    public String getNome(){
        if(this.aluno != null){
            return this.aluno.getNome();
        }else{
            return this.professor.getNome();
        }
    }

    public String getTitulo(){
        if(this.aluno != null){
            return this.aluno.getTitulo();
        }else{
            return this.professor.getTitulo();
        }
    }

    @Override
    public String toString() {
        if (this.aluno != null){
            return "Aluno :" + aluno;
        }else{
            return "Professor : " + professor;
        }

    }
}
