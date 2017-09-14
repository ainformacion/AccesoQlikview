/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author bmontoya
 */
public class UsuarioModel extends BaseModel {
    
    private final connectdb.OracleConnection connect = new connectdb.OracleConnection();
    private String sql;
    
    /**
     * Valida el acceso del administrador
     * @param usuario
     * @param password
     * @return boolean
     */
    public boolean accesoUsuario(String usuario, String password) {
        try {
            if(connect.Conectar(this.userDB, this.passDB)) {
                sql = "{ ? = call QLIKVIEW.PK_QLIKVIEW.FN_ACCESO_USUARIO(?,?,?,?) }";
                CallableStatement stm = connect.getConexion().prepareCall(sql);
                stm.registerOutParameter(1, Types.INTEGER);
                stm.setString(2, usuario.toUpperCase());
                stm.setString(3, password.toUpperCase());
                stm.registerOutParameter(4, Types.INTEGER);
                stm.registerOutParameter(5, Types.VARCHAR);
                stm.executeUpdate();
                
                int out = stm.getInt(1);
                this.setCodper(stm.getInt(4));
                this.setNomper(stm.getString(5));
                
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
    
}
