package br.com.lorrane.security;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        var credentials = new ObjectMapper().readValue(req.getInputStream(), UsuarioDTO.class);
        var auth = new UsernamePasswordAuthenticationToken(credentials.getUsuario(), credentials.getSenha());
        return authenticationManager.authenticate(auth);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain filterChain,
                                            Authentication auth) {
        UserDetailsImpl details = ((UserDetailsImpl) auth.getPrincipal());
        String token = jwtUtil.generateToken(details);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
        ObjectMapper mapper = new ObjectMapper();
        UsuarioDTO autenticacao = UsuarioDTO.builder()
                .usuario(details.getUsername())
                .token(token)
                .build();
        res.getWriter().write(mapper.writeValueAsString(autenticacao));
    }

    private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException exception) throws IOException {
            response.getWriter().append(unauthorized());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }

        private String unauthorized() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Nao autorizado\", "
                    + "\"message\": \"Usuário ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
