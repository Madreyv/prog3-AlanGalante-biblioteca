package Dao.Autor;

import Dao.Dao;
import models.Autor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDao implements Dao<Autor> {
    private static List<Autor> autores = new ArrayList<Autor>();
    private static final String ARQUIVO = "autores.json";
    private static AutorDados autorDados = new AutorDados();

    @Override
    public void gravar(Autor objeto) throws Exception {
        //autores.add(objeto);

        autorDados.getAutores().add(objeto);
        autores = autorDados.getAutores();

        JSONArray JAutores = new JSONArray();

        for (int i = 0; i < autores.size(); i++) {
            JSONObject JAutor = new JSONObject();
            JAutor.put("id", autores.get(i).getId());
            JAutor.put("nome", autores.get(i).getNome());
            JAutor.put("sobrenome", autores.get(i).getSobrenome());

            JAutores.add(JAutor);
        }

        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(JAutores.toJSONString());
            arquivoJson.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Autor> listar() throws Exception {
        return autorDados.getAutores();
    }

    @Override
    public void excluir(Autor objeto) throws Exception {
        autores.remove(objeto);
    }

    public void carregar() throws Exception{
        autorDados.getAutores().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray jAutores = (JSONArray) obj;

        jAutores.forEach(autor -> autorParse((JSONObject) autor));

    }

    public void autorParse(JSONObject autor){
        autorDados.getAutores().add(
                new Autor(
                        (String) autor.get("nome"),
                        (String) autor.get("sobrenome"),
                        (Number) autor.get("id")
                )
        );

    }

    public Autor filtrar(String id){
        List<Autor> auxiliar = autorDados.getAutores();
        for (int i = 0; i < auxiliar.size(); i++) {
            if (auxiliar.get(i).getId().toString().equals(id)){
                return auxiliar.get(i);
            }
        }
        return null;

    }
}
