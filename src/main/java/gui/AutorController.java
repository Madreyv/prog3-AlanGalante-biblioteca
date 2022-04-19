package gui;

import Dao.Autor.AutorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorController {
    private AutorDao autorDao = new AutorDao();


    @FXML
    private Button BtnCancelar;

    @FXML
    private ListView<Autor> ListItensAutor;

    @FXML
    private Button BtnNovo;

    @FXML
    private TextField TxtNomeAutor;

    @FXML
    private TextField TxtIDAutor;

    @FXML
    private TextField TxtSobreNomeAutor;

    @FXML
    private Button BtnCadastrar;

    private void habilitarInterface(Boolean habilitar){
        BtnCadastrar.setDisable(!habilitar);
        BtnCancelar.setDisable(!habilitar);
        TxtNomeAutor.setDisable(!habilitar);
        TxtSobreNomeAutor.setDisable(!habilitar);

        BtnNovo.setDisable(habilitar);
    }

    private void limparTela(){
        TxtNomeAutor.setText("");
        TxtSobreNomeAutor.setText("");
        TxtIDAutor.setText("");
    }

    private void atualizarLista(){
        List<Autor> autores = null;
        try{
            autores = autorDao.listar();
        }catch (Exception e){
            autores = new ArrayList<Autor>();
        }

        ObservableList<Autor> autorOb = FXCollections.observableArrayList(autores);
        ListItensAutor.setItems(autorOb);

    }

    private void exibirAutor(){
        Autor autor = ListItensAutor.getSelectionModel().getSelectedItem();
        if (autor == null) return;

        TxtNomeAutor.setText(autor.getNome());
        TxtSobreNomeAutor.setText(autor.getSobrenome());
        TxtIDAutor.setText(autor.getId().toString());
    }



    @FXML
    private void ListItensAutorMouseClicked(MouseEvent evento) {
        exibirAutor();
    }

    @FXML
    private void initialize() throws Exception {
        ListItensAutor.getItems().clear();
        atualizarLista();
    }



    @FXML
    void btnIncluirAction(ActionEvent event) {
        this.habilitarInterface(true);
        this.limparTela();
        TxtNomeAutor.requestFocus();
    }

    @FXML
    void btnCadastrasAction(ActionEvent event) {
        String nome = TxtNomeAutor.getText();
        String sobrenome = TxtSobreNomeAutor.getText();

        Autor autor = new Autor(nome, sobrenome);
        try{
            autorDao.gravar(autor);
        }catch (Exception e){
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

}
