/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.org.jblue.vistas.vistas;

import com.org.jblue.Const;
import com.org.jblue.Func;
import com.org.jblue.Sistema.ProgramaInfo;
import com.org.jblue.Sistema.cache.cacheBD.Cache;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox.ModeloComboBoxCalles;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox.ModeloComboBoxTomas;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas.ModeloTablaCalles;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas.ModeloTablaConsumidores;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas.ModeloTablaTitulares;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas.ModeloTablaTomas;
import com.org.jblue.controlador.CBaseDatos;
import com.org.jblue.controlador.MouseFunction;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.vistas.class_aux.ClassPanel;
import com.org.jblue.vistas.class_aux.Comparador;
import com.org.jblue.vistas.class_aux.Funciones;
import com.org.jblue.vistas.ventanas.JFMenu;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class JPBaseDatos extends ClassPanel implements Funciones {

    private final String titulo = INFO + ProgramaInfo.getSECCIONES(2);
    private final JFMenu menu;
    private final CBaseDatos controlador;
    private final Cache memoriaCache;
    //Modelos
    private final ModeloComboBoxCalles modeloBoxCalles;
    private final ModeloComboBoxTomas modeloBoxTomas;
    //
    private final ModeloTablaCalles modeloTablaCalles;
    private final ModeloTablaTomas modeloTablaTomas;
    private final ModeloTablaTitulares modeloTablaTitulares;
    private final ModeloTablaConsumidores modeloTablaConsumidores;

    /**
     * Creates new form PAdministracion
     *
     * @param menu
     */
    public JPBaseDatos(JFMenu menu) {
        this.menu = menu;
        this.memoriaCache = Cache.getInstancia();
        this.initComponents();
        this.controlador = new CBaseDatos(this);
        //
        modeloBoxCalles = new ModeloComboBoxCalles(Cache.getInstancia().getCalles());
        modeloBoxCalles.setComboBox(jcbTitularCalle);
        modeloBoxTomas = new ModeloComboBoxTomas(Cache.getInstancia().getTomas());
        modeloBoxTomas.setComboBox(jcbTitularToma);
        //
        modeloTablaCalles = new ModeloTablaCalles(Cache.getInstancia().getCalles(), (DefaultTableModel) jtCalles.getModel());
        modeloTablaTomas = new ModeloTablaTomas(Cache.getInstancia().getTomas(), (DefaultTableModel) jtTomas.getModel());
        modeloTablaTitulares = new ModeloTablaTitulares(Cache.getInstancia().getTitulares(), (DefaultTableModel) jtTitulares.getModel());
        modeloTablaConsumidores = new ModeloTablaConsumidores(Cache.getInstancia().getConsumidores(), (DefaultTableModel) jtConsumidores.getModel());
        this.call();
    }

    @Override
    public final void call() {
        info();
        init();
        addListeners();
    }

    @Override
    public void info() {
        menu.setTitle(titulo);
    }

    @Override
    public void init() {
        jspCalleNumero.setBackground(Color.white);
        jspCalleNumero.setForeground(Color.black);
        //
        modeloBoxCalles.llenar();
        modeloBoxTomas.llenar();
        modeloTablaTitulares.llenar();
        //
        jbtTomasGuardar.setEnabled(true);
        jbtTomasEliminar.setEnabled(false);
        jbtTomasActualizar.setEnabled(false);
        //
        jbtCallesGuardar.setEnabled(true);
        jbtCallesEliminar.setEnabled(false);
        jbtCallesActualizar.setEnabled(false);
        //
        jbtTitularGuardar.setEnabled(true);
        jbtTitularActualizar.setEnabled(false);
        jbtTitularEliminar.setEnabled(false);
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
        //CRUD titulares
        jbtTitularGuardar.addActionListener(e -> controlador.addUsuarios());
        jbtTitularActualizar.addActionListener(e -> controlador.setUsuario());
        jbtTitularEliminar.addActionListener(e -> controlador.removeUsuario());
        jbtTitularCancelar.addActionListener(e -> {
            jbtTitularGuardar.setEnabled(true);
            jbtTitularEliminar.setEnabled(false);
            jbtTitularActualizar.setEnabled(false);
            jtTitulares.clearSelection();
            vaciarCamposUsuario();

        });
        //Panel
        jtpBD.addChangeListener((e) -> {
            if (jpTitulares.isVisible()) {
                modeloBoxCalles.llenar();
                modeloBoxTomas.llenar();
                modeloTablaTitulares.llenar();
            } else {
                modeloBoxCalles.vaciar();
                modeloBoxTomas.vaciar();
                modeloTablaTitulares.vaciar();
            }

            if (jpConsumidores.isVisible()) {
                modeloTablaConsumidores.llenar();
            } else {
                modeloTablaConsumidores.vaciar();
            }
            if (jpOtros.isVisible()) {
                modeloTablaCalles.llenar();
            } else {
                modeloTablaCalles.vaciar();
            }
        });

        jtpDC.addChangeListener((e) -> {
            if (jpCalles.isVisible()) {
                modeloTablaCalles.llenar();
            } else if (jpTiposTomas.isVisible()) {
                modeloTablaTomas.llenar();

            } else {
                modeloTablaCalles.vaciar();
                modeloTablaTomas.vaciar();
            }
        });

        //
        MouseFunction jtTomasEvent = new MouseFunction();

        jtTomasEvent.addMouseClickedEvent((e) -> {
            jtfTomasNombre.setText((String) jtTomas.getValueAt(jtTomas.getSelectedRow(), 1));
            jtfTomasPrecio.setText((String) jtTomas.getValueAt(jtTomas.getSelectedRow(), 2));
            jbtTomasGuardar.setEnabled(false);
            jbtTomasEliminar.setEnabled(true);
            jbtTomasActualizar.setEnabled(true);
        });
        jtTomas.addMouseListener(jtTomasEvent);
        //
        MouseFunction mfCalles = new MouseFunction();
        mfCalles.addMouseClickedEvent((e) -> {
            jbtCallesGuardar.setEnabled(false);
            jbtCallesEliminar.setEnabled(true);
            jbtCallesActualizar.setEnabled(true);
            jtfCalleNombre.setText((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 1));
            int value = Integer.parseInt((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 2));
            jspCalleNumero.setValue(value);
        });
        jtCalles.addMouseListener(mfCalles);
        //
        MouseFunction mfUsuarios = new MouseFunction();
        mfUsuarios.addMouseClickedEvent(e -> {
            jbtTitularGuardar.setEnabled(false);
            jbtTitularActualizar.setEnabled(true);
            jbtTitularEliminar.setEnabled(true);
            jtfTitularNombre.setText((String) jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 1));
            jtfTitularAP.setText((String) jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 2));
            jtfTitularAM.setText((String) jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 3));
            jcbTitularCalle.setSelectedItem(jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 4));
            jcbTitularToma.setSelectedItem(jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 5));
            jcbTitularEstado.setSelectedItem(jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 7));
        });
        jtTitulares.addMouseListener(mfUsuarios);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jtpBD = new javax.swing.JTabbedPane();
        jpTitulares = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTitulares = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfTitularNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfTitularAP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbTitularToma = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbTitularCalle = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbTitularEstado = new javax.swing.JComboBox<>();
        jtfTitularAM = new javax.swing.JTextField();
        jbtTitularCancelar = new javax.swing.JButton();
        jbtTitularEliminar = new javax.swing.JButton();
        jbtTitularActualizar = new javax.swing.JButton();
        jbtTitularGuardar = new javax.swing.JButton();
        jpConsumidores = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtConsumidores = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jtfUsuarioNombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtfUsuarioAP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jcbUsuarioEstado = new javax.swing.JComboBox<>();
        jtfUsuarioAM = new javax.swing.JTextField();
        jbtUsuariosCancelar1 = new javax.swing.JButton();
        jbtUsuariosEliminar1 = new javax.swing.JButton();
        jbtUsuariosActualizar1 = new javax.swing.JButton();
        jbtUsuariosGuardar1 = new javax.swing.JButton();
        jcbUsuarioTitular = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jpOtros = new javax.swing.JPanel();
        jtpDC = new javax.swing.JTabbedPane();
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
        jpTiposTomas = new javax.swing.JPanel();
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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

        jButton2.setBackground(new java.awt.Color(0, 0, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/nota-adhesiva x32.png"))); // NOI18N
        jButton2.setToolTipText("Administrador");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jtpBD.setBackground(new java.awt.Color(255, 255, 255));
        jtpBD.setForeground(new java.awt.Color(0, 0, 0));
        jtpBD.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtpBD.setAutoscrolls(true);
        jtpBD.setFocusCycleRoot(true);

        jpTitulares.setBackground(new java.awt.Color(0, 153, 255));

        jtTitulares.setBackground(new java.awt.Color(255, 255, 255));
        jtTitulares.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtTitulares.setForeground(new java.awt.Color(0, 0, 0));
        jtTitulares.setModel(new javax.swing.table.DefaultTableModel(
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
        jtTitulares.setGridColor(new java.awt.Color(0, 0, 0));
        jtTitulares.setOpaque(false);
        jtTitulares.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtTitulares.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtTitulares.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtTitulares.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtTitulares.setShowGrid(true);
        jtTitulares.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtTitulares);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre:");
        jLabel1.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfTitularNombre.setBackground(new java.awt.Color(255, 255, 255));
        jtfTitularNombre.setForeground(new java.awt.Color(0, 0, 0));
        jtfTitularNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTitularNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apellido Paterno:");
        jLabel2.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfTitularAP.setBackground(new java.awt.Color(255, 255, 255));
        jtfTitularAP.setForeground(new java.awt.Color(0, 0, 0));
        jtfTitularAP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTitularAP.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Apellido Materno:");
        jLabel3.setPreferredSize(new java.awt.Dimension(52, 30));

        jcbTitularToma.setBackground(new java.awt.Color(255, 255, 255));
        jcbTitularToma.setForeground(new java.awt.Color(0, 0, 0));
        jcbTitularToma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbTitularToma.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Seleccione el tipo de toma:");
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 30));

        jcbTitularCalle.setBackground(new java.awt.Color(255, 255, 255));
        jcbTitularCalle.setForeground(new java.awt.Color(0, 0, 0));
        jcbTitularCalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbTitularCalle.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Seleccione una calle:");
        jLabel5.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Seleccione una calle:");
        jLabel6.setPreferredSize(new java.awt.Dimension(52, 30));

        jcbTitularEstado.setBackground(new java.awt.Color(255, 255, 255));
        jcbTitularEstado.setForeground(new java.awt.Color(0, 0, 0));
        jcbTitularEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jcbTitularEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbTitularEstado.setPreferredSize(new java.awt.Dimension(118, 30));

        jtfTitularAM.setBackground(new java.awt.Color(255, 255, 255));
        jtfTitularAM.setForeground(new java.awt.Color(0, 0, 0));
        jtfTitularAM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTitularAM.setPreferredSize(new java.awt.Dimension(85, 30));

        jbtTitularCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTitularCancelar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTitularCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtTitularCancelar.setText("Cancelar");
        jbtTitularCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTitularCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTitularEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTitularEliminar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTitularEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtTitularEliminar.setText("Eliminar");
        jbtTitularEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTitularEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTitularActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTitularActualizar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTitularActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtTitularActualizar.setText("Actualizar");
        jbtTitularActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTitularActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTitularGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTitularGuardar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTitularGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtTitularGuardar.setText("Guardar");
        jbtTitularGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTitularGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfTitularNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jcbTitularToma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfTitularAP, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfTitularAM, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtTitularGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(jbtTitularActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbTitularCalle, 0, 300, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jcbTitularEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtTitularEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtTitularCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(jbtTitularGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTitularNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitularAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitularAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtTitularActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtTitularEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbTitularToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTitularCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbTitularEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtTitularCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTitularesLayout = new javax.swing.GroupLayout(jpTitulares);
        jpTitulares.setLayout(jpTitularesLayout);
        jpTitularesLayout.setHorizontalGroup(
            jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitularesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jpTitularesLayout.setVerticalGroup(
            jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitularesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpBD.addTab("TITULARES", jpTitulares);

        jpConsumidores.setBackground(new java.awt.Color(0, 153, 255));

        jtConsumidores.setBackground(new java.awt.Color(255, 255, 255));
        jtConsumidores.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtConsumidores.setForeground(new java.awt.Color(0, 0, 0));
        jtConsumidores.setModel(new javax.swing.table.DefaultTableModel(
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
        jtConsumidores.setGridColor(new java.awt.Color(0, 0, 0));
        jtConsumidores.setOpaque(false);
        jtConsumidores.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtConsumidores.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtConsumidores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtConsumidores.setShowGrid(true);
        jtConsumidores.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtConsumidores);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nombre:");
        jLabel7.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfUsuarioNombre.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsuarioNombre.setForeground(new java.awt.Color(0, 0, 0));
        jtfUsuarioNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Apellido Paterno:");
        jLabel12.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfUsuarioAP.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsuarioAP.setForeground(new java.awt.Color(0, 0, 0));
        jtfUsuarioAP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioAP.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Apellido Materno:");
        jLabel13.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Estado:");
        jLabel16.setPreferredSize(new java.awt.Dimension(52, 30));

        jcbUsuarioEstado.setBackground(new java.awt.Color(255, 255, 255));
        jcbUsuarioEstado.setForeground(new java.awt.Color(0, 0, 0));
        jcbUsuarioEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jcbUsuarioEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbUsuarioEstado.setPreferredSize(new java.awt.Dimension(118, 30));

        jtfUsuarioAM.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsuarioAM.setForeground(new java.awt.Color(0, 0, 0));
        jtfUsuarioAM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfUsuarioAM.setPreferredSize(new java.awt.Dimension(85, 30));

        jbtUsuariosCancelar1.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosCancelar1.setForeground(new java.awt.Color(0, 0, 0));
        jbtUsuariosCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtUsuariosCancelar1.setText("Cancelar");
        jbtUsuariosCancelar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosCancelar1.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosEliminar1.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosEliminar1.setForeground(new java.awt.Color(0, 0, 0));
        jbtUsuariosEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtUsuariosEliminar1.setText("Eliminar");
        jbtUsuariosEliminar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosEliminar1.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosActualizar1.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosActualizar1.setForeground(new java.awt.Color(0, 0, 0));
        jbtUsuariosActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtUsuariosActualizar1.setText("Actualizar");
        jbtUsuariosActualizar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosActualizar1.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtUsuariosGuardar1.setBackground(new java.awt.Color(204, 204, 204));
        jbtUsuariosGuardar1.setForeground(new java.awt.Color(0, 0, 0));
        jbtUsuariosGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtUsuariosGuardar1.setText("Guardar");
        jbtUsuariosGuardar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtUsuariosGuardar1.setPreferredSize(new java.awt.Dimension(83, 30));

        jcbUsuarioTitular.setBackground(new java.awt.Color(255, 255, 255));
        jcbUsuarioTitular.setForeground(new java.awt.Color(0, 0, 0));
        jcbUsuarioTitular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jcbUsuarioTitular.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Titular");
        jLabel17.setPreferredSize(new java.awt.Dimension(52, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfUsuarioNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jcbUsuarioTitular, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfUsuarioAP, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfUsuarioAM, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtUsuariosGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(jbtUsuariosActualizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jcbUsuarioEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(324, 324, 324)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtUsuariosEliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtUsuariosCancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtUsuariosGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuarioAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuarioAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtUsuariosActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jbtUsuariosEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addComponent(jbtUsuariosCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addComponent(jcbUsuarioTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addComponent(jcbUsuarioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpConsumidoresLayout = new javax.swing.GroupLayout(jpConsumidores);
        jpConsumidores.setLayout(jpConsumidoresLayout);
        jpConsumidoresLayout.setHorizontalGroup(
            jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsumidoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jpConsumidoresLayout.setVerticalGroup(
            jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsumidoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpBD.addTab("CONSUMIDORES", jpConsumidores);

        jpOtros.setBackground(new java.awt.Color(255, 255, 255));
        jpOtros.setForeground(new java.awt.Color(0, 0, 0));

        jtpDC.setBackground(new java.awt.Color(255, 255, 255));
        jtpDC.setForeground(new java.awt.Color(0, 0, 0));
        jtpDC.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtpDC.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jpCalles.setBackground(new java.awt.Color(0, 153, 255));

        jtCalles.setBackground(new java.awt.Color(255, 255, 255));
        jtCalles.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtCalles.setForeground(new java.awt.Color(0, 0, 0));
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
        jtCalles.setGridColor(new java.awt.Color(0, 0, 0));
        jtCalles.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtCalles.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtCalles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCalles.setShowGrid(true);
        jtCalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtCalles);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos de la calle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setOpaque(false);

        jtfCalleNombre.setBackground(new java.awt.Color(255, 255, 255));
        jtfCalleNombre.setForeground(new java.awt.Color(0, 0, 0));
        jtfCalleNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfCalleNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nombre de la calle");
        jLabel10.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
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
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCalleNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCalleNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtCallesGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesGuardar.setForeground(new java.awt.Color(0, 0, 0));
        jbtCallesGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtCallesGuardar.setText("Guardar");
        jbtCallesGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesActualizar.setForeground(new java.awt.Color(0, 0, 0));
        jbtCallesActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtCallesActualizar.setText("Actualizar");
        jbtCallesActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesEliminar.setForeground(new java.awt.Color(0, 0, 0));
        jbtCallesEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtCallesEliminar.setText("Eliminar");
        jbtCallesEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtCallesEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtCallesCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtCallesCancelar.setForeground(new java.awt.Color(0, 0, 0));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCallesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtpDC.addTab("CALLES", jpCalles);

        jpTiposTomas.setBackground(new java.awt.Color(0, 153, 255));

        jtTomas.setBackground(new java.awt.Color(255, 255, 255));
        jtTomas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jtTomas.setForeground(new java.awt.Color(0, 0, 0));
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
        jtTomas.setGridColor(new java.awt.Color(0, 0, 0));
        jtTomas.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jtTomas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jtTomas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtTomas.setShowGrid(true);
        jtTomas.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtTomas);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos de la toma", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.setOpaque(false);

        jtfTomasNombre.setBackground(new java.awt.Color(255, 255, 255));
        jtfTomasNombre.setForeground(new java.awt.Color(0, 0, 0));
        jtfTomasNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfTomasNombre.setPreferredSize(new java.awt.Dimension(85, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tipo de toma");
        jLabel8.setPreferredSize(new java.awt.Dimension(52, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Precio de la toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(52, 30));

        jtfTomasPrecio.setBackground(new java.awt.Color(255, 255, 255));
        jtfTomasPrecio.setForeground(new java.awt.Color(0, 0, 0));
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
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jtfTomasPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTomasNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTomasPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtTomasGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasGuardar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTomasGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/disquete.png"))); // NOI18N
        jbtTomasGuardar.setText("Guardar");
        jbtTomasGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasGuardar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasActualizar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasActualizar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTomasActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/actualizar-flecha.png"))); // NOI18N
        jbtTomasActualizar.setText("Actualizar");
        jbtTomasActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasActualizar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasEliminar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTomasEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/basura.png"))); // NOI18N
        jbtTomasEliminar.setText("Eliminar");
        jbtTomasEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasEliminar.setPreferredSize(new java.awt.Dimension(83, 30));

        jbtTomasCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jbtTomasCancelar.setForeground(new java.awt.Color(0, 0, 0));
        jbtTomasCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/org/jblue/Icons/crud/cancelar.png"))); // NOI18N
        jbtTomasCancelar.setText("Cancelar");
        jbtTomasCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtTomasCancelar.setPreferredSize(new java.awt.Dimension(83, 30));

        javax.swing.GroupLayout jpTiposTomasLayout = new javax.swing.GroupLayout(jpTiposTomas);
        jpTiposTomas.setLayout(jpTiposTomasLayout);
        jpTiposTomasLayout.setHorizontalGroup(
            jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTiposTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtTomasEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtTomasCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTiposTomasLayout.setVerticalGroup(
            jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTiposTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jpTiposTomasLayout.createSequentialGroup()
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

        jtpDC.addTab("TIPOS DE TOMAS", jpTiposTomas);

        javax.swing.GroupLayout jpOtrosLayout = new javax.swing.GroupLayout(jpOtros);
        jpOtros.setLayout(jpOtrosLayout);
        jpOtrosLayout.setHorizontalGroup(
            jpOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpDC)
        );
        jpOtrosLayout.setVerticalGroup(
            jpOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpDC)
        );

        jtpBD.addTab("DATOS COMPLEMENTARIOS", jpOtros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpBD, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpBD))
        );

        jtpBD.getAccessibleContext().setAccessibleName("JTPBD");
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
        jtfTitularNombre.setText(null);
        jtfTitularAP.setText(null);
        jtfTitularAM.setText(null);
        jcbTitularCalle.setSelectedIndex(0);
        jcbTitularEstado.setSelectedIndex(0);
        jcbTitularToma.setSelectedIndex(0);
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos de Tipos de Tomas"> 
    public String[] getTomasInsert() {
        String[] o = new String[3];
        o[0] = "0";
        o[1] = jtfTomasNombre.getText();
        o[2] = jtfTomasPrecio.getText();
        return Func.getArrayFormatoDeEntrada(o);
    }

    public String[] getTomasUpdate() {
        String[] o = new String[3];
        int row = jtTomas.getSelectedRow();
        int col = jtTomas.getSelectedRow();
        o[0] = (String) jtTomas.getValueAt(row, col);
        o[1] = jtfTomasNombre.getText();
        o[2] = jtfTomasPrecio.getText();
        return Func.getArrayFormatoDeEntrada(o);
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
    // <editor-fold defaultstate="collapsed" desc="Metodos de Calles Registradas"> 
    public String[] getCalleInsert() {
        String[] o = new String[Const.BD_CALLES.length];
        o[0] = "0";
        o[1] = jtfCalleNombre.getText();
        o[2] = "" + ((Integer) jspCalleNumero.getValue());
        return Func.getArrayFormatoDeEntrada(o);
    }

    public String[] getCallesUpdate() {
        String[] o = new String[Const.BD_CALLES.length];
        o[0] = (String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 0);
        o[1] = jtfCalleNombre.getText();
        o[2] = (String) jspCalleNumero.getValue();
        return Func.getArrayFormatoDeEntrada(o);
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
    // <editor-fold defaultstate="collapsed" desc="Metodos de Titulares"> 
    public String[] getUsuariosInsert() {
        String[] o = new String[8];
        o[0] = "0";
        o[1] = jtfTitularNombre.getText();
        o[2] = jtfTitularAP.getText();
        o[3] = jtfTitularAM.getText();
        //
        o[4] = jcbTitularCalle.getItemAt(jcbTitularCalle.getSelectedIndex());
        Comparador<OCalles, String> fun = (x, y) -> {
            String nom = x.getNombre() + " No. " + x.getNumero();
            return nom.compareToIgnoreCase(y);
        };
        OCalles rs = Func.getBuscarLineal(Cache.getInstancia().getCalles(), o[4], fun);
        o[4] = rs.getId();
        //
        o[5] = jcbTitularToma.getItemAt(jcbTitularToma.getSelectedIndex());
        o[5] = controlador.getIDToma(o[5]);
        //
        LocalDateTime fecha = LocalDateTime.now();
        o[6] = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        o[7] = jcbTitularEstado.getItemAt(jcbTitularEstado.getSelectedIndex());
        if (o[7].equals("Activo")) {
            o[7] = "1";
        } else {
            o[7] = "0";
        }
        return Func.getArrayFormatoDeEntrada(o);
    }

    public String[] getUsuariosUpdate() {
        String[] o = new String[8];
        o[0] = "0";
        o[1] = jtfTitularNombre.getText();
        o[2] = jtfTitularAP.getText();
        o[3] = jtfTitularAM.getText();
        //
        o[4] = jcbTitularCalle.getItemAt(jcbTitularCalle.getSelectedIndex());
        o[4] = controlador.getIDCalle(o[4]);
        //
        o[5] = jcbTitularToma.getItemAt(jcbTitularToma.getSelectedIndex());
        o[5] = controlador.getIDToma(o[5]);
        //
        LocalDateTime fecha = LocalDateTime.now();
        o[6] = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        o[7] = jcbTitularEstado.getItemAt(jcbTitularEstado.getSelectedIndex());
        if (o[7].equals("Activo")) {
            o[7] = "1";
        } else {
            o[7] = "0";
        }
        return Func.getArrayFormatoDeEntrada(o);
    }

    public String getUsuarioDelete() {
        return (String) jtTitulares.getValueAt(jtTitulares.getSelectedRow(), 0);
    }

    public int camposVaciosUsuario() {
        int i = 0;
        if (!textValido(jtfTitularNombre.getText())) {
            i++;
        }
        if (!textValido(jtfTitularAP.getText())) {
            i++;
        }
        if (!textValido(jtfTitularAM.getText())) {
            i++;
        }
        if (jcbTitularCalle.getSelectedIndex() == 0) {
            i++;
        }
        if (jcbTitularToma.getSelectedIndex() == 0) {
            i++;
        }
        return i;
    }

    //</editor-fold>
    public ModeloComboBoxCalles getModeloBoxCalles() {
        return modeloBoxCalles;
    }

    public ModeloComboBoxTomas getModeloBoxTomas() {
        return modeloBoxTomas;
    }

    public ModeloTablaCalles getModeloTablaCalles() {
        return modeloTablaCalles;
    }

    public ModeloTablaTomas getModeloTablaTomas() {
        return modeloTablaTomas;
    }

    public ModeloTablaTitulares getModeloTablaTitulares() {
        return modeloTablaTitulares;
    }

    public ModeloTablaConsumidores getModeloTablaConsumidores() {
        return modeloTablaConsumidores;
    }
// <editor-fold defaultstate="collapsed" desc="Variables del Interfaz"> 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbtCallesActualizar;
    private javax.swing.JButton jbtCallesCancelar;
    private javax.swing.JButton jbtCallesEliminar;
    private javax.swing.JButton jbtCallesGuardar;
    private javax.swing.JButton jbtTitularActualizar;
    private javax.swing.JButton jbtTitularCancelar;
    private javax.swing.JButton jbtTitularEliminar;
    private javax.swing.JButton jbtTitularGuardar;
    private javax.swing.JButton jbtTomasActualizar;
    private javax.swing.JButton jbtTomasCancelar;
    private javax.swing.JButton jbtTomasEliminar;
    private javax.swing.JButton jbtTomasGuardar;
    private javax.swing.JButton jbtUsuariosActualizar1;
    private javax.swing.JButton jbtUsuariosCancelar1;
    private javax.swing.JButton jbtUsuariosEliminar1;
    private javax.swing.JButton jbtUsuariosGuardar1;
    private javax.swing.JComboBox<String> jcbTitularCalle;
    private javax.swing.JComboBox<String> jcbTitularEstado;
    private javax.swing.JComboBox<String> jcbTitularToma;
    private javax.swing.JComboBox<String> jcbUsuarioEstado;
    private javax.swing.JComboBox<String> jcbUsuarioTitular;
    private javax.swing.JPanel jpCalles;
    private javax.swing.JPanel jpConsumidores;
    private javax.swing.JPanel jpOtros;
    private javax.swing.JPanel jpTiposTomas;
    private javax.swing.JPanel jpTitulares;
    private javax.swing.JSpinner jspCalleNumero;
    private javax.swing.JTable jtCalles;
    private javax.swing.JTable jtConsumidores;
    private javax.swing.JTable jtTitulares;
    private javax.swing.JTable jtTomas;
    private javax.swing.JTextField jtfCalleNombre;
    private javax.swing.JTextField jtfTitularAM;
    private javax.swing.JTextField jtfTitularAP;
    private javax.swing.JTextField jtfTitularNombre;
    private javax.swing.JTextField jtfTomasNombre;
    private javax.swing.JTextField jtfTomasPrecio;
    private javax.swing.JTextField jtfUsuarioAM;
    private javax.swing.JTextField jtfUsuarioAP;
    private javax.swing.JTextField jtfUsuarioNombre;
    private javax.swing.JTabbedPane jtpBD;
    private javax.swing.JTabbedPane jtpDC;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
