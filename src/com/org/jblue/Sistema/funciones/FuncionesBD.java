/*s
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.funciones;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.Sistema.cache.cacheBD.Cache;
import com.org.jblue.modelo.objetos.OTitulares;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author jp
 */
public final class FuncionesBD {

    private static final FuncionesBD instancia = new FuncionesBD();

    public static FuncionesBD getInstancia() {
        return instancia;
    }

    private final Conexion cn;
    private final Cache cache;

    private FuncionesBD() {
        this.cn = Conexion.getInstancia();
        this.cache = Cache.getInstancia();
    }

    public void getListaRetardos(String id) throws SQLException {
        LocalDate fecha = LocalDate.now();
        int dia, mes, anio;
        dia = fecha.getDayOfMonth();
        mes = fecha.getMonthValue();
        anio = fecha.getYear();
        //if (fecha.getDayOfWeek().getValue() == 1) {
        String mes1 = Const.getMes(mes - 1);
        ResultSet select = cn.select("pagos_hechos", "*", "mes_p = '" + mes1 + "' and anio = '" + anio + "' and usuario = '" + id + "'");
        if (!select.next()) {
            for (OTitulares usuario : cache.getTitulares()) {
                if (usuario.getId().equals(id)) {
                    System.out.println(usuario.getNombre() + " " + usuario.getAp() + " " + usuario.getAm());
                }
            }

        } else {
            System.out.println("ok");
        }
        // }
    }

}
