package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tarifa;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tarifa {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TARIFA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TARIFA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tarifa(idtarifa,fecha_creado,tipo,monto_tarifa) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE tarifa SET fecha_creado=?,tipo=?,monto_tarifa=? WHERE idtarifa=?;";
    private String sql_select = "SELECT idtarifa,fecha_creado,tipo,monto_tarifa FROM tarifa order by 1 desc;";
    private String sql_cargar = "SELECT idtarifa,fecha_creado,tipo,monto_tarifa FROM tarifa WHERE idtarifa=";

    public void insertar_tarifa(Connection conn, tarifa tar) {
        tar.setC1idtarifa(eveconn.getInt_ultimoID_mas_uno(conn, tar.getTb_tarifa(), tar.getId_idtarifa()));
        String titulo = "insertar_tarifa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tar.getC1idtarifa());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, tar.getC3tipo());
            pst.setDouble(4, tar.getC4monto_tarifa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tar.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tar.toString(), titulo);
        }
    }

    public void update_tarifa(Connection conn, tarifa tar) {
        String titulo = "update_tarifa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, tar.getC3tipo());
            pst.setDouble(3, tar.getC4monto_tarifa());
            pst.setInt(4, tar.getC1idtarifa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tar.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tar.toString(), titulo);
        }
    }

    public void cargar_tarifa(Connection conn, tarifa tar, int id) {
        String titulo = "Cargar_tarifa";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                tar.setC1idtarifa(rs.getInt(1));
                tar.setC2fecha_creado(rs.getString(2));
                tar.setC3tipo(rs.getString(3));
                tar.setC4monto_tarifa(rs.getDouble(4));
                tar.setC4monto_tarifa_global(rs.getDouble(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tar.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tar.toString(), titulo);
        }
    }

    public void actualizar_tabla_tarifa(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tarifa(tbltabla);
    }

    public void ancho_tabla_tarifa(JTable tbltabla) {
        int Ancho[] = {25, 25, 25, 25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
