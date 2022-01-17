	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_empresa;
	import FORMULARIO.ENTIDAD.empresa;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_empresa {
private DAO_empresa emp_dao = new DAO_empresa();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_empresa(empresa emp, JTable tbltabla) {
		String titulo = "insertar_empresa";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			emp_dao.insertar_empresa(conn, emp);
			emp_dao.actualizar_tabla_empresa(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, emp.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, emp.toString(), titulo);
			}
		}
	}
	public void update_empresa(empresa emp, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR EMPRESA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_empresa";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				emp_dao.update_empresa(conn, emp);
				emp_dao.actualizar_tabla_empresa(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, emp.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, emp.toString(), titulo);
				}
			}
		}
	}
}
