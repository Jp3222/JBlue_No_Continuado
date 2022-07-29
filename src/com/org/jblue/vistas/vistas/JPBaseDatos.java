/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.org.jblue.vistas.vistas;

import com.org.jblue.Const;
import com.org.jblue.Sistema.Cache;
import com.org.jblue.Sistema.ProgramaInfo;
import com.org.jblue.controlador.CBaseDatos;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.vistas.class_aux.ClassPanel;
import com.org.jblue.vistas.class_aux.Funciones;
import com.org.jblue.vistas.ventanas.JFMenu;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class JPBaseDatos extends ClassPanel implements Funciones {

    private final String titulo = INFO + ProgramaInfo.getSECCIONES(2);
    private final JFMenu menu;
    private final DefaultTableModel modelUsuarios, modelCalles, modelTomas;
    private final CBaseDatos controlador;
    private final Cache memoriaCache;

    /**
     * Creates new form PAdministracion
     *
     * @param menu
     */
    public JPBaseDatos(JFMenu menu) {
        this.menu = menu;
        this.memoriaCache = Cache.getInstancia();
        this.initComponents();
        this.modelUsuarios = (DefaultTableModel) jtUsuarios.getModel();
        this.modelCalles = (DefaultTableModel) jtCalles.getModel();
        this.modelTomas = (DefaultTableModel) jtTomas.getModel();
       
        this.controlador = new CBaseDatos(this);
        this.controlador.setModelCalles(modelCalles);
        this.controlador.setModelTomas(modelTomas);
        this.controlador.setModelUsuarios(modelUsuarios);
        
        //
        this.call();
    }

    @Override
    public final void call() {
        Info();
        init();
        addListeners();
    }

    @Override
    public void Info() {
        menu.setTitle(titulo);
    }

    @Override
    public void init() {
        jspCalleNumero.setBackground(Color.white);
        jspCalleNumero.setForeground(Color.black);
        //
        controlador.llenarTablaUsuarios();
        llenarListaCalles();
        llenarListaTomas();
        jbtTomasGuardar.setEnabled(true);
        jbtTomasEliminar.setEnabled(false);
        jbtTomasActualizar.setEnabled(false);
        //
        jbtCallesGuardar.setEnabled(true);
        jbtCallesEliminar.setEnabled(false);
        jbtCallesActualizar.setEnabled(false);
        //
        jbtUsuariosGuardar.setEnabled(true);
        jbtUsuariosActualizar.setEnabled(false);
        jbtUsuariosEliminar.setEnabled(false);
        jButton1.createToolTip().setBackground(Color.red);
    }

    @Override
    public void addListeners() {
        //CRUD tomas de agua
        jbtTomasGuardar.addActionListener(e -> controlador.addToma());
        jbtTomasActualizar.addActionListener(e -> controlador.setTomas());
        jbtTomasEliminar.addActionListener(e -> controlador.removeTomas());
        jbtTomasCancelar.addActionListener(e -> {
            jbtTomasGuardar.setEnabled(true);
            jbtTomasEliminar.setEnabled(false);
            jbtTomasActualizar.setEnabled(false);
            jtTomas.clearSelection();
            vaciarCamposTomas();
        });
        //CRUD Calles
        jbtCallesGuardar.addActionListener(e -> controlador.addCalle());
        jbtCallesActualizar.addActionListener(e -> controlador.setCalle());
        jbtCallesEliminar.addActionListener(e -> controlador.removeCalle());
        jbtCallesCancelar.addActionListener(e -> {
            jbtCallesGuardar.setEnabled(true);
            jbtCallesEliminar.setEnabled(false);
            jbtCallesActualizar.setEnabled(false);
            jtCalles.clearSelection();
            vaciarCamposCalles();
        });
        //CRUD Usuarios
        jbtUsuariosGuardar.addActionListener(e -> controlador.addUsuarios());
        jbtUsuariosActualizar.addActionListener(e -> controlador.setUsuario());
        jbtUsuariosEliminar.addActionListener(e -> controlador.removeUsuario());
        jbtUsuariosCancelar.addActionListener(e -> {
            jbtUsuariosGuardar.setEnabled(true);
            jbtUsuariosEliminar.setEnabled(false);
            jbtUsuariosActualizar.setEnabled(false);
            jtUsuarios.clearSelection();
            vaciarCamposUsuario();

        });
        //Panel
        jTabbedPane1.addChangeListener((e) -> {
            if (jpUsuarios.isVisible()) {
                llenarListaCalles();
                llenarListaTomas();
                controlador.llenarTablaUsuarios();
            } else {
                vaciarListaCalles();
                vaciarListaTomas();
                controlador.vaciarTablaUsuarios();
            }
            //
            if (jpTomas.isVisible()) {
                controlador.llenarTablaTomas();
            } else {
                controlador.vaciarTablaTomas();
            }
            //
            if (jpCalles.isVisible()) {
                controlador.llenarTablaCalles();
            } else {
                controlador.vaciarTablaCalles();
            }
        });

        jtTomas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jtfTomasNombre.setText((String) jtTomas.getValueAt(jtTomas.getSelectedRow(), 1));
                jtfTomasPrecio.setText((String) jtTomas.getValueAt(jtTomas.getSelectedRow(), 2));
                jbtTomasGuardar.setEnabled(false);
                jbtTomasEliminar.setEnabled(true);
                jbtTomasActualizar.setEnabled(true);
            }

        });

        /*
        
         */
        jtCalles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jbtCallesGuardar.setEnabled(false);
                jbtCallesEliminar.setEnabled(true);
                jbtCallesActualizar.setEnabled(true);
                jtfCalleNombre.setText((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 1));
                int value = Integer.parseInt((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 2));
                jspCalleNumero.setValue(value);
            }
        });
        jtUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jbtUsuariosGuardar.setEnabled(false);
                jbtUsuariosActualizar.setEnabled(true);
                jbtUsuariosEliminar.setEnabled(true);
                jtfUsuarioNombre.setText((String) jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 1));
                jtfUsuarioAP.setText((String) jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 2));
                jtfUsuarioAM.setText((String) jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 3));
                jcbUsuarioCalle.setSelectedItem(jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 4));
                jcbUsuarioToma.setSelectedItem(jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 5));
                jcbUsuarioEstado.setSelectedItem(jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 7));

            }
        });
    }

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtUsuarios = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfUsuarioNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfUsuarioAP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbUsuarioToma = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbUsuarioCalle = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbUsuarioEstado = new javax.swing.JComboBox<>();
        jtfUsuarioAM = new javax.swing.JTextField();
        jbtUsuariosCancelar = new javax.swing.JButton();
        jbtUsuariosEliminar = new javax.swing.JButton();
        jbtUsuariosActualizar = new javax.swing.JButton();
        jbtUsuariosGuardar = new javax.swing.JButton();
        jpTomas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTomas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jtfTomasNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfTomasPrecio = new javax.swing.JTextField();
        jbtTomasGuardar = new javax.swing.JButton();
        jbtTomasActualizar = new javax.swing.JButton();
        jbtTomasEliminar = new javax.swing.JButton();
        jbtTomasCancelar = new javax.swing.JButton();
        jpCalles = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtCalles = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jtfCalleNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jspCalleNumero = new javax.swing.JSpinner();
        jbtCallesGuardar = new javax.swing.JButton();
        jbtCallesActualizar = new javax.swing.JButton();
        jbtCallesEliminar = new javax.swing.JButton();
        jbtCallesCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));

        jToolBar1.setBackground(new java.awt.Color(0, 0, 153));
        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(1200, 40));
        jToolBar1.setPreferredSize(new java.awt.Dimension(1200, 40));

        jButton1.setBackground(new java.awt.Color(0, 0, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/cambio_de_ventanas/flecha-hacia-atras.png"))); // NOI18N
        jButton1.setToolTipText("Atras");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFocusCycleRoot(true);

        jpUsuarios.setBackground(new java.awt.Color(0, 153, 255));

        jtUsuarios.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "A. PATERNO", "A. MATERNO", "CALLE", "T. TOMA", "F. REGISTRO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtUsuarios.setOpaque(false);
        jtUsuarios.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtUsuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.setShowGrid(true);
        jtUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtUsuarios);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de usuario"));
        jPanel4.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        jLabel1.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfUsuarioNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellido Paterno");
        jLabel2.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfUsuarioAP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioAP.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido Materno");
        jLabel3.setPreferredSize(new java.awt.Dimension(52, 30));

        jcbUsuarioToma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbUsuarioToma.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Seleccione el tipo de toma");
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 30));

        jcbUsuarioCalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbUsuarioCalle.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Seleccione una calle");
        jLabel5.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Seleccione una calle");
        jLabel6.setPreferredSize(new java.awt.Dimension(52, 30));

        jcbUsuarioEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jcbUsuarioEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbUsuarioEstado.setPreferredSize(new java.awt.Dimension(118, 30));

        jtfUsuarioAM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioAM.setPreferredSize(new java.awt.Dimension(85, 30));

        jbtUsuariosCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtUsuariosCancelar.setText("Cancelar");
        jbtUsuariosCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtUsuariosEliminar.setText("Eliminar");
        jbtUsuariosEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtUsuariosActualizar.setText("Actualizar");
        jbtUsuariosActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtUsuariosGuardar.setText("Guardar");
        jbtUsuariosGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbUsuarioToma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jtfUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfUsuarioAP, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfUsuarioAM, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtUsuariosGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(jbtUsuariosActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbUsuarioCalle, 0, 300, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jcbUsuarioEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtUsuariosEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtUsuariosCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtUsuariosGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuarioAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuarioAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtUsuariosActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtUsuariosEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbUsuarioToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbUsuarioCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbUsuarioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtUsuariosCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpUsuariosLayout = new javax.swing.GroupLayout(jpUsuarios);
        jpUsuarios.setLayout(jpUsuariosLayout);
        jpUsuariosLayout.setHorizontalGroup(
            jpUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpUsuariosLayout.setVerticalGroup(
            jpUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuarios", jpUsuarios);

        jpTomas.setBackground(new java.awt.Color(0, 153, 255));

        jtTomas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtTomas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TIPO DE TOMA", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTomas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtTomas.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtTomas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtTomas.setShowGrid(true);
        jtTomas.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtTomas);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos de la toma"));
        jPanel6.setOpaque(false);

        jtfTomasNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTomasNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo de toma");
        jLabel8.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Precio de la toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfTomasPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTomasPrecio.setPreferredSize(new java.awt.Dimension(85, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTomasNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfTomasPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTomasNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTomasPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jbtTomasGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtTomasGuardar.setText("Guardar");
        jbtTomasGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtTomasActualizar.setText("Actualizar");
        jbtTomasActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtTomasEliminar.setText("Eliminar");
        jbtTomasEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtTomasCancelar.setText("Cancelar");
        jbtTomasCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        javax.swing.GroupLayout jpTomasLayout = new javax.swing.GroupLayout(jpTomas);
        jpTomas.setLayout(jpTomasLayout);
        jpTomasLayout.setHorizontalGroup(
            jpTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtTomasCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jpTomasLayout.setVerticalGroup(
            jpTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jpTomasLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTomasGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTomasActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTomasEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTomasCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tomas", jpTomas);

        jpCalles.setBackground(new java.awt.Color(0, 153, 255));

        jtCalles.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtCalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "NUMERO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCalles.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtCalles.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtCalles.setShowGrid(true);
        jtCalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtCalles);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos de la calle"));
        jPanel8.setOpaque(false);

        jtfCalleNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfCalleNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nombre de la calle");
        jLabel10.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Numero");
        jLabel11.setPreferredSize(new java.awt.Dimension(52, 30));

        jspCalleNumero.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));
        jspCalleNumero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jspCalleNumero.setEditor(new javax.swing.JSpinner.NumberEditor(jspCalleNumero, ""));
        jspCalleNumero.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCalleNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspCalleNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCalleNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCalleNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        jbtCallesGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtCallesGuardar.setText("Guardar");
        jbtCallesGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtCallesActualizar.setText("Actualizar");
        jbtCallesActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtCallesEliminar.setText("Eliminar");
        jbtCallesEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtCallesCancelar.setText("Cancelar");
        jbtCallesCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        javax.swing.GroupLayout jpCallesLayout = new javax.swing.GroupLayout(jpCalles);
        jpCalles.setLayout(jpCallesLayout);
        jpCallesLayout.setHorizontalGroup(
            jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCallesGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCallesActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCallesEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtCallesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jpCallesLayout.setVerticalGroup(
            jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jpCallesLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCallesGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCallesActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCallesEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtCallesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Calles", jpCalles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menu.mostrarCaja();
    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean textValido(String o) {
        return o != null && !o.isEmpty();
    }

    public void vaciarCamposTomas() {
        jtfTomasNombre.setText(null);
        jtfTomasPrecio.setText(null);
    }

    public void vaciarCamposCalles() {
        jtfCalleNombre.setText(null);
        jspCalleNumero.setValue(1);
    }

    public void vaciarCamposUsuario() {
        jtfUsuarioNombre.setText(null);
        jtfUsuarioAP.setText(null);
        jtfUsuarioAM.setText(null);
        jcbUsuarioCalle.setSelectedIndex(0);
        jcbUsuarioEstado.setSelectedIndex(0);
        jcbUsuarioToma.setSelectedIndex(0);
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos de Tomas"> 
    public String[] getTomasInsert() {
        String[] o = new String[3];
        o[0] = "0";
        o[1] = jtfTomasNombre.getText();
        o[2] = jtfTomasPrecio.getText();
        return o;
    }

    public String[] getTomasUpdate() {
        String[] o = new String[3];
        int row = jtTomas.getSelectedRow();
        int col = jtTomas.getSelectedRow();
        o[0] = (String) jtTomas.getValueAt(row, col);
        o[1] = jtfTomasNombre.getText();
        o[2] = jtfTomasPrecio.getText();
        return o;
    }

    public String getTomasDelete() {
        return (String) jtTomas.getValueAt(jtTomas.getSelectedRow(), 0);
    }

    public int camposVaciosTomas() {
        int i = 0;
        if (!textValido(jtfTomasNombre.getText())) {
            i++;
        }
        if (!textValido(jtfTomasPrecio.getText())) {
            i++;
        }
        return i;
    }

    //</editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="Metodos de calles"> 
    public String[] getCalleInsert() {
        String[] o = new String[Const.BD_CALLES.length];
        o[0] = "0";
        o[1] = jtfCalleNombre.getText();
        o[2] = "" + ((Integer) jspCalleNumero.getValue());
        return o;
    }

    public String[] getCallesUpdate() {
        String[] o = new String[Const.BD_CALLES.length];
        o[0] = (String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 0);
        o[1] = jtfCalleNombre.getText();
        o[2] = (String) jspCalleNumero.getValue();
        return o;
    }

    public String getCallesDelete() {
        return (String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 0);
    }

    public int campoVaciosCalles() {
        int i = 0;
        if (!textValido(jtfCalleNombre.getText())) {
            i++;
        }
        return i;
    }

    //</editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="Metodos de Usuario"> 
    public String[] getUsuariosInsert() {
        String[] o = new String[8];
        o[0] = "0";
        o[1] = jtfUsuarioNombre.getText();
        o[2] = jtfUsuarioAP.getText();
        o[3] = jtfUsuarioAM.getText();
        //
        o[4] = jcbUsuarioCalle.getItemAt(jcbUsuarioCalle.getSelectedIndex());
        o[4] = controlador.getIDCalle(o[4]);
        //
        o[5] = jcbUsuarioToma.getItemAt(jcbUsuarioToma.getSelectedIndex());
        o[5] = controlador.getIDToma(o[5]);
        //
        LocalDateTime fecha = LocalDateTime.now();
        o[6] = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        o[7] = jcbUsuarioEstado.getItemAt(jcbUsuarioEstado.getSelectedIndex());
        if (o[7].equals("Activo")) {
            o[7] = "1";
        } else {
            o[7] = "0";
        }
        return o;
    }

    public String[] getUsuariosUpdate() {
        String[] o = new String[8];
        o[0] = "0";
        o[1] = jtfUsuarioNombre.getText();
        o[2] = jtfUsuarioAP.getText();
        o[3] = jtfUsuarioAM.getText();
        //
        o[4] = jcbUsuarioCalle.getItemAt(jcbUsuarioCalle.getSelectedIndex());
        o[4] = controlador.getIDCalle(o[4]);
        //
        o[5] = jcbUsuarioToma.getItemAt(jcbUsuarioToma.getSelectedIndex());
        o[5] = controlador.getIDToma(o[5]);
        //
        LocalDateTime fecha = LocalDateTime.now();
        o[6] = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        o[7] = jcbUsuarioEstado.getItemAt(jcbUsuarioEstado.getSelectedIndex());
        if (o[7].equals("Activo")) {
            o[7] = "1";
        } else {
            o[7] = "0";
        }
        return o;
    }

    public String getUsuarioDelete() {
        return (String) jtUsuarios.getValueAt(jtUsuarios.getSelectedRow(), 0);
    }

    public int camposVaciosUsuario() {
        int i = 0;
        if (!textValido(jtfUsuarioNombre.getText())) {
            i++;
        }
        if (!textValido(jtfUsuarioAP.getText())) {
            i++;
        }
        if (!textValido(jtfUsuarioAM.getText())) {
            i++;
        }
        if (jcbUsuarioCalle.getSelectedIndex() == 0) {
            i++;
        }
        if (jcbUsuarioToma.getSelectedIndex() == 0) {
            i++;
        }
        return i;
    }

    //</editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="Metodos de llenado de listas"> 
    public void llenarListaTomas() {
        ArrayList<OTomas> tomas = memoriaCache.getTomas();
        jcbUsuarioToma.addItem("- - Tomas - -");
        for (OTomas toma : tomas) {
            jcbUsuarioToma.addItem(toma.getTipo());
        }
    }

    public void llenarListaCalles() {
        ArrayList<OCalles> calles = memoriaCache.getCalles();
        jcbUsuarioCalle.addItem("- - Calles - -");
        for (OCalles calle : calles) {
            jcbUsuarioCalle.addItem(calle.getNombre() + " No. " + calle.getNumero());
        }
    }

    public void vaciarListaTomas() {
        while (jcbUsuarioToma.getItemCount() > 0) {
            jcbUsuarioToma.removeItemAt(0);
        }
    }

    public void vaciarListaCalles() {
        while (jcbUsuarioCalle.getItemCount() > 0) {
            jcbUsuarioCalle.removeItemAt(0);
        }
    }

    public void reiniciarListaTomas() {
        vaciarListaTomas();
        llenarListaTomas();
    }

    public void reiniciarListaCalles() {
        vaciarListaCalles();
        llenarListaCalles();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variables del Interfaz"> 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbtCallesActualizar;
    private javax.swing.JButton jbtCallesCancelar;
    private javax.swing.JButton jbtCallesEliminar;
    private javax.swing.JButton jbtCallesGuardar;
    private javax.swing.JButton jbtTomasActualizar;
    private javax.swing.JButton jbtTomasCancelar;
    private javax.swing.JButton jbtTomasEliminar;
    private javax.swing.JButton jbtTomasGuardar;
    private javax.swing.JButton jbtUsuariosActualizar;
    private javax.swing.JButton jbtUsuariosCancelar;
    private javax.swing.JButton jbtUsuariosEliminar;
    private javax.swing.JButton jbtUsuariosGuardar;
    private javax.swing.JComboBox<String> jcbUsuarioCalle;
    private javax.swing.JComboBox<String> jcbUsuarioEstado;
    private javax.swing.JComboBox<String> jcbUsuarioToma;
    private javax.swing.JPanel jpCalles;
    private javax.swing.JPanel jpTomas;
    private javax.swing.JPanel jpUsuarios;
    private javax.swing.JSpinner jspCalleNumero;
    private javax.swing.JTable jtCalles;
    private javax.swing.JTable jtTomas;
    private javax.swing.JTable jtUsuarios;
    private javax.swing.JTextField jtfCalleNombre;
    private javax.swing.JTextField jtfTomasNombre;
    private javax.swing.JTextField jtfTomasPrecio;
    private javax.swing.JTextField jtfUsuarioAM;
    private javax.swing.JTextField jtfUsuarioAP;
    private javax.swing.JTextField jtfUsuarioNombre;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
