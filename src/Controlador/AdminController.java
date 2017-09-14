/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Clases.Acceso;
import Clases.Aplicacion;
import Clases.Reporte;
import Clases.Usuario;
import Modelo.AdminModel;
import java.util.ArrayList;

/**
 *
 * @author bmontoya
 */
public class AdminController extends BaseController {
    
    AdminModel modelo = new AdminModel();
    
    /**
     * Obtiene la lista de usuario del dominio que tendran acceso a los reportes qlikview
     * @return ArrayList
     */
    public ArrayList<Usuario> listaUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        try {
            lista = modelo.listaUsuario();
        }
        catch(Exception ex) {
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
            lista = modelo.listaAplicacion();
        }
        catch(Exception ex) {
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
            lista = modelo.listaReporteActivo(idaplicacion);
        }
        catch(Exception ex) {
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
            lista = modelo.listaReporte(idaplicacion);
        }
        catch(Exception ex) {
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
            boolean result = modelo.registrarUsuario(idusuario, usuario, estado);
            
            return result;
        }
        catch(Exception ex) {
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
            boolean result = modelo.registrarAplicacion(idaplicacion, aplicacion, archivo, estado);
            
            return result;
        }
        catch(Exception ex) {
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
            boolean result = modelo.registrarReporte(idreporte, idaplicacion, reporte, boton, estado);
            
            return result;
        }
        catch(Exception ex) {
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
            boolean result = modelo.registrarAccesoQlikview(idusuario, idreportes);
            
            return result;
        }
        catch(Exception ex) {
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
            boolean result = modelo.eliminarAccesoQlikview(idusuario, idaplicacion);
            
            return result;
        }
        catch(Exception ex) {
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
            lista = modelo.listaAccesoQlikview(idusuario);
        }
        catch(Exception ex) {
            this.setError(ex.getMessage());
        }
        
        return lista;
    }
    
}
