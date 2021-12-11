package co.gov.mintic.misiontic.ciclo4.usa.modelo;

public class Usuario {

    private String cedula;
    private String nombre;
    private String email;
    private String clave;

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String email, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
