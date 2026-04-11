package com.jhontruse.wsrestaurante.repository.impl;

import com.jhontruse.wsrestaurante.exception.BusinessException;
import com.jhontruse.wsrestaurante.model.entity.Menu;
import com.jhontruse.wsrestaurante.repository.MenuRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Menu procedureSaveMenu(Menu menu) {
        log.info("MenuRepositoryCustomImpl - procedureSaveMenu");
        log.info("menu {}", menu);
        // ---------- SALIDAS ----------
        SimpleJdbcCall spInsertMenu = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("BD_RESTAURANTE")
                .withCatalogName("PK_BD_RESTAURANTE")
                .withProcedureName("P_INSERTA_MENU")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ID", Types.VARCHAR),
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_RUTA", Types.VARCHAR),
                        new SqlParameter("P_ICONO", Types.VARCHAR),
                        new SqlParameter("P_ORDEN", Types.INTEGER),
                        new SqlParameter("P_ACTIVO", Types.BOOLEAN),
                        // ---------- SALIDAS ----------
                        new SqlOutParameter("P_STATUS", Types.INTEGER),
                        new SqlOutParameter("P_MSG", Types.VARCHAR));
        // ---------- FIN  ----------
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID", menu.getId())
                .addValue("P_NOMBRE", menu.getNombre())
                .addValue("P_RUTA", menu.getRuta())
                .addValue("P_ICONO", menu.getIcono())
                .addValue("P_ORDEN", menu.getOrden())
                .addValue("P_ACTIVO", menu.getActivo());
        Map<String, Object> result = spInsertMenu.execute(params);
        Integer pStatus = (Integer) result.get("P_STATUS");
        String pMsg = (String) result.get("P_MSG");
        log.info("pStatus {}", pStatus);
        log.info("pMsg {}", pMsg);
        if (pStatus != 0) {
            throw new BusinessException(pStatus, "BUSINESS_ERROR", pMsg);
        }
        return menu;
    }

    @Override
    public Menu procedureUpdateMenu(Menu menu) {
        log.info("MenuRepositoryCustomImpl - procedureUpdateMenu");
        log.info("menu {}", menu);
        // ---------- SALIDAS ----------
        SimpleJdbcCall spUpdateMenu = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("BD_RESTAURANTE")
                .withCatalogName("PK_BD_RESTAURANTE")
                .withProcedureName("P_UPDATE_MENU")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ID", Types.VARCHAR),
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_RUTA", Types.VARCHAR),
                        new SqlParameter("P_ICONO", Types.VARCHAR),
                        new SqlParameter("P_ORDEN", Types.INTEGER),
                        new SqlParameter("P_ACTIVO", Types.BOOLEAN),
                        // ---------- SALIDAS ----------
                        new SqlOutParameter("P_STATUS", Types.INTEGER),
                        new SqlOutParameter("P_MSG", Types.VARCHAR));
        // ---------- FIN  ----------
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID", menu.getId())
                .addValue("P_NOMBRE", menu.getNombre())
                .addValue("P_RUTA", menu.getRuta())
                .addValue("P_ICONO", menu.getIcono())
                .addValue("P_ORDEN", menu.getOrden())
                .addValue("P_ACTIVO", menu.getActivo());
        Map<String, Object> result = spUpdateMenu.execute(params);
        Integer pStatus = (Integer) result.get("P_STATUS");
        String pMsg = (String) result.get("P_MSG");
        log.info("pStatus {}", pStatus);
        log.info("pMsg {}", pMsg);
        if (pStatus != 0) {
            throw new BusinessException(pStatus, "BUSINESS_ERROR", pMsg);
        }
        return menu;
    }
}
