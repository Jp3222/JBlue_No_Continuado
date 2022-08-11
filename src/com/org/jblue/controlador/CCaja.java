/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.controlador;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.Sistema.Cache;
import com.org.jblue.modelo.objetos.OPagosTitular;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.operaciones.OperacionesPagosTitulares;
import com.org.jblue.vistas.vistas.JPCaja;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CCaja extends Controlador {

    private final JPCaja CAJA;
    private final CCaja_Adaptador ADAPTADOR;
    private final Cache memoriaCache;
    private OTitulares usuario_en_caja;
    private OTomas tipo_de_toma;
    private DefaultTableModel modelPagosHoy, modelTodosPagos;
    private final OperacionesPagosTitulares operacionesPagos;

    public CCaja(JPCaja CAJA) {
        this.CAJA = CAJA;
        this.ADAPTADOR = new CCaja_Adaptador();
        this.memoriaCache = Cache.getInstancia();
        this.operacionesPagos = new OperacionesPagosTitulares();
    }

    public CCaja_Adaptador getADAPTADOR() {
        return ADAPTADOR;
    }

    public void setTipo_de_toma(OTomas tipo_de_toma) {
        this.tipo_de_toma = tipo_de_toma;
    }

    public void setUsuario_en_caja(OTitulares usuario_en_caja) {
        this.usuario_en_caja = usuario_en_caja;
    }

    public void setModelPagosHoy(DefaultTableModel modelPagosHoy) {
        this.modelPagosHoy = modelPagosHoy;
    }

    public class CCaja_Adaptador extends Adaptador {

        public CCaja_Adaptador() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "cobrar":
                    cobrar();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        public void cobrar() {
            OTitulares usuario = CAJA.getUsuario();
            LocalDate fecha = LocalDate.now();
            int meses_pagados = getMesesPagados(usuario.getId());
            int meses_a_pagar = CAJA.Meses_A_Pagar();
            if (meses_pagados >= 0) {
                if (meses_pagados + meses_a_pagar > 12) {
                    JOptionPane.showMessageDialog(CAJA, "Este usuario no puede hacer mas de 12 pagos");
                    return;
                }
                pagos(meses_pagados, meses_a_pagar);
                JOptionPane.showMessageDialog(null, "Pago Hecho");
            } else {
                JOptionPane.showMessageDialog(null, "Pago Erroneo");
            }
        }

        public void pagos(int inicio_1, int fin_2) {
            int inicio = inicio_1, fin = inicio + fin_2;
            OTitulares usuario = CAJA.getUsuario();
            System.out.println(usuario.getEstado());
            OTomas toma = CAJA.getToma();
            Conexion cn = Conexion.getInstancia();
            LocalDate fecha = LocalDate.now();
            for (int i = inicio, j = inicio; j < fin; i++, j++) {
                if (i >= 11) {
                    i = 0;
                }
                llenarTablaPagosHoy();
                cn.insert("pagos_hechos",
                        cn.getCampos("usuario", "mes_p", "dia", "mes", "anio"),
                        cn.getDatos(usuario.getId(), Const.getMes(i), fecha.getDayOfMonth() + "", fecha.getMonthValue() + "", fecha.getYear() + "")
                );
            }
        }

        public int getMesesPagados(String usuario) {
            try {
                Conexion cn = Conexion.getInstancia();
                LocalDate fecha = LocalDate.now();
                int i;
                try ( ResultSet select = cn.select("pagos_hechos", "mes_p", "usuario = " + usuario + " and anio = " + fecha.getYear())) {
                    i = 0;
                    while (select.next()) {
                        i++;
                    }
                }
                return i;
            } catch (SQLException ex) {
                Logger.getLogger(CCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;

        }

        public void llenarTablaPagosHoy() {
            vaciarTablaPagosHoy();
            String query = query();
            ArrayList<OPagosTitular> lista = operacionesPagos.getLista(query);
            if (lista.isEmpty()) {
                return;
            }
            for (OPagosTitular oPagos : lista) {
                String[] info = getInfo(oPagos.getTitular());
                String[] array = {info[0], info[1], oPagos.getMes_pagado()};
                modelPagosHoy.addRow(array);
            }
        }

        public void vaciarTablaPagosHoy() {
            while (modelPagosHoy.getRowCount() > 0) {
                modelPagosHoy.removeRow(0);
            }
        }

        String[] getInfo(String id) {
            ArrayList<OTitulares> usuarios = memoriaCache.getUsuarios();
            for (OTitulares usuario : usuarios) {
                if (usuario.getId().equals(id)) {
                    String nombre = usuario.getNombre() + " " + usuario.getAp() + " " + usuario.getAm();
                    return new String[]{
                        nombre, getTomas(usuario.getToma())
                    };
                }
            }
            return null;
        }

        String getTomas(String id) {
            ArrayList<OTomas> tomas = memoriaCache.getTomas();
            for (OTomas toma : tomas) {
                if (toma.getId().equals(id)) {
                    return toma.getTipo();
                }
            }
            return null;
        }

        public void llenarPagosHechos() {

        }

        public String query() {
            LocalDate fecha = LocalDate.now();
            return "dia = " + fecha.getDayOfMonth() + " and mes = " + fecha.getMonthValue() + " and anio = " + fecha.getYear();
        }
    }
}
