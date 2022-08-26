/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import com.org.JFiles.Systema.So;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author jp
 */
public class ConstructorDeArchivos {

    private static ConstructorDeArchivos instancia;

    public static ConstructorDeArchivos getInstancia() {
        if (instancia == null) {
            instancia = new ConstructorDeArchivos();
        }
        return instancia;
    }
    private final String es = "Documentos";
    private final String en = "Documents";
    private File[] dirs;
    private File[] files;

    private ConstructorDeArchivos() {
        switch (So.SO_NOMBRE.toLowerCase()) {
            case "linux":
                linux();
                break;
            case "windows":
                windows();
                break;
            case "mac":
                mac();
                break;
        }
    }

    private void windows() {
        dirs = new File[3];
        dirs[0] = new File(So.USUARIO_DIR_INICIO + "/Documents/jblue");
        dirs[1] = new File(So.USUARIO_DIR_INICIO + "/Desktop/jblue");
        dirs[2] = new File(So.USUARIO_DIR_INICIO + "/Desktop/jblue/Pagos");
        files = new File[2];
        files[0] = new File(So.USUARIO_DIR_INICIO + "/Documents/.jblue/conf.jff");
        files[1] = new File(So.USUARIO_DIR_INICIO + "/Documents/.jblue/user.jff");
        dirs[0].setReadable(true);
    }

    private void linux() {
        dirs = new File[3];
        dirs[0] = new File(So.USUARIO_DIR_INICIO + "/Documentos/.jblue");
        dirs[1] = new File(So.USUARIO_DIR_INICIO + "/Escritorio/blue");
        dirs[2] = new File(So.USUARIO_DIR_INICIO + "/Escritorio/jblue/Pagos");
        files = new File[2];
        files[0] = new File(So.USUARIO_DIR_INICIO + "/Documentos/.jblue/conf.jff");
        files[1] = new File(So.USUARIO_DIR_INICIO + "/Documentos/.jblue/user.jff");
    }

    private void mac() {
    }

    public void run() {
        for (File dir : dirs) {
            if (dir.mkdir()) {
                System.out.println("ruta: " + dir.getAbsolutePath());
            }
        }
        System.out.println("fin 1");
        for (File file : files) {
            if (file.exists()) {
                System.out.println("ruta: " + file.getAbsolutePath());
            } else {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println("fin 2");
    }

    /**
     * @param i index que determina el objeto retornado
     * <br> 0 "/Documents/.jshop/conf.jff");
     * <br> 1 "/Documents/.jshop/user.jff");
     * @return un objeto de tipo File con la ruta escogida por el index
     */
    public File getFiles(int i) {
        return files[i];
    }

    /**
     *
     * @param i
     * <br> 0 "/Documentos/.jshop"
     * <br> 1 "/Documentos/.jshop/cache"
     * <br> 2 "/Documentos/.jshop/img"
     * <br> 3 "/Documentos/.jshop/docs"
     * <br> 4 "/Documentos/.jshop/sql"
     * @return un objeto de tipo File con la ruta segun el index
     */
    public File getDirs(int i) {
        return dirs[i];
    }

}
