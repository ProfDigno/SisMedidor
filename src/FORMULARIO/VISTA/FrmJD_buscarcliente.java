/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnMySql;
import Evento.Color.*;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;

/**
 *
 * @author Digno
 */
public class FrmJD_buscarcliente extends javax.swing.JDialog {

    private EvenJFRAME evetbl = new EvenJFRAME();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenFecha evefec = new EvenFecha();
    private EvenJtable evejta = new EvenJtable();
    private EvenJtable evejt = new EvenJtable();
    private Connection conn = ConnMySql.getConnMySql();
    private EvenConexion eveconn = new EvenConexion();
    private cla_color_pelete clacolor = new cla_color_pelete();
    cliente ENTcli = new cliente();
    private DAO_cliente DAOcli = new DAO_cliente();
    private BO_cliente BOcli = new BO_cliente();
    private dato_medidor entdm = new dato_medidor();
    private DAO_dato_medidor DAOdm = new DAO_dato_medidor();
    private tarifa entta = new tarifa();
    private DAO_tarifa DAOta = new DAO_tarifa();
    private EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();

    private void abrir_formulario() {
        this.setTitle("BUSCAR CLIENTE");
        DAOcli.actualizar_tabla_cliente_buscar(conn, tblbuscar_cliente, "nombre", "");
        color_formulario();
    }

    private void color_formulario() {
        jPanel_dato.setBackground(clacolor.getColor_tabla());
        jPanel_tabla.setBackground(clacolor.getColor_tabla());
    }

    private void seleccionar_tabla() {
        if (tblbuscar_cliente.getSelectedRow() >= 0) {
            int idcliente = evejt.getInt_select_id(tblbuscar_cliente);
            DAOcli.cargar_cliente(conn, ENTcli, idcliente);
            if (ENTcli.isBusca_factura()) {
                FrmFacturar.txtcliente_nombre.setText(ENTcli.getC3nombre());
                FrmFacturar.txtcliente_ruc.setText(ENTcli.getC5ruc());
                FrmFacturar.txtcliente_direccion.setText(ENTcli.getC7ubicacion());
                FrmFacturar.txtclinte_telefono.setText(ENTcli.getC6telefono());
                cargar_medidor(ENTcli.getC8fk_iddato_medidor());
                FrmFacturar.txtcliente_direccion.setBackground(Color.orange);
                FrmFacturar.txtcliente_direccion.grabFocus();
            }
            if (ENTcli.isBusca_usuario()) {
                FrmUsuario.txtu2nombre.setText(ENTcli.getC3nombre());
            }
        }
    }

    private void cargar_medidor(int fk_iddato_medidor) {
        DAOdm.cargar_dato_medidor(conn, entdm, fk_iddato_medidor);
        if (ENTcli.isBusca_factura()) {
            FrmFacturar.txtdm_nombre.setText(entdm.getC3nombre());
            FrmFacturar.txtdm_numero_medidor.setText(String.valueOf(entdm.getC2numero_medidor()));
            FrmFacturar.txtdm_pulso_kw.setText(String.valueOf(entdm.getC4pulso_kw()));
            FrmFacturar.txtdm_face.setText(entdm.getC5face());
            DAOta.cargar_tarifa(conn, entta, entdm.getC6fk_idtarifa());
            FrmFacturar.txtta_tipo.setText(entta.getC3tipo());
            FrmFacturar.jFmonto_tarifa.setValue(entta.getC4monto_tarifa());
        }
    }

    private void buscar_cliente(java.awt.event.KeyEvent evt, String campo, String buscar) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DAOcli.actualizar_tabla_cliente_buscar(conn, tblbuscar_cliente, campo, buscar);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblbuscar_cliente.requestFocus();
            tblbuscar_cliente.changeSelection(0, 0, false, false);
        }
    }

    public FrmJD_buscarcliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jPanel_dato = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jPanel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbuscar_cliente = new javax.swing.JTable();
        btnsalect_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel_dato.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("RUC:");

        txtruc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrucKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_datoLayout = new javax.swing.GroupLayout(jPanel_dato);
        jPanel_dato.setLayout(jPanel_datoLayout);
        jPanel_datoLayout.setHorizontalGroup(
            jPanel_datoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_datoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_datoLayout.setVerticalGroup(
            jPanel_datoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_datoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_datoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA CLIENTE"));

        tblbuscar_cliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblbuscar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblbuscar_clienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblbuscar_clienteMouseReleased(evt);
            }
        });
        tblbuscar_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblbuscar_clienteKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblbuscar_cliente);

        btnsalect_salir.setText("SELECCIONAR Y SALIR");
        btnsalect_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalect_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_tablaLayout = new javax.swing.GroupLayout(jPanel_tabla);
        jPanel_tabla.setLayout(jPanel_tablaLayout);
        jPanel_tablaLayout.setHorizontalGroup(
            jPanel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_tablaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnsalect_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_tablaLayout.setVerticalGroup(
            jPanel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalect_salir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_dato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_dato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(701, 476));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        DAOcli.ancho_tabla_cliente_buscar(tblbuscar_cliente);
    }//GEN-LAST:event_formWindowOpened

    private void tblbuscar_clienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbuscar_clienteMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tblbuscar_clienteMouseReleased

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
        buscar_cliente(evt, "nombre", txtnombre.getText());
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txtrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyPressed
        // TODO add your handling code here:
        buscar_cliente(evt, "ruc", txtruc.getText());
    }//GEN-LAST:event_txtrucKeyPressed

    private void btnsalect_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalect_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ENTcli.setBusca_factura(false);
        ENTcli.setBusca_usuario(false);
    }//GEN-LAST:event_btnsalect_salirActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyReleased

    private void tblbuscar_clienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbuscar_clienteMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            seleccionar_tabla();
            this.dispose();
            ENTcli.setBusca_factura(false);
            ENTcli.setBusca_usuario(false);
        }
    }//GEN-LAST:event_tblbuscar_clienteMousePressed

    private void tblbuscar_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblbuscar_clienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionar_tabla();
            this.dispose();
            ENTcli.setBusca_factura(false);
            ENTcli.setBusca_usuario(false);
        }
    }//GEN-LAST:event_tblbuscar_clienteKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmJD_buscarcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJD_buscarcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJD_buscarcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJD_buscarcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmJD_buscarcliente dialog = new FrmJD_buscarcliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsalect_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel_dato;
    private javax.swing.JPanel jPanel_tabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblbuscar_cliente;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtruc;
    // End of variables declaration//GEN-END:variables
}
