/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.criptografia;

import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Clase para generar el hash de una cadena de manera que se pueda guardar en BBDD.
 *
 * @author aitkiar
 */
public class CodificarCadenas
{
    /**
     * Codifica una cadena usando 1024 iteraciones del algoritmo PBKDF2WithHmacSHA512
     * y devuelve el resultado codificado en base64 para que se pueda almacenar
     * almacenar fácilmente en BBDD, o comparar como cadena.
     * 
     * El algoritmo funciona de manera que el resultado, una vez convertido a base 64 sea de 1024 caracteres.
     * 
     * @param cadena
     * @param salt
     * @return cadena en base64 de 1024 caracteres.
     * @throws Exception 
     */
    public static String codificar( String cadena, String salt ) throws Exception
    {
        PBEKeySpec spec = new PBEKeySpec( cadena.toCharArray(), salt.getBytes(), 1024, 1024 * 6 );
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        
        return Base64.getEncoder().encodeToString( skf.generateSecret(spec).getEncoded() );
    }
    
    public static void main ( String args[] )
    {
        try
        {                   
            System.out.println( CodificarCadenas.codificar("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1123456789"));
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
