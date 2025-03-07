import java.util.*;
import state.Usuario;
import state.Activo;
import state.Moroso;
import template.Prestamo;
import template.PrestamoNormal;
import template.PrestamoExpress;
import iterator.ColeccionMaterial;
import iterator.IteratorInterface;

public class Main {
    // Conjunto para almacenar los títulos de materiales en préstamo.
    private static Set<String> materialesPrestados = new HashSet<>();
    // Mapa para gestionar reservas: título del material -> lista de nombres de usuarios que lo reservaron.
    private static Map<String, List<String>> reservas = new HashMap<>();

    // Catálogo de materiales de la biblioteca.
    private static ColeccionMaterial catalogo;

    // Usuario actual (se crea al inicio de la sesión).
    private static Usuario usuario;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialización del catálogo con datos de ejemplo.
        List<String> libros = new ArrayList<>(Arrays.asList(
            "Cálculo Avanzado", 
            "Cien años de soledad", 
            "Don Quijote de la Mancha"
        ));
        String[] revistas = {"Revista de Ciencia", "Revista de Literatura"};
        Hashtable<Integer, String> audiolibros = new Hashtable<>();
        audiolibros.put(1, "Audiolibro de Historia");
        audiolibros.put(2, "Audiolibro de Tecnología");
        catalogo = new ColeccionMaterial(libros, revistas, audiolibros);

