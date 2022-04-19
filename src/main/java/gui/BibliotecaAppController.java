package gui;

import Dao.Autor.AutorDao;
import Dao.Livro.LivroDao;
import Dao.Usuario.Aluno.AlunoDao;
import Dao.Usuario.Professor.ProfessorDao;
import Dao.Usuario.UsuarioDao;
import com.example.biblioteca.BibliotecaApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BibliotecaAppController {
    AutorDao autorDao = new AutorDao();
    AlunoDao alunoDao = new AlunoDao();
    ProfessorDao professorDao = new ProfessorDao();
    LivroDao livroDao = new LivroDao();
    UsuarioDao usuarioDao = new UsuarioDao();

    @FXML
    private MenuItem menuCadUserButton;

    @FXML
    private MenuItem menuCadAutorButton;

    @FXML
    private MenuItem menuCadEmpreButton;

    @FXML
    private MenuItem menuCadLivro;

    @FXML
    private MenuBar menuCadButton;


    @FXML
    private void initialize() throws Exception {
        autorDao.carregar();
        alunoDao.carregar();
        professorDao.carregar();
        livroDao.carregar();
        livroDao.carregarCopias();
        usuarioDao.carregar();
    }

    @FXML
    void acessarFormularioUsuario(ActionEvent event) throws IOException {
        abrirFormulario("UsuarioView");
    }

    @FXML
    void acessarFormularioEmprestimo(ActionEvent event) throws IOException {
        abrirFormulario("EmprestimoView");
    }

    @FXML
    void acessarFormularioLivro(ActionEvent event) throws IOException {
        abrirFormulario("LivroView");
    }


    @FXML
    void acessarFormularioAutor(ActionEvent event) throws IOException {
        abrirFormulario("AutorView");
    }

    public void abrirFormulario(String formulario) throws IOException {
        Parent form = FXMLLoader.load(BibliotecaApp.class.getResource( formulario + ".fxml"));
        Stage stage = new Stage();
        stage.setScene((new Scene(form)));
        //stage.setTitle("Formulário Autor");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();


    }
}