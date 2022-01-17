/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_pelete;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Utilitario.EvenNumero_a_Letra;
import FORMULARIO.BO.BO_cliente;
import FORMULARIO.BO.BO_factura;
import FORMULARIO.DAO.DAO_cliente;
import FORMULARIO.DAO.DAO_dato_medidor;
import FORMULARIO.DAO.DAO_factura;
import FORMULARIO.DAO.DAO_tarifa;
import FORMULARIO.ENTIDAD.cliente;
import FORMULARIO.ENTIDAD.dato_medidor;
import FORMULARIO.ENTIDAD.factura;
import FORMULARIO.ENTIDAD.tarifa;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmFacturar extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    EvenFecha evefec = new EvenFecha();
    Connection conn = ConnPostgres.getConnPosgres();
    EvenConexion eveconn = new EvenConexion();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    cla_color_pelete clacolor = new cla_color_pelete();
    EvenNumero_a_Letra nl = new EvenNumero_a_Letra();
    public static DefaultTableModel model_itemf = new DefaultTableModel();
    private factura entfac = new factura();
    private DAO_factura DAOfac = new DAO_factura();
    private BO_factura BOfac = new BO_factura();
    private tarifa entta = new tarifa();
    private cliente entcl = new cliente();
    private int idfactura_ultimo = 0;
    private int fk_iddato_medidor = 0;
    private int fk_idtarifa = 1;
    private int fk_idcliente = 0;
    private int fk_idempresa = 1;
    private int fk_idusuario = 1;
    private String estado_EMITIDO = "EMITIDO";
    private String estado_ANULADO = "ANULADO";
    private String monto_letra = "CERO";
    private double monto_total = 0;
    private double iva10 = 0;
    private int cant_while_error;

    private void abrir_formulario() {
        this.setTitle("FACTURA");
        evetbl.centrar_formulario_internalframa(this);
        crear_item_factura();
        reestableser_factura();
        eveJtab.ocultar_columna(tblitemfactura, 0);
        eveJtab.ocultar_columna(tblitemfactura, 1);
        eveJtab.ocultar_columna(tblitemfactura, 2);
    }

    void reestableser_factura() {
        idfactura_ultimo = eveconn.getInt_ultimoID_mas_uno(conn, entfac.getTb_factura(), entfac.getId_idfactura());
        txtfec_inicio.setText(evefec.getString_formato_fecha_hora());
        txtfec_final.setText(evefec.getString_formato_fecha_hora());
        eveJtab.limpiar_tabla_datos(model_itemf);
        txtano.setText("2022");
        cmbmes.setSelectedIndex(0);
        limpiar_cliente();
        limpiar_item_venta();
        sumar_item_venta();
        DAOfac.actualizar_tabla_factura(conn, tblfactura);
    }

    void limpiar_cliente() {
        txtcliente_nombre.setText(null);
        txtcliente_direccion.setText(null);
        txtcliente_ruc.setText(null);
        txtclinte_telefono.setText(null);
        txtdm_nombre.setText(null);
        txtdm_numero_medidor.setText(null);
        txtdm_pulso_kw.setText(null);
        txtdm_face.setText(null);
        txtta_tipo.setText(null);
        jFmonto_tarifa.setText(null);
        fk_idcliente = 0;
        entcl.setC1idcliente_global(fk_idcliente);
    }

    void crear_item_factura() {
        String dato[] = {"idm", "idt", "idf", "descripcion", "fec-ini", "fec-fin", "kw-ini", "kw-fin", "kw-uso", "tarifa", "subtotal"};
        eveJtab.crear_tabla_datos(tblitemfactura, model_itemf, dato);
    }

    void sumar_item_venta() {
        double IVA10 = 11;
        double total = eveJtab.getDouble_sumar_tabla(tblitemfactura, 10);
        monto_total = total;
        monto_letra = nl.Convertir(String.valueOf((int) monto_total), true);
        txtmonto_letra.setText(monto_letra);
        iva10 = (monto_total / IVA10);
        jFmonto_total.setValue(monto_total);
        jFmonto_iva10.setValue(iva10);
    }

    void cargar_factura() {
        entfac.setC3monto_total(monto_total);
        entfac.setC4iva10(iva10);
        entfac.setC5monto_letra(monto_letra);
        entfac.setC6estado(estado_EMITIDO);
        entfac.setC7fk_idcliente(fk_idcliente);
        entfac.setC8fk_idempresa(fk_idempresa);
        entfac.setC9fk_idusuario(fk_idusuario);
    }

    void limpiar_item_venta() {
        txtfec_inicio.setText(null);
        txtfec_final.setText(null);
        txtinicio_kw.setText(null);
        txtfinal_kw.setText(null);
    }

    boolean validar_item_factura() {
        fk_idcliente = entcl.getC1idcliente_global();
        if (evejtf.getBoo_JTextField_vacio(txtfec_inicio, "CARGAR FECHA INICIO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtinicio_kw, "CARGAR INICIO KW")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfec_final, "CARGAR FECHA FINAL")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfinal_kw, "CARGAR FINAL KW")) {
            return false;
        }
        if (fk_idcliente == 0) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    void cargar_item_factura() {
        if (validar_item_factura()) {
            String descripcion = "CORRESPONDE A";
            if (cmbmes.getSelectedIndex() > 0) {
                descripcion = "Ano:" + txtano.getText() + " Mes:" + cmbmes.getSelectedItem().toString();
            }
            String idm = String.valueOf(fk_iddato_medidor);
            String idt = String.valueOf(fk_idtarifa);
            String idf = String.valueOf(idfactura_ultimo);

            String fec_inicio = txtfec_inicio.getText();
            String fec_final = txtfec_final.getText();
            String inicio_kw = txtinicio_kw.getText();
            String final_kw = txtfinal_kw.getText();
            double Dinicio_kw = Double.parseDouble(inicio_kw);
            double Dfinal_kw = Double.parseDouble(final_kw);
            double Duso_kw = Dfinal_kw - Dinicio_kw;
            double Dtarifa = entta.getC4monto_tarifa_global();
            double Dsubtotal = Duso_kw * Dtarifa;
            String uso_kw = String.valueOf(Duso_kw);
            String tarifa = String.valueOf(Dtarifa);
            String subtotal = String.valueOf(Dsubtotal);
            String dato[] = {idm, idt, idf, descripcion, fec_inicio, fec_final, inicio_kw, final_kw, uso_kw, tarifa, subtotal};
            eveJtab.cargar_tabla_datos(tblitemfactura, model_itemf, dato);
            sumar_item_venta();
            limpiar_item_venta();
        }
    }

    boolean validar_factura() {
        fk_idcliente = entcl.getC1idcliente_global();
        if (fk_idcliente == 0) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tblitemfactura.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "CARGAR AL MENOS UN ITEM", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    void boton_guardar_factura() {
        if (validar_factura()) {
            cargar_factura();
            if (BOfac.getBoolean_insertar_factura(entfac, tblitemfactura)) {
                DAOfac.imprimir_rep_factura(conn, idfactura_ultimo);
                reestableser_factura();
            }
        }
    }

    void ancho_item_factura() {
        int Ancho[] = {1, 1, 1, 20, 12, 12, 10, 10, 10, 10, 11};
        eveJtab.setAnchoColumnaJtable(tblitemfactura, Ancho);
    }

    void boton_eliminar_item() {
        if (!eveJtab.getBoolean_validar_select(tblitemfactura)) {
            if (eveJtab.getBoolean_Eliminar_Fila(tblitemfactura, model_itemf)) {
                sumar_item_venta();
            }
        }
    }

    boolean validar_carga_dato_medidor() {
        fk_idcliente = entcl.getC1idcliente_global();
        if (evejtf.getBoo_JTextField_vacio(txtano, "SE DEBE CARGAR UN ANO")) {
            return false;
        }
        if (cmbmes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "DEBE CARGAR UN MES");
            return false;
        }
        if (fk_idcliente == 0) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    void boton_cargar_dato_medidor() {
        if (validar_carga_dato_medidor()) {
            cargar_dato_medidor(true);

        }

    }

    void cargar_dato_medidor(boolean coninicio) {
        int dia = entcl.getC9dia_fac_global();
        int fk_iddato_medidor = entcl.getC8fk_iddato_medidor_global();
        String titulo = "consulta_dato_medidor";
        int ano = Integer.parseInt(txtano.getText());
        int mes = cmbmes.getSelectedIndex();
        int mesfinal = mes + 1;
        if (mesfinal == 13) {
            mesfinal = 1;
            ano++;
        }
        String sql = "";
        String sql1 = "select t1.fecha_creado,t1.lectura_kw from item_consumo_medidor t1 \n"
                + "where (t1.fecha_creado=(select min(t2.fecha_creado) as fec_inicio from item_consumo_medidor t2\n"
                + "where  date_part('year',t2.fecha_creado)=" + ano + " \n"
                + "and date_part('month',t2.fecha_creado)=" + mes + " \n"
                + "and date_part('day',t2.fecha_creado)=" + dia + " \n"
                + "and t2.fk_iddato_medidor=" + fk_iddato_medidor + ") or  \n"
                + "t1.fecha_creado=(select min(t2.fecha_creado) as fec_fin from item_consumo_medidor t2\n"
                + "where  date_part('year',t2.fecha_creado)=" + ano + "\n"
                + "and date_part('month',t2.fecha_creado)=" + mesfinal + " \n"
                + "and date_part('day',t2.fecha_creado)=" + dia + "\n"
                + "and t2.fk_iddato_medidor=" + fk_iddato_medidor + "))\n"
                + "and t1.fk_iddato_medidor=" + fk_iddato_medidor + "\n"
                + "order by 1 asc;";
        String sql2 = "select t1.fecha_creado,t1.lectura_kw from item_consumo_medidor t1 \n"
                + "where (t1.fecha_creado=(select min(t2.fecha_creado) as fec_inicio from item_consumo_medidor t2\n"
                + "where  date_part('year',t2.fecha_creado)=" + ano + " \n"
                + "and date_part('month',t2.fecha_creado)=" + mes + " \n"
                //                + "and date_part('day',t2.fecha_creado)="+dia+" \n"
                + "and t2.fk_iddato_medidor=" + fk_iddato_medidor + ") or  \n"
                + "t1.fecha_creado=(select min(t2.fecha_creado) as fec_fin from item_consumo_medidor t2\n"
                + "where  date_part('year',t2.fecha_creado)=" + ano + "\n"
                + "and date_part('month',t2.fecha_creado)=" + mesfinal + " \n"
                //                + "and date_part('day',t2.fecha_creado)="+dia+"\n"
                + "and t2.fk_iddato_medidor=" + fk_iddato_medidor + "))\n"
                + "and t1.fk_iddato_medidor=" + fk_iddato_medidor + "\n"
                + "order by 1 asc;";
        if (coninicio) {
            sql = sql1;
        } else {
            sql = sql2;
        }
        int cant_insertado = 0;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                cant_insertado++;
                cant_while_error++;
                String fecha_creado = rs.getString("fecha_creado");
                String lectura_kw = rs.getString("lectura_kw");
                System.out.println("cant_insertado:" + cant_insertado + " fecha_creado:" + fecha_creado + " lectura_kw:" + lectura_kw);
                if (cant_insertado == 1) {
                    txtfec_inicio.setText(fecha_creado);
                    txtinicio_kw.setText(lectura_kw);
                }
                if (cant_insertado == 2) {
                    txtfec_final.setText(fecha_creado);
                    txtfinal_kw.setText(lectura_kw);
                }
                System.out.println("cant_while_error:" + cant_while_error);

            }
            if (cant_insertado == 0) {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN DATO PARA CARGAR \nANO: " + ano + "\nMES:" + mes);
                limpiar_item_venta();
            }
            if (cant_insertado == 1) {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN DATO PARA CARGAR EL MES INICIAL\nANO: " + ano + "\nMES:" + mes);
                txtfec_final.setText(txtfec_inicio.getText());
                txtfinal_kw.setText(txtinicio_kw.getText());
                txtfec_inicio.setText(null);
                txtinicio_kw.setText(null);
            }
            if (cant_insertado == 2) {
                cant_while_error = 0;
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
        if (cant_while_error > 20) {
            JOptionPane.showMessageDialog(null, "Error:\nEL SISTEMA SE REINICIA", titulo, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    void boton_estado_venta_anular() {
        if (!eveJtab.getBoolean_validar_select(tblfactura)) {
            if (evemen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR LA FACTURA", "ANULAR", "ANULAR", "CANCELAR")) {
                int idfactura = eveJtab.getInt_select_id(tblfactura);
                entfac.setC6estado(estado_ANULADO);
                entfac.setC1idfactura(idfactura);
                BOfac.estado_update_factura(entfac);
                DAOfac.actualizar_tabla_factura(conn, tblfactura);
            }
        }
    }

    void boton_imprimirPos_venta() {
        if (!eveJtab.getBoolean_validar_select(tblfactura)) {
            int idfactura = eveJtab.getInt_select_id(tblfactura);
            DAOfac.imprimir_rep_factura(conn, idfactura);
        }
    }

    public FrmFacturar() {
        initComponents();
        abrir_formulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPcliente = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtcliente_nombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtcliente_ruc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnbuscar_cliente = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtcliente_direccion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtclinte_telefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblitemfactura = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtfec_inicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtinicio_kw = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfec_final = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfinal_kw = new javax.swing.JTextField();
        btncargar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmbmes = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtano = new javax.swing.JTextField();
        btncargardatos_medidor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtdm_numero_medidor = new javax.swing.JTextField();
        txtdm_pulso_kw = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdm_face = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtta_tipo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jFmonto_tarifa = new javax.swing.JFormattedTextField();
        txtdm_nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtmonto_letra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jFmonto_iva10 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jFmonto_total = new javax.swing.JFormattedTextField();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btneliminar_item = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblfactura = new javax.swing.JTable();
        btnimprimirNota = new javax.swing.JButton();
        btnanularventa = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPcliente.setBackground(new java.awt.Color(153, 204, 255));
        jPcliente.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS CLIENTE"));

        jLabel7.setText("CLIENTE:");

        txtcliente_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcliente_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcliente_nombreKeyPressed(evt);
            }
        });

        jLabel9.setText("RUC:");

        txtcliente_ruc.setEditable(false);
        txtcliente_ruc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("F1: BUSCAR");

        btnbuscar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_cliente.setText("F1");
        btnbuscar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_clienteActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_nuevo.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setText("DIRECCION:");

        txtcliente_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcliente_direccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcliente_direccionKeyReleased(evt);
            }
        });

        jLabel20.setText("TEL:");

        txtclinte_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtclinte_telefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclinte_telefonoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPclienteLayout = new javax.swing.GroupLayout(jPcliente);
        jPcliente.setLayout(jPclienteLayout);
        jPclienteLayout.setHorizontalGroup(
            jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPclienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcliente_direccion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcliente_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtclinte_telefono)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbuscar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPclienteLayout.setVerticalGroup(
            jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPclienteLayout.createSequentialGroup()
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(txtcliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtcliente_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtcliente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtclinte_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnbuscar_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM FACTURA"));

        tblitemfactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblitemfactura);

        jLabel1.setText("Fec. Inicio:");

        jLabel2.setText("KW:");

        txtinicio_kw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinicio_kwKeyTyped(evt);
            }
        });

        jLabel3.setText("Fec. Final:");

        jLabel4.setText("KW:");

        txtfinal_kw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfinal_kwKeyTyped(evt);
            }
        });

        btncargar.setText("CARGAR");
        btncargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarActionPerformed(evt);
            }
        });

        jLabel18.setText("MES DE CONSUMO:");

        cmbmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(MES)", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));

        jLabel19.setText("AÑO:");

        btncargardatos_medidor.setText("CARGAR DATOS");
        btncargardatos_medidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargardatos_medidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfec_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtinicio_kw, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfec_final, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfinal_kw, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargardatos_medidor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfec_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtinicio_kw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtfec_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtfinal_kw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncargardatos_medidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS MEDIDOR"));

        jLabel8.setText("MEDIDOR:");

        jLabel10.setText("NUMERO:");

        txtdm_numero_medidor.setEditable(false);

        txtdm_pulso_kw.setEditable(false);

        jLabel12.setText("PULSO KW:");

        txtdm_face.setEditable(false);

        jLabel13.setText("FACE:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("TARIFA"));

        jLabel14.setText("TIPO:");

        txtta_tipo.setEditable(false);

        jLabel15.setText("MONTO:");

        jFmonto_tarifa.setEditable(false);
        jFmonto_tarifa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00 Gs"))));
        jFmonto_tarifa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_tarifa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtta_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jFmonto_tarifa))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtta_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFmonto_tarifa)
                        .addContainerGap())))
        );

        txtdm_nombre.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdm_pulso_kw, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdm_numero_medidor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdm_face, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdm_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtdm_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtdm_numero_medidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtdm_pulso_kw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdm_face, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("MONTO LETRA:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("IVA 10:");

        jFmonto_iva10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_iva10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("TOTAL:");

        jFmonto_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btneliminar_item.setText("ELIMINAR ITEM");
        btneliminar_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_itemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 177, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btneliminar_item, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFmonto_iva10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFmonto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmonto_letra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtmonto_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16)
                    .addComponent(jFmonto_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFmonto_iva10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar_item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("DATOS FACTURA", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("FACTURA"));

        tblfactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblfactura);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        btnimprimirNota.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnimprimirNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/ven_imprimir.png"))); // NOI18N
        btnimprimirNota.setText("IMPRIMIR");
        btnimprimirNota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnimprimirNota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnimprimirNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirNotaActionPerformed(evt);
            }
        });

        btnanularventa.setBackground(new java.awt.Color(255, 51, 51));
        btnanularventa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnanularventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/anular.png"))); // NOI18N
        btnanularventa.setText("ANULAR");
        btnanularventa.setToolTipText("");
        btnanularventa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnanularventa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnanularventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanularventaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnanularventa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimprimirNota, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnanularventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnimprimirNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FILTRO FACTURA", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcliente_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcliente_nombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            FrmJD_buscarcliente frm = new FrmJD_buscarcliente(null, true);
            frm.setVisible(true);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
