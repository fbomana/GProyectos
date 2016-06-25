/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.util;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Clase para ejecutar sentencias arbitrarias de SQL. Util para inicializaciones
 * 
 * @author aitkiar
 */
public class JDBCExecutor
{
    @Resource(name="java:jdbc/proyectos")
    DataSource ds;
    
    public JDBCExecutor() throws Exception
    {
        if ( ds == null )
        {
            InitialContext ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("jdbc/proyectos");
        }
    }


    
    /**
     * Ejecuta una instrucción de tipo insert o update.
     * 
     * @param sql
     * @throws Exception 
     */
    public void executeUpadate( String sql ) throws Exception
    {
        Connection conexion = null;
        try
        {
            if ( ds != null )
            {
                conexion = ds.getConnection();
                executeUpdate( sql, conexion );
            }
            else
            {
                throw new Exception ("No se encontro el datasource jdbc/proyectos en el contexto jndi");
            }
        }
        finally
        {
            if ( conexion != null  )
            {
                conexion.close();
            }
        }
    }
    
    private void executeUpdate( String sql, Connection conexion ) throws Exception
    {
        PreparedStatement ps = null;
        try
        {
            ps = conexion.prepareStatement(sql);
            ps.executeUpdate();
        }
        finally
        {
            if ( ps != null )
            {
                ps.close();
            }
        }
    }
}
