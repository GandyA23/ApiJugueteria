package mx.edu.utez.modelo;

public class Carrito_JugueteBean {
    private CarritoBean carrito ;
    private JugueteBean juguete ;
    private double precio ;
    private int cantidad ;

    public Carrito_JugueteBean() {
    }

    public Carrito_JugueteBean(CarritoBean carrito, JugueteBean juguete, double precio, int cantidad) {
        this.carrito = carrito;
        this.juguete = juguete;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public CarritoBean getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoBean carrito) {
        this.carrito = carrito;
    }

    public JugueteBean getJuguete() {
        return juguete;
    }

    public void setJuguete(JugueteBean juguete) {
        this.juguete = juguete;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
