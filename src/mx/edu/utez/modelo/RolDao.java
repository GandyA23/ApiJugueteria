package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_QUERY = "SELECT * FROM rol WHERE id = ?";
    private final String SQL_QUERY_ALL = "SELECT * FROM rol";

    public RolBean query(int id){
        RolBean rol = null ;

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next())
                rol = new RolBean(rs.getInt("id"), rs.getString("nombre"));
        }catch (Exception e){
            System.out.println("Error en RolDao().queryAll(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en RolDao().query(int id)");
            }
        }

        return rol ;
    }

    public List<RolBean> queryAll(){
        List<RolBean> roles = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                roles.add(new RolBean(rs.getInt("id"), rs.getString("nombre")));
        }catch (Exception e){
            System.out.println("Error en RolDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en RolDao().queryAll()");
            }
        }

        return roles;
    }

}
