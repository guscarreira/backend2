package br.com.luizgustavo.medicalconsult.usuario.services;

import br.com.luizgustavo.medicalconsult.usuario.domain.Usuario;
import br.com.luizgustavo.medicalconsult.usuario.repositories.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario){

        return usuarioRepository.save(usuario);
    }
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id){
    return usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado", id));
    }
}
