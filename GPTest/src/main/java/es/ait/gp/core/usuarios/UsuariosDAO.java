/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.usuarios;

import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz que deben implementar los DAOS de usuarios.
 * 
 * @author aitkiar
 */
@Local
public interface UsuariosDAO
{

    /**
     * Crea un nuevo usuario en la BBDD.
     * 
     * @param usuario
     * @throws Exception 
     */
    void create( Usuarios usuario ) throws Exception;

    /**
     * Modifica un usuario en la BBDD.
     * 
     * @param usuario
     * @throws Exception 
     */
    void edit( Usuarios usuario ) throws Exception;

    /**
     * Elimina un usuario de la BBDD.
     * 
     * @param usuario
     * @throws Exception 
     */
    void remove( Usuarios usuario ) throws Exception;
    
    /**
     * Cambia la contraseña del usuario en BBDD, por la del objeto usuario pasado
     * como paráemtro asumiento que esta está en texto plano.
     * 
     * @param usuario
     * @throws Exception 
     */
    void changePassword( Usuarios usuario ) throws Exception;

    /**
     * Busca un usuario a partir de su id numérico
     * @param id
     * @return
     * @throws Exception 
     */
    Usuarios find( Object id ) throws Exception;
    
    /**
     * Busca un usuario a partir de su login.
     * @param login
     * @return
     * @throws Exception 
     */
    Usuarios findByLogin( String login ) throws Exception;
    
    /**
     * Usa los campos login, nopmbre, email y activo del objeto usuario para obtener
     * una lista de usuarios que coincidan con la busqueda realizada.
     * 
     * Si usuario es null devuelve todos los usuarios
     * de la tabla.
     * 
     * @param usuario
     * @return
     * @throws Exception 
     */
    List<Usuarios>findByFilter( Usuarios usuario ) throws Exception;
    
}
