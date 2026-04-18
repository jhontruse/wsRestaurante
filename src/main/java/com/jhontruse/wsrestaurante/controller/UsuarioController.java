package com.jhontruse.wsrestaurante.controller;

import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.model.entity.Usuario;
import com.jhontruse.wsrestaurante.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Gestión de usuario del sistema")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final IUsuarioService iUsuarioService;

    @Operation(summary = "Buscar usuarios", description = "")
    @GetMapping("find/all")
    public ResponseEntity<List<Usuario>> findAll() {
        log.info("UsuarioController - findAll");
        List<Usuario> usuarios = iUsuarioService.findAll();
        log.info("usuarios {}", usuarios);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Buscar usuario", description = "")
    @GetMapping("find/id/{id}")
    public ResponseEntity<Optional<Usuario>> findUsuarioById(
            @PathVariable @Parameter(description = "UUID a buscar", example = "", required = true) String id) {
        log.info("UsuarioController - findUsuarioById");
        log.info("id: {}", id);
        Optional<Usuario> usuarioOpt = iUsuarioService.findUsuarioById(id);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/persona/{personaId}")
    public ResponseEntity<List<Usuario>> findUsuarioByPersonaId(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String personaId) {
        log.info("UsuarioController - findUsuarioByPersonaId");
        log.info("personaId: {}", personaId);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByPersonaId(personaId);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/username/{username}")
    public ResponseEntity<List<Usuario>> findUsuarioByUsername(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String username) {
        log.info("UsuarioController - findUsuarioByUsername");
        log.info("username: {}", username);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByUsername(username);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/rol/{rolId}")
    public ResponseEntity<List<Usuario>> findUsuarioByRolId(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String rolId) {
        log.info("UsuarioController - findUsuarioByRolId");
        log.info("rolId: {}", rolId);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByRolId(rolId);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/activo/{activo}")
    public ResponseEntity<List<Usuario>> findUsuarioByActivo(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) Boolean activo) {
        log.info("UsuarioController - findUsuarioByActivo");
        log.info("activo: {}", activo);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByActivo(activo);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/bloqueado/{mcaBloqueado}")
    public ResponseEntity<List<Usuario>> findUsuarioByMcaBloqueado(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String mcaBloqueado) {
        log.info("UsuarioController - findUsuarioByMcaBloqueado");
        log.info("mcaBloqueado: {}", mcaBloqueado);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByMcaBloqueado(mcaBloqueado);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/username/start/{username}")
    public ResponseEntity<List<Usuario>> findUsuarioByUsernameStartingWith(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String username) {
        log.info("UsuarioController - findUsuarioByUsernameStartingWith");
        log.info("username: {}", username);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByUsernameStartingWith(username);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

    @Operation(summary = "Buscar menu por persona ID", description = "")
    @GetMapping("find/username/like/{username}")
    public ResponseEntity<List<Usuario>> findUsuarioByUsernameContaining(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String username) {
        log.info("UsuarioController - findUsuarioByUsernameContaining");
        log.info("username: {}", username);
        List<Usuario> usuarioOpt = iUsuarioService.findUsuarioByUsernameContaining(username);
        log.info("usuarioOpt {}", usuarioOpt);
        return ResponseEntity.ok(usuarioOpt);
    }

}
