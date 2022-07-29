package main;

import com.org.jblue.Sistema.Sistema;
import com.org.jblue.Sistema.funciones.FuncionesBD;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase encargada del inicio del programa
 *
 * @author jp
 */
public class J_Blue {

    /**
     * @param args the command line arguments Metodo principal
     */
    public static void main(String[] args) throws SQLException {
        Sistema sistema = Sistema.getInstancia();
        //sistema.run();
        FuncionesBD fun = FuncionesBD.getInstancia();
        fun.getListaRetardos("13");
    }

    public static void xd() {
        LocalDate fechaMin = LocalDate.MIN;

        LocalDate fechaMax = LocalDate.MAX;
        System.out.println(fechaMin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println(fechaMax.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
