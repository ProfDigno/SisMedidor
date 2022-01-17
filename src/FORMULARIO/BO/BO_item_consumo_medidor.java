package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_consumo_medidor;
import FORMULARIO.ENTIDAD.item_consumo_medidor;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_consumo_medidor {

    private DAO_item_consumo_medidor icm_dao = new DAO_item_consumo_medidor();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_consumo_medidor(item_consumo_medidor icm) {
        String titulo = "insertar_item_consumo_medidor";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            icm_dao.insertar_item_consumo_medidor(conn, icm);
//            icm_dao.actualizar_tabla_item_consumo_medidor(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, icm.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, icm.toString(), titulo);
            }
        }
    }

    public void update_item_consumo_medidor(item_consumo_medidor icm, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_CONSUMO_MEDIDOR", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_consumo_medidor";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                icm_dao.update_item_consumo_medidor(conn, icm);
                icm_dao.actualizar_tabla_item_consumo_medidor(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, icm.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, icm.toString(), titulo);
                }
            }
        }
    }
}