//            boton_guardar_venta();
        }
    }//GEN-LAST:event_txtcliente_nombreKeyPressed

    private void btnbuscar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_clienteActionPerformed
        // TODO add your handling code here:
        FrmJD_buscarcliente frm = new FrmJD_buscarcliente(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_btnbuscar_clienteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmCliente());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtcliente_direccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcliente_direccionKeyPressed
        // TODO add your handling code here:
        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //
        //            txtclinte_telefono.grabFocus();
        //        }
        evejtf.saltar_campo_enter(evt, txtcliente_direccion, txtclinte_telefono);
    }//GEN-LAST:event_txtcliente_direccionKeyPressed

    private void txtcliente_direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcliente_direccionKeyReleased
        // TODO add your handling code here:
//        contar_key_update_cliente++;
//        if (contar_key_update_cliente >= 3) {
//            update_cliente = true;
//        }
    }//GEN-LAST:event_txtcliente_direccionKeyReleased

    private void txtclinte_telefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclinte_telefonoKeyPressed
        // TODO add your handling code here:
        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ////            update_cliente=true;
        //            txtprod_2cod_barra.grabFocus();
        //        }
//        evejtf.saltar_campo_enter(evt, txtclinte_telefono, txtprod_2cod_barra);
    }//GEN-LAST:event_txtclinte_telefonoKeyPressed

    private void txtclinte_telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclinte_telefonoKeyReleased
        // TODO add your handling code here:
