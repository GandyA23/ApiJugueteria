package mx.edu.utez.modelo;

import mx.edu.utez.servicio.general.AES;
import mx.edu.utez.servicio.general.ConexionMySQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao extends ConexionMySQL {

    private final AES aes = new AES();

    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_QUERY_ID = "SELECT `id`, `nombre`, `apellido1`, `apellido2`, `direccion`, `fechaNacimiento`, `email`, " + aes.decrypt("password") + " AS `pass`, `telefono`, `status`, `idRol` FROM persona WHERE id = ? ";
    private final String SQL_QUERY_EMAIL = "SELECT `id`, `nombre`, `apellido1`, `apellido2`, `direccion`, `fechaNacimiento`, `email`, " + aes.decrypt("password") + " AS `pass`, `telefono`, `status`, `idRol` FROM persona WHERE email = ? ";
    private final String SQL_QUERY_ALL = "SELECT `id`, `nombre`, `apellido1`, `apellido2`, `direccion`, `fechaNacimiento`, `email`, " + aes.decrypt("password") + " AS `pass`, `telefono`, `status`, `idRol` FROM persona";;
    private final String SQL_ADD = "INSERT INTO persona VALUES(null, ?, ?, ?, ?, ?, ?, " + aes.encrypt() + ", ?, 1, ?) ";
    private final String SQL_DELETE_ID = "UPDATE persona SET status = 0 WHERE id = ? ";
    private final String SQL_DELETE_EMAIL = "UPDATE persona SET status = 0 WHERE email = ? ";
    private final String SQL_UPDATE = "UPDATE persona SET `nombre` = ?, `apellido1` = ?, `apellido2` = ?, `direccion` = ?, `fechaNacimiento` = ?, `email` = ?, `telefono` = ?, `idRol` = ? WHERE id = ? ";
    private final String SQL_UPDATE_PASSWORD = "UPDATE persona SET `password` = " + aes.encrypt() + " WHERE id = ?";

    public PersonaBean query(int id){
        PersonaBean persona = null ;
        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ID);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if(rs.next()){
                persona = new PersonaBean(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("direccion"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("telefono"),
                        rs.getBoolean("status"),
                        new RolBean( rs.getInt("idRol") )
                );
            }

        }catch (Exception e){
            System.out.println("Error en PersonaDao().query(int id)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().query(int id)");
            }
        }

        return persona ;
    }

    public PersonaBean query(String email){
        PersonaBean persona = null ;
        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_EMAIL);
            pstm.setString(1, email);

            rs = pstm.executeQuery();
            if(rs.next()){
                persona = new PersonaBean(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("direccion"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("telefono"),
                        rs.getBoolean("status"),
                        new RolBean( rs.getInt("idRol") )
                );
            }

        }catch (Exception e){
            System.out.println("Error en PersonaDao().query(String email)");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().query(String email)");
            }
        }

        return persona ;
    }

    public List<PersonaBean> queryAll(){
        List<PersonaBean> personas = new ArrayList<>();

        try{
            pstm = getConexion().prepareStatement(SQL_QUERY_ALL);
            rs = pstm.executeQuery();

            while(rs.next())
                personas.add(
                        new PersonaBean(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getString("direccion"),
                                rs.getString("fechaNacimiento"),
                                rs.getString("email"),
                                rs.getString("pass"),
                                rs.getString("telefono"),
                                rs.getBoolean("status"),
                                new RolBean( rs.getInt("idRol") )
                        )
                );
        }catch (Exception e){
            System.out.println("Error en PersonaDao().queryAll()");
            System.out.println(e);
        }finally {
            try{
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().queryAll()");
            }
        }

        return personas;
    }

    public int add(PersonaBean bean){

        int id = 0 ;

        try{
            //El segundo parametro es para que pueda retornar el id una vez que lo inserte
            pstm = getConexion().prepareStatement(SQL_ADD, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getApellido1());
            pstm.setString(3, bean.getApellido2());
            pstm.setString(4, bean.getDireccion());
            pstm.setString(5, bean.getFechaNacimiento());
            pstm.setString(6, bean.getEmail());
            pstm.setString(7, bean.getPassword());
            pstm.setString(8, bean.getTelefono());
            pstm.setInt(9, bean.getRol().getId());

            id = pstm.executeUpdate() ;

            rs = pstm.getGeneratedKeys();

            if (rs.next())
                id = rs.getInt(1) ;

        }catch (Exception e){
            System.out.println("Error en PersonaDao().add(PersonaBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().add(PersonaBean bean)");
            }
        }

        return id;
    }

    public boolean delete(int id){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_DELETE_ID);
            pstm.setInt(1, id);
            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en PersonaDao().delete(int id)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().delete(int id)");
            }
        }

        return flag;
    }

    public boolean delete(String email){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_DELETE_EMAIL);
            pstm.setString(1, email);

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en PersonaDao().delete(String email)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().delete(iString email)");
            }
        }

        return flag;
    }

    public boolean update(PersonaBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE);

            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getApellido1());
            pstm.setString(3, bean.getApellido2());
            pstm.setString(4, bean.getDireccion());
            pstm.setString(5, bean.getFechaNacimiento());
            pstm.setString(6, bean.getEmail());
            pstm.setString(7, bean.getTelefono());
            pstm.setInt(8, bean.getRol().getId());
            pstm.setInt(9, bean.getId());

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en PersonaDao().update(Persona bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().update(Persona bean)");
            }
        }

        return flag;
    }

    public boolean updatePassword(PersonaBean bean){
        boolean flag = false ;

        try{
            pstm = getConexion().prepareStatement(SQL_UPDATE_PASSWORD);

            pstm.setString(1, bean.getPassword());
            pstm.setInt(2, bean.getId());

            flag = pstm.executeUpdate() == 1;

        }catch (Exception e){
            System.out.println("Error en PersonaDao().updatePassword(PersonaBean bean)");
            System.out.println(e);
        }finally {
            try{
                pstm.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexiones de DB en PersonaDao().updatePassword(PersonaBean bean)");
            }
        }

        return flag;
    }
}
