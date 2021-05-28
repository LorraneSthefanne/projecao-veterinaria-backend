package br.com.lorrane.services.impl;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import br.com.lorrane.entities.Usuario;
import br.com.lorrane.exceptions.ConflictException;
import br.com.lorrane.exceptions.Mensagem;
import br.com.lorrane.exceptions.NotFoundException;
import br.com.lorrane.repositories.UsuarioRepository;
import br.com.lorrane.security.UserDetailsImpl;
import br.com.lorrane.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario cadastrar(Usuario usuario) {

        Boolean existe = usuarioRepository.existe(usuario.getUsuario());

        Optional.of(existe)
                .filter(Predicate.not(Boolean::booleanValue))
                .orElseThrow(ConflictException.supplier(Mensagem.USUARIO_JA_CADASTRADO));

        String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());

        Usuario novoUsuario = Usuario.builder()
                .usuario(usuario.getUsuario())
                .senha(senha)
                .build();

        return usuarioRepository.salvar(novoUsuario);
    }

    @Override
    public Usuario buscar(String usuario) {
        return usuarioRepository.buscarPorUsuario(usuario)
                .orElseThrow(NotFoundException.supplier(Mensagem.USUARIO_NAO_ENCONTRADO));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = buscar(s);
        UsuarioDTO login = UsuarioDTO.builder()
                .usuario(usuario.getUsuario())
                .senha(usuario.getSenha())
                .build();
        return new UserDetailsImpl(login);
    }
}
