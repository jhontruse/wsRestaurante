package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Menu;

public interface MenuRepositoryCustom {

    Menu procedureSaveMenu(Menu menu);

    Menu procedureUpdateMenu(Menu menu);
}
