package br.com.lorrane.services;

import br.com.lorrane.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Usuario cadastrar(Usuario usuario);

    Usuario buscar(String usuario);
}
