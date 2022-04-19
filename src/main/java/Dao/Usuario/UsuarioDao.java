package Dao.Usuario;

import Dao.Dao;
import Dao.Emprestimo.EmprestimoDados;
import Dao.Livro.CopiaLivroDados;
import Dao.Usuario.Aluno.AlunoDados;
import Dao.Usuario.Professor.ProfessorDados;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Dao<Usuarios> {
    private static List<Usuarios> usuarios = new ArrayList<Usuarios>();
    private static final String ARQUIVO = "Usuarios.json";
    private static UsuarioDados usuarioDados = new UsuarioDados();

    private AlunoDados alunoDados = new AlunoDados();
    private ProfessorDados professorDados = new ProfessorDados();
    private CopiaLivroDados copiaLivroDados = new CopiaLivroDados();

    @Override
    public void gravar(Usuarios objeto) throws Exception {
        usuarioDados.getUsuarios().add(objeto);
        usuarios = usuarioDados.getUsuarios();
        JSONArray JUsuarios = new JSONArray();


        for (int i = 0; i < usuarios.size(); i++) {

            JSONObject JUsuario = new JSONObject();
            if(usuarios.get(i).getAluno() != null){
                System.out.println(usuarios.get(i).getAluno());
                JUsuario.put("Titulo", "Aluno");

            }else{
                System.out.println(usuarios.get(i).getProfessor());
                JUsuario.put("Titulo", "Professor");
            }

            JUsuario.put("nome", usuarios.get(i).getNome());
            JUsuario.put("cpf", usuarios.get(i).getCFP());

            JUsuarios.add(JUsuario);
        }

        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(JUsuarios.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregar() throws Exception{
        usuarioDados.getUsuarios().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray jprofessor = (JSONArray) obj;

        jprofessor.forEach(Usuario -> UsuarioParse((JSONObject) Usuario));

    }

    public void UsuarioParse(JSONObject usuario){
        String titulo = (String) usuario.get("Titulo");
        String cpf = (String) usuario.get("cpf");
        String nome = (String) usuario.get("nome");
        Usuarios usuarios = new Usuarios();
        if (titulo.equals("Aluno")){
            Aluno aluno = new Aluno(cpf, nome);
            usuarios.setAluno(aluno);
        }else{
            Professor professor = new Professor(cpf,nome);
            usuarios.setProfessor(professor);
        }
        usuarioDados.getUsuarios().add(usuarios);

    }

    @Override
    public List listar() throws Exception {
        return usuarioDados.getUsuarios();
    }

    @Override
    public void excluir(Usuarios objeto) throws Exception {

    }

    public Usuario getUsuarioByID(String dados){
        usuarios = usuarioDados.getUsuarios();
        usuarios.forEach(usuario -> {
            if
        });
    }

}
