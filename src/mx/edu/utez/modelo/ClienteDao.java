package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final PersonaDao personaDao = new PersonaDao();

    private final String SQL_QUERY = "SELECT * FROM cliente WHERE idPersona = ? ";
    private final String SQL_QUERY_ALL = "SELECT * FROM cliente";;
    private final String SQL_ADD = "INSERT INTO cliente VALUES(?, ?) ";
    private final String SQL_UPDATE = "UPDATE cliente SET saldo = ? WHERE idPersona = ? ";

    public ClienteBean query(int id){
        ClienteBean cliente = null ;
        try{
            pstm = getConexion().prepareStatement(SQL_QUERY);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next()){
                cliente = new ClienteBean(
                    personaDao.query(id),
                    rs.getDouble("saldo")
                );
            }

        }catch (Exception e){
            System.out.println("Error en ClienteDao().query(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en ClienteDao().query(int id)");
            }
        }

        return cliente ;
    }

    public List<ClienteBean> queryAll(){
        List<ClienteBean> clientes = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                clientes.add(
                    new ClienteBean(
                        personaDao.query( rs.getInt("idPersona") ),
                        rs.getDouble("saldo")
                    )
                );
        }catch (Exception e){
            System.out.println("Error en ClienteDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en CLienteDao().queryAll()");
            }
        }

        return clientes;
    }

    public boolean add(ClienteBean bean){
        boolean flag = false ;
        int id = 0 ;
        try{
            //Primero ingreso la persona
            id = personaDao.add(bean.getPersona());

            //Un número diferente a 0 quiere decir que si se ingreso a la persona
            if( id != 0 ){
                pstm = getConexion().prepareStatement(SQL_ADD);
                pstm.setInt(1, id);
                pstm.setDouble(2, bean.getSaldo());

                flag = pstm.executeUpdate() == 1 ;
            }

        }catch (Exception e){

            if(id != 0){
                personaDao.delete(id);
                System.out.println("Se ingreso una persona, así que se borrara porque hubo un error");
            }

            System.out.println("Error en ClienteDao().add(ClienteBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
            }
            System.out.println("Error al cerrar conexiones de DB en ClienteDao().add(ClienteBean bean)");
        }

        return flag;
    }

    public boolean delete(int id){
        boolean flag = false ;

        try{
            flag = personaDao.delete(id);

        }catch (Exception e){
            System.out.println("Error en ClienteDao().delete(int id)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en ClienteDao().delete(int id)");
            }
        }

        return flag;
    }

    /**
     * En teoría, este metodo solo lo puede llamar un admin para cambiar
     * el saldo del cliente
     * */
    public boolean update(ClienteBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE);
            pstm.setDouble(1, bean.getSaldo());
            pstm.setInt(2, bean.getPersona().getId());

            flag = pstm.executeUpdate() == 1 ;

        }catch (Exception e){
            System.out.println("Error en ClienteDao().update(Cliente bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en ClienteDao().update(Cliente bean)");
            }
        }

        return flag;
    }
}
