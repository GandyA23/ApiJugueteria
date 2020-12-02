package mx.edu.utez.modelo;

public class CategoriaBean {
    private int id ;
    private String nombre ;
    private boolean status ;

    public CategoriaBean() {
    }

    public CategoriaBean(int id) {
        this.id = id;
    }

    public CategoriaBean(int id, String nombre, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
