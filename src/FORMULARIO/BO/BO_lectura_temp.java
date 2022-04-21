package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnMySql;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_lectura_temp;
import FORMULARIO.ENTIDAD.lectura_temp;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_lectura_temp {

    private DAO_lectura_temp ltmp_dao = new DAO_lectura_temp();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_lectura_temp(lectura_temp ltmp) {
        String titulo = "insertar_lectura_temp";
        Connection conn = ConnMySql.getConnMySql();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ltmp_dao.insertar_lectura_temp(conn, ltmp);
//            ltmp_dao.actualizar_tabla_lectura_temp(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ltmp.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ltmp.toString(), titulo);
            }
        }
    }

    public void update_lectura_temp(lectura_temp ltmp, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LECTURA_TEMP", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_lectura_temp";
            Connection conn = ConnMySql.getConnMySql();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ltmp_dao.update_lectura_temp(conn, ltmp);
                ltmp_dao.actualizar_tabla_lectura_temp(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ltmp.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ltmp.toString(), titulo);
                }
            }
        }
    }
    public void truncate_lectura_temp() {
            String titulo = "truncate_lectura_temp";
            Connection conn = ConnMySql.getConnMySql();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ltmp_dao.truncate_lectura_temp(conn);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e,titulo , titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1,titulo, titulo);
                }
            }
        }
}
