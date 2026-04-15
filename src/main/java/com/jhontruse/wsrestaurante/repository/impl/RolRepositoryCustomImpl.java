package com.jhontruse.wsrestaurante.repository.impl;

import com.jhontruse.wsrestaurante.exception.BusinessException;
import com.jhontruse.wsrestaurante.model.entity.Rol;
import com.jhontruse.wsrestaurante.repository.RolRepositoryCustom;
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
public class RolRepositoryCustomImpl implements RolRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Rol procedureSaveRol(Rol rol) {
        log.info("RolRepositoryCustomImpl - procedureSaveRol");
        log.info("rol {}", rol);
        // ---------- SALIDAS ----------
        SimpleJdbcCall spInsertRol = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("BD_RESTAURANTE")
                .withCatalogName("PK_BD_RESTAURANTE")
                .withProcedureName("p_inserta_rol")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ID", Types.VARCHAR),
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_DESCRIPCION", Types.VARCHAR),
                        new SqlParameter("P_ACTIVO", Types.BOOLEAN),
                        new SqlParameter("P_FECHA_CREACION", Types.DATE),
                        // ---------- SALIDAS ----------
                        new SqlOutParameter("P_STATUS", Types.INTEGER),
                        new SqlOutParameter("P_MSG", Types.VARCHAR));
        // ---------- FIN  ----------
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID", rol.getId())
                .addValue("P_NOMBRE", rol.getNombre())
                .addValue("P_DESCRIPCION", rol.getDescripcion())
                .addValue("P_ACTIVO", rol.getActivo())
                .addValue("P_FECHA_CREACION", rol.getFechaCreacion());
        Map<String, Object> result = spInsertRol.execute(params);
        Integer pStatus = (Integer) result.get("P_STATUS");
        String pMsg = (String) result.get("P_MSG");
        log.info("pStatus {}", pStatus);
        log.info("pMsg {}", pMsg);
        if (pStatus != 0) {
            throw new BusinessException(pStatus, "BUSINESS_ERROR", pMsg);
        }
        return rol;
    }

    @Override
    public Rol procedureUpdateRol(Rol rol) {
        log.info("RolRepositoryCustomImpl - procedureUpdateRol");
        log.info("rol {}", rol);
        // ---------- SALIDAS ----------
        SimpleJdbcCall spUpdateRol = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("BD_RESTAURANTE")
                .withCatalogName("PK_BD_RESTAURANTE")
                .withProcedureName("p_update_rol")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ID", Types.VARCHAR),
                        new SqlParameter("P_NOMBRE", Types.VARCHAR),
                        new SqlParameter("P_DESCRIPCION", Types.VARCHAR),
                        new SqlParameter("P_ACTIVO", Types.BOOLEAN),
                        new SqlParameter("P_FECHA_CREACION", Types.DATE),
                        // ---------- SALIDAS ----------
                        new SqlOutParameter("P_STATUS", Types.INTEGER),
                        new SqlOutParameter("P_MSG", Types.VARCHAR));
        // ---------- FIN  ----------
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID", rol.getId())
                .addValue("P_NOMBRE", rol.getNombre())
                .addValue("P_DESCRIPCION", rol.getDescripcion())
                .addValue("P_ACTIVO", rol.getActivo())
                .addValue("P_FECHA_CREACION", rol.getFechaCreacion());
        Map<String, Object> result = spUpdateRol.execute(params);
        Integer pStatus = (Integer) result.get("P_STATUS");
        String pMsg = (String) result.get("P_MSG");
        log.info("pStatus {}", pStatus);
        log.info("pMsg {}", pMsg);
        if (pStatus != 0) {
            throw new BusinessException(pStatus, "BUSINESS_ERROR", pMsg);
        }
        return rol;
    }

    @Override
    public void procedureSaveRolWithMenus(String idRol, String idMenu) {
        log.info("RolRepositoryCustomImpl - procedureSaveRolWithMenus");
        log.info("idRol {}", idRol);
        log.info("idMenu {}", idMenu);
        // ---------- SALIDAS ----------
        SimpleJdbcCall spInsertRolMenu = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("BD_RESTAURANTE")
                .withCatalogName("PK_BD_RESTAURANTE")
                .withProcedureName("p_insert_rol_menu")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ROL_ID", Types.VARCHAR),
                        new SqlParameter("P_MENU_ID", Types.VARCHAR),
                        // ---------- SALIDAS ----------
                        new SqlOutParameter("P_STATUS", Types.INTEGER),
                        new SqlOutParameter("P_MSG", Types.VARCHAR));
        // ---------- FIN  ----------
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ROL_ID", idRol)
                .addValue("P_MENU_ID", idMenu);
        Map<String, Object> result = spInsertRolMenu.execute(params);
        Integer pStatus = (Integer) result.get("P_STATUS");
        String pMsg = (String) result.get("P_MSG");
        log.info("pStatus {}", pStatus);
        log.info("pMsg {}", pMsg);
        if (pStatus != 0) {
            throw new BusinessException(pStatus, "BUSINESS_ERROR", pMsg);
        }
    }
}
