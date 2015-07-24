/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util;

import es.ait.gp.core.historico.AccionesHistorico;
import es.ait.gp.core.historico.AccionesHistoricoDAO;
import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.permisos.ConstantesPermisos;
import es.ait.gp.core.permisos.Permisos;
import es.ait.gp.core.permisos.PermisosDAO;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.core.relaciones.ConstantesRelaciones;
import es.ait.gp.core.relaciones.Relaciones;
import es.ait.gp.core.relaciones.RelacionesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 * Clase que se tiene que ejecutar la primera vez que se arranque la aplicación 
 * para inicializar los valores por defecto en la BBDD.
 * 
 * Se considera que la BBDD no está inicializada si no existe el usuario admin
 *
 * @author aitkiar
 */
@Named("PrimeraEjecución")
@javax.enterprise.context.ApplicationScoped
public class PrimeraEjecucion
{
    @EJB
    UsuariosDAO usuariosDao;
    
    @EJB
    PermisosDAO permisosDao;
    
    @EJB
    RolesDAO rolesDao;
    
    @EJB
    AccionesHistoricoDAO accionesHistoricoDao;
    
    @EJB
    RelacionesDAO relacionesDao;
    
    public void execute() throws Exception
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
            ejecutor.executeUpadate("insert into GENERATOR_TABLE values ( 'historico_proyectos', 0 )");
            
            
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
                    if ( permisosDao.findByName( campo.getName() ) == null  )
                    {
                        permisosDao.create( permiso );
                    }                            
                }
                catch( Exception e )
                {
                    e.printStackTrace();
                }
            }

            System.out.println("\tInsertando acciones de histórico");
            campos = ConstantesAccionesHistorico.class.getDeclaredFields();
            ConstantesAccionesHistorico constantesh = new ConstantesAccionesHistorico();
            for ( Field campo : campos )
            {
                
                try
                {
                    if ( accionesHistoricoDao.find( ((AccionesHistorico)campo.get( constantesh )).getAchiId() ) == null )
                    {
                        accionesHistoricoDao.create(((AccionesHistorico)campo.get( constantesh )) );
                    }
                }
                catch( Exception e )
                {
                    e.printStackTrace();
                }
            }
            
            System.out.println("\tInsertando relaciones de usuarios y tareas");
            campos = ConstantesRelaciones.class.getDeclaredFields();
            ConstantesRelaciones constantesp = new ConstantesRelaciones();
            for ( Field campo : campos )
            {
                try
                {
                    if ( relacionesDao.find( ((Relaciones)campo.get( constantesp )).getRelaId()) == null )
                    {
                        relacionesDao.create(((Relaciones)campo.get( constantesp )) );
                    }
                }
                catch( Exception e )
                {
                    e.printStackTrace();
                }
            }
            
            System.out.println("\tInsertando el role superadministrador.");
            Roles role = null;
            try
            {
                role = rolesDao.findByName("Superadministrador"); 
                if ( role == null )
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
            }
            catch ( Exception e )
            {
                e.printStackTrace();
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
