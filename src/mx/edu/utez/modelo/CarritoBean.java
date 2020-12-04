package mx.edu.utez.modelo;

public class CarritoBean {
    private int id ;
    private PersonaBean persona ;
    private String fecha ;
    private boolean status ;

    public CarritoBean() {
    }

    public CarritoBean(PersonaBean persona, String fecha, boolean status) {
        this.persona = persona;
        this.fecha = fecha;
        this.status = status;
    }

    public CarritoBean(int id, PersonaBean persona, String fecha, boolean status) {
        this.id = id;
        this.persona = persona;
        this.fecha = fecha;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonaBean getPersona() {
        return persona;
    }

    public void setPersona(PersonaBean persona) {
        this.persona = persona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
