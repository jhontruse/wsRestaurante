package com.jhontruse.wsrestaurante.service.impl;

import com.jhontruse.wsrestaurante.exception.ResourceNotFoundException;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.repository.IMenuRepository;
import com.jhontruse.wsrestaurante.service.IMenuService;
import com.jhontruse.wsrestaurante.util.validator.MenuValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {

    private final IMenuRepository iMenuRepository;

    private final MenuValidator menuValidator;

    @Override
    public List<Menu> findAll() {
        log.info("MenuService - findAll");
        List<Menu> menus = iMenuRepository.findAll().stream().sorted(Comparator.comparing(Menu::getOrden)).toList();
        log.info("menus {}", menus);
        return menus;
    }

    @Override
    public Optional<Menu> findMenuById(String id) {
        log.info("MenuService - findMenuById");
        log.info("id {}", id);
        Optional<Menu> menuOpt = iMenuRepository.findMenuById(id).stream().findFirst();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByNombre(String nombre) {
        log.info("MenuService - findMenuByNombre");
        log.info("nombre {}", nombre);
        List<Menu> menuOpt = iMenuRepository.findMenuByNombre(nombre).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByRuta(String ruta) {
        log.info("MenuService - findMenuByRuta");
        log.info("ruta {}", ruta);
        List<Menu> menuOpt = iMenuRepository.findMenuByRuta(ruta).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByIcono(String icono) {
        log.info("MenuService - findMenuByIcono");
        log.info("icono {}", icono);
        List<Menu> menuOpt = iMenuRepository.findMenuByIcono(icono).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByOrden(Integer orden) {
        log.info("MenuService - findMenuByOrden");
        log.info("orden {}", orden);
        List<Menu> menuOpt = iMenuRepository.findMenuByOrden(orden).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByActivo(boolean activo) {
        log.info("MenuService - findMenuByActivo");
        log.info("activo {}", activo);
        List<Menu> menuOpt = iMenuRepository.findMenuByActivo(activo).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByNombreIsLike(String nombre) {
        log.info("MenuService - findMenuByNombreIsLike");
        log.info("nombre {}", nombre);
        List<Menu> menuOpt = iMenuRepository.findMenuByNombreIsLike(nombre).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> findMenuByNombreStartingWith(String nombre) {
        log.info("MenuService - findMenuByNombreStartingWith");
        log.info("nombre {}", nombre);
        List<Menu> menuOpt = iMenuRepository.findMenuByNombreStartingWith(nombre).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public List<Menu> procedureFindMenuByUsername(String username) {
        log.info("MenuService - procedureFindMenuByUsername");
        log.info("username {}", username);
        List<Menu> menuOpt = iMenuRepository.procedureFindMenuByUsername(username).stream().toList();
        log.info("menuOpt {}", menuOpt);
        if (menuOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de Menu");
        }
        return menuOpt;
    }

    @Override
    public Menu save(Menu menu) {
        log.info("MenuService - save");
        menuValidator.validarParaCrear(menu);
        String uuid = UUID.randomUUID().toString();
        log.info("uuid {}", uuid);
        menu.setId(uuid);
        log.info("menu {}", menu);
        Menu savedMenu = iMenuRepository.procedureSaveMenu(menu);
        log.info("savedMenu {}", savedMenu);
        return savedMenu;
    }

    @Override
    public Menu update(Menu menu) {
        log.info("MenuService - update");
        menuValidator.validarParaActualizar(menu);
        Menu updateMenu = iMenuRepository.procedureUpdateMenu(menu);
        log.info("updateMenu {}", updateMenu);
        return updateMenu;
    }

    @Override
    public void delete(Menu menu) {
        menuValidator.validarParaEliminar(menu);
        iMenuRepository.deleteById(menu.getId());
    }

    @Override
    public void deleteById(String id) {
        menuValidator.validarParaEliminar(id);
        iMenuRepository.deleteById(id);
    }
}