//        contar_key_update_cliente++;
//        if (contar_key_update_cliente >= 3) {
//            update_cliente = true;
//        }
    }//GEN-LAST:event_txtclinte_telefonoKeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_item_factura();
        DAOfac.ancho_tabla_factura(tblfactura);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_formFocusGained

    private void btncargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarActionPerformed
        // TODO add your handling code here:
        cargar_item_factura();
    }//GEN-LAST:event_btncargarActionPerformed

    private void btneliminar_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_itemActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item();
    }//GEN-LAST:event_btneliminar_itemActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar_factura();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:
        reestableser_factura();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtinicio_kwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinicio_kwKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtinicio_kwKeyTyped

    private void txtfinal_kwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfinal_kwKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtfinal_kwKeyTyped

    private void btncargardatos_medidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargardatos_medidorActionPerformed
        // TODO add your handling code here:
        boton_cargar_dato_medidor();
    }//GEN-LAST:event_btncargardatos_medidorActionPerformed

    private void btnimprimirNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirNotaActionPerformed
        // TODO add your handling code here:
        boton_imprimirPos_venta();
    }//GEN-LAST:event_btnimprimirNotaActionPerformed

    private void btnanularventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularventaActionPerformed
        // TODO add your handling code here:
        boton_estado_venta_anular();
    }//GEN-LAST:event_btnanularventaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanularventa;
    private javax.swing.JButton btnbuscar_cliente;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btncargar;
    private javax.swing.JButton btncargardatos_medidor;
    private javax.swing.JButton btneliminar_item;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimprimirNota;
    private javax.swing.JComboBox<String> cmbmes;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFmonto_iva10;
    public static javax.swing.JFormattedTextField jFmonto_tarifa;
    private javax.swing.JFormattedTextField jFmonto_total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPcliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblfactura;
    private javax.swing.JTable tblitemfactura;
    private javax.swing.JTextField txtano;
    public static javax.swing.JTextField txtcliente_direccion;
    public static javax.swing.JTextField txtcliente_nombre;
    public static javax.swing.JTextField txtcliente_ruc;
    public static javax.swing.JTextField txtclinte_telefono;
    public static javax.swing.JTextField txtdm_face;
    public static javax.swing.JTextField txtdm_nombre;
    public static javax.swing.JTextField txtdm_numero_medidor;
    public static javax.swing.JTextField txtdm_pulso_kw;
    private javax.swing.JTextField txtfec_final;
    private javax.swing.JTextField txtfec_inicio;
    private javax.swing.JTextField txtfinal_kw;
    private javax.swing.JTextField txtinicio_kw;
    private javax.swing.JTextField txtmonto_letra;
    public static javax.swing.JTextField txtta_tipo;
    // End of variables declaration//GEN-END:variables
}
