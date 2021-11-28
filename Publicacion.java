
import java.time.LocalTime;

public class Publicacion
{
    public static int secuencialPublicaciones = 0;

    private int numeroPublicacion;
    private String usuario;
    private LocalTime fechaHora;
    private String texto;

    public Publicacion(String usuario, String texto) {
        this.numeroPublicacion = secuencialPublicaciones;
        Publicacion.secuencialPublicaciones++;
        this.usuario = usuario;
        fechaHora = LocalTime.now();
        this.texto = texto;
    }

   
    public int getNumeroPublicacion() {
        return numeroPublicacion;
    }

    
    public String getUsuario() {
        return usuario;
    }

    
    public String getFechaHora() {
        return this.fechaHora.toString();
    }



  
    public String getTexto() {
        return  this.texto;
    }


}
