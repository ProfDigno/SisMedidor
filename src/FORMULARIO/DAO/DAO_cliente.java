package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.cliente;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import org.jfree.data.category.DefaultCategoryDataset;

public class DAO_cliente {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "CLIENTE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "CLIENTE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO cliente(idcliente,fecha_creacion,nombre,cedula,ruc,telefono,ubicacion,fk_iddato_medidor,dia_fac,activo) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE cliente SET fecha_creacion=?,nombre=?,cedula=?,ruc=?,telefono=?,ubicacion=?,fk_iddato_medidor=?,dia_fac=?,activo=? WHERE idcliente=?;";
    private String sql_select = "select c.idcliente,c.nombre,c.ruc,c.telefono,c.dia_fac as dia,\n"
            + "m.numero_medidor as medidor\n"
            + " from cliente c,dato_medidor m\n"
            + " where c.fk_iddato_medidor=m.iddato_medidor "
            + " ";
    private String sql_cargar = "SELECT idcliente,fecha_creacion,nombre,cedula,ruc,telefono,ubicacion,fk_iddato_medidor,dia_fac,activo FROM cliente WHERE idcliente=";
    

    public void insertar_cliente(Connection conn, cliente cl) {
        cl.setC1idcliente(eveconn.getInt_ultimoID_mas_uno(conn, cl.getTb_cliente(), cl.getId_idcliente()));
        String titulo = "insertar_cliente";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, cl.getC1idcliente());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, cl.getC3nombre());
            pst.setString(4, cl.getC4cedula());
            pst.setString(5, cl.getC5ruc());
            pst.setString(6, cl.getC6telefono());
            pst.setString(7, cl.getC7ubicacion());
            pst.setInt(8, cl.getC8fk_iddato_medidor());
            pst.setInt(9, cl.getC9dia_fac());
            pst.setBoolean(10, cl.getC10activo());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + cl.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + cl.toString(), titulo);
        }
    }

    public void update_cliente(Connection conn, cliente cl) {
        String titulo = "update_cliente";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, cl.getC3nombre());
            pst.setString(3, cl.getC4cedula());
            pst.setString(4, cl.getC5ruc());
            pst.setString(5, cl.getC6telefono());
            pst.setString(6, cl.getC7ubicacion());
            pst.setInt(7, cl.getC8fk_iddato_medidor());
            pst.setInt(8, cl.getC9dia_fac());
            pst.setBoolean(9, cl.getC10activo());
            pst.setInt(10, cl.getC1idcliente());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + cl.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + cl.toString(), titulo);
        }
    }

    public void cargar_cliente(Connection conn, cliente cl, int id) {
        String titulo = "Cargar_cliente";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                cl.setC1idcliente(rs.getInt(1));
                cl.setC1idcliente_global(rs.getInt(1));
                cl.setC2fecha_creacion(rs.getString(2));
                cl.setC3nombre(rs.getString(3));
                cl.setC4cedula(rs.getString(4));
                cl.setC5ruc(rs.getString(5));
                cl.setC6telefono(rs.getString(6));
                cl.setC7ubicacion(rs.getString(7));
                cl.setC8fk_iddato_medidor(rs.getInt(8));
                cl.setC8fk_iddato_medidor_global(rs.getInt(8));
                cl.setC9dia_fac(rs.getInt(9));
                cl.setC9dia_fac_global(rs.getInt(9));
                cl.setC10activo(rs.getBoolean(10));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + cl.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + cl.toString(), titulo);
        }
    }

    public void actualizar_tabla_cliente(Connection conn, JTable tbltabla,String filtro) {
        eveconn.Select_cargar_jtable(conn, sql_select+filtro+" order by 2 asc; ", tbltabla);
        ancho_tabla_cliente(tbltabla);
    }

    public void ancho_tabla_cliente(JTable tbltabla) {
        int Ancho[] = {5, 35, 15, 15, 10, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_cliente_buscar(Connection conn, JTable tbltabla, String campo, String buscar) {
        String sql_select_buscar = "SELECT idcliente as id,nombre,ruc "
                + "FROM cliente where "
                + " " + campo + " like'%" + buscar + "%' and activo=true "
                + "order by nombre desc limit 100;";
        eveconn.Select_cargar_jtable(conn, sql_select_buscar, tbltabla);
        ancho_tabla_cliente_buscar(tbltabla);
    }

    public void ancho_tabla_cliente_buscar(JTable tbltabla) {
        int Ancho[] = {10, 70, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
    public void actualizar_tabla_cliente_facturas(Connection conn, JTable tbltabla, int idcliente) {
         String sql_factura = "select f.idfactura as idf,f.fecha_creado as fecha,\n"
            + "m.numero_medidor as medidor_nro,it.descripcion,\n"
            + "(it.final_kw-it.inicio_kw) as uso_kw,\n"
            + "((it.final_kw-it.inicio_kw)*it.monto_tarifa) as monto\n"
            + "from factura f,cliente c,dato_medidor m,item_factura it\n"
            + "where f.fk_idcliente=c.idcliente\n"
            + "and f.idfactura=it.fk_idfactura\n"
            + "and c.fk_iddato_medidor=m.iddato_medidor\n"
            + "and f.estado='EMITIDO' and f.fk_idcliente="+idcliente
            + " order by 1 desc;";
        eveconn.Select_cargar_jtable(conn, sql_factura, tbltabla);
        ancho_tabla_cliente_facturas(tbltabla);
    }
    public void ancho_tabla_cliente_facturas(JTable tbltabla) {
        int Ancho[] = {10, 20, 15,25,10,20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
    public DefaultCategoryDataset getDataset_consume_por_mes(Connection conn, int idcliente, int columna) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String titulo = "getDataset_consume_por_mes";
        String sql = "select it.descripcion,\n"
                + "(it.final_kw-it.inicio_kw) as uso_kw,\n"
                + "((it.final_kw-it.inicio_kw)*it.monto_tarifa) as monto\n"
                + "from factura f,cliente c,item_factura it\n"
                + "where f.fk_idcliente=c.idcliente \n"
                + "and f.idfactura=it.fk_idfactura\n"
                + "and f.estado='EMITIDO'\n"
                + "and f.fk_idcliente=" + idcliente
                + " order by f.idfactura desc limit 12";
        String vendidos = "CONSUMO KW";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                String nombre = (rs.getString(1));
                double valor = rs.getDouble(columna);
                dataset.addValue(valor, vendidos, nombre);
            }
        } catch (Exception e) {
            evemen.Imprimir_serial_sql_error(e, sql, titulo);
        }
        return dataset;
    }
}
