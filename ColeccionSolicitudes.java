import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionSolicitudes {
    static ArrayList<SolicitudAmistad> solicitudes = new ArrayList<>();

    public static void agregarSolicitud(String usuario1, String usuario2) {
        SolicitudAmistad solicitud = new SolicitudAmistad(usuario2, usuario1);
        solicitudes.add(solicitud);
    }

    public static void buscarSolicitud(String usuario1) {
        for (SolicitudAmistad solicitud: solicitudes) {
            if (solicitud.getUsuario1().equals(usuario1)) {
                System.out.println(solicitud.getUsuario2());
            }
        }
    }
    public static void aceptarSolicitud(String u1, String u2) {
        for (SolicitudAmistad solicitud: solicitudes) {
            if (solicitud.getUsuario1().equals(u1) && solicitud.getUsuario2().equals(u2)) {
                Amistad amistad = new Amistad(u1, u2);
                ColeccionAmistades.agregarAmistad(amistad);
                solicitudes.remove(solicitud);
                System.out.println("La amistad ha sido creada.");
                break;
            }
            else {
                System.out.println("No se ha encontrado la solicitud pedida.");
            }
        }
    }
    public static void guardar() throws IOException {
        File archivo = new File("Solicitudes.txt");
        FileWriter escribir = new FileWriter(archivo);
        for (SolicitudAmistad solicitud: solicitudes) {
            escribir.write(solicitud.getUsuario1() +"," + solicitud.getUsuario2());
            escribir.write("\r\n");
        }
        escribir.close();
    }
    public static void cargar() {
        File file = new File("Solicitudes.txt");
        try {
            Scanner input = new Scanner(file);
            String line;
            while(input.hasNextLine()) {
                line = input.nextLine();
                String[] solAmistad = line.split(",");

                SolicitudAmistad solicitud = new SolicitudAmistad(solAmistad[0], solAmistad[1]);
                solicitudes.add(solicitud);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
