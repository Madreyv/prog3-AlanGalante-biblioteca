package Dao.Usuario.Aluno;

import Dao.Dao;
import models.Aluno;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements Dao<Aluno> {
    private static List<Aluno> alunos = new ArrayList<Aluno>();
    private static final String ARQUIVO = "alunos.json";
    private static AlunoDados AlunoDados = new AlunoDados();

    @Override
    public void gravar(Aluno objeto) throws Exception {

        AlunoDados.getAlunos().add(objeto);
        alunos = AlunoDados.getAlunos();

        JSONArray Jalunos = new JSONArray();

        for (int i = 0; i < alunos.size(); i++) {
            JSONObject JUsuario = new JSONObject();
            JUsuario.put("cpf", alunos.get(i).getCpf());
            JUsuario.put("nomeCompleto", alunos.get(i).getNome());

            Jalunos.add(JUsuario);
        }

        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(Jalunos.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aluno> listar() throws Exception {
        return AlunoDados.getAlunos();
    }

    @Override
    public void excluir(Aluno objeto) throws Exception {
        alunos.remove(objeto);
    }

    public void carregar() throws Exception{
        AlunoDados.getAlunos().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray jalunos = (JSONArray) obj;

        jalunos.forEach(Usuario -> UsuarioParse((JSONObject) Usuario));

    }

    public void UsuarioParse(JSONObject Usuario){
        AlunoDados.getAlunos().add(
                new Aluno(
                        (String) Usuario.get("cpf"),
                        (String) Usuario.get("nomeCompleto")
                )
        );

    }
}
