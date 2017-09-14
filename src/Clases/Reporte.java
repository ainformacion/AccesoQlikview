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
public class Reporte {

    /**
     * @return the idreporte
     */
    public String getIdreporte() {
        return idreporte;
    }

    /**
     * @param idreporte the idreporte to set
     */
    public void setIdreporte(String idreporte) {
        this.idreporte = idreporte;
    }

    /**
     * @return the reporte
     */
    public String getReporte() {
        return reporte;
    }

    /**
     * @param reporte the reporte to set
     */
    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    /**
     * @return the botonreporte
     */
    public String getBotonreporte() {
        return botonreporte;
    }

    /**
     * @param botonreporte the botonreporte to set
     */
    public void setBotonreporte(String botonreporte) {
        this.botonreporte = botonreporte;
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
    
    private String idreporte;
    private String reporte;
    private String botonreporte;
    private String estado;
    
}
