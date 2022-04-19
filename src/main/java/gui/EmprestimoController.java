package gui;

import Dao.Emprestimo.EmprestimoDao;
import Dao.Livro.LivroDao;
import Dao.Usuario.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;

import java.time.LocalDate;

public class EmprestimoController {
    UsuarioDao usuarioDao = new UsuarioDao();
    LivroDao livroDao = new LivroDao();
    EmprestimoDao emprestimoDao = new EmprestimoDao();
    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtIDEmprestimo;

    @FXML
    private Button BtnNovo;

    @FXML
    private ListView<?> ListItens;

    @FXML
    private DatePicker DPDataEmprestimo;

    @FXML
    private DatePicker DPDataDevolucao;

    @FXML
    private ComboBox<Usuarios> CBOUsuarios;

    @FXML
    private Button BtnCadastrar;

    @FXML
    private ComboBox<CopiaLivro> CBOLivros;

    @FXML
    void btnIncluirAction(ActionEvent event) throws Exception {
        this.habilitarInterface(true);
        this.limparTela();
        this.carregarCombos();
        CBOUsuarios.requestFocus();
    }

    @FXML
    void BtnCancelarAction(ActionEvent event) {
        this.habilitarInterface(false);
        this.limparTela();
    }

    @FXML
    public void initialize() throws Exception {
        this.carregarCombos();

    }

    @FXML
    void BtnCadastrarAction(ActionEvent event) {
        Usuarios usuarios = CBOUsuarios.getValue();
        CopiaLivro copiaLivro = CBOLivros.getValue();
        LocalDate dataEmprestimo = DPDataEmprestimo.getValue();
        LocalDate dataDevolucao = DPDataDevolucao.getValue();

        livroDao.mudarStatusLivro(copiaLivro.getCodigo(), false);

        //System.out.println(usuarios);
        //System.out.println(copiaLivro);
        System.out.println(dataEmprestimo);
        //System.out.println(dataDevolucao);

        try{
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataDevolucao(dataDevolucao);
            emprestimo.setCopiaLivro(copiaLivro);
            emprestimo.setUsuario(usuarios.getUsuario());

            emprestimoDao.gravar(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }



       // atualizarLista();*/
        habilitarInterface(false);
        limparTela();
    }

    private void habilitarInterface(Boolean habilitar){
        BtnCadastrar.setDisable(!habilitar);
        BtnCancelar.setDisable(!habilitar);
        CBOUsuarios.setDisable(!habilitar);
        CBOLivros.setDisable(!habilitar);
        DPDataDevolucao.setDisable(!habilitar);
        DPDataEmprestimo.setDisable(!habilitar);

        BtnNovo.setDisable(habilitar);
    }

    private void limparTela(){
        CBOUsuarios.getSelectionModel().clearSelection();
        CBOLivros.getSelectionModel().clearSelection();
        DPDataEmprestimo.setValue(null);
        DPDataDevolucao.setValue(null);
        TxtIDEmprestimo.setText("");
    }

    private void carregarCombos() throws Exception {
        usuarioDao.carregar();
        livroDao.carregar();
        ObservableList<Usuarios> usuarios = FXCollections.observableArrayList(usuarioDao.listar());
        CBOUsuarios.setItems(usuarios);

        //livroDao.listarCopiasDisponíveis();
        ObservableList<CopiaLivro> copiaLivros = FXCollections.observableArrayList(livroDao.listarCopiasDisponíveis());
        CBOLivros.setItems(copiaLivros);
    }

}
