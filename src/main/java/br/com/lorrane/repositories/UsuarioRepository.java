package br.com.lorrane.repositories;

import br.com.lorrane.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, QuerydslPredicateExecutor<Usuario> {

    Optional<Usuario> findByUsuario(String usuario);

    default Optional<Usuario> buscarPorUsuario(String usuario) {
        return findByUsuario(usuario);
    }

    default Boolean existe(String usuario) {
        return existsByUsuario(usuario);
    }

    default Usuario salvar(Usuario usuario) {
        return save(usuario);
    }

    Boolean existsByUsuario(String usuario);

}
