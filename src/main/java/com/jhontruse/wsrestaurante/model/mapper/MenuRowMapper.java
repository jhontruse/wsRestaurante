package com.jhontruse.wsrestaurante.model.mapper;


import com.jhontruse.wsrestaurante.model.entity.Menu;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MenuRowMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        Menu menu = new Menu();
        menu.setId(rs.getString("ID"));
        menu.setNombre(rs.getString("NOMBRE"));
        menu.setRuta(rs.getString("RUTA"));
        menu.setIcono(rs.getString("ICONO"));
        menu.setOrden(rs.getInt("ORDEN"));
        menu.setActivo(rs.getBoolean("ACTIVO"));
        return menu;
    }

}
