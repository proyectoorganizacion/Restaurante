package Views;

import Model.Restaurante;
import Services.RestauranteService;
import java.util.Scanner;

public class RegistroRestaurante {
    private RestauranteService restauranteService;

    public RegistroRestaurante(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    public void mostrarFormulario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- REGISTRO DE RESTAURANTE ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("NIT: ");
        String nit = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("URL Logo: ");
        String urlLogo = scanner.nextLine();

        System.out.print("ID del Propietario: ");
        Long idPropietario = scanner.nextLong();

        Restaurante restaurante = new Restaurante(nombre, nit, direccion, telefono, urlLogo, idPropietario);

        try {
            restauranteService.crearRestaurante(restaurante);
            System.out.println("Restaurante registrado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }
}