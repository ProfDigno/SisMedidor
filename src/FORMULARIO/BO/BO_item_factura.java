package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnMySql;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_factura;
import FORMULARIO.ENTIDAD.item_factura;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_factura {

    private DAO_item_factura ifac_dao = new DAO_item_factura();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_factura(item_factura ifac, JTable tbltabla) {
        String titulo = "insertar_item_factura";
        Connection conn = ConnMySql.getConnMySql();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ifac_dao.insertar_item_factura(conn, ifac);
            ifac_dao.actualizar_tabla_item_factura(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ifac.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ifac.toString(), titulo);
            }
        }
    }

    public void update_item_factura(item_factura ifac, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_FACTURA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_factura";
            Connection conn = ConnMySql.getConnMySql();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ifac_dao.update_item_factura(conn, ifac);
                ifac_dao.actualizar_tabla_item_factura(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ifac.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ifac.toString(), titulo);
                }
            }
        }
    }
}
