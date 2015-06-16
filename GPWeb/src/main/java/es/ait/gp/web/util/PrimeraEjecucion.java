/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util;

import es.ait.gp.core.permisos.ConstantesPermisos;
import es.ait.gp.core.permisos.Permisos;
import es.ait.gp.core.permisos.PermisosDAO;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * Clase que se tiene que ejecutar la primera vez que se arranque la aplicación 
 * para inicializar los valores por defecto en la BBDD.
 * 
 * Se considera que la BBDD no está inicializada si no existe el usuario admin
 *
 * @author aitkiar
 */
public class PrimeraEjecucion
{
    public void execute( UsuariosDAO usuariosDao, PermisosDAO permisosDao, RolesDAO rolesDao ) throws Exception
    {
        if ( usuariosDao.findByLogin("admin") == null )
        {
            System.out.println("No se ha encontrado el usuario admin, cargando datos...");

            System.out.println("[INICIO]Actualizando tablas iniciales");
            System.out.println("\tObteniendo ejecutor");
            JDBCExecutor ejecutor = new JDBCExecutor();
            
            System.out.println("\tInsertando GENERATOR TABLE");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'proyectos', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'usuarios', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'tareas', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'relaciones', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'tipos_documentacion', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'documentacion', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'versiones', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'permisos', 0 )");
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'roles', 0 )");
            
            
            System.out.println("\tInsertando estado_proyectos");
            ejecutor.executeUpadate("insert into estado_proyectos values ( 'INICIADO', 'Estado en el que se encuentra un proyecto nada más arrancar' )");
            ejecutor.executeUpadate("insert into estado_proyectos values ( 'CERRADO', 'El proyecto y todas sus tareas asociadas han concluido.' )");

            
            System.out.println("\tInsertando en permisos");
            Field[] campos = ConstantesPermisos.class.getDeclaredFields();
            for ( Field campo : campos )
            {
                Permisos permiso = new Permisos();
                permiso.setPermNombre( campo.getName() );
                permiso.setPermDescripcion(campo.getName());
                try
                {
                    permisosDao.findByName( campo.getName());
                }
                catch( Exception e )
                {
                    permisosDao.create( permiso );
                }
            }

            System.out.println("\tInsertando el role superadministrador.");
            Roles role = null;
            try
            {
                role = rolesDao.findByName("Superadministrador"); 
            }
            catch ( Exception e )
            {
                role = new Roles();
                role.setRoleDescripcion("Superadministrador");
            }
            
            role.setPermisosList( permisosDao.findAll());
            if ( role.getRoleId() != null )
            {
                rolesDao.edit( role );
            }
            else
            {
                rolesDao.create( role );
            }

            
            System.out.println("[FIN]Actualizando tablas iniciales");
            
            System.out.println("[INICIO]Creando usuario administrador");
            Usuarios usuario = new Usuarios();
            usuario.setUsuaLogin("admin");
            usuario.setUsuaPassword("admin");
            usuario.setUsuaNombre("Usuario administrador por defecto");
            usuario.setUsuaActivo( true );
            usuario.setUsuaFxAlta( new Date());
            usuario.setUsuaEmail("aitkiar@gmail.com");
            List<Roles> roles = new ArrayList();
            roles.add( role );
            usuario.setRolesList( roles );
            usuariosDao.create( usuario );
            System.out.println("[FIN]Creando usuario administrador");
        }
        else
        {
           System.out.println("Usuario Adminisatrador encontrado en BBDD. No se hace nada.");
        }
        
    }
}
