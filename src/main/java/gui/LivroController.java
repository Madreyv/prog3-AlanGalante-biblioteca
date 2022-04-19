package gui;

import Dao.Autor.AutorDao;
import Dao.Livro.LivroDao;
import Dao.Usuario.Aluno.AlunoDao;
import Dao.Usuario.Professor.ProfessorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivroController {
    LivroDao livroDao = new LivroDao();
    AutorDao autorDao = new AutorDao();

    @FXML
    private TextField TxtTitulo;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnNovo;

    @FXML
    private RadioButton RDCopia;

    @FXML
    private ListView<Livro> ListItensLivros;

    @FXML
    private ComboBox<Generos> CBOGeneros;

    @FXML
    private TextField TxtID;

    @FXML
    private Button BtnCadastrar;

    @FXML
    private ComboBox<Autor> CBOAutores;

    @FXML
    private TextField TxtAnoPublicacao;

    @FXML
    private HBox HBoxLivros;

    @FXML
    private ComboBox<Livro> CBOLivros;


    private void habilitarInterface(Boolean habilitar){
        BtnCadastrar.setDisable(!habilitar);
        BtnCancelar.setDisable(!habilitar);
        TxtAnoPublicacao.setDisable(!habilitar);
        TxtTitulo.setDisable(!habilitar);
        CBOAutores.setDisable((!habilitar));
        CBOGeneros.setDisable((!habilitar));
        RDCopia.setDisable(!habilitar);


        BtnNovo.setDisable(habilitar);

    }

    private void limparTela(){
        TxtTitulo.setText("");
        TxtID.setText("");
        TxtAnoPublicacao.setText("");

    }

    private void atualizarAutores(Livro livro){
        //livro.getAutor();
        System.out.println(livro.getAutor());
    }

    private void atualizarLista(){
        List<Livro> livros = null;
        try{
            livros = livroDao.listar();
           livros.forEach(e -> this.atualizarAutores(e));
        }catch (Exception e){
            livros = new ArrayList<Livro>();
        }
        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        ListItensLivros.setItems(livrosOb);
        CBOLivros.setItems(livrosOb);


    }

    private void exibirLivros(){
        Livro livro = ListItensLivros.getSelectionModel().getSelectedItem();
        if (livro == null) return;

        TxtID.setText(livro.getId().toString());
        TxtAnoPublicacao.setText(livro.getAno().toString());
        TxtTitulo.setText(livro.getTitulo());
        CBOGeneros.setValue(livro.getGenero());
        CBOAutores.setValue(livro.getAutor());

    }

    @FXML
    private void ListItensLivrosMouseClicked(MouseEvent evento) {
        exibirLivros();
    }

    @FXML
    public void initialize() throws Exception {
        livroDao.carregar();

        ObservableList<Generos> generos = FXCollections.observableArrayList(Generos.values());
        CBOGeneros.setItems(generos);

        ObservableList<Autor> autores = FXCollections.observableArrayList(autorDao.listar());
        CBOAutores.setItems(autores);

        atualizarLista();
    }

    @FXML
    void btnIncluirAction(ActionEvent event) {
        this.habilitarInterface(true);
        this.limparTela();
        TxtTitulo.requestFocus();
    }

    @FXML
    void btnCadastrasAction(ActionEvent event) {
        String titulo = TxtTitulo.getText();
        Number anoPublicacao = Integer.parseInt(TxtAnoPublicacao.getText());
        Generos genero = CBOGeneros.getValue();
        Autor autor = CBOAutores.getValue();


        try{
            if (RDCopia.isSelected()){
                Number id = Integer.parseInt(TxtID.getText());
                Livro livro = new Livro(id,titulo,autor,anoPublicacao,genero);
                CopiaLivro copiaLivro = new CopiaLivro(livro);
                livroDao.gravarCopia(copiaLivro);
            }else{
                Livro livro = new Livro(titulo,autor,anoPublicacao,genero);
                livroDao.gravar(livro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        atualizarLista();
        habilitarInterface(false);
        limparTela();
    }

    @FXML
    void BtnCancelarAction(ActionEvent event) {
        this.habilitarInterface(false);
        this.limparTela();
    }

    @FXML
    void copiaRadioAction(ActionEvent event) {
        Boolean copia = RDCopia.isSelected();
        if (copia){
            HBoxLivros.setVisible(true);
        }else{
            HBoxLivros.setVisible(false);
        }
    }

    @FXML
    void CBOLivrosOnAction(ActionEvent event){
        Livro livro = CBOLivros.getValue();
        if (livro == null) return;

        TxtID.setText(livro.getId().toString());
        TxtAnoPublicacao.setText(livro.getAno().toString());
        TxtTitulo.setText(livro.getTitulo());
        CBOGeneros.setValue(livro.getGenero());
        CBOAutores.setValue(livro.getAutor());
    }

}
