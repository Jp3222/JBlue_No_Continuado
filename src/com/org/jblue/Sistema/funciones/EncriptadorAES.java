/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.funciones;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jp
 */
public class EncriptadorAES {

    private final static EncriptadorAES instancia = new EncriptadorAES();

    public synchronized static EncriptadorAES getInstancia() {
        return instancia;
    }

    public EncriptadorAES() {
    }

    /**
     * Crea la clave de encriptacion usada internamente
     *
     * @param clave Clave que se usara para encriptar
     * @return Clave de encriptacion
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec crearClave(String clave) {
        try {
            byte[] claveEncriptacion = clave.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            claveEncriptacion = sha.digest(claveEncriptacion);
            claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
            SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");
            return secretKey;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return null;
    }

    /**
     * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
     *
     * @param datos Cadena a encriptar
     * @param claveSecreta Clave para encriptar
     * @return Información encriptada
     */
    public String encriptar(String datos, String claveSecreta) {
        try {
            SecretKeySpec secretKey = this.crearClave(claveSecreta);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] datosEncriptar = datos.getBytes("UTF-8");
            byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
            String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
            return encriptado;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Desencripta la cadena de texto indicada usando la clave de encriptacion
     *
     * @param datosEncriptados Datos encriptados
     * @param claveSecreta Clave de encriptacion
     * @return Informacion desencriptada
     */
    private String desencriptar(String datosEncriptados, String claveSecreta) {
        try {
            SecretKeySpec secretKey = this.crearClave(claveSecreta);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
            byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
            String datos = new String(datosDesencriptados);
            return datos;
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return null;
    }

    /**
     * compara si un par de datos desencriptados tienen el mismo resultado que
     * dos pares de datos encriptados
     *
     * @param VE valor encriptado
     * @param LE llave encriptada
     * @param VD valor desencriptado
     * @param LD llave desencriptada
     * @return true si al encriptar el valor y la llame optienen los mismos resultados que los datos ya encriptados
     */
    public boolean comparadorEncriptado(String VE, String LE, String VD, String LD) {
        String v = encriptar(VD, LD);
        String k = encriptar(LD, VD);
        return VE.equals(v) && LE.equals(k);

    }

}
