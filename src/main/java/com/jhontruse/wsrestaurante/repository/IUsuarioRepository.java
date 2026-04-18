package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Usuario;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends CrudRepository<Usuario, String>,
        PagingAndSortingRepository<Usuario, String> {

    @NonNull
    List<Usuario> findAll();

    Optional<Usuario> findUsuarioById(String id);

    List<Usuario> findUsuarioByPersonaId(String personaId);

    List<Usuario> findUsuarioByUsername(String username);

    List<Usuario> findUsuarioByRolId(String rolId);

    List<Usuario> findUsuarioByActivo(Boolean activo);

    List<Usuario> findUsuarioByMcaBloqueado(String mcaBloqueado);

    List<Usuario> findUsuarioByUsernameStartingWith(String username);

    List<Usuario> findUsuarioByUsernameContaining(String username);

}
