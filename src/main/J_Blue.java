package main;

import com.org.jblue.Sistema.Sistema;
import java.sql.SQLException;

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
        if (sistema.run()) {
            System.out.println("¡¡¡JBlue Ok!!!");
        } else {
            System.out.println("JBlue Error");
        }
    }

}
