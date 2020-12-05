package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Carrito_JugueteDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_ADD = "INSERT INTO carrito_juguete VALUES (?, ?, ?, ?)";
    private final String SQL_UPDATE_CANTIDAD = "UPDATE carrito_juguete SET cantidad = ? WHERE idCarrito = ? AND idJuguete = ?";
    private final String SQL_HISTORY = "SELECT * FROM carrito_juguete WHERE idCarrito = ? ";
    private final String SQL_DELETE = "DELETE FROM carrito_juguete WHERE idCarrito = ? AND idJuguete = ?";

    public boolean add(Carrito_JugueteBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_ADD);
            pstm.setInt(1, bean.getCarrito().getId());
            pstm.setInt(2, bean.getJuguete().getId());
            pstm.setDouble(3, bean.getPrecio());
            pstm.setInt(4, bean.getCantidad());
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en Carrito_JugueteDao().add(Carrito_JugueteBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en Carrito_JugueteDao().add(Carrito_JugueteBean bean)");
            }
        }

        return flag;
    }

    public boolean updateCantidad(Carrito_JugueteBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE_CANTIDAD);
            pstm.setInt(1, bean.getCantidad());
            pstm.setInt(2, bean.getCarrito().getId());
            pstm.setInt(3, bean.getJuguete().getId());

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en Carrito_JugueteDao().updateCantidad(Carrito_JugueteBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en Carrito_JugueteDao().updateCantidad(Carrito_JugueteBean bean)");
            }
        }

        return flag;
    }

    public List<Carrito_JugueteBean> history(int idCarrito){
        List<Carrito_JugueteBean> carrito_juguetes = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_HISTORY);
            pstm.setInt(1, idCarrito);

            rs = pstm.executeQuery();

            while(rs.next())
                carrito_juguetes.add(new Carrito_JugueteBean(
                    new CarritoBean(rs.getInt("idCarrito")),
                    new JugueteBean(rs.getInt("idJuguete")),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad")
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

        return carrito_juguetes;
    }

    public boolean delete(Carrito_JugueteBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_DELETE);
            pstm.setInt(1, bean.getCarrito().getId());
            pstm.setInt(2, bean.getJuguete().getId());

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en Carrito_JugueteDao().delete(Carrito_JugueteBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en Carrito_JugueteDao().delete(Carrito_JugueteBean bean)");
            }
        }

        return flag;
    }
}
