package br.com.luizgustavo.medicalconsult.usuario.services;

import br.com.luizgustavo.medicalconsult.usuario.domain.Usuario;
import br.com.luizgustavo.medicalconsult.usuario.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void cadastrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("diego");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        var result = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(result);
        Assertions.assertEquals("diego", result.getNomeUsuario());

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void listarUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("diego");
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("gulia");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        var resulta = usuarioService.listarUsuarios();

        assertAll(
                () -> assertNotNull(resulta),
                () -> assertEquals(2, resulta.size()),
                () -> Assertions.assertEquals("diego", resulta.get(0).getNomeUsuario()),
                () -> Assertions.assertEquals("gulia", resulta.get(1).getNomeUsuario())
        );
    }

    @Test
    void buscarUsuario() {
        Usuario user = new Usuario();
        user.setIdUsuario(1L);
        user.setNomeUsuario("diego");

        when(usuarioRepository.findById(user.getIdUsuario())).thenReturn(Optional.of(user));

        var resultado = usuarioService.buscarUsuario(user.getIdUsuario());

        assertAll(
                () -> assertNotNull(resultado),
                () -> Assertions.assertEquals("diego", resultado.getNomeUsuario())
        );
    }

    @Test
    void deletarUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Fabio");

        // Mock necessário: Encontrar o usuário
        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));

        // Mock necessário: Deletar o usuário
        doNothing().when(usuarioRepository).deleteById(usuario.getIdUsuario());

        // Act
        usuarioService.deletarUsuario(usuario.getIdUsuario());

        // Assert
        verify(usuarioRepository, times(1)).findById(usuario.getIdUsuario());
        verify(usuarioRepository, times(1)).deleteById(usuario.getIdUsuario());
    }

}
