package Dao.Usuario.Professor;

import Dao.Dao;
import models.Professor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements Dao<Professor> {
    private static List<Professor> professores = new ArrayList<Professor>();
    private static final String ARQUIVO = "professor.json";
    private static ProfessorDados professorDados = new ProfessorDados();

    @Override
    public void gravar(Professor objeto) throws Exception {

        professorDados.getProfessores().add(objeto);
        professores = professorDados.getProfessores();

        JSONArray Jprofessor = new JSONArray();

        for (int i = 0; i < professores.size(); i++) {
            JSONObject JUsuario = new JSONObject();
            JUsuario.put("cpf", professores.get(i).getCpf());
            JUsuario.put("nomeCompleto", professores.get(i).getNome());

            Jprofessor.add(JUsuario);
        }

        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(Jprofessor.toJSONString());
            arquivoJson.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Professor> listar() throws Exception {
        return professorDados.getProfessores();
    }

    @Override
    public void excluir(Professor objeto) throws Exception {
        professores.remove(objeto);
    }

    public void carregar() throws Exception{
        professorDados.getProfessores().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray jprofessor = (JSONArray) obj;

        jprofessor.forEach(Usuario -> UsuarioParse((JSONObject) Usuario));

    }

    public void UsuarioParse(JSONObject usuario){
        professorDados.getProfessores().add(
                new Professor(
                        (String) usuario.get("cpf"),
                        (String) usuario.get("nomeCompleto")
                )
        );

    }
}
