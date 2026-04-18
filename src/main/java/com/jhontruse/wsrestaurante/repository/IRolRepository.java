package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Rol;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IRolRepository extends CrudRepository<Rol, String>,
        PagingAndSortingRepository<Rol, String>,
        RolRepositoryCustom {

    @NonNull
    List<Rol> findAll();

    List<Rol> findRolByNombre(String nombre);

    List<Rol> findRolByDescripcion(String descripcion);

    List<Rol> findRolByActivo(Boolean activo);

    List<Rol> findRolByNombreStartingWith(String nombre);

    List<Rol> findRolByDescripcionContaining(String descripcion);

}
