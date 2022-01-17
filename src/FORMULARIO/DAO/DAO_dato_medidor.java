package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.dato_medidor;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_dato_medidor {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "DATO_MEDIDOR GUARDADO CORRECTAMENTE";
    private String mensaje_update = "DATO_MEDIDOR MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO dato_medidor(iddato_medidor,numero_medidor,nombre,pulso_kw,face,fk_idtarifa) VALUES (?,?,?,?,?,?);";
    private String sql_update = "UPDATE dato_medidor SET numero_medidor=?,nombre=?,pulso_kw=?,face=?,fk_idtarifa=? WHERE iddato_medidor=?;";
    private String sql_select = "SELECT iddato_medidor as iddm,numero_medidor as numero,nombre,pulso_kw,face FROM dato_medidor order by 1 desc;";
    private String sql_cargar = "SELECT iddato_medidor,numero_medidor,nombre,pulso_kw,face,fk_idtarifa FROM dato_medidor WHERE iddato_medidor=";

    public void insertar_dato_medidor(Connection conn, dato_medidor dam) {
        dam.setC1iddato_medidor(eveconn.getInt_ultimoID_mas_uno(conn, dam.getTb_dato_medidor(), dam.getId_iddato_medidor()));
        String titulo = "insertar_dato_medidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, dam.getC1iddato_medidor());
            pst.setInt(2, dam.getC2numero_medidor());
            pst.setString(3, dam.getC3nombre());
            pst.setInt(4, dam.getC4pulso_kw());
            pst.setString(5, dam.getC5face());
            pst.setInt(6, dam.getC6fk_idtarifa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + dam.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + dam.toString(), titulo);
        }
    }

    public void update_dato_medidor(Connection conn, dato_medidor dam) {
        String titulo = "update_dato_medidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setInt(1, dam.getC2numero_medidor());
            pst.setString(2, dam.getC3nombre());
            pst.setInt(3, dam.getC4pulso_kw());
            pst.setString(4, dam.getC5face());
            pst.setInt(5, dam.getC6fk_idtarifa());
            pst.setInt(6, dam.getC1iddato_medidor());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + dam.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + dam.toString(), titulo);
        }
    }

    public void cargar_dato_medidor(Connection conn, dato_medidor dam, int id) {
        String titulo = "Cargar_dato_medidor";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                dam.setC1iddato_medidor(rs.getInt(1));
                dam.setC2numero_medidor(rs.getInt(2));
                dam.setC3nombre(rs.getString(3));
                dam.setC4pulso_kw(rs.getInt(4));
                dam.setC5face(rs.getString(5));
                dam.setC6fk_idtarifa(rs.getInt(6));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + dam.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + dam.toString(), titulo);
        }
    }

    public void actualizar_tabla_dato_medidor(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_dato_medidor(tbltabla);
    }

    public void ancho_tabla_dato_medidor(JTable tbltabla) {
        int Ancho[] = {5,30,20,20,25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
