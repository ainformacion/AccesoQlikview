/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author bmontoya
 */
public class Aplicacion {

    /**
     * @return the idaplicacion
     */
    public String getIdaplicacion() {
        return idaplicacion;
    }

    /**
     * @param idaplicacion the idaplicacion to set
     */
    public void setIdaplicacion(String idaplicacion) {
        this.idaplicacion = idaplicacion;
    }

    /**
     * @return the aplicacion
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * @param aplicacion the aplicacion to set
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private String idaplicacion;
    private String aplicacion;
    private String archivo;
    private String estado;
    
}
