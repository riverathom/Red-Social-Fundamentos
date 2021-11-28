
/**
 * En esta clase se maneja la colección de los usuarios que se han
 * inscrito en la aplicación.
 *
 * @author Nombre del estudiante
 * @version Versión 0
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionUsuarios
{

    static ArrayList<Usuario> usuarios;


    public ColeccionUsuarios() {
        usuarios = new ArrayList<>();
    }


    public boolean agregarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

 
    public Usuario buscarUsuario(String usuarioBuscado) {
        for(Usuario u: usuarios) {
            if(u.getUsuario().equals(usuarioBuscado)) {
                return u;
            }
        }
        return null;
    }

    
    public Usuario buscarUsuarioClave(String usuario, String clave) {
        for(Usuario u: usuarios) {
            if(u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                return u;
            }
        }
        return null;
    }
    public static void guardar() throws IOException {
        File archivo = new File("Usuarios.txt");
        FileWriter escribir = new FileWriter(archivo);
        for (Usuario usuario: usuarios) {
            escribir.write(usuario.getUsuario() + "," + usuario.getNombreCompleto() + "," + usuario.getClave());
            escribir.write("\r\n");
        }
        escribir.close();
    }
    public static void cargar() {
        File file = new File("Usuarios.txt");
        try {
            Scanner input = new Scanner(file);
            String line;
            while(input.hasNextLine()) {
                line = input.nextLine();
                String[] colUsuarios = line.split(",");

                Usuario u1 = new Usuario(colUsuarios[0], colUsuarios[2], colUsuarios[1]);
                usuarios.add(u1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
