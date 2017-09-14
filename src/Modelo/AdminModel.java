/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Clases.Acceso;
import Clases.Aplicacion;
import Clases.Reporte;
import Clases.Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author bmontoya
 */
public class AdminModel extends BaseModel {
    
    private final connectdb.OracleConnection connect = new connectdb.OracleConnection();
    private String sql;
    
    /**
     * Obtiene la lista de usuario del dominio que tendran acceso a los reportes qlikview
     * @return ArrayList
     */
    public ArrayList<Usuario> listaUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "SELECT QLV_IDUSUARIO_N_PK, QLV_USUARIODOMINIO_V, PARA_ESTADO_N_COD FROM QLV_USUARIO";
                
                ResultSet rs = connect.ejecutarConsulta(sql);
                while(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdusuario(rs.getString("QLV_IDUSUARIO_N_PK"));
                    usuario.setUsuario(rs.getString("QLV_USUARIODOMINIO_V"));
                    usuario.setEstado(rs.getString("PARA_ESTADO_N_COD"));
                    lista.add(usuario);
                }
                
                connect.Desconectar();
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
    /**
     * Obtiene la lista de aplicaciones qlikview creadas
     * @return ArrayList
     */
    public ArrayList<Aplicacion> listaAplicacion() {
        ArrayList<Aplicacion> lista = new ArrayList<Aplicacion>();
        
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "SELECT QLV_IDAPLICACION_N_PK, QLV_DESCRIPCION_V, QLV_ARCHIVO_V, PARA_ESTADO_N_COD FROM QLV_APLICACION";
                
                ResultSet rs = connect.ejecutarConsulta(sql);
                while(rs.next()){
                    Aplicacion aplicacion = new Aplicacion();
                    aplicacion.setIdaplicacion(rs.getString("QLV_IDAPLICACION_N_PK"));
                    aplicacion.setAplicacion(rs.getString("QLV_DESCRIPCION_V"));
                    aplicacion.setArchivo(rs.getString("QLV_ARCHIVO_V"));
                    aplicacion.setEstado(rs.getString("PARA_ESTADO_N_COD"));
                    lista.add(aplicacion);
                }
                
                connect.Desconectar();
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
    /**
     * Obtiene la lista de reportes activos que tiene la aplicacion qlikview
     * @param idaplicacion
     * @return ArrayList
     */
    public ArrayList<Reporte> listaReporteActivo(String idaplicacion) {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "SELECT QLV_IDREPORTE_N_PK, QLV_DESCRIPCION_V, QLV_BOTONREPORTE_V, PARA_ESTADO_N_COD FROM QLV_REPORTE WHERE QLV_IDAPLICACION_N_PK = " + idaplicacion + " AND PARA_ESTADO_N_COD = 1";
                
                ResultSet rs = connect.ejecutarConsulta(sql);
                while(rs.next()){
                    Reporte reporte = new Reporte();
                    reporte.setIdreporte(rs.getString("QLV_IDREPORTE_N_PK"));
                    reporte.setReporte(rs.getString("QLV_DESCRIPCION_V"));
                    reporte.setBotonreporte(rs.getString("QLV_BOTONREPORTE_V"));
                    reporte.setEstado(rs.getString("PARA_ESTADO_N_COD"));
                    lista.add(reporte);
                }
                
                connect.Desconectar();
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
    /**
     * Obtiene la lista de reportes que tiene la aplicacion qlikview
     * @param idaplicacion
     * @return ArrayList
     */
    public ArrayList<Reporte> listaReporte(String idaplicacion) {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "SELECT QLV_IDREPORTE_N_PK, QLV_DESCRIPCION_V, QLV_BOTONREPORTE_V, PARA_ESTADO_N_COD FROM QLV_REPORTE WHERE QLV_IDAPLICACION_N_PK = " + idaplicacion;
                
                ResultSet rs = connect.ejecutarConsulta(sql);
                while(rs.next()){
                    Reporte reporte = new Reporte();
                    reporte.setIdreporte(rs.getString("QLV_IDREPORTE_N_PK"));
                    reporte.setReporte(rs.getString("QLV_DESCRIPCION_V"));
                    reporte.setBotonreporte(rs.getString("QLV_BOTONREPORTE_V"));
                    reporte.setEstado(rs.getString("PARA_ESTADO_N_COD"));
                    lista.add(reporte);
                }
                
                connect.Desconectar();
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
    /**
     * Realiza el registro de datos del usuario de dominio
     * @param idusuario
     * @param usuario
     * @param estado
     * @return boolean
     */
    public boolean registrarUsuario(String idusuario, String usuario, String estado) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_REGISTRA_USUARIO(?,?,?) }";
                CallableStatement stm = connect.getConexion().prepareCall(sql);
                stm.registerOutParameter(1, Types.INTEGER);
                stm.setInt(2, Integer.valueOf(idusuario));
                stm.setString(3, usuario.toUpperCase());
                stm.setInt(4, Integer.valueOf(estado));
                stm.executeUpdate();
                
                int out = stm.getInt(1);
                
                connect.Desconectar();
                
                if (out > 0) {
                    return true;
                }
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * Realiza el registro de los datos de la aplicación qlikview
     * @param idaplicacion
     * @param aplicacion
     * @param archivo
     * @param estado
     * @return boolean
     */
    public boolean registrarAplicacion(String idaplicacion, String aplicacion, String archivo, String estado) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_REGISTRA_APLICACION(?,?,?,?) }";
                CallableStatement stm = connect.getConexion().prepareCall(sql);
                stm.registerOutParameter(1, Types.INTEGER);
                stm.setInt(2, Integer.valueOf(idaplicacion));
                stm.setString(3, aplicacion.toUpperCase());
                stm.setString(4, archivo.toUpperCase());
                stm.setInt(5, Integer.valueOf(estado));
                stm.executeUpdate();
                
                int out = stm.getInt(1);
                
                connect.Desconectar();
                
                if (out > 0) {
                    return true;
                }
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * Realiza el registro de los datos del reporte de la aplicación qlikview
     * @param idreporte
     * @param idaplicacion
     * @param reporte
     * @param boton
     * @param estado
     * @return boolean
     */
    public boolean registrarReporte(String idreporte, String idaplicacion, String reporte, String boton, String estado) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_REGISTRA_REPORTE(?,?,?,?,?) }";
                CallableStatement stm = connect.getConexion().prepareCall(sql);
                stm.registerOutParameter(1, Types.INTEGER);
                stm.setInt(2, Integer.valueOf(idreporte));
                stm.setInt(3, Integer.valueOf(idaplicacion));
                stm.setString(4, reporte.toUpperCase());
                stm.setString(5, boton.toUpperCase());
                stm.setInt(6, Integer.valueOf(estado));
                stm.executeUpdate();
                
