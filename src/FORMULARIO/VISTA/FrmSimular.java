/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnMySql;
import Evento.Color.cla_color_pelete;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.BO_lectura_temp;
//import FORMULARIO.BO.BO_lectura_temp;
import FORMULARIO.DAO.DAO_lectura_temp;
import FORMULARIO.ENTIDAD.lectura_temp;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmSimular extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnMySql.getConnMySql();
    EvenFecha evefec = new EvenFecha();
    cla_color_pelete clacolor = new cla_color_pelete();
    lectura_temp entlt = new lectura_temp();
    DAO_lectura_temp DAOlt = new DAO_lectura_temp();
    BO_lectura_temp BOlt = new BO_lectura_temp();
    public static DefaultTableModel model_itemf = new DefaultTableModel();
    private Timer tiempo;
    private int contador_id = 0;
    Calendar calendario = Calendar.getInstance();
    private Date tempDate;
    int lectura_kw_ini = 0;
    private String numero_medidor = "1001";
    private java.sql.Date fecfinal;
    private int contador_tiempo_seg;

    private void abrir_formulario() {
        this.setTitle("SIMULADOR");
        evetbl.centrar_formulario_internalframa(this);
        crear_item_factura();
        txtfecinicio.setText(evefec.getString_formato_fecha_hora());
        txtfecfinal.setText(evefec.getString_formato_fecha_hora());
    }

    void crear_item_factura() {
        String dato[] = {"idl", "fecha_creado", "lectura_kw", "medidor"};
        eveJtab.crear_tabla_datos(tblmedidor, model_itemf, dato);
    }

    void ancho_item_factura() {
        int Ancho[] = {15, 30, 25, 30};
        eveJtab.setAnchoColumnaJtable(tblmedidor, Ancho);
    }

    boolean validar_simulador() {
        if (evejtf.getBoo_JTextField_vacio(txtfecinicio, "CARGAR FECHA INICIO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfecfinal, "CARGAR FECHA FINAL")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtiniciokw, "CARGAR INICIO KW")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmedidor, "CARGAR NUMERO MEDIDOR")) {
            return false;
        }
        return true;
    }

    void boton_iniciar_simulador() {
        if (validar_simulador()) {
//            DAOlt.truncate_lectura_temp(conn);
            Date fecinicio = evefec.getDate_fecha_cargado(txtfecinicio.getText());
            lectura_kw_ini = Integer.parseInt(txtiniciokw.getText());
            fecfinal = evefec.getDate_fecha_cargado(txtfecfinal.getText());
            calendario.setTime(fecinicio);
            tempDate = calendario.getTime();
            System.out.println("Fecha actual: " + tempDate);
            iniciarTiempo();
        }
    }

    void boton_parar() {
        pararTiempo();
        this.dispose();
    }

    void crear_simulacion() {
        contador_tiempo_seg++;
        Random claseRandom = new Random();
        int randomInt = 2 + claseRandom.nextInt(50 - 2);
        contador_id++;
        lectura_kw_ini++;
        calendario.set(Calendar.MINUTE, calendario.get(Calendar.MINUTE) + randomInt);
        tempDate = calendario.getTime();
        String fecha_creado = evefec.getString_formato_fecha_date(tempDate);
        String medidor = txtmedidor.getText();
        entlt.setC2fecha_creado(fecha_creado);
        entlt.setC3lectura_kw(lectura_kw_ini);
        entlt.setC4numero_medidor(Double.parseDouble(medidor));
        BOlt.insertar_lectura_temp(entlt);
        if (contador_tiempo_seg > 60) {
            DAOlt.actualizar_tabla_lectura_temp(conn, tblmedidor);
            contador_tiempo_seg = 0;
        }
        if (tempDate.after(fecfinal)) {
            boton_parar();
        }
    }

    void cargar_datos(String idl, String fecha_creado, String lectura_kw, String medidor) {
        String dato[] = {idl, fecha_creado, lectura_kw, medidor};
        eveJtab.cargar_tabla_datos(tblmedidor, model_itemf, dato);
    }

    void iniciarTiempo() {
        tiempo = new Timer();
        //le asignamos una tarea al timer
        tiempo.schedule(new FrmSimular.clasetimer(), 0, 1000 * 1);
        System.out.println("Timer Iniciado");
    }

    void pararTiempo() {
        tiempo.cancel();
        System.out.println("Timer Parado");
    }

    class clasetimer extends TimerTask {

        public void run() {
            crear_simulacion();
        }
    }

    public FrmSimular() {
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

        jLabel2 = new javax.swing.JLabel();
        txtfecinicio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfecfinal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtiniciokw = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmedidor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmedidor = new javax.swing.JTable();
        btniniciarsimulador = new javax.swing.JButton();
        btnparar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
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

        jLabel2.setText("Fec. Inicio:");

        jLabel3.setText("Fec. Final:");

        jLabel1.setText("INICIO KW:");

        jLabel4.setText("MEDIDOR:");

        tblmedidor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblmedidor);

        btniniciarsimulador.setText("INICIAR SIMULADOR");
        btniniciarsimulador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniciarsimuladorActionPerformed(evt);
            }
        });

        btnparar.setText("PARAR");
        btnparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmedidor))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtiniciokw))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfecfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64)
                .addComponent(btniniciarsimulador)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnparar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfecinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btniniciarsimulador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtfecfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtiniciokw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtmedidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnparar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_item_factura();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btniniciarsimuladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniciarsimuladorActionPerformed
        // TODO add your handling code here:
        boton_iniciar_simulador();
    }//GEN-LAST:event_btniniciarsimuladorActionPerformed

    private void btnpararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpararActionPerformed
        // TODO add your handling code here:
        boton_parar();
    }//GEN-LAST:event_btnpararActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btniniciarsimulador;
    private javax.swing.JButton btnparar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblmedidor;
    private javax.swing.JTextField txtfecfinal;
    private javax.swing.JTextField txtfecinicio;
    private javax.swing.JTextField txtiniciokw;
    private javax.swing.JTextField txtmedidor;
    // End of variables declaration//GEN-END:variables
}
