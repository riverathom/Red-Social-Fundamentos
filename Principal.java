
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal
{
    static ColeccionUsuarios coleccionUsuarios;
    static ColeccionPublicaciones coleccionPublicaciones;
    static ColeccionAmistades coleccionAmistades;
    static Usuario usuarioActivo;

    static Scanner in = new Scanner(System.in);

    public static void inicializarColecciones() {
        coleccionUsuarios = new ColeccionUsuarios();
        coleccionPublicaciones = new ColeccionPublicaciones();
        coleccionAmistades = new ColeccionAmistades();
    }

    public static void menuPrincipal() throws IOException {
        int opcion;
        do {
            System.out.println("========================");
            System.out.println("1. Crear usuario");
            System.out.println("2. Ingresar al sistema");
            System.out.println("3. Almacenar la información");
            System.out.println("4. Cargar la información almacenada");
            System.out.println("9. Salir del sistema");
            System.out.println("Por favor digite su opción");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearUsuario();
                    break;
                case 2:
                    menuIngresar();
                    break;
                case 3:
                    almacenarDatosIngresados();
                case 4:
                    CargarDatosAlmacenados();
            }
        } while (opcion != 9);
    }

    public static void menuCrearUsuario() {
        boolean exito = true;
        String usuario;
        String clave;
        String nombreCompleto;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar el nuevo usuario: (o fin para terminar)");
            usuario = in.next();
            if (usuario.equals("fin")) return;
            if(coleccionUsuarios.buscarUsuario(usuario) != null) {
                System.out.println("Ese usuario ya existe");
            } else {
                exito = true;
            }
        } while (!exito);
        System.out.println("Favor ingresar la clave: ");
        clave = in.next();
        System.out.println("Favor ingresar el nombre completo: ");
        nombreCompleto = in.next();
        Usuario nuevoUsuario = new Usuario(usuario, clave, nombreCompleto);
        coleccionUsuarios.agregarUsuario(nuevoUsuario);
        System.out.println("Bienvenido(a) al sistema");
        return;
    }

    public static void menuIngresar() {
        boolean exito = false;
        String usuario;
        String clave;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar su usuario: (fin para terminar)");
            usuario = in.next();
            if(usuario.equals("fin")) return;
            System.out.println("Favor ingresar su clave: ");
            clave = in.next();
            Principal.usuarioActivo = coleccionUsuarios.buscarUsuarioClave(usuario, clave);
            if(usuario == null) {
                System.out.println("Combinación usuario y clave inválida");
            } else {
                exito = true;
            }
        } while (!exito);
        System.out.println("Hola, " + Principal.usuarioActivo.getNombreCompleto());
        System.out.println("Estás en el sistema");
        menuUsuarioActivo();
    }

    public static void menuUsuarioActivo() {
        int opcion;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("1. Crear una publicación");
            System.out.println("2. Pedir una amistad");
            System.out.println("3. Ver las publicaciones de un(a) amigo(a)");
            System.out.println("4. Ver mis publicaciones");
            System.out.println("5. Ver mis solicitudes de amistad");
            System.out.println("9. Regresar");
            System.out.println("Por favor digite su opción");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearPublicacion();
                    break;
                case 2:
                    menuPedirAmistad();
                    break;
                case 3:
                    menuVerPublicacionesAmigo();
                    break;
                case 4:
                    menuVerPublicacionesPropias();
                    break;
                case 5:
                    menuSolicitudAmistad();
                    break;
            }
        } while (opcion != 9);
    }

    public static void menuCrearPublicacion() {
        String texto;
        //in = new Scanner(System.in);
        System.out.println("========================");
        System.out.println("Favor ingresar el texto de su publicación: ");
        texto = in.next();
        Publicacion publicacion =
                new Publicacion(Principal.usuarioActivo.getUsuario(), texto);
        Principal.coleccionPublicaciones.agregarPublicacion(publicacion);
        System.out.println("La publicación ha sido agregada");
    }

    public static void menuPedirAmistad() {
        System.out.println("Ingrese el usuario al que quiere enviar la solicitud");
        String orden = in.next();
        if (!orden.equals("fin")){
            ColeccionSolicitudes.agregarSolicitud(usuarioActivo.getUsuario(), orden);
            System.out.println("La solicitud ha sido enviada.");
        }
    }

    public static void menuVerPublicacionesAmigo() {
        String usuario;
        System.out.println("========================");
        System.out.println("Favor ingresar el(la) amigo(a): (fin para terminar)");
        usuario = in.next();
        if(Principal.coleccionAmistades.buscarAmistad(Principal.usuarioActivo.getUsuario(), usuario)) {
            ArrayList<Publicacion> seleccionadas = Principal.coleccionPublicaciones.getPublicacionesDeUnUsuario(usuario);
            if(seleccionadas.size()==0){
                System.out.println(usuario+" no tiene publicaciones");
            }
            else{
                System.out.println("Las publicaciones de "+ usuario + " son: ");
                for(int i = seleccionadas.size() - 1; i >= 0; i--) {
                    System.out.println(seleccionadas.get(i).getTexto());
                }
            }
        }
    }

    public static void menuVerPublicacionesPropias() {
        String usuario = Principal.usuarioActivo.getUsuario();
        ArrayList<Publicacion> seleccionadas = Principal.coleccionPublicaciones.getPublicacionesDeUnUsuario(usuario);
        System.out.println("Las publicaciones de " + Principal.usuarioActivo.getNombreCompleto() + ": ");
        for(Publicacion p: seleccionadas) {
            System.out.println(p.getTexto());
        }
    }

    public static void menuSolicitudAmistad() {
        String usuarioActivo = Principal.usuarioActivo.getUsuario();
        ColeccionSolicitudes.buscarSolicitud(usuarioActivo);
        System.out.println("Para aceptar una solicitud de amistad escriba el nombre de usuario del amigo a aceptar");
        String amigo = in.next();
        ColeccionSolicitudes.aceptarSolicitud(usuarioActivo, amigo);
    }

    public static void CargarDatosAlmacenados() {
        ColeccionSolicitudes.cargar();
        ColeccionAmistades.cargar();
        ColeccionPublicaciones.cargar();
        ColeccionUsuarios.cargar();
        System.out.println("Los datos se han cargado satisfactoriamente.");
    }


    public static void almacenarDatosIngresados() throws IOException {
        ColeccionAmistades.guardar();
        ColeccionPublicaciones.guardar();
        ColeccionUsuarios.guardar();
        ColeccionSolicitudes.guardar();
    }

    public static void main(String [] args) throws IOException {
        inicializarColecciones();
        menuPrincipal();

    }

}
