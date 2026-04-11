package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Menu;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMenuRepository extends CrudRepository<Menu, String>,
        PagingAndSortingRepository<Menu, String>,
        MenuRepositoryCustom {

    @NonNull
    List<Menu> findAll();

    Optional<Menu> findMenuById(String id);

    List<Menu> findMenuByNombre(String nombre);

    List<Menu> findMenuByRuta(String ruta);

    List<Menu> findMenuByIcono(String icono);

    List<Menu> findMenuByOrden(Integer orden);

    List<Menu> findMenuByActivo(Boolean activo);

    List<Menu> findMenuByNombreIsLike(String nombre);

    List<Menu> findMenuByNombreStartingWith(String nombre);

}
