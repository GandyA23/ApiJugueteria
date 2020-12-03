package mx.edu.utez.modelo;

public class ClienteBean {
    private PersonaBean persona ;
    private double saldo ;

    public ClienteBean() {
    }

    public ClienteBean(PersonaBean persona, double saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public PersonaBean getPersona() {
        return persona;
    }

    public void setPersona(PersonaBean persona) {
        this.persona = persona;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
