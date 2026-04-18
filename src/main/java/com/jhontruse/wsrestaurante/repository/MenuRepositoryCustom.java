package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Menu;

import java.util.List;

public interface MenuRepositoryCustom {

    Menu procedureSaveMenu(Menu menu);

    Menu procedureUpdateMenu(Menu menu);

    List<Menu> procedureFindMenuByUsername(String username);

}
