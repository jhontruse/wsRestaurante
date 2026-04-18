package com.jhontruse.wsrestaurante.service.impl;

import com.jhontruse.wsrestaurante.exception.ResourceNotFoundException;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.model.entity.Usuario;
import com.jhontruse.wsrestaurante.repository.IUsuarioRepository;
import com.jhontruse.wsrestaurante.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository iUsuarioRepository;

    @Override
    public List<Usuario> findAll() {
        log.info("UsuarioService - findAll");
        List<Usuario> usuarios =
                iUsuarioRepository.findAll().stream().sorted(Comparator.comparing(Usuario::getUsername)).toList();
        log.info("usuarios {}", usuarios);
        return usuarios;
    }

    @Override
    public Optional<Usuario> findUsuarioById(String id) {
        log.info("UsuarioService - findUsuarioById");
        log.info("id {}", id);
        Optional<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioById(id).stream().findFirst();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByPersonaId(String personaId) {
        log.info("UsuarioService - findUsuarioByPersonaId");
        log.info("personaId {}", personaId);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByPersonaId(personaId).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByUsername(String username) {
        log.info("UsuarioService - findUsuarioByUsername");
        log.info("username {}", username);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByUsername(username).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByRolId(String rolId) {
        log.info("UsuarioService - findUsuarioByRolId");
        log.info("rolId {}", rolId);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByRolId(rolId).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByActivo(Boolean activo) {
        log.info("UsuarioService - findUsuarioByActivo");
        log.info("activo {}", activo);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByActivo(activo).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByMcaBloqueado(String mcaBloqueado) {
        log.info("UsuarioService - findUsuarioByMcaBloqueado");
        log.info("mcaBloqueado {}", mcaBloqueado);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByMcaBloqueado(mcaBloqueado).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByUsernameStartingWith(String username) {
        log.info("UsuarioService - findUsuarioByUsernameStartingWith");
        log.info("username {}", username);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByUsernameStartingWith(username).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }

    @Override
    public List<Usuario> findUsuarioByUsernameContaining(String username) {
        log.info("UsuarioService - findUsuarioByUsernameContaining");
        log.info("username {}", username);
        List<Usuario> usuarioOpt = iUsuarioRepository.findUsuarioByUsernameContaining(username).stream().toList();
        log.info("usuarioOpt {}", usuarioOpt);
        if (usuarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return usuarioOpt;
    }
}
