package br.com.lorrane.security;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import br.com.lorrane.entities.Usuario;
import br.com.lorrane.exceptions.Mensagem;
import br.com.lorrane.exceptions.NotFoundException;
import br.com.lorrane.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JWTUtil jwtUtil,
                                  UsuarioRepository usuarioRepository) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = obterAuthentication(header.substring(7));
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken obterAuthentication(String token) {
        if (jwtUtil.tokenValido(token)) {
            String username = jwtUtil.getUsername(token);
            Usuario usuarioEncontrado = usuarioRepository.findByUsuario(username)
                    .orElseThrow(NotFoundException.supplier(Mensagem.USUARIO_NAO_ENCONTRADO));
            UsuarioDTO login = UsuarioDTO.builder()
                    .usuario(usuarioEncontrado.getUsuario())
                    .senha(usuarioEncontrado.getSenha())
                    .build();
            UserDetails userDetails = new UserDetailsImpl(login);
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities(), userDetails.getAuthorities());
        }
        return null;
    }
}
