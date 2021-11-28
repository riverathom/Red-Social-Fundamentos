
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionAmistades
{
    static ArrayList<Amistad> amistades;

    public ColeccionAmistades() {
        amistades = new ArrayList<>();
    }

    public static void agregarAmistad(Amistad amistad) {
        amistades.add(amistad);
    }

    public boolean buscarAmistad(String usuario1, String usuario2) {
        for(Amistad a: amistades) {
            if(a.getUsuario1().equals(usuario1) && a.getUsuario2().equals(usuario2)) {
                return true;
            }
            if(a.getUsuario1().equals(usuario2) && a.getUsuario2().equals(usuario1)) {
                return true;
            }
        }
        return false;
    }
    public static void guardar() throws IOException {
        File archivo = new File("Amistades.txt");
        FileWriter escribir = new FileWriter(archivo);
        for (Amistad amistad: amistades) {
            escribir.write(amistad.getUsuario1() + "," + amistad.getUsuario2());
            escribir.write("\r\n");
        }
        escribir.close();
    }
    public static void cargar() {
        File file = new File("Amistades.txt");
        try {
            Scanner input = new Scanner(file);
            String line;
            while(input.hasNextLine()) {
                line = input.nextLine();
                String[] amist = line.split(",");

                Amistad a = new Amistad(amist[0], amist[1]);
                amistades.add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
