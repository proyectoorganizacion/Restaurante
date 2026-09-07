package Views;

import Model.Restaurante;
import Services.RestauranteService;

import java.util.Scanner;

public class RegistroRestaurante {

    private RestauranteService restauranteService;
    private Scanner scanner;

    public RegistroRestaurante(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarFormulario() {

        System.out.println();
        System.out.println("--- REGISTRO DE RESTAURANTE ---");

        String nombre = leerNombre();
        String nit = leerNit();
        String direccion = leerCampoObligatorio("Dirección");
        String telefono = leerTelefono();
        String urlLogo = leerCampoObligatorio("URL Logo");
        String idPropietario = leerCampoObligatorio("ID del Propietario");

        Restaurante restaurante = new Restaurante(
                nombre,
                nit,
                direccion,
                telefono,
                urlLogo,
                idPropietario
        );

        try {

            restauranteService.crearRestaurante(restaurante);

            System.out.println();
            System.out.println("Restaurante registrado con éxito.");

        } catch (Exception e) {

            System.out.println();
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    private String leerNombre() {

        while (true) {

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isBlank()) {
                System.out.println("El nombre es obligatorio.");
                continue;
            }

            if (nombre.matches("^[0-9]+$")) {
                System.out.println(
                        "El nombre no puede estar compuesto únicamente por números."
                );
                continue;
            }

            return nombre;
        }
    }


    private String leerNit() {

        while (true) {

            System.out.print("NIT: ");
            String nit = scanner.nextLine().trim();

            if (nit.isBlank()) {
                System.out.println("El NIT es obligatorio.");
                continue;
            }

            if (!nit.matches("^[0-9]+$")) {
                System.out.println(
                        "El NIT debe contener únicamente números."
                );
                continue;
            }

            return nit;
        }
    }

    private String leerTelefono() {

        while (true) {

            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine().trim();

            if (telefono.isBlank()) {
                System.out.println("El teléfono es obligatorio.");
                continue;
            }

            if (!telefono.matches("^\\+?[0-9]+$")) {
                System.out.println(
                        "El teléfono solo puede contener números y opcionalmente '+' al inicio."
                );
                continue;
            }

            if (telefono.length() > 13) {
                System.out.println(
                        "El teléfono debe contener máximo 13 caracteres."
                );
                continue;
            }

            return telefono;
        }
    }

    private String leerCampoObligatorio(String campo) {

        while (true) {

            System.out.print(campo + ": ");
            String valor = scanner.nextLine().trim();

            if (valor.isBlank()) {
                System.out.println(
                        "El campo " + campo + " es obligatorio."
                );
                continue;
            }

            return valor;
        }
    }
}