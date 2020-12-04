package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao extends ConexionMySQL {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final PersonaDao personaDao = new PersonaDao();

    private final String SQL_QUERY = "SELECT * FROM empleado WHERE idPersona = ? ";
    private final String SQL_QUERY_ALL = "SELECT * FROM empleado";;
    private final String SQL_ADD = "INSERT INTO empleado VALUES(?, ?) ";
    private final String SQL_UPDATE = "UPDATE empleado SET curp = ? WHERE idPersona = ? ";

    public EmpleadoBean query(int id){
        EmpleadoBean empleado = null ;
        try{
            pstm = getConexion().prepareStatement(SQL_QUERY);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next()){
                empleado = new EmpleadoBean(
                        personaDao.query(id),
                        rs.getString("curp")
                );
            }

        }catch (Exception e){
            System.out.println("Error en EmpleadoDao().query(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en EmpleadoDao().query(int id)");
            }
        }

        return empleado ;
    }

    public List<EmpleadoBean> queryAll(){
        List<EmpleadoBean> empleados = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                empleados.add(
                        new EmpleadoBean(
                                personaDao.query( rs.getInt("idPersona") ),
                                rs.getString("curp")
                        )
                );
        }catch (Exception e){
            System.out.println("Error en EmpleadoDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en EmpleadoDao().queryAll()");
            }
        }

        return empleados;
    }

    public boolean add(EmpleadoBean bean){
        boolean flag = false ;
        int id = 0 ;
        try{
            //Primero ingreso la persona
            id = personaDao.add(bean.getPersona());

            //Un número diferente a 0 quiere decir que si se ingreso a la persona
            if( id != 0 ){
                pstm = getConexion().prepareStatement(SQL_ADD);
                pstm.setInt(1, id);
                pstm.setString(2, bean.getCurp());

                flag = pstm.executeUpdate() == 1 ;
            }

        }catch (Exception e){

            if(id != 0){
                personaDao.delete(id);
                System.out.println("Se ingreso una persona, así que se borrara porque hubo un error");
            }

            System.out.println("Error en EmpleadoDao().add(EmpleadoBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
            }
            System.out.println("Error al cerrar conexiones de DB en EmpleadoDao().add(EmpleadoBean bean)");
        }

        if(!flag && id != 0){
            personaDao.delete(id);
            System.out.println("Se ingreso una persona, así que se borrara porque hubo un error");
        }

        return flag;
    }

    public boolean delete(int id){
        boolean flag = false ;

        try{
            flag = personaDao.delete(id);

        }catch (Exception e){
            System.out.println("Error en EmpleadoDao().delete(int id)");
            System.out.println(e);
        }

        return flag;
    }

    public boolean update(EmpleadoBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE);
            pstm.setString(1, bean.getCurp());
            pstm.setInt(2, bean.getPersona().getId());

            flag = pstm.executeUpdate() == 1 ;

        }catch (Exception e){
            System.out.println("Error en EmpleadoDao().update(Empleado bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en EmpleadoDao().update(Empleado bean)");
            }
        }

        return flag;
    }
}
