package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_consumo_medidor;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_consumo_medidor {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_CONSUMO_MEDIDOR GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_CONSUMO_MEDIDOR MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_consumo_medidor"
            + "(iditem_consumo_medidor,fecha_creado,lectura_kw,fk_iddato_medidor) "
            + "VALUES (?,?,?,?);";
    private String sql_update = "UPDATE item_consumo_medidor SET fecha_creado=?,lectura_kw=?,fk_iddato_medidor=? WHERE iditem_consumo_medidor=?;";
    private String sql_select = "SELECT iditem_consumo_medidor,fecha_creado,lectura_kw,fk_iddato_medidor FROM item_consumo_medidor order by 1 desc;";
    private String sql_cargar = "SELECT iditem_consumo_medidor,fecha_creado,lectura_kw,fk_iddato_medidor FROM item_consumo_medidor WHERE iditem_consumo_medidor=";
    private String sql_truncate="truncate item_consumo_medidor;";

    public void insertar_item_consumo_medidor(Connection conn, item_consumo_medidor icm) {
        icm.setC1iditem_consumo_medidor(eveconn.getInt_ultimoID_mas_uno(conn, icm.getTb_item_consumo_medidor(), icm.getId_iditem_consumo_medidor()));
        String titulo = "insertar_item_consumo_medidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, icm.getC1iditem_consumo_medidor());
            pst.setTimestamp(2, evefec.getTimestamp_fecha_cargado(icm.getC2fecha_creado()));
            pst.setInt(3, icm.getC3lectura_kw());
            pst.setInt(4, icm.getC4fk_iddato_medidor());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + icm.toString(), titulo);
//            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + icm.toString(), titulo);
        }
    }

    public void update_item_consumo_medidor(Connection conn, item_consumo_medidor icm) {
        String titulo = "update_item_consumo_medidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setInt(2, icm.getC3lectura_kw());
            pst.setInt(3, icm.getC4fk_iddato_medidor());
            pst.setInt(4, icm.getC1iditem_consumo_medidor());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + icm.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + icm.toString(), titulo);
        }
    }
    public void truncate_item_consumo_medidor(Connection conn) {
        String titulo = "truncate_item_consumo_medidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_truncate);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_truncate + "\n", titulo);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_truncate + "\n", titulo);
        }
    }
    public void cargar_item_consumo_medidor(Connection conn, item_consumo_medidor icm, int id) {
        String titulo = "Cargar_item_consumo_medidor";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                icm.setC1iditem_consumo_medidor(rs.getInt(1));
                icm.setC2fecha_creado(rs.getString(2));
                icm.setC3lectura_kw(rs.getInt(3));
                icm.setC4fk_iddato_medidor(rs.getInt(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + icm.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + icm.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_consumo_medidor(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_consumo_medidor(tbltabla);
    }

    public void ancho_tabla_item_consumo_medidor(JTable tbltabla) {
        int Ancho[] = {25, 25, 25, 25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
