package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_tarifa;
import FORMULARIO.ENTIDAD.tarifa;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_tarifa {

    private DAO_tarifa tar_dao = new DAO_tarifa();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_tarifa(tarifa tar, JTable tbltabla) {
        String titulo = "insertar_tarifa";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            tar_dao.insertar_tarifa(conn, tar);
            tar_dao.actualizar_tabla_tarifa(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, tar.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, tar.toString(), titulo);
            }
        }
    }

    public void update_tarifa(tarifa tar, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TARIFA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tarifa";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                tar_dao.update_tarifa(conn, tar);
                tar_dao.actualizar_tabla_tarifa(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, tar.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, tar.toString(), titulo);
                }
            }
        }
    }
}
