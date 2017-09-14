/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.UsuarioModel;

/**
 *
 * @author bmontoya
 */
public class UsuarioController extends BaseController {
    
    UsuarioModel modelo = new UsuarioModel();
    
    /**
     * Valida el acceso del administrador
     * @param usuario
     * @param password
     * @return boolean
     */
    public boolean accesoUsuario(String usuario, String password) {
        try {
            boolean result = modelo.accesoUsuario(usuario, password);
            if(!result) {
                this.setError(modelo.getError());
            }
            else {
                this.setCodper(modelo.getCodper());
                this.setNomper(modelo.getNomper());
            }
            
            return result;
        }
        catch(Exception ex) {
            this.setError(ex.getMessage());
        }
        
        return false;
    }
    
}
