import java.util.ArrayList;

public class Usuario
{
    private String usuario;
    private String clave;
    private String nombreCompleto;
    private ArrayList<SolicitudAmistad> solicitudes;

    public ArrayList<SolicitudAmistad> getSolicitudes() {
        return solicitudes;
    }


    public void setSolicitudes(ArrayList<SolicitudAmistad> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Usuario(String usuario, String clave, String nombreCompleto) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