        // Solicitar el nombre del usuario e inicializarlo en estado Activo.
        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = scanner.nextLine();
        usuario = new Usuario(nombreUsuario);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Explorar materiales por categoría");
            System.out.println("2. Buscar material por título");
            System.out.println("3. Solicitar un préstamo");
            System.out.println("4. Devolver material");
            System.out.println("5. Reservar material");
            System.out.println("6. Ver estado del usuario");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    explorarMateriales(scanner);
                    break;
                case "2":
                    buscarMaterial(scanner);
                    break;
                case "3":
                    solicitarPrestamo(scanner);
                    break;
                case "4":
                    devolverMaterial(scanner);
                    break;
                case "5":
                    reservarMaterial(scanner);
                    break;
                case "6":
                    verEstadoUsuario();
                    break;
                case "7":
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
        scanner.close();
    }

    // Permite explorar los materiales según la categoría seleccionada.
    private static void explorarMateriales(Scanner scanner) {
        System.out.println("\nCategorías disponibles:");
        System.out.println("1. Libros");
        System.out.println("2. Revistas");
        System.out.println("3. Audiolibros");
        System.out.print("Seleccione una categoría: ");
        String opcionCat = scanner.nextLine();

        IteratorInterface iterador = null;
        switch (opcionCat) {
            case "1":
                iterador = catalogo.getLibrosIterator();
                System.out.println("\n--- Lista de Libros ---");
                break;
            case "2":
                iterador = catalogo.getRevistasIterator();
                System.out.println("\n--- Lista de Revistas ---");
                break;
            case "3":
                iterador = catalogo.getAudiolibrosIterator();
                System.out.println("\n--- Lista de Audiolibros ---");
                break;
            default:
                System.out.println("Categoría inválida.");
                return;
        }
        while (iterador.hasNext()) {
            System.out.println("• " + iterador.next());
        }
    }

    // Busca un material en el catálogo por su título.
    private static void buscarMaterial(Scanner scanner) {
        System.out.print("\nIngrese el título del material a buscar: ");
        String titulo = scanner.nextLine();
        boolean encontrado = false;
        
        // Buscar en Libros.
        IteratorInterface iterLibros = catalogo.getLibrosIterator();
        while (iterLibros.hasNext()) {
            String libro = (String) iterLibros.next();
            if (libro.equalsIgnoreCase(titulo)) {
                System.out.println("Libro \"" + libro + "\" encontrado. " + disponibilidadMensaje(libro));
                encontrado = true;
            }
        }
        // Buscar en Revistas.
        IteratorInterface iterRevistas = catalogo.getRevistasIterator();
        while (iterRevistas.hasNext()) {
            String revista = (String) iterRevistas.next();
            if (revista.equalsIgnoreCase(titulo)) {
                System.out.println("Revista \"" + revista + "\" encontrada. " + disponibilidadMensaje(revista));
                encontrado = true;
            }
        }
        // Buscar en Audiolibros.
        IteratorInterface iterAudiolibros = catalogo.getAudiolibrosIterator();
        while (iterAudiolibros.hasNext()) {
            String audiolibro = (String) iterAudiolibros.next();
            if (audiolibro.equalsIgnoreCase(titulo)) {
                System.out.println("Audiolibro \"" + audiolibro + "\" encontrado. " + disponibilidadMensaje(audiolibro));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Material no encontrado en el catálogo.");
        }
    }

    // Retorna un mensaje de disponibilidad del material.
    private static String disponibilidadMensaje(String titulo) {
        return materialesPrestados.contains(titulo) ? "Actualmente NO disponible." : "Disponible.";
    }

    // Solicita el préstamo de un material.
    private static void solicitarPrestamo(Scanner scanner) {
        System.out.print("\nIngrese el título del material que desea pedir: ");
        String titulo = scanner.nextLine();
        
        // Verificar si el material existe en el catálogo.
        if (!existeMaterial(titulo)) {
            System.out.println("El material \"" + titulo + "\" no existe en el catálogo.");
            return;
        }
        // Verificar disponibilidad.
        if (materialesPrestados.contains(titulo)) {
            System.out.println("El material \"" + titulo + "\" ya se encuentra en préstamo.");
            return;
        }
        // Verificar si el usuario está en estado moroso.
        if (usuario.getEstado() instanceof Moroso) {
            usuario.solicitarPrestamo();
            return;
        }
        
        // Seleccionar el tipo de préstamo.
        System.out.println("Seleccione el tipo de préstamo:");
        System.out.println("1. Normal (15 días, con posibilidad de renovar hasta 3 veces)");
        System.out.println("2. Express (7 días, sin renovaciones)");
        System.out.print("Opción: ");
        String opcionTipo = scanner.nextLine();
        
        Prestamo prestamo = null;
        if ("1".equals(opcionTipo)) {
            prestamo = new PrestamoNormal();
        } else if ("2".equals(opcionTipo)) {
            prestamo = new PrestamoExpress();
        } else {
            System.out.println("Opción de préstamo inválida.");
            return;
        }
        // Procesar el préstamo mediante el Template Pattern.
        prestamo.procesarPrestamo(usuario.getNombre(), titulo);
        // Marcar el material como prestado.
        materialesPrestados.add(titulo);
        // Notificar la acción al usuario (a través del método del patrón State).
        usuario.solicitarPrestamo();
    }

    // Procesa la devolución de un material.
    private static void devolverMaterial(Scanner scanner) {
        System.out.print("\nIngrese el título del material que desea devolver: ");
        String titulo = scanner.nextLine();
        
        if (!materialesPrestados.contains(titulo)) {
            System.out.println("El material \"" + titulo + "\" no se encuentra en préstamo.");
            return;
        }
        // Procesar la devolución y actualizar el estado del usuario si corresponde.
        usuario.devolverPrestamo();
        materialesPrestados.remove(titulo);
        System.out.println("Material \"" + titulo + "\" devuelto exitosamente.");
        // Notificar al siguiente usuario en la lista de reservas, si existe.
        if (reservas.containsKey(titulo)) {
            List<String> listaReservas = reservas.get(titulo);
            if (!listaReservas.isEmpty()) {
                String usuarioNotificado = listaReservas.remove(0);
                System.out.println("Notificación: " + usuarioNotificado + ", el material \"" + titulo + "\" ya está disponible.");
                if (listaReservas.isEmpty()) {
                    reservas.remove(titulo);
                }
            }
        }
    }

    // Permite reservar un material que ya está prestado.
    private static void reservarMaterial(Scanner scanner) {
        System.out.print("\nIngrese el título del material que desea reservar: ");
        String titulo = scanner.nextLine();
        
        // Verificar existencia en el catálogo.
        if (!existeMaterial(titulo)) {
            System.out.println("El material \"" + titulo + "\" no existe en el catálogo.");
            return;
        }
        // Solo se permite reservar si el material no está disponible.
        if (!materialesPrestados.contains(titulo)) {
            System.out.println("El material \"" + titulo + "\" está disponible, no es necesario reservarlo.");
            return;
        }
        // Agregar el nombre del usuario a la lista de reservas para ese material.
        List<String> listaReservas = reservas.getOrDefault(titulo, new ArrayList<>());
        if (!listaReservas.contains(usuario.getNombre())) {
            listaReservas.add(usuario.getNombre());
            reservas.put(titulo, listaReservas);
            System.out.println("Has reservado el material \"" + titulo + "\". Recibirás una notificación cuando esté disponible.");
        } else {
            System.out.println("Ya tienes una reserva para el material \"" + titulo + "\".");
        }
    }

    // Muestra el estado actual del usuario.
    private static void verEstadoUsuario() {
        String estado = (usuario.getEstado() instanceof Activo) ? "Activo" : "Moroso";
        System.out.println("\nEstado del usuario: " + estado);
    }

    // Verifica si un material existe en el catálogo.
    private static boolean existeMaterial(String titulo) {
        // Buscar en Libros.
        IteratorInterface iterLibros = catalogo.getLibrosIterator();
        while (iterLibros.hasNext()) {
            if (((String) iterLibros.next()).equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        // Buscar en Revistas.
        IteratorInterface iterRevistas = catalogo.getRevistasIterator();
        while (iterRevistas.hasNext()) {
            if (((String) iterRevistas.next()).equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        // Buscar en Audiolibros.
        IteratorInterface iterAudiolibros = catalogo.getAudiolibrosIterator();
        while (iterAudiolibros.hasNext()) {
            if (((String) iterAudiolibros.next()).equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }
}
