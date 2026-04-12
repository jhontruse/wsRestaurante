package com.jhontruse.wsrestaurante.service;

import com.jhontruse.wsrestaurante.model.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface IMenuService {

    List<Menu> findAll();

    Optional<Menu> findMenuById(String id);

    List<Menu> findMenuByNombre(String nombre);

    List<Menu> findMenuByRuta(String ruta);

    List<Menu> findMenuByIcono(String icono);

    List<Menu> findMenuByOrden(Integer orden);

    List<Menu> findMenuByActivo(boolean activo);

    List<Menu> findMenuByNombreIsLike(String nombre);

    List<Menu> findMenuByNombreStartingWith(String nombre);

    Menu save(Menu menu);

    Menu update(Menu menu);

    void delete(Menu menu);

    void deleteById(String id);

}
