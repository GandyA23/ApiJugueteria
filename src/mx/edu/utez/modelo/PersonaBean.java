package mx.edu.utez.modelo;

import java.util.Date;

public class PersonaBean {
    private int id ;
    private String nombre ;
    private String apellido1 ;
    private String apellido2 ;
    private String direccion ;
    private String fechaNacimiento ;
    private String email ;
    private String password ;
    private String telefono ;
    private boolean status ;
    private RolBean rol ;

    public PersonaBean() {
    }

    public PersonaBean(int id) {
        this.id = id;
    }

    public PersonaBean(String nombre, String apellido1, String apellido2, String direccion, String fechaNacimiento, String email, String password, String telefono, boolean status, RolBean rol) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.status = status;
        this.rol = rol;
    }

    public PersonaBean(int id, String nombre, String apellido1, String apellido2, String direccion, String fechaNacimiento, String email, String password, String telefono, boolean status, RolBean rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.status = status;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RolBean getRol() {
        return rol;
    }

    public void setRol(RolBean rol) {
        this.rol = rol;
    }
}
