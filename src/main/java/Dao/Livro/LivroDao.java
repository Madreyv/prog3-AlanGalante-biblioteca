package Dao.Livro;

import Dao.Autor.AutorDao;
import Dao.Dao;
import models.Autor;
import models.CopiaLivro;
import models.Generos;
import models.Livro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LivroDao implements Dao<Livro> {
    private static List<Livro> livros = new ArrayList<Livro>();
    private static List<CopiaLivro> copiaLivros = new ArrayList<CopiaLivro>();
    private static final String ARQUIVO = "livros.json";
    private static final String ARQUIVOCOPIA = "copias.json";
    private static LivroDados livroDados = new LivroDados();
    private static CopiaLivroDados copiaLivroDados = new CopiaLivroDados();
    private Generos generos = null;
    private AutorDao autores = new AutorDao();

    @Override
    public void gravar(Livro objeto) throws Exception {

        livroDados.getLivros().add(objeto);
        livros = livroDados.getLivros();

        JSONArray JLivros = new JSONArray();


        for (int i = 0; i < livros.size(); i++) {

            JSONObject JLivro = new JSONObject();
            JLivro.put("id", livros.get(i).getId());
            JLivro.put("titulo", livros.get(i).getTitulo());
            JLivro.put("ano", livros.get(i).getAno());
            JLivro.put("genero", livros.get(i).getGenero().toString());
            JLivro.put("autor", livros.get(i).getAutor().getId());

            JLivros.add(JLivro);
        }


        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVO);
            arquivoJson.write(JLivros.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void gravarCopia(CopiaLivro objeto) throws Exception {

        copiaLivroDados.getLivros().add(objeto);
        copiaLivros = copiaLivroDados.getLivros();

        JSONArray JLivros = new JSONArray();


        for (int i = 0; i < copiaLivros.size(); i++) {

            JSONObject JLivro = new JSONObject();
            JLivro.put("codigo", copiaLivros.get(i).getCodigo());
            JLivro.put("disponivel", copiaLivros.get(i).getDisponível());
            JLivro.put("livro", copiaLivros.get(i).getLivro().getId());
            System.out.println( copiaLivros.get(i).getLivro().getId());

            JLivros.add(JLivro);
        }


        try{
            FileWriter arquivoJson = new FileWriter(ARQUIVOCOPIA);
            arquivoJson.write(JLivros.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Livro> listar() throws Exception {
        return livroDados.getLivros();
    }


    public List<CopiaLivro> listarCopias() throws Exception {
        return copiaLivroDados.getLivros();
    }

    public List<CopiaLivro> listarCopiasDisponíveis() throws Exception {
        List<CopiaLivro> copias = new ArrayList<CopiaLivro>();
        copiaLivros = copiaLivroDados.getLivros();
        System.out.println(copiaLivros);
        copiaLivros.forEach(copia -> {
            if (copia.getDisponível()){
                System.out.println("Copias disponíveis!");
                System.out.println(copia);
                copias.add(copia);
            }
        });

        return copias;
    }


    public void mudarStatusLivro(Number id, Boolean disponibilidade){
        copiaLivroDados.getLivroByID(id).setDisponível(disponibilidade);
    }

    @Override
    public void excluir(Livro objeto) throws Exception {
        livros.remove(objeto);
    }

    public void carregar() throws Exception{
        livroDados.getLivros().clear();
        FileReader file = new FileReader(ARQUIVO);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray JLivros = (JSONArray) obj;

        JLivros.forEach(livro -> livroParse((JSONObject) livro));


    }

    public void carregarCopias() throws Exception{
        copiaLivroDados.getLivros().clear();
        FileReader file = new FileReader(ARQUIVOCOPIA);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(file);

        JSONArray JLivros = (JSONArray) obj;

        JLivros.forEach(livro -> copiaParse((JSONObject) livro));
        System.out.println(copiaLivroDados.getLivros());


    }

    private void  copiaParse(JSONObject livro){

        copiaLivroDados.getLivros().add(
                new CopiaLivro(
                        (Number) livro.get("codigo"),
                        (Boolean) livro.get("disponivel"),
                        livroDados.getLivroByID((Number) livro.get("livro"))
                )
        );

    }

    private void getGenero(String genero){
        for (Generos p : Generos.values()){
            if(p.toString().equals(genero)){

                generos = p;
            }

        }
    }

    private Autor getAutor(String id){
        Autor autor = autores.filtrar(id);

        return autor;
    }

    public void livroParse(JSONObject livro){
        getGenero(livro.get("genero").toString());
        getAutor(livro.get("autor").toString());

       livroDados.getLivros().add(
                new Livro(
                        (Number) livro.get("id"),
                        (String) livro.get("titulo"),
                        getAutor(livro.get("autor").toString()),
                        (Number) livro.get("ano"),
                        generos
                )
        );

    }
}
