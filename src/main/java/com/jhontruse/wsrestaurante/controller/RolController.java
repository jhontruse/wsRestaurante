package com.jhontruse.wsrestaurante.controller;

import com.jhontruse.wsrestaurante.model.dto.request.RolListMenuRequest;
import com.jhontruse.wsrestaurante.model.dto.response.RolListMenuResponse;
import com.jhontruse.wsrestaurante.model.entity.Rol;
import com.jhontruse.wsrestaurante.service.IRolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/rol")
@Tag(name = "Rol", description = "Gestión de rol del sistema")
@SecurityRequirement(name = "bearerAuth")
public class RolController {

    private final IRolService iRolService;

    @Operation(summary = "Buscar roles", description = "")
    @GetMapping("find/all")
    public ResponseEntity<List<Rol>> findAll() {
        log.info("RolController - findAll");
        List<Rol> roles = iRolService.findAll();
        log.info("roles {}", roles);
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Buscar rol", description = "")
    @GetMapping("find/id/{id}")
    public ResponseEntity<Optional<Rol>> findRolById(@PathVariable @Parameter(description = "UUID a buscar", example = "", required = true) String id) {
        log.info("RolController - findRolById");
        log.info("id: {}", id);
        Optional<Rol> rolOpt = iRolService.findRolById(id);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Buscar rol", description = "")
    @GetMapping("find/nombre/{nombre}")
    public ResponseEntity<List<Rol>> findRolByNombre(@PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String nombre) {
        log.info("RolController - findRolByNombre");
        log.info("nombre: {}", nombre);
        List<Rol> rolOpt = iRolService.findRolByNombre(nombre);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Buscar rol", description = "")
    @GetMapping("find/descripcion/{descripcion}")
    public ResponseEntity<List<Rol>> findRolByDescripcion(@PathVariable @Parameter(description = "Descripción a buscar", example = "", required = true) String descripcion) {
        log.info("RolController - findRolByDescripcion");
        log.info("descripcion: {}", descripcion);
        List<Rol> rolOpt = iRolService.findRolByDescripcion(descripcion);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Buscar rol", description = "")
    @GetMapping("find/activo/{activo}")
    public ResponseEntity<List<Rol>> findRolByActivo(@PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) boolean activo) {
        log.info("RolController - findRolByActivo");
        log.info("activo: {}", activo);
        List<Rol> rolOpt = iRolService.findRolByActivo(activo);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/nombre/start/{nombre}")
    public ResponseEntity<List<Rol>> findRolByNombreStartingWith(@PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String nombre) {
        log.info("RolController - findRolByNombreStartingWith");
        log.info("nombre: {}", nombre);
        List<Rol> rolOpt = iRolService.findRolByNombreStartingWith(nombre);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/descripcion/like/{descripcion}")
    public ResponseEntity<List<Rol>> findRolByDescripcionContaining(@PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String descripcion) {
        log.info("RolController - findRolByDescripcionContaining");
        log.info("descripcion: {}", descripcion);
        List<Rol> rolOpt = iRolService.findRolByDescripcionContaining(descripcion);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Registrar rol", description = "")
    @PostMapping("save")
    public ResponseEntity<Rol> save(@Valid @RequestBody Rol rol) {
        log.info("RolController - save");
        log.info("rol: {}", rol);
        Rol rolOpt = iRolService.save(rol);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.created(URI.create("/" + rolOpt.getId())).body(rolOpt);
    }

    @Operation(summary = "Actualizar rol", description = "")
    @PutMapping("update")
    public ResponseEntity<Rol> update(@Valid @RequestBody Rol rol) {
        log.info("RolController - update");
        log.info("rol: {}", rol);
        Rol rolOpt = iRolService.update(rol);
        log.info("rolOpt {}", rolOpt);
        return ResponseEntity.ok(rolOpt);
    }

    @Operation(summary = "Eliminar menu", description = "")
    @DeleteMapping("delete")
    public ResponseEntity<Integer> deleteRol(@Valid @RequestBody Rol rol) {
        log.info("RolController - deleteRol");
        log.info("rol: {}", rol);
        iRolService.delete(rol);
        return ResponseEntity.ok(1);
    }

    @Operation(summary = "Eliminar menu", description = "")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Integer> deleteId(
            @PathVariable @Parameter(description = "Id a eliminr", example = "", required = true) String id
    ) {
        log.info("RolController - deleteId");
        log.info("id: {}", id);
        iRolService.deleteById(id);
        return ResponseEntity.ok(1);
    }

    @Operation(summary = "Registrar rol y lista menus", description = "")
    @PostMapping("save/dto")
    public ResponseEntity<RolListMenuResponse> saveWithMenus(@RequestBody RolListMenuRequest rolListMenuRequest) {
        log.info("RolController - saveWithMenus");
        log.info("rolListMenuRequest: {}", rolListMenuRequest);
        RolListMenuResponse rolListMenuResponse = iRolService.saveWithMenus(rolListMenuRequest);
        log.info("rolListMenuResponse {}", rolListMenuResponse);
        return ResponseEntity.created(URI.create("/" + rolListMenuResponse.getId())).body(rolListMenuResponse);
    }

}
