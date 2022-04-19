package gui;

import Dao.Usuario.Aluno.AlunoDao;
import Dao.Usuario.Professor.ProfessorDao;
import Dao.Usuario.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Aluno;
import models.Autor;
import models.Professor;
import models.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private AlunoDao alunoDao = new AlunoDao();
    private ProfessorDao professorDao = new ProfessorDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    List<Aluno> alunos = new ArrayList<Aluno>();
    List<Professor> professores = new ArrayList<Professor>();

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtNome;

    @FXML
    private Button BtnNovo;

    @FXML
    private RadioButton RDAluno;

    @FXML
    private ListView<Professor> ListItensProfessores;


    @FXML
    private ListView<Aluno> ListItensAlunos;


    @FXML
    private Button BtnCadastrar;

    @FXML
    private TextField TxtCPF;

    @FXML
    private RadioButton RDProfessor;

    private void habilitarInterface(Boolean habilitar){
        BtnCadastrar.setDisable(!habilitar);
        BtnCancelar.setDisable(!habilitar);
        TxtCPF.setDisable(!habilitar);
        TxtNome.setDisable(!habilitar);
        RDAluno.setDisable(!habilitar);
        RDProfessor.setDisable((!habilitar));

        BtnNovo.setDisable(habilitar);
    }

    private void limparTela(){
        TxtCPF.setText("");
        TxtNome.setText("");
        RDAluno.setSelected(false);
        RDProfessor.setSelected(false);
    }

    private void atualizarLista(){

        List<Usuarios> usuarios = null;
        try{
            usuarios = usuarioDao.listar();
            usuarios.forEach(usuario ->{
                if(usuario.getTitulo().equals("Aluno")){
                    this.alunos.add(usuario.getAluno());
                }else{
                    this.professores.add(usuario.getProfessor());
                }
            });

        }catch (Exception e){

        }
        ObservableList<Aluno> alunosOb = FXCollections.observableArrayList(this.alunos);
        ObservableList<Professor> professorOb = FXCollections.observableArrayList(this.professores);
        ListItensAlunos.setItems(alunosOb);
        ListItensProfessores.setItems(professorOb);

    }

    private void exibirAluno(){
        Aluno aluno = ListItensAlunos.getSelectionModel().getSelectedItem();
        if (aluno == null) return;

        TxtNome.setText(aluno.getNome());
        TxtCPF.setText(aluno.getCpf());
        RDProfessor.setSelected(false);
        RDAluno.setSelected(true);

    }
    private void exibirProfessor(){
        Professor professor = ListItensProfessores.getSelectionModel().getSelectedItem();
        if (professor == null) return;

        TxtNome.setText(professor.getNome());
        TxtCPF.setText(professor.getCpf());
        RDProfessor.setSelected(true);
        RDAluno.setSelected(false);

    }



    @FXML
    private void ListItensAlunoMouseClicked(MouseEvent evento) {
        exibirAluno();
    }

    @FXML
    private void ListItensProfessorMouseClicked(MouseEvent evento) {
        exibirProfessor();
    }

    @FXML
    private void initialize() throws Exception {
        ListItensProfessores.getItems().clear();
        ListItensAlunos.getItems().clear();
        atualizarLista();
    }

    @FXML
    void btnIncluirAction(ActionEvent event) {
        this.habilitarInterface(true);
        this.limparTela();
        TxtCPF.requestFocus();
    }

    @FXML
    void btnCadastrasAction(ActionEvent event) {
        String cpf = TxtCPF.getText();
        String nome = TxtNome.getText();
        Boolean btnAluno = RDAluno.isSelected();
        Boolean btnProfessor = RDProfessor.isSelected();

        Usuarios usuarios = new Usuarios();
        if(btnAluno){
            Aluno aluno = new Aluno(cpf, nome);


            try{
                //alunoDao.gravar(aluno);
                usuarios.setAluno(aluno);
                usuarioDao.gravar(usuarios);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(btnProfessor){
            Professor professor = new Professor(cpf, nome);
            usuarios.setProfessor(professor);
            try{
                //professorDao.gravar(professor);
                usuarioDao.gravar(usuarios);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    void alunoRadioAction(ActionEvent event) {
        Boolean aluno = RDAluno.isSelected();
        if (aluno){
            RDProfessor.setSelected(false);
        }
    }
    @FXML
    void professorRadioAction(ActionEvent event) {
        Boolean professor = RDProfessor.isSelected();
        if (professor){
            RDAluno.setSelected(false);
        }
    }

}
