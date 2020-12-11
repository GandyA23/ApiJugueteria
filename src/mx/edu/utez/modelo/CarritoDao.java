package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarritoDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_ADD = "INSERT INTO carrito VALUES (null, ?, null, 1)";
    private final String SQL_BUY = "UPDATE carrito SET status = 0, fecha = NOW() WHERE id = ?";
    private final String SQL_HISTORY = "SELECT * FROM carrito WHERE idPersona = ? AND status = 0";
    private final String SQL_QUERY_CARRITO_ACTIVO = "SELECT * FROM carrito WHERE idPersona = ? AND status = 1";

    public boolean add(int idPersona){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_ADD);
            pstm.setInt(1, idPersona);
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en CarritoDao().add(CarritoBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CarritoDao().add(CarritoBean bean)");
            }
        }

        return flag;
    }

    public CarritoBean queryActivo(int idPersona){
        CarritoBean bean = new CarritoBean();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_CARRITO_ACTIVO);
            pstm.setInt(1, idPersona);

            rs = pstm.executeQuery();

            while(rs.next())
                bean = new CarritoBean(
                    rs.getInt("id"),
                    new PersonaBean(idPersona),
                    rs.getString("fecha"),
                    rs.getBoolean("status")
                );
        }catch (Exception e){
            System.out.println("Error en CarritoDao().queryCarritoActivo(int idPersona)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CarritoDao().queryCarritoActivo(int idPersona)");
            }
        }

        return bean;
    }
    
    public boolean buy(int id){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_BUY);
            pstm.setInt(1, id);
            flag = pstm.executeUpdate() == 1;
        }catch (Exception e){
            System.out.println("Error en CarritoDao().buy(int id)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CarritoDao().buy(int id)");
            }
        }

        return flag;
    }

    public List<CarritoBean> queryAll(int idPersona){
        List<CarritoBean> carritos = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_HISTORY);
            pstm.setInt(1, idPersona);

            rs = pstm.executeQuery();

            while(rs.next())
                carritos.add(new CarritoBean(
                    rs.getInt("id"),
                    new PersonaBean(idPersona),
                    rs.getString("fecha"),
                    rs.getBoolean("status")
                ));
        }catch (Exception e){
            System.out.println("Error en CarritoDao().queryAll(int idPersona)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CarritoDao().queryAll(int idPersona)");
            }
        }

        return carritos;
    }
}
