package com.jhontruse.wsrestaurante.controller;

import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.service.IMenuService;
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
@RequestMapping("/menu")
@Tag(name = "Menu", description = "Gestión de menu del sistema")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    private final IMenuService iMenuService;

    @Operation(summary = "Buscar menus", description = "")
    @GetMapping("find/all")
    public ResponseEntity<List<Menu>> findAll() {
        log.info("MenuController - findAll");
        List<Menu> menus = iMenuService.findAll();
        log.info("menus {}", menus);
        return ResponseEntity.ok(menus);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/id/{id}")
    public ResponseEntity<Optional<Menu>> findMenuById(
            @PathVariable @Parameter(description = "UUID a buscar", example = "", required = true) String id) {
        log.info("MenuController - findMenuById");
        log.info("id: {}", id);
        Optional<Menu> menuOpt = iMenuService.findMenuById(id);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/nombre/{nombre}")
    public ResponseEntity<List<Menu>> findMenuByNombre(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String nombre) {
        log.info("MenuController - findMenuByNombre");
        log.info("nombre: {}", nombre);
        List<Menu> menuOpt = iMenuService.findMenuByNombre(nombre);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/ruta/{ruta}")
    public ResponseEntity<List<Menu>> findMenuByRuta(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String ruta) {
        log.info("MenuController - findMenuByRuta");
        log.info("ruta: {}", ruta);
        List<Menu> menuOpt = iMenuService.findMenuByRuta(ruta);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/icono/{icono}")
    public ResponseEntity<List<Menu>> findMenuByIcono(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String icono) {
        log.info("MenuController - findMenuByIcono");
        log.info("icono: {}", icono);
        List<Menu> menuOpt = iMenuService.findMenuByIcono(icono);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/orden/{orden}")
    public ResponseEntity<List<Menu>> findMenuByOrden(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) Integer orden) {
        log.info("MenuController - findMenuByOrden");
        log.info("orden: {}", orden);
        List<Menu> menuOpt = iMenuService.findMenuByOrden(orden);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/activo/{activo}")
    public ResponseEntity<List<Menu>> findMenuByActivo(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) boolean activo) {
        log.info("MenuController - findMenuByActivo");
        log.info("activo: {}", activo);
        List<Menu> menuOpt = iMenuService.findMenuByActivo(activo);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/nombre/like/{nombre}")
    public ResponseEntity<List<Menu>> findMenuByNombreIsLike(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String nombre) {
        log.info("MenuController - findMenuByNombreIsLike");
        log.info("nombre: {}", nombre);
        List<Menu> menuOpt = iMenuService.findMenuByNombreIsLike(nombre);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Buscar menu", description = "")
    @GetMapping("find/nombre/start/{nombre}")
    public ResponseEntity<List<Menu>> findMenuByNombreStartingWith(
            @PathVariable @Parameter(description = "Nombre a buscar", example = "", required = true) String nombre) {
        log.info("MenuController - findMenuByNombreStartingWith");
        log.info("nombre: {}", nombre);
        List<Menu> menuOpt = iMenuService.findMenuByNombreStartingWith(nombre);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Registrar menu", description = "")
    @PostMapping("save")
    public ResponseEntity<Menu> save(@Valid @RequestBody Menu menu) {
        log.info("MenuController - save");
        log.info("menu: {}", menu);
        Menu menuOpt = iMenuService.save(menu);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.created(URI.create("/" + menuOpt.getId())).body(menuOpt);
    }

    @Operation(summary = "Actualizar menu", description = "")
    @PutMapping("update")
    public ResponseEntity<Menu> update(@Valid @RequestBody Menu menu) {
        log.info("MenuController - update");
        log.info("menu: {}", menu);
        Menu menuOpt = iMenuService.update(menu);
        log.info("menuOpt {}", menuOpt);
        return ResponseEntity.ok(menuOpt);
    }

    @Operation(summary = "Eliminar menu", description = "")
    @DeleteMapping("delete")
    public ResponseEntity<Integer> deleteMenu(@Valid @RequestBody Menu menu) {
        log.info("MenuController - deleteMenu");
        log.info("menu: {}", menu);
        iMenuService.delete(menu);
        return ResponseEntity.ok(1);
    }

    @Operation(summary = "Eliminar menu", description = "")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Integer> deleteId(
            @PathVariable @Parameter(description = "Id a eliminr", example = "", required = true) String id
    ) {
        log.info("MenuController - deleteId");
        log.info("id: {}", id);
        iMenuService.deleteById(id);
        return ResponseEntity.ok(1);
    }

}
