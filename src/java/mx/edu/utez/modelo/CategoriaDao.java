package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_QUERY = "SELECT * FROM categoria WHERE id = ?";
    private final String SQL_QUERY_ALL = "SELECT * FROM categoria";
    private final String SQL_ADD = "INSERT INTO categoria VALUES (null, ?)";
    private final String SQL_DELETE = "DELETE FROM categoria WHERE id = ?";
    private final String SQL_UPDATE = "UPDATE categoria SET nombre=? WHERE id = ? ";

    public CategoriaBean query(int id){
        CategoriaBean categoria = null ;

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next())
                categoria = new CategoriaBean(rs.getInt("id"), rs.getString("nombre"));
        }catch (Exception e){
            System.out.println("Error en CategoriaDao().query(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CategoriaDao().query(int id)");
            }
        }

        return categoria ;
    }

    public List<CategoriaBean> queryAll(){
        List<CategoriaBean> categorias = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                categorias.add(new CategoriaBean(rs.getInt("id"), rs.getString("nombre")));
        }catch (Exception e){
            System.out.println("Error en CategoriaDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CategoriaDao().queryAll()");
            }
        }

        return categorias;
    }

    public boolean add(CategoriaBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_ADD);
            pstm.setString(1, bean.getNombre());
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en CategoriaDao().add(CategoriaBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CategoriaDao().add(CategoriaBean bean)");
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
            System.out.println("Error en CategoriaDao().delete(int id)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CategoriaDao().delete(int id)");
            }
        }

        return flag;
    }

    public boolean update(CategoriaBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE);
            pstm.setString(1, bean.getNombre());
            pstm.setInt(2, bean.getId());
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en CategoriaDao().update(Categoria bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CategoriaDao().update(Categoria bean)");
            }
        }

        return flag;
    }
}
