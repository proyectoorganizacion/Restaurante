package Views;

import Model.CrearPlato;
import Model.SesionUsuario;
import Services.CrearPlatoService;

import java.util.Scanner;

public class RegistroPlato {

    private final CrearPlatoService crearPlatoService;
    private final Scanner scanner;

    private static Long contadorId = 1L;

    public RegistroPlato(CrearPlatoService crearPlatoService) {
        this.crearPlatoService = crearPlatoService;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarFormulario() {

        System.out.println("\n=================================");
        System.out.println("       REGISTRO DE PLATO");
        System.out.println("=================================");

        String rolUsuario = SesionUsuario.getInstancia().estaAutenticado()
                ? SesionUsuario.getInstancia().getUsuarioAutenticado().getRole()
                : "";

        Long id = generarId();
        String nombre = leerNombre();
        Integer precio = leerPrecio();
        String descripcion = leerDescripcion();
        String urlImagen = leerUrlImagen();
        String categoria = leerCategoria();
        String nitRestaurante = leerNitRestaurante();

        try {

            CrearPlato platoCreado = crearPlatoService.crearPlato(
                    rolUsuario,
                    id,
                    nombre,
                    precio,
                    descripcion,
                    urlImagen,
                    categoria,
                    nitRestaurante
            );

            System.out.println("\n¡Plato registrado exitosamente!");
            System.out.println("ID del plato: " + platoCreado.getId());
            System.out.println(platoCreado);

        } catch (IllegalArgumentException e) {

            System.out.println("\nError de validación: " + e.getMessage());
        }
    }

    public void modificarPlato() {

        System.out.println("\n=================================");
        System.out.println("        MODIFICAR PLATO");
        System.out.println("=================================");

        try {

            Long idPlato = leerIdPlato();
            Integer nuevoPrecio = leerPrecio();
            String nuevaDescripcion = leerDescripcion();
            String nitRestauranteUsuario = leerNitRestaurante();

            CrearPlato platoModificado = crearPlatoService.modificarPlato(
                    idPlato,
                    nuevoPrecio,
                    nuevaDescripcion,
                    nitRestauranteUsuario
            );

            System.out.println("\n¡Plato modificado exitosamente!");
            System.out.println("\n=== DATOS DEL PLATO MODIFICADO ===");
            System.out.println(platoModificado);

        } catch (Exception e) {

            System.out.println("\nError al modificar el plato: " + e.getMessage());
        }
    }

    private Long leerIdPlato() {
        while (true) {
            System.out.print("Ingrese el ID del plato que desea modificar: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("El ID del plato es obligatorio.");
                continue;
            }
            try {
                Long id = Long.parseLong(entrada);
                if (id <= 0) {
                    System.out.println("El ID debe ser mayor a 0.");
                    continue;
                }
                return id;
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un número entero.");
            }
        }
    }

    private String leerNombre() {
        while (true) {
            System.out.print("Ingrese el Nombre del plato: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre del plato es obligatorio.");
                continue;
            }
            if (nombre.matches("^[0-9]+$")) {
                System.out.println("El nombre del plato no puede contener únicamente números.");
                continue;
            }
            return nombre;
        }
    }

    private Integer leerPrecio() {
        while (true) {
            System.out.print("Ingrese el Precio del plato: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("El precio es obligatorio.");
                continue;
            }
            try {
                Integer precio = Integer.parseInt(entrada);
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor a 0.");
                    continue;
                }
                return precio;
            } catch (NumberFormatException e) {
                System.out.println("El precio debe ser un número entero.");
            }
        }
    }

    private String leerDescripcion() {
        while (true) {
            System.out.print("Ingrese la Descripción: ");
            String descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("La descripción es obligatoria.");
                continue;
            }
            return descripcion;
        }
    }

    private String leerUrlImagen() {
        while (true) {
            System.out.print("Ingrese la URL de la imagen: ");
            String url = scanner.nextLine().trim();
            if (url.isEmpty()) {
                System.out.println("La URL de la imagen es obligatoria.");
                continue;
            }
            return url;
        }
    }

    private String leerCategoria() {
        while (true) {
            System.out.print("Ingrese la Categoría: ");
            String categoria = scanner.nextLine().trim();
            if (categoria.isEmpty()) {
                System.out.println("La categoría es obligatoria.");
                continue;
            }
            return categoria;
        }
    }

    private String leerNitRestaurante() {
        while (true) {
            System.out.print("Ingrese el NIT del restaurante: ");
            String nit = scanner.nextLine().trim();
            if (nit.isEmpty()) {
                System.out.println("El NIT del restaurante es obligatorio.");
                continue;
            }
            if (!nit.matches("^[0-9]+$")) {
                System.out.println("El NIT debe contener únicamente números.");
                continue;
            }
            return nit;
        }
    }

    private Long generarId() {
        return contadorId++;
    }
}