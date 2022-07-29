/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.org.jblue.vistas.vistas;

import com.org.jblue.Sistema.Cache;
import com.org.jblue.Sistema.ProgramaInfo;
import com.org.jblue.controlador.CCaja;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.OUsuarios;
import com.org.jblue.vistas.class_aux.ClassPanel;
import com.org.jblue.vistas.class_aux.Funciones;
import com.org.jblue.vistas.ventanas.JFMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class JPCaja extends ClassPanel implements Funciones {

    private final JFMenu menu;
    private final Cache cache;
    private final ArrayList<OUsuarios> listaSugerencias;
    private final CCaja controlador;
    private DefaultListModel<String> modeloListaSugerencias;
    private DefaultTableModel model;
    private OUsuarios usuario;
    private OTomas toma;

    /**
     * Creates new form PCaja
     *
     * @param menu
     */
    public JPCaja(JFMenu menu) {
        this.menu = menu;
        this.cache = Cache.getInstancia();
        this.listaSugerencias = cache.getUsuarios();
        controlador = new CCaja(this);
        initComponents();
        call();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jtfNombreBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jbtBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlSugerencias = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtPagosHoy = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfToma = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfCuota = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jbtCobrar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jbtRecargarTabla = new javax.swing.JButton();
        jbtGenerarPDF = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 600));

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(1200, 40));
        jToolBar1.setPreferredSize(new java.awt.Dimension(1200, 40));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/cambio_de_ventanas/flecha-hacia-atras.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar Sesion");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon("/home/jp/Documentos/NetBeansProyects/J_Blue/src/com/org/jblue/Icons/crud/nota-adhesiva x32.png")); // NOI18N
        jButton5.setToolTipText("Base de datos");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1200, 554));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtfNombreBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jtfNombreBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jtfNombreBuscar.setToolTipText("Usuario");
        jtfNombreBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pagos de hoy");
        jLabel1.setToolTipText("");

        jbtBuscar.setBackground(new java.awt.Color(204, 204, 204));
        jbtBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jbtBuscar.setText("Buscar");
        jbtBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtBuscar.setPreferredSize(new java.awt.Dimension(100, 30));

        jlSugerencias.setBackground(new java.awt.Color(255, 255, 255));
        jlSugerencias.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jlSugerencias);

        jtPagosHoy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USUARIOS", "TIPO DE TOMA", "MESES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtPagosHoy);

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre:");
        jLabel2.setPreferredSize(new java.awt.Dimension(62, 30));

        jtfNombre.setBackground(new java.awt.Color(255, 255, 255));
        jtfNombre.setForeground(new java.awt.Color(0, 0, 0));
        jtfNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tipo de toma:");
        jLabel3.setPreferredSize(new java.awt.Dimension(62, 30));

        jtfToma.setBackground(new java.awt.Color(255, 255, 255));
        jtfToma.setForeground(new java.awt.Color(0, 0, 0));
        jtfToma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfToma.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cuota:");
        jLabel4.setPreferredSize(new java.awt.Dimension(62, 30));

        jtfCuota.setBackground(new java.awt.Color(255, 255, 255));
        jtfCuota.setForeground(new java.awt.Color(0, 0, 0));
        jtfCuota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfCuota.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Meses:");
        jLabel5.setPreferredSize(new java.awt.Dimension(62, 30));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSpinner1.setOpaque(true);
        jSpinner1.setPreferredSize(new java.awt.Dimension(64, 30));

        jbtCobrar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCobrar.setForeground(new java.awt.Color(0, 0, 0));
        jbtCobrar.setText("Cobrar");
        jbtCobrar.setActionCommand("cobrar");
        jbtCobrar.setPreferredSize(new java.awt.Dimension(75, 30));

        jbtCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCancelar.setForeground(new java.awt.Color(0, 0, 0));
        jbtCancelar.setText("Cancelar");
        jbtCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Buscar:");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Coinsidencias");
        jLabel7.setToolTipText("");

        jToolBar2.setBackground(new java.awt.Color(0, 102, 255));
        jToolBar2.setBorder(null);
        jToolBar2.setRollover(true);
        jToolBar2.setPreferredSize(new java.awt.Dimension(100, 40));

        jbtRecargarTabla.setBackground(new java.awt.Color(0, 153, 255));
        jbtRecargarTabla.setForeground(new java.awt.Color(0, 0, 0));
        jbtRecargarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/recargar.png"))); // NOI18N
        jbtRecargarTabla.setToolTipText("Recargar Tabla con los pagos de hoy");
        jbtRecargarTabla.setFocusable(false);
        jbtRecargarTabla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtRecargarTabla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jbtRecargarTabla);

        jbtGenerarPDF.setBackground(new java.awt.Color(0, 153, 255));
        jbtGenerarPDF.setForeground(new java.awt.Color(0, 0, 0));
        jbtGenerarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/pdf.png"))); // NOI18N
        jbtGenerarPDF.setToolTipText("Generar PDF con los pagos del dia de hoy");
        jbtGenerarPDF.setFocusable(false);
        jbtGenerarPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtGenerarPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jbtGenerarPDF);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                            .addComponent(jtfNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfToma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfCuota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jbtCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jbtCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Caja", jPanel1);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pagos Registrados", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        menu.mostrarAdministracion();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menu.irLogin();
    }//GEN-LAST:event_jButton1ActionPerformed
    //</editor-fold>

    @Override
    public final void call() {
        this.Info();
        this.init();
        this.addListeners();
    }

    @Override
    public void Info() {
        menu.setTitle(ProgramaInfo.getSECCIONES(1) + INFO);

    }

    @Override
    public void init() {
        model = (DefaultTableModel) jtPagosHoy.getModel();
        modeloListaSugerencias = new DefaultListModel<>();
        jlSugerencias.setModel(modeloListaSugerencias);
        controlador.setModelPagosHoy(model);
        controlador.getADAPTADOR().llenarTablaPagosHoy();

    }

    @Override
    public void addListeners() {
        //
        jtfNombreBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                modeloListaSugerencias.removeAllElements();
                for (OUsuarios usuario : listaSugerencias) {
                    String nombre = usuario.getId() + " " + usuario.getNombre() + " " + usuario.getAp() + " " + usuario.getAm();
                    if (nombre.contains(jtfNombreBuscar.getText().toUpperCase())) {
                        modeloListaSugerencias.addElement(nombre);
                    }
                }

            }
        });

        jlSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 1:
                        jtfNombreBuscar.setText(jlSugerencias.getSelectedValue());
                        break;
                    case 2:
                        String id = getID(jlSugerencias.getSelectedValue());
                        usuario = getUsuario(id);
                        toma = getToma(usuario.getToma());
                        jtfNombre.setText(usuario.getNombre() + " " + usuario.getAp() + " " + usuario.getAm());
                        jtfToma.setText(toma.getTipo());
                        jtfCuota.setText(toma.getCosto() + "");
                        jtfNombreBuscar.setText(null);
                        modeloListaSugerencias.removeAllElements();
                        break;
                }
            }
        });

        jbtCobrar.addActionListener(controlador.getADAPTADOR());
        jbtRecargarTabla.addActionListener(e -> controlador.getADAPTADOR().llenarTablaPagosHoy());
    }

    private String nombre() {
        return getID(jlSugerencias.getSelectedValue());
    }

    public OUsuarios getUsuario(String id) {
        for (OUsuarios o : cache.getUsuarios()) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    public OTomas getToma(String id) {
        for (OTomas o : cache.getTomas()) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    public int Meses_A_Pagar() {
        int meses = (int) jSpinner1.getValue();
        return meses;
    }

    public String getID(String str) {
        String o = str;
        String array[] = o.split(" ");
        return array[0];
    }

    public OUsuarios getUsuario() {
        return usuario;
    }

    public OTomas getToma() {
        return toma;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbtBuscar;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtCobrar;
    private javax.swing.JButton jbtGenerarPDF;
    private javax.swing.JButton jbtRecargarTabla;
    private javax.swing.JList<String> jlSugerencias;
    private javax.swing.JTable jtPagosHoy;
    private javax.swing.JTextField jtfCuota;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfNombreBuscar;
    private javax.swing.JTextField jtfToma;
    // End of variables declaration//GEN-END:variables
}