package br.com.lorrane.security;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private String usuario;
    private String senha;
    private GrantedAuthority authoritie;

    public UserDetailsImpl(UsuarioDTO usuario) {
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        this.authoritie = new SimpleGrantedAuthority("AUTORIZADO");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(this.authoritie);
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
