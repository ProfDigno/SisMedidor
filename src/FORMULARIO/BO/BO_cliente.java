package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_cliente;
import FORMULARIO.ENTIDAD.cliente;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_cliente {

    private DAO_cliente cl_dao = new DAO_cliente();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_cliente(cliente cl, JTable tbltabla) {
        String titulo = "insertar_cliente";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            cl_dao.insertar_cliente(conn, cl);
            cl_dao.actualizar_tabla_cliente(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, cl.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, cl.toString(), titulo);
            }
        }
    }

    public void update_cliente(cliente cl, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR CLIENTE", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_cliente";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                cl_dao.update_cliente(conn, cl);
                cl_dao.actualizar_tabla_cliente(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, cl.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, cl.toString(), titulo);
                }
            }
        }
    }
}
