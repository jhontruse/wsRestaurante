package com.jhontruse.wsrestaurante.service.impl;

import com.jhontruse.wsrestaurante.exception.ResourceNotFoundException;
import com.jhontruse.wsrestaurante.model.dto.request.RolListMenuRequest;
import com.jhontruse.wsrestaurante.model.dto.response.RolListMenuResponse;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.model.entity.Rol;
import com.jhontruse.wsrestaurante.model.mapper.RolListMenuResponseMapper;
import com.jhontruse.wsrestaurante.repository.IMenuRepository;
import com.jhontruse.wsrestaurante.repository.IRolRepository;
import com.jhontruse.wsrestaurante.service.IRolService;
import com.jhontruse.wsrestaurante.util.validator.MenuValidator;
import com.jhontruse.wsrestaurante.util.validator.RolValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolService implements IRolService {

    private final IRolRepository iRolRepository;

    private final IMenuRepository iMenuRepository;

    private final RolValidator rolValidator;

    private final MenuValidator menuValidator;

    @Override
    public List<Rol> findAll() {
        log.info("RolService - findAll");
        List<Rol> roles = iRolRepository.findAll().stream().sorted(Comparator.comparing(Rol::getNombre)).toList();
        log.info("roles {}", roles);
        return roles;
    }

    @Override
    public Optional<Rol> findRolById(String id) {
        log.info("RolService - findRolById");
        log.info("id {}", id);
        Optional<Rol> rolOpt = iRolRepository.findById(id).stream().findFirst();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public List<Rol> findRolByNombre(String nombre) {
        log.info("RolService - findRolByNombre");
        log.info("nombre {}", nombre);
        List<Rol> rolOpt = iRolRepository.findRolByNombre(nombre).stream().toList();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public List<Rol> findRolByDescripcion(String descripcion) {
        log.info("RolService - findRolByDescripcion");
        log.info("descripcion {}", descripcion);
        List<Rol> rolOpt = iRolRepository.findRolByDescripcion(descripcion).stream().toList();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public List<Rol> findRolByActivo(Boolean activo) {
        log.info("RolService - findRolByActivo");
        log.info("activo {}", activo);
        List<Rol> rolOpt = iRolRepository.findRolByActivo(activo).stream().toList();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public List<Rol> findRolByNombreStartingWith(String nombre) {
        log.info("RolService - findRolByNombreStartingWith");
        log.info("nombre {}", nombre);
        List<Rol> rolOpt = iRolRepository.findRolByNombreStartingWith(nombre).stream().toList();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public List<Rol> findRolByDescripcionContaining(String descripcion) {
        log.info("RolService - findRolByDescripcionContaining");
        log.info("descripcion {}", descripcion);
        List<Rol> rolOpt = iRolRepository.findRolByDescripcionContaining(descripcion).stream().toList();
        log.info("rolOpt {}", rolOpt);
        if (rolOpt.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros de rol");
        }
        return rolOpt;
    }

    @Override
    public Rol save(Rol rol) {
        log.info("RolService - save");
        rolValidator.validarParaCrear(rol);
        String uuid = UUID.randomUUID().toString();
        log.info("uuid {}", uuid);
        rol.setId(uuid);
        LocalDateTime nowFec = LocalDateTime.now();
        log.info("nowFec {}", nowFec);
        rol.setFechaCreacion(nowFec);
        log.info("rol {}", rol);
        Rol savedRol = iRolRepository.procedureSaveRol(rol);
        log.info("savedRol {}", savedRol);
        return savedRol;
    }

    @Override
    public RolListMenuResponse saveWithMenus(RolListMenuRequest rolListMenuRequest) {
        log.info("RolService - saveWithMenus");
        log.info("rolListMenuRequest {}", rolListMenuRequest);
        rolValidator.validarNombreUnico(rolListMenuRequest.getNombre());
        String uuid = UUID.randomUUID().toString();
        log.info("uuid {}", uuid);
        rolListMenuRequest.setId(uuid);
        LocalDateTime nowFec = LocalDateTime.now();
        log.info("nowFec {}", nowFec);
        rolListMenuRequest.setFechaCreacion(nowFec);
        log.info("rolListMenuRequest {}", rolListMenuRequest);

        rolListMenuRequest.getMenuIds().forEach(menuId -> {
            log.info("Paso 1 - menuId:" + menuId);
            log.info("menuId {}", menuId);
            menuValidator.validarIdMenuExist(menuId);
        });

        Rol rol = new Rol();
        rol.setId(rolListMenuRequest.getId());
        rol.setNombre(rolListMenuRequest.getNombre());
        rol.setDescripcion(rolListMenuRequest.getDescripcion());
        rol.setActivo(rolListMenuRequest.getActivo());
        rol.setFechaCreacion(rolListMenuRequest.getFechaCreacion());
        log.info("rol {}", rol);

        Rol rolSave = save(rol);

        log.info("rol {}", rol);

        List<Menu> menus = new ArrayList<>();

        rolListMenuRequest.getMenuIds().forEach(menuId -> {
            log.info("rol.getId() {}", rol.getId());
            log.info("menuId {}", menuId);
            iRolRepository.procedureSaveRolWithMenus(rol.getId(), menuId);

            Optional<Menu> menusOpt = iMenuRepository.findMenuById(menuId);

            menusOpt.ifPresent(menus::add);

            log.info("menus {}", menus);
        });

        return RolListMenuResponseMapper.toResponse(rolSave, menus);
    }

    @Override
    public Rol update(Rol rol) {
        log.info("RolService - update");
        rolValidator.validarParaActualizar(rol);
        Rol updateRol = iRolRepository.procedureUpdateRol(rol);
        log.info("updateRol {}", updateRol);
        return updateRol;
    }

    @Override
    public void delete(Rol rol) {
        rolValidator.validarParaEliminar(rol);
        iRolRepository.deleteById(rol.getId());
    }

    @Override
    public void deleteById(String id) {
        rolValidator.validarParaEliminar(id);
        iRolRepository.deleteById(id);
    }
}
