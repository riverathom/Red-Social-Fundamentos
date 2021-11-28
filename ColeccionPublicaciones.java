
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionPublicaciones
{
    static ArrayList<Publicacion> publicaciones;

    public ColeccionPublicaciones() {
        publicaciones = new ArrayList<>();
    }

    public boolean agregarPublicacion(Publicacion p) {
        return publicaciones.add(p);
    }

    public ArrayList<Publicacion> getPublicacionesDeUnUsuario(String usuario) {
        ArrayList<Publicacion> seleccionadas = new ArrayList<>();
        for(Publicacion p: publicaciones) {
            if(p.getUsuario().equals(usuario)) {
                seleccionadas.add(p);
            }
        }
        return seleccionadas;
    }
    public static void guardar() throws IOException {
        File archivo = new File("Publicaciones.txt");
        FileWriter escribir = new FileWriter(archivo);
        for (Publicacion publicacion: publicaciones) {
            escribir.write(publicacion.getUsuario() + "," + publicacion.getNumeroPublicacion() + "," + publicacion.getTexto()
                    + "," + publicacion.getFechaHora());
            escribir.write("\r\n");
        }
        escribir.close();
    }
    public static void cargar() {
        File file = new File("Publicaciones.txt");
        try {
            Scanner input = new Scanner(file);
            String line;
            while(input.hasNextLine()) {
                line = input.nextLine();
                String[] publica = line.split(",");

                Publicacion p = new Publicacion(publica[0], publica[2]);
                publicaciones.add(p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
