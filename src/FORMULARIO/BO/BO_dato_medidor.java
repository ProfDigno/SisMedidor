	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_dato_medidor;
	import FORMULARIO.ENTIDAD.dato_medidor;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_dato_medidor {
private DAO_dato_medidor dam_dao = new DAO_dato_medidor();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_dato_medidor(dato_medidor dam, JTable tbltabla) {
		String titulo = "insertar_dato_medidor";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			dam_dao.insertar_dato_medidor(conn, dam);
			dam_dao.actualizar_tabla_dato_medidor(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, dam.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, dam.toString(), titulo);
			}
		}
	}
	public void update_dato_medidor(dato_medidor dam, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR DATO_MEDIDOR", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_dato_medidor";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				dam_dao.update_dato_medidor(conn, dam);
				dam_dao.actualizar_tabla_dato_medidor(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, dam.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, dam.toString(), titulo);
				}
			}
		}
	}
}
