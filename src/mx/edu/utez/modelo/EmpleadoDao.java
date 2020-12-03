package mx.edu.utez.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpleadoDao {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private final String SQL_QUERY = "SELECT * FROM empleado WHERE id = ?";
    private final String SQL_QUERY_ALL = "SELECT * FROM empleado";
    private final String SQL_ADD = "INSERT INTO empleado VALUES (?, ?)";
    private final String SQL_UPDATE = "UPDATE categoria SET nombre=? WHERE id = ? ";


}
