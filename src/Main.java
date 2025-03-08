import java.util.*;
import state.Usuario;
import state.Activo;
import state.Moroso;
import template.Prestamo;
import template.PrestamoNormal;
import template.PrestamoExpress;
import iterator.ColeccionMaterial;
import iterator.IteratorInterface;

/**
 * Clase principal que ejecuta la aplicación de gestión de préstamos de materiales de una biblioteca.
 * <p>
 * Esta clase gestiona el catálogo de materiales, el estado del usuario, las reservas y la interacción
 * con el usuario a través de un menú interactivo. Permite explorar materiales, buscar por título, solicitar
 * préstamos, devolver materiales y realizar reservas.
 * </p>
 */
public class Main {
    /**
     * Conjunto para almacenar los títulos de materiales que se encuentran actualmente en préstamo.
     */
    private static Set<String> materialesPrestados = new HashSet<>();

    /**
     * Mapa para gestionar reservas, donde la llave es el título del material y el valor es una lista
     * de nombres de usuarios que han reservado dicho material.
     */
    private static Map<String, List<String>> reservas = new HashMap<>();

    /**
     * Catálogo de materiales de la biblioteca.
     */
    private static ColeccionMaterial catalogo;

    /**
     * Usuario actual que utiliza la aplicación.
     */
    private static Usuario usuario;

    /**
     * Método principal que inicia la aplicación.
     * <p>
     * Configura el catálogo de la biblioteca, inicializa al usuario y muestra un menú interactivo para que
     * el usuario explore, busque, solicite préstamos, devuelva materiales y reserve materiales.
     * </p>
     *
     * @param args argumentos de línea de comandos (no se utilizan en este programa).
     */
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
	    System.out.println("8. Simular retraso (pasar a estado MOROSO)");
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
		case "8":
		    // Simular que el usuario se retrasa en devolver y se pasa a estado Moroso.
		    usuario.setEstado(new Moroso());
		    System.out.println("Simulación: El usuario ha pasado a estado MOROSO por retraso en la devolución.");
		    break;    
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
        scanner.close();
    }

    /**
     * Permite explorar los materiales según la categoría seleccionada por el usuario.
     * <p>
     * Muestra un menú de categorías y utiliza un iterador para recorrer y mostrar los materiales de la categoría seleccionada.
     * </p>
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Busca un material en el catálogo por su título.
     * <p>
     * Recorre el catálogo utilizando iteradores para libros, revistas y audiolibros. Si se encuentra el material,
     * muestra un mensaje indicando su disponibilidad.
     * </p>
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Retorna un mensaje de disponibilidad para un material.
     *
     * @param titulo el título del material.
     * @return "Disponible." si el material no se encuentra en préstamo; de lo contrario, "Actualmente NO disponible."
     */
    private static String disponibilidadMensaje(String titulo) {
        return materialesPrestados.contains(titulo) ? "Actualmente NO disponible." : "Disponible.";
    }

    /**
     * Solicita el préstamo de un material.
     * <p>
     * Verifica la existencia y disponibilidad del material en el catálogo, así como el estado del usuario.
     * Permite al usuario seleccionar el tipo de préstamo (Normal o Express) y, utilizando el Template Pattern,
     * procesa el préstamo. Finalmente, marca el material como prestado y notifica la acción al usuario.
     * </p>
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Procesa la devolución de un material.
     * <p>
     * Verifica que el material esté en préstamo, actualiza el estado del usuario, elimina el material del conjunto
     * de materiales prestados y, si existen reservas para el material, notifica al siguiente usuario en la lista.
     * </p>
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Permite al usuario reservar un material que ya se encuentra en préstamo.
     * <p>
     * Verifica la existencia del material en el catálogo y que el material no esté disponible para préstamo.
     * Si el usuario aún no ha reservado el material, agrega su nombre a la lista de reservas.
     * </p>
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Muestra el estado actual del usuario.
     * <p>
     * Indica si el usuario se encuentra en estado "Activo" o "Moroso".
     * </p>
     */
    private static void verEstadoUsuario() {
        String estado = (usuario.getEstado() instanceof Activo) ? "Activo" : "Moroso";
        System.out.println("\nEstado del usuario: " + estado);
    }

    /**
     * Verifica si un material existe en el catálogo.
     * <p>
     * Recorre las colecciones de libros, revistas y audiolibros utilizando iteradores para determinar si el material existe.
     * </p>
     *
     * @param titulo el título del material a buscar.
     * @return {@code true} si el material existe en el catálogo; {@code false} en caso contrario.
     */
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

