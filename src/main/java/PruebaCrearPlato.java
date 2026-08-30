import Model.CrearPlato;
import repositories.CrearPlatoRepository;
import Services.CrearPlatoService;
import java.util.Scanner;

    private static Long contadorId = 1L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CrearPlatoRepository repository = new CrearPlatoRepository();
        CrearPlatoService service = new CrearPlatoService(repository);

        System.out.println("=== FORMULARIO PARA CREAR PLATO ===");

        try {
            System.out.print("Ingrese su Rol (ej. PROPIETARIO): ");
            String rolUsuario = scanner.nextLine();

            Long id = contadorId++;

            System.out.print("Ingrese el Nombre del plato: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el Precio del plato: ");
            Integer precio = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese la Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Ingrese la URL de la imagen: ");
            String urlImagen = scanner.nextLine();

            System.out.print("Ingrese la Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Ingrese el ID del restaurante: ");
            Long idRestaurante = Long.parseLong(scanner.nextLine());

            CrearPlato platoCreado = service.crearPlato(
                    rolUsuario, id, nombre, precio, descripcion, urlImagen, categoria, idRestaurante);

            System.out.println("\n ¡Plato registrado exitosamente con el ID asignado " + id + "!");
            System.out.println(platoCreado);

        } catch (NumberFormatException e) {
            System.out.println("\n El precio y el ID del restaurante deben ser números enteros.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n Error de validación: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
