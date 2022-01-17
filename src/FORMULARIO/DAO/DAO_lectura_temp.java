package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lectura_temp;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_lectura_temp {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LECTURA_TEMP GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LECTURA_TEMP MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO lectura_temp(idlectura_temp,fecha_creado,lectura_kw,numero_medidor) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE lectura_temp SET fecha_creado=?,lectura_kw=?,numero_medidor=? WHERE idlectura_temp=?;";
    private String sql_select = "SELECT idlectura_temp,fecha_creado,lectura_kw,numero_medidor FROM lectura_temp order by 1 desc;";
    private String sql_cargar = "SELECT idlectura_temp,fecha_creado,lectura_kw,numero_medidor FROM lectura_temp WHERE idlectura_temp=";
    private String sql_truncate = "truncate lectura_temp;";
    public void insertar_lectura_temp(Connection conn, lectura_temp ltmp) {
        ltmp.setC1idlectura_temp(eveconn.getInt_ultimoID_mas_uno(conn, ltmp.getTb_lectura_temp(), ltmp.getId_idlectura_temp()));
        String titulo = "insertar_lectura_temp";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ltmp.getC1idlectura_temp());
            pst.setTimestamp(2, evefec.getTimestamp_fecha_cargado(ltmp.getC2fecha_creado()));
            pst.setDouble(3, ltmp.getC3lectura_kw());
            pst.setDouble(4, ltmp.getC4numero_medidor());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ltmp.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ltmp.toString(), titulo);
        }
    }

    public void update_lectura_temp(Connection conn, lectura_temp ltmp) {
        String titulo = "update_lectura_temp";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setDouble(2, ltmp.getC3lectura_kw());
            pst.setDouble(3, ltmp.getC4numero_medidor());
            pst.setInt(4, ltmp.getC1idlectura_temp());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ltmp.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ltmp.toString(), titulo);
        }
    }
    public void truncate_lectura_temp(Connection conn) {
        String titulo = "truncate_lectura_temp";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_truncate);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_truncate , titulo);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_truncate , titulo);
        }
    }

    public void cargar_lectura_temp(Connection conn, lectura_temp ltmp, int id) {
        String titulo = "Cargar_lectura_temp";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ltmp.setC1idlectura_temp(rs.getInt(1));
                ltmp.setC2fecha_creado(rs.getString(2));
                ltmp.setC3lectura_kw(rs.getDouble(3));
                ltmp.setC4numero_medidor(rs.getDouble(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ltmp.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ltmp.toString(), titulo);
        }
    }

    public void actualizar_tabla_lectura_temp(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_lectura_temp(tbltabla);
    }

    public void ancho_tabla_lectura_temp(JTable tbltabla) {
        int Ancho[] = {25, 25, 25, 25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
