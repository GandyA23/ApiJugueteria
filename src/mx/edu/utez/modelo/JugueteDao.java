package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JugueteDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_QUERY = "SELECT * FROM juguete WHERE id = ? ";
    private final String SQL_QUERY_ALL = "SELECT * FROM juguete";;
    private final String SQL_ADD = "INSERT INTO juguete VALUES(null, ?, ?, ?, ?, 1) ";
    private final String SQL_UPDATE = "UPDATE juguete SET nombre = ?, precio = ?, marca = ?, idCategoria = ? WHERE id = ? ";
    private final String SQL_DELETE = "UPDATE juguete SET status = 0 WHERE id = ?";

    public JugueteBean query(int id){
        JugueteBean juguete = null ;
        try{
            pstm = getConexion().prepareStatement(SQL_QUERY);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next())
                juguete = new JugueteBean(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getString("marca"),
                    new CategoriaBean( rs.getInt("idCategoria") ),
                    rs.getBoolean("status")
                );

        }catch (Exception e){
            System.out.println("Error en JugueteDao().query(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en JugueteDao().query(int id)");
            }
        }

        return juguete ;
    }

    public List<JugueteBean> queryAll(){
        List<JugueteBean> juguetes = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                juguetes.add(
                    new JugueteBean(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("marca"),
                        new CategoriaBean( rs.getInt("idCategoria") ),
                        rs.getBoolean("status")
                    )
                );
        }catch (Exception e){
            System.out.println("Error en JugueteDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en JugueteDao().queryAll()");
            }
        }

        return juguetes;
    }

    public boolean add(JugueteBean bean){

        boolean flag = false ;
        
        try{
            pstm = getConexion().prepareStatement(SQL_ADD, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, bean.getNombre());
            pstm.setDouble(2, bean.getPrecio());
            pstm.setString(3, bean.getMarca());
            pstm.setInt(4, bean.getCategoria().getId());
            
            flag = pstm.executeUpdate() == 1 ;
            
        }catch (Exception e){
            System.out.println("Error en JugueteDao().add(JugueteBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en JugueteDao().add(JugueteBean bean)");
            }
        }

        return flag;
    }

    public boolean delete(int id){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_DELETE);
            pstm.setInt(1, id);
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en JugueteDao().delete(int id)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en JugueteDao().delete(int id)");
            }
        }

        return flag;
    }

    public boolean update(JugueteBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE);

            pstm.setString(1, bean.getNombre());
            pstm.setDouble(2, bean.getPrecio());
            pstm.setString(3, bean.getMarca());
            pstm.setInt(4, bean.getCategoria().getId());
            pstm.setInt(5, bean.getId());

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en JugueteDao().update(Juguete bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en JugueteDao().update(Juguete bean)");
            }
        }

        return flag;
    }

}
