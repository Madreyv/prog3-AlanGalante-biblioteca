package Dao.Emprestimo;

import Dao.Dao;
import Dao.Livro.CopiaLivroDados;
import Dao.Usuario.Aluno.AlunoDados;
import Dao.Usuario.Aluno.AlunoDao;
import Dao.Usuario.Professor.ProfessorDados;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmprestimoDao implements Dao<Emprestimo> {
    private static List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private static final String ARQUIVO = "emprestimos.json";
    private static EmprestimoDados emprestimoDados = new EmprestimoDados();

    private AlunoDados alunoDados = new AlunoDados();
    private ProfessorDados professorDados = new ProfessorDados();
    private CopiaLivroDados copiaLivroDados = new CopiaLivroDados();

    @Override
    public void gravar(Emprestimo objeto) throws Exception {

        emprestimoDados.getEmprestimos().add(objeto);
        emprestimos = emprestimoDados.getEmprestimos();

        JSONArray JEmprestimos = new JSONArray();

        for (int i = 0; i < emprestimos.size(); i++) {
            JSONObject JEmprestimo = new JSONObject();
            JEmprestimo.put("id", emprestimos.get(i).getId());
            JEmprestimo.put("usuario", emprestimos.get(i).getUsuario().getCpf());
            JEmprestimo.put("livro", emprestimos.get(i).getCopiaLivro().getCodigo());
            JEmprestimo.put("DataEmprestimo", emprestimos.get(i).getDataEmprestimo().toString());

            if(emprestimos.get(i).getDataDevolucao() != null){
                JEmprestimo.put("DataDevolucao", emprestimos.get(i).getDataDevolucao().toString());
            }else{
                JEmprestimo.put("DataDevolucao", "");
            }


            JEmprestimos.add(JEmprestimo);
        }

        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(JEmprestimos.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Emprestimo> listar() throws Exception {
        return emprestimoDados.getEmprestimos();
    }

    @Override
    public void excluir(Emprestimo objeto) throws Exception {
        emprestimos.remove(objeto);
    }

    public void carregar() throws Exception{
        AlunoDao
        emprestimoDados.getEmprestimos().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray jAutores = (JSONArray) obj;

        jAutores.forEach(emprestimo -> emprestimoParse((JSONObject) emprestimo));

    }

    public void emprestimoParse(JSONObject emprestimo){
        Aluno aluno = alunoDados.getAlunoByID((String) emprestimo.get("usuario"));
        Professor professor = professorDados.getProfessorByID((String) emprestimo.get("usuario") );

        /*emprestimoDados.getEmprestimos().add(
               new Emprestimo(
                        (Number) emprestimo.get("id"),
                        aluno,
                        (Date) emprestimo.get("DataEmprestimo"),
                        (Date) emprestimo.get("DataDevolucao"),
                        copiaLivroDados.getLivroByID((Number) emprestimo.get("livro"))



                )
        );*/

    }

    /*public Autor filtrar(String id){
        List<Autor> auxiliar = autorDados.getAutores();
        for (int i = 0; i < auxiliar.size(); i++) {
            if (auxiliar.get(i).getId().toString().equals(id)){
                return auxiliar.get(i);
            }
        }
        /*Autor autor = (Autor) this.autores.stream()
                .filter(a -> a.getId().toString().equals(id));*
        // System.out.println(autor);
        return null;

    }*/
}

