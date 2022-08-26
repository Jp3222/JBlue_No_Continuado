package main;

import com.org.jblue.Sistema.Sistema;
import com.org.jblue.Sistema.cache.Cache;
import com.org.jblue.Sistema.cache.GetCache;

/**
 * Clase encargada del inicio del programa
 *
 * @author jp
 */
public class J_Blue {

    /**
     * @param args the command line arguments Metodo principal
     */
    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstancia();
        if (sistema.run()) {
            System.out.println("¡¡¡JBlue OK!!!");
        } else {
            System.out.println("¡¡¡JBlue Error!!!");
        }
    }

}