                int out = stm.getInt(1);
                
                connect.Desconectar();
                
                if (out > 0) {
                    return true;
                }
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * Realiza el registro de los accesos del usuario del dominio
     * @param idusuario
     * @param idreportes
     * @return 
     */
    public boolean registrarAccesoQlikview(String idusuario, String[] idreportes) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                int out = 0;
                for (String idreporte : idreportes) {
                    sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_REGISTRA_ACCESO_QLIKVIEW(?,?,?) }";
                    CallableStatement stm = connect.getConexion().prepareCall(sql);
                    stm.registerOutParameter(1, Types.INTEGER);
                    stm.setInt(2, Integer.valueOf(idusuario));
                    stm.setInt(3, Integer.valueOf(idreporte));
                    stm.setInt(4, 1);
                    stm.executeUpdate();
                    
                    out = stm.getInt(1);
                }
                
                connect.Desconectar();
                
                if (out > 0) {
                    return true;
                }
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * Realiza la eliminación de los accesos del usuario del dominio
     * @param idusuario
     * @param idaplicacion
     * @return boolean
     */
    public boolean eliminarAccesoQlikview(String idusuario, String idaplicacion) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_ELIMINA_ACCESO_QLIKVIEW(?,?) }";
                CallableStatement stm = connect.getConexion().prepareCall(sql);
                stm.registerOutParameter(1, Types.INTEGER);
                stm.setInt(2, Integer.valueOf(idusuario));
                stm.setInt(3, Integer.valueOf(idaplicacion));
                stm.executeUpdate();
                
                int out = stm.getInt(1);
                
                connect.Desconectar();
                
                if (out > 0) {
                    return true;
                }
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * Obtiene la lista de accesos registrados para el usuario de dominio
     * @param idusuario
     * @return ArrayList
     */
    public ArrayList<Acceso> listaAccesoQlikview(String idusuario) {
        ArrayList<Acceso> lista = new ArrayList<Acceso>();
        
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql =   "SELECT C.QLV_IDAPLICACION_N_PK AS IDAPLICACION, C.QLV_DESCRIPCION_V AS APLICACION, A.QLV_IDREPORTE_N_PK AS IDREPORTE, B.QLV_DESCRIPCION_V AS REPORTE, B.QLV_BOTONREPORTE_V AS BOTONQLIKVIEW " +
                        "FROM QLV_ACCESO A INNER JOIN QLV_REPORTE B ON A.QLV_IDREPORTE_N_PK = B.QLV_IDREPORTE_N_PK " +
                        "                  INNER JOIN QLV_APLICACION C ON B.QLV_IDAPLICACION_N_PK = C.QLV_IDAPLICACION_N_PK " +
                        "WHERE A.QLV_IDUSUARIO_N_PK = " + idusuario +
                        "  AND B.PARA_ESTADO_N_COD = 1 " +
                        "ORDER BY C.QLV_DESCRIPCION_V, B.QLV_DESCRIPCION_V";
                
                ResultSet rs = connect.ejecutarConsulta(sql);
                while(rs.next()){
                    Acceso acceso = new Acceso();
                    acceso.setIdaplicacion(rs.getString("IDAPLICACION"));
                    acceso.setAplicacion(rs.getString("APLICACION"));
                    acceso.setIdreporte(rs.getString("IDREPORTE"));
                    acceso.setReporte(rs.getString("REPORTE"));
                    acceso.setBoton(rs.getString("BOTONQLIKVIEW"));
                    lista.add(acceso);
                }
                
                connect.Desconectar();
            }
        }
        catch(SQLException ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
}
