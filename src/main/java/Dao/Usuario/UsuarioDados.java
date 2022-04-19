package Dao.Usuario;

import models.Aluno;
import models.Usuario;
import models.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDados {
    private List<Usuarios> usuarios = new ArrayList<Usuarios>();

    public List<Usuarios> getUsuarios(){
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios){
        this.usuarios  = usuarios;
    }

    public Usuarios getAlunoByID(String id){
        List<Usuario> usuarioAux = new ArrayList<>();

        usuarios.forEach(usuario -> {
            if(usuario.getAluno() != null){
                if (usuario.getAluno().getCpf().equals(id)){
                    usuarioAux.add(usuario) ;
                }
            }
        });

    }
}
