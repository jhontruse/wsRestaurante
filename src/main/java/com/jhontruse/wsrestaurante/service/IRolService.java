package com.jhontruse.wsrestaurante.service;

import com.jhontruse.wsrestaurante.model.dto.request.RolListMenuRequest;
import com.jhontruse.wsrestaurante.model.dto.response.RolListMenuResponse;
import com.jhontruse.wsrestaurante.model.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    List<Rol> findAll();

    Optional<Rol> findRolById(String id);

    List<Rol> findRolByNombre(String nombre);

    List<Rol> findRolByDescripcion(String descripcion);

    List<Rol> findRolByActivo(Boolean activo);

    List<Rol> findRolByNombreStartingWith(String nombre);

    List<Rol> findRolByDescripcionContaining(String descripcion);

    Rol save(Rol rol);

    RolListMenuResponse saveWithMenus(RolListMenuRequest rolListMenuRequest);

    Rol update(Rol rol);

    void delete(Rol rol);

    void deleteById(String id);

}
