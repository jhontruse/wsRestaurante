package com.jhontruse.wsrestaurante.repository;

import com.jhontruse.wsrestaurante.model.entity.Rol;

public interface RolRepositoryCustom {

    Rol procedureSaveRol(Rol rol);

    Rol procedureUpdateRol(Rol rol);

    void procedureSaveRolWithMenus(String idRol, String idMenu);

}
