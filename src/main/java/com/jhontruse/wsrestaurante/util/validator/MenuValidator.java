package com.jhontruse.wsrestaurante.util.validator;

import com.jhontruse.wsrestaurante.exception.BusinessException;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.repository.IMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MenuValidator {

    private final IMenuRepository iMenuRepository;

    // ── Valida nombre duplicado ────────────────────────────────
    public void validarNombreUnico(String nombre) {
        log.info("MenuValidator - validarNombreUnico");
        log.info("nombre {}", nombre);
        boolean exist = iMenuRepository.findMenuByNombre(nombre).stream().findFirst().isPresent();
        if (exist) {
            log.info("exist {}", exist);
            throw new BusinessException(99, "BUSINESS_ERROR", "El nombre ya existe en el sistema");
        }
    }

    // ── Valida ruta duplicado ────────────────────────────────
    public void validarRutaUnico(String ruta) {
        log.info("MenuValidator - validarRutaUnico");
        log.info("ruta {}", ruta);
        boolean exist = iMenuRepository.findMenuByRuta(ruta).stream().findFirst().isPresent();
        if (exist) {
            log.info("exist {}", exist);
            throw new BusinessException(99, "BUSINESS_ERROR", "La ruta ya existe en el sistema");
        }
    }

    // ── Valida nombre duplicado ────────────────────────────────
    public void validarOrdenUnico(Integer orden) {
        log.info("MenuValidator - validarOrdenUnico");
        log.info("orden {}", orden);
        boolean exist = iMenuRepository.findMenuByOrden(orden).stream().findFirst().isPresent();
        if (exist) {
            log.info("exist {}", exist);
            throw new BusinessException(99, "BUSINESS_ERROR", "El orden ya existe en el sistema");
        }
    }

    public boolean validarIdMenu(String id) {
        log.info("MenuValidator - validarIdMenu");
        log.info("id {}", id);
        return iMenuRepository.existsById(id);
    }

    public void validarIdMenuExist(String id) {
        log.info("MenuValidator - validarIdMenu");
        log.info("id {}", id);
        if (!validarIdMenu(id)) {
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no existe en el sistema");
        }
    }

    public void validarParaCrear(Menu menu) {
        log.debug("MenuValidator - validarParaCrear");
        log.info("menu {}", menu);
        validarNombreUnico(menu.getNombre());
        validarRutaUnico(menu.getRuta());
        validarOrdenUnico(menu.getOrden());
    }

    public void validarParaActualizar(Menu menu) {
        log.info("MenuValidator - validarParaActualizar");
        log.info("menu {}", menu);
        if (!validarIdMenu(menu.getId())) {
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no existe en el sistema");
        }
        Optional<Menu> menuOpt = iMenuRepository.findMenuById(menu.getId()).stream().findFirst();
        log.info("menuOpt {}", menuOpt);
        List<Menu> menuListNombre = iMenuRepository.findMenuByNombre(menu.getNombre()).stream().toList();
        log.info("menuListNombre {}", menuListNombre);
        boolean existNombre = menuListNombre.stream().anyMatch(m -> !m.getNombre().equals(menuOpt.get().getNombre()));
        if (existNombre) {
            log.info("existNombre {}", existNombre);
            throw new BusinessException(99, "BUSINESS_ERROR", "El nombre ya existe en el sistema");
        }
        List<Menu> menuListRuta = iMenuRepository.findMenuByRuta(menu.getRuta()).stream().toList();
        log.info("menuListRuta {}", menuListRuta);
        boolean existRuta = menuListRuta.stream().anyMatch(m -> !m.getRuta().equals(menuOpt.get().getRuta()));
        if (existRuta) {
            log.info("existRuta {}", existRuta);
            throw new BusinessException(99, "BUSINESS_ERROR", "La ruta ya existe en el sistema");
        }
        List<Menu> menuListOrden = iMenuRepository.findMenuByOrden(menu.getOrden()).stream().toList();
        log.info("menuListOrden {}", menuListOrden);
        boolean existOrden = menuListOrden.stream().anyMatch(m -> !m.getOrden().equals(menuOpt.get().getOrden()));
        if (existOrden) {
            log.info("existOrden {}", existOrden);
            throw new BusinessException(99, "BUSINESS_ERROR", "El orden ya existe en el sistema");
        }
    }

    public void validarParaEliminar(Menu menu) {
        log.info("MenuValidator - validarParaEliminar");
        log.info("menu {}", menu);
        if (!validarIdMenu(menu.getId())) {
            log.info("!validarIdMenu(menu.getId()) {}", !validarIdMenu(menu.getId()));
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no ya existe en el sistema");
        }
    }

    public void validarParaEliminar(String id) {
        log.info("MenuValidator - validarParaEliminar");
        log.info("id {}", id);
        if (!validarIdMenu(id)) {
            log.info("!validarIdMenu(id) {}", !validarIdMenu(id));
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no ya existe en el sistema");
        }
    }

}