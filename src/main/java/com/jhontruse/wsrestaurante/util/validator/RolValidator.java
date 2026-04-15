package com.jhontruse.wsrestaurante.util.validator;

import com.jhontruse.wsrestaurante.exception.BusinessException;
import com.jhontruse.wsrestaurante.model.entity.Rol;
import com.jhontruse.wsrestaurante.repository.IRolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolValidator {

    private final IRolRepository iRolRepository;

    // ── Valida nombre duplicado ────────────────────────────────
    public void validarNombreUnico(String nombre) {
        log.info("IRolRepository - validarNombreUnico");
        log.info("nombre {}", nombre);
        boolean exist = iRolRepository.findRolByNombre(nombre).stream().findFirst().isPresent();
        if (exist) {
            log.info("exist {}", exist);
            throw new BusinessException(99, "BUSINESS_ERROR", "El nombre ya existe en el sistema");
        }
    }

    public boolean validarIdRol(String id) {
        log.info("IRolRepository - validarIdRol");
        log.info("id {}", id);
        return iRolRepository.existsById(id);
    }

    public void validarParaCrear(Rol rol) {
        log.debug("IRolRepository - validarParaCrear");
        log.info("rol {}", rol);
        validarNombreUnico(rol.getNombre());
    }

    public void validarParaActualizar(Rol rol) {
        log.info("IRolRepository - validarParaActualizar");
        log.info("rol {}", rol);
        if (!validarIdRol(rol.getId())) {
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no existe en el sistema");
        }
        Optional<Rol> rolOpt = iRolRepository.findById(rol.getId()).stream().findFirst();
        log.info("rolOpt {}", rolOpt);
        List<Rol> rolListNombre = iRolRepository.findRolByNombre(rol.getNombre()).stream().toList();
        log.info("rolListNombre {}", rolListNombre);
        boolean existNombre = rolListNombre.stream().anyMatch(m -> !m.getNombre().equals(rolOpt.get().getNombre()));
        if (existNombre) {
            log.info("existNombre {}", existNombre);
            throw new BusinessException(99, "BUSINESS_ERROR", "El nombre ya existe en el sistema");
        }
    }

    public void validarParaEliminar(Rol rol) {
        log.info("IRolRepository - validarParaEliminar");
        log.info("rol {}", rol);
        if (!validarIdRol(rol.getId())) {
            log.info("!validarIdMenu(menu.getId()) {}", !validarIdRol(rol.getId()));
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no ya existe en el sistema");
        }
    }

    public void validarParaEliminar(String id) {
        log.info("IRolRepository - validarParaEliminar");
        log.info("id {}", id);
        if (!validarIdRol(id)) {
            log.info("!validarIdRol(id) {}", !validarIdRol(id));
            throw new BusinessException(99, "BUSINESS_ERROR", "El id no ya existe en el sistema");
        }
    }

}
