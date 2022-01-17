package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.factura;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_factura {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "FACTURA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "FACTURA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO factura(idfactura,fecha_creado,monto_total,iva10,monto_letra,estado,fk_idcliente,fk_idempresa,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE factura SET fecha_creado=?,monto_total=?,iva10=?,monto_letra=?,estado=?,fk_idcliente=?,fk_idempresa=?,fk_idusuario=? WHERE idfactura=?;";
    private String sql_select = "select f.idfactura as idf,to_char(f.fecha_creado,'yyyy-MM-dd HH24:MI') as fecha,\n"
            + "c.nombre as cliente,c.ruc,m.numero_medidor as medidor_nro,it.descripcion,\n"
            + "(it.final_kw-it.inicio_kw) as uso_kw,\n"
            + "TRIM(to_char(((it.final_kw-it.inicio_kw)*it.monto_tarifa),'999G999G999D99')) as monto,\n"
            + "f.estado\n"
            + "from factura f,cliente c,dato_medidor m,item_factura it\n"
            + "where f.fk_idcliente=c.idcliente\n"
            + "and f.idfactura=it.fk_idfactura\n"
            + "and c.fk_iddato_medidor=m.iddato_medidor\n"
//            + "and f.estado='EMITIDO' and f.fk_idcliente=1\n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT idfactura,fecha_creado,monto_total,iva10,monto_letra,estado,fk_idcliente,fk_idempresa,fk_idusuario FROM factura WHERE idfactura=";
    private String sql_update_estado = "UPDATE factura SET estado=? WHERE idfactura=?;";

    public void insertar_factura(Connection conn, factura fac) {
        fac.setC1idfactura(eveconn.getInt_ultimoID_mas_uno(conn, fac.getTb_factura(), fac.getId_idfactura()));
        String titulo = "insertar_factura";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, fac.getC1idfactura());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setDouble(3, fac.getC3monto_total());
            pst.setDouble(4, fac.getC4iva10());
            pst.setString(5, fac.getC5monto_letra());
            pst.setString(6, fac.getC6estado());
            pst.setInt(7, fac.getC7fk_idcliente());
            pst.setInt(8, fac.getC8fk_idempresa());
            pst.setInt(9, fac.getC9fk_idusuario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + fac.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + fac.toString(), titulo);
        }
    }

    public void update_factura(Connection conn, factura fac) {
        String titulo = "update_factura";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setDouble(2, fac.getC3monto_total());
            pst.setDouble(3, fac.getC4iva10());
            pst.setString(4, fac.getC5monto_letra());
            pst.setString(5, fac.getC6estado());
            pst.setInt(6, fac.getC7fk_idcliente());
            pst.setInt(7, fac.getC8fk_idempresa());
            pst.setInt(8, fac.getC9fk_idusuario());
            pst.setInt(9, fac.getC1idfactura());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + fac.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + fac.toString(), titulo);
        }
    }

    public void estado_update_factura(Connection conn, factura fac) {
        String titulo = "estado_update_factura";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_estado);
            pst.setString(1, fac.getC6estado());
            pst.setInt(2, fac.getC1idfactura());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_estado + "\n" + fac.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_estado + "\n" + fac.toString(), titulo);
        }
    }

    public void cargar_factura(Connection conn, factura fac, int id) {
        String titulo = "Cargar_factura";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                fac.setC1idfactura(rs.getInt(1));
                fac.setC2fecha_creado(rs.getString(2));
                fac.setC3monto_total(rs.getDouble(3));
                fac.setC4iva10(rs.getDouble(4));
                fac.setC5monto_letra(rs.getString(5));
                fac.setC6estado(rs.getString(6));
                fac.setC7fk_idcliente(rs.getInt(7));
                fac.setC8fk_idempresa(rs.getInt(8));
                fac.setC9fk_idusuario(rs.getInt(9));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + fac.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + fac.toString(), titulo);
        }
    }

    public void actualizar_tabla_factura(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_factura(tbltabla);
    }

    public void ancho_tabla_factura(JTable tbltabla) {
        int Ancho[] = {5, 15, 21, 8, 8, 20, 5,10, 8};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void imprimir_rep_factura(Connection conn, int idfactura) {
        String sql = "select f.idfactura as idfac,to_char(f.fecha_creado,'yyyy-MM-dd HH24:MI') as fecha,\n"
                + "f.monto_total as total,f.iva10 as iva10,f.monto_letra as montoletra,\n"
                + "e.nombre as emp_nombre,e.direccion as emp_direc,e.telefono as emp_tel,\n"
                + "cl.nombre as cli_nombre,cl.ruc as cli_ruc,cl.telefono as cli_tel,\n"
                + "dm.numero_medidor as med_nro,dm.face as med_fase,\n"
                + "to_char(it.fecha_inicio,'yyyy-MM-dd') as fecini,\n"
                + "to_char(it.fecha_final,'yyyy-MM-dd') as fecfin,\n"
                + "it.inicio_kw as kwini,\n"
                + "it.final_kw as kwfin,\n"
                + "(it.final_kw-it.inicio_kw) as kwuso,\n"
                + "it.monto_tarifa as tarifa,((it.final_kw-it.inicio_kw)*it.monto_tarifa) as subtotal \n"
                + "from factura f,empresa e,cliente cl,dato_medidor dm,item_factura it\n"
                + "where f.fk_idcliente=cl.idcliente \n"
                + "and cl.fk_iddato_medidor=dm.iddato_medidor\n"
                + "and f.idfactura=it.fk_idfactura\n"
                + "and f.fk_idempresa=e.idempresa\n"
                + "and f.idfactura=" + idfactura
                + " order by it.iditem_factura asc ";
        String titulonota = "FACTURA";
        String direccion = "src/REPORTE/FACTURA/repFactura1.jrxml";
        rep.imprimirjasper(conn, sql, titulonota, direccion);
    }
}
