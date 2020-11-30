package mx.edu.utez.servicio.modelo;

public class RolBean {
    private int id ;
    private String nombre ;

    public RolBean() {
    }

    public RolBean(String nombre) {
        this.nombre = nombre;
    }

    public RolBean(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "RolBean{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
