package mx.edu.utez.modelo;

public class EmpleadoBean {
    private PersonaBean persona ;
    private String curp ;

    public EmpleadoBean() {
    }

    public EmpleadoBean(PersonaBean persona, String curp) {
        this.persona = persona;
        this.curp = curp;
    }

    public PersonaBean getPersona() {
        return persona;
    }

    public void setPersona(PersonaBean persona) {
        this.persona = persona;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
