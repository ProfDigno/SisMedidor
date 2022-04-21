package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnMySql;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_factura;
import FORMULARIO.DAO.DAO_item_factura;
import FORMULARIO.ENTIDAD.factura;
import FORMULARIO.ENTIDAD.item_factura;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_factura {

    private DAO_factura fac_dao = new DAO_factura();
    private DAO_item_factura ifac_dao = new DAO_item_factura();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public boolean getBoolean_insertar_factura(factura fac, JTable tbltabla) {
        boolean insertar=false;
        String titulo = "getBoolean_insertar_factura";
        Connection conn = ConnMySql.getConnMySql();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            fac_dao.insertar_factura(conn, fac);
            ifac_dao.insertar_item_factura_de_tabla(conn, tbltabla);
            conn.commit();
            insertar=true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, fac.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, fac.toString(), titulo);
            }
        }
        return insertar;
    }

    public void update_factura(factura fac, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR FACTURA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_factura";
            Connection conn = ConnMySql.getConnMySql();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                fac_dao.update_factura(conn, fac);
                fac_dao.actualizar_tabla_factura(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, fac.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, fac.toString(), titulo);
                }
            }
        }
    }
    public void estado_update_factura(factura fac) {
            String titulo = "estado_update_factura";
            Connection conn = ConnMySql.getConnMySql();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                fac_dao.estado_update_factura(conn, fac);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, fac.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, fac.toString(), titulo);
                }
            }
        }
}
