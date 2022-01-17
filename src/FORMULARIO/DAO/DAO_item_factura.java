package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_factura;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_factura {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_FACTURA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_FACTURA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_factura(iditem_factura,fecha_creado,fecha_inicio,fecha_final,descripcion,inicio_kw,final_kw,monto_tarifa,fk_iddato_medidor,fk_idtarifa,fk_idfactura) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_factura SET fecha_creado=?,fecha_inicio=?,fecha_final=?,descripcion=?,inicio_kw=?,final_kw=?,monto_tarifa=?,fk_iddato_medidor=?,fk_idtarifa=?,fk_idfactura=? WHERE iditem_factura=?;";
    private String sql_select = "SELECT iditem_factura,fecha_creado,fecha_inicio,fecha_final,descripcion,inicio_kw,final_kw,monto_tarifa,fk_iddato_medidor,fk_idtarifa,fk_idfactura FROM item_factura order by 1 desc;";
    private String sql_cargar = "SELECT iditem_factura,fecha_creado,fecha_inicio,fecha_final,descripcion,inicio_kw,final_kw,monto_tarifa,fk_iddato_medidor,fk_idtarifa,fk_idfactura FROM item_factura WHERE iditem_factura=";

    public void insertar_item_factura(Connection conn, item_factura ifac) {
        ifac.setC1iditem_factura(eveconn.getInt_ultimoID_mas_uno(conn, ifac.getTb_item_factura(), ifac.getId_iditem_factura()));
        String titulo = "insertar_item_factura";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ifac.getC1iditem_factura());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setTimestamp(3, evefec.getTimestamp_fecha_cargado(ifac.getC3fecha_inicio()));
            pst.setTimestamp(4, evefec.getTimestamp_fecha_cargado(ifac.getC4fecha_final()));
            pst.setString(5, ifac.getC5descripcion());
            pst.setDouble(6, ifac.getC6inicio_kw());
            pst.setDouble(7, ifac.getC7final_kw());
            pst.setDouble(8, ifac.getC8monto_tarifa());
            pst.setInt(9, ifac.getC9fk_iddato_medidor());
            pst.setInt(10, ifac.getC10fk_idtarifa());
            pst.setInt(11, ifac.getC11fk_idfactura());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ifac.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ifac.toString(), titulo);
        }
    }

    public void update_item_factura(Connection conn, item_factura ifac) {
        String titulo = "update_item_factura";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setTimestamp(3, evefec.getTimestamp_sistema());
            pst.setString(4, ifac.getC5descripcion());
            pst.setDouble(5, ifac.getC6inicio_kw());
            pst.setDouble(6, ifac.getC7final_kw());
            pst.setDouble(7, ifac.getC8monto_tarifa());
            pst.setInt(8, ifac.getC9fk_iddato_medidor());
            pst.setInt(9, ifac.getC10fk_idtarifa());
            pst.setInt(10, ifac.getC11fk_idfactura());
            pst.setInt(11, ifac.getC1iditem_factura());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ifac.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ifac.toString(), titulo);
        }
    }

    public void cargar_item_factura(Connection conn, item_factura ifac, int id) {
        String titulo = "Cargar_item_factura";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ifac.setC1iditem_factura(rs.getInt(1));
                ifac.setC2fecha_creado(rs.getString(2));
                ifac.setC3fecha_inicio(rs.getString(3));
                ifac.setC4fecha_final(rs.getString(4));
                ifac.setC5descripcion(rs.getString(5));
                ifac.setC6inicio_kw(rs.getDouble(6));
                ifac.setC7final_kw(rs.getDouble(7));
                ifac.setC8monto_tarifa(rs.getDouble(8));
                ifac.setC9fk_iddato_medidor(rs.getInt(9));
                ifac.setC10fk_idtarifa(rs.getInt(10));
                ifac.setC11fk_idfactura(rs.getInt(11));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ifac.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ifac.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_factura(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_factura(tbltabla);
    }

    public void ancho_tabla_item_factura(JTable tbltabla) {
        int Ancho[] = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
    public void insertar_item_factura_de_tabla(Connection conn, JTable tblitem) {
        //String dato[] = {"idm", "descripcion", "fec-ini", "fec-fin", "kw-ini", "kw-fin","kw-uso", "tarifa","subtotal"};
        for (int row = 0; row < tblitem.getRowCount(); row++) {
            String fk_iddato_medidor = ((tblitem.getModel().getValueAt(row, 0).toString()));
            String fk_idtarifa = ((tblitem.getModel().getValueAt(row, 1).toString()));
            String fk_idfactura = ((tblitem.getModel().getValueAt(row, 2).toString()));
            String descripcion = ((tblitem.getModel().getValueAt(row, 3).toString()));
            String fecha_inicio = ((tblitem.getModel().getValueAt(row, 4).toString()));
            String fecha_final = ((tblitem.getModel().getValueAt(row, 5).toString()));
            String inicio_kw = ((tblitem.getModel().getValueAt(row, 6).toString()));
            String final_kw = ((tblitem.getModel().getValueAt(row, 7).toString()));
            String monto_tarifa = ((tblitem.getModel().getValueAt(row, 9).toString()));
            try {
                item_factura ifac=new item_factura();
                ifac.setC3fecha_inicio(fecha_inicio);
                ifac.setC4fecha_final(fecha_final);
                ifac.setC5descripcion(descripcion);
                ifac.setC6inicio_kw(Double.parseDouble(inicio_kw));
                ifac.setC7final_kw(Double.parseDouble(final_kw));
                ifac.setC8monto_tarifa(Double.parseDouble(monto_tarifa));
                ifac.setC9fk_iddato_medidor(Integer.parseInt(fk_iddato_medidor));
                ifac.setC10fk_idtarifa(Integer.parseInt(fk_idtarifa));
                ifac.setC11fk_idfactura(Integer.parseInt(fk_idfactura));
                insertar_item_factura(conn, ifac);
            } catch (Exception e) {
                evemen.mensaje_error(e, "insertar_item_factura_de_tabla");
                break;
            }

        }
    }
}
