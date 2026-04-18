package com.jhontruse.wsrestaurante.service;

import com.jhontruse.wsrestaurante.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

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
