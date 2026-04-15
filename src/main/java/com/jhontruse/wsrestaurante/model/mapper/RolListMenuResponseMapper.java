package com.jhontruse.wsrestaurante.model.mapper;

import com.jhontruse.wsrestaurante.model.dto.response.RolListMenuResponse;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.model.entity.Rol;

import java.util.List;

public class RolListMenuResponseMapper {

    private RolListMenuResponseMapper() {
    }

    public static RolListMenuResponse toResponse(Rol rol, List<Menu> menus) {
        return RolListMenuResponse.builder()
                .id(rol.getId())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .activo(rol.getActivo())
                .fechaCreacion(rol.getFechaCreacion())
                .menus(menus.stream()
                        .map(RolListMenuResponseMapper::toMenuItemResponse)
                        .sorted((m1, m2) -> m1.getOrden().compareTo(m2.getOrden()))
                        .toList())
                .build();
    }

    private static RolListMenuResponse.MenuItemResponse toMenuItemResponse(Menu menu) {
        return RolListMenuResponse.MenuItemResponse.builder()
                .id(menu.getId())
                .nombre(menu.getNombre())
                .ruta(menu.getRuta())
                .icono(menu.getIcono())
                .orden(menu.getOrden())
                .activo(menu.getActivo())
                .build();
    }

}
