package mx.edu.utez.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDao {
    private PreparedStatement pstm ;
    private ResultSet rs ;

    private String SQL_ADD = "INSERT INTO cliente VALUES (?, ?)";


}
