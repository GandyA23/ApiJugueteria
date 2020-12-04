package mx.edu.utez.modelo;

public class JugueteBean {
    private int id ;
    private String nombre ;
    private double precio ;
    private String marca ;
    private CategoriaBean categoria ;
    private boolean status ;

    public JugueteBean() {
    }

    public JugueteBean(String nombre, double precio, String marca, CategoriaBean categoria, boolean status) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
        this.status = status;
    }

    public JugueteBean(int id, String nombre, double precio, String marca, CategoriaBean categoria, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public CategoriaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaBean categoria) {
        this.categoria = categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
