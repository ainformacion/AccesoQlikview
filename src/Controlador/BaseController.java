/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author bmontoya
 */
public class BaseController {

    /**
     * @return the codper
     */
    public int getCodper() {
        return codper;
    }

    /**
     * @param codper the codper to set
     */
    public void setCodper(int codper) {
        this.codper = codper;
    }

    /**
     * @return the nomper
     */
    public String getNomper() {
        return nomper;
    }

    /**
     * @param nomper the nomper to set
     */
    public void setNomper(String nomper) {
        this.nomper = nomper;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
    
    private String mensaje;
    private String error;
    private int codper;
    private String nomper;
    
}
