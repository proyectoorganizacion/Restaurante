//Models
import Model.Restaurante;
import Model.Propietario;
import Model.CrearPlato;

//Services
import Services.PropietarioService;
import Services.RestauranteService;
import Services.CrearPlatoService;

//Repositories
import repositories.PropietarioRepository;
import repositories.RestauranteRepository;
import repositories.CrearPlatoRepository;

//Views
import Views.RegistroPlato;
import Views.RegistroPropietario;
import Views.RegistroRestaurante;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Repositories
        PropietarioRepository propietarioRepository = new PropietarioRepository();
        RestauranteRepository restauranteRepository = new RestauranteRepository();
        CrearPlatoRepository crearPlatoRepository = new CrearPlatoRepository();

        //Services
        PropietarioService service = new PropietarioService(propietarioRepository);
        RestauranteService service2 = new RestauranteService(restauranteRepository, propietarioRepository);
        CrearPlatoService service3 = new CrearPlatoService(crearPlatoRepository);

        //Views
        RegistroPropietario registro = new RegistroPropietario(service);
        RegistroRestaurante registroRestaurante = new RegistroRestaurante(service2);
        RegistroPlato registroPlato = new RegistroPlato(service3);

        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       SISTEMA DE RESTAURANTES");
            System.out.println("=================================");
            System.out.println("1. Registrar propietario");
            System.out.println("2. Registrar restaurante");
            System.out.println("3. Crear Plato");
            System.out.println("4. Modficar Plato");
            System.out.println("5. Salir");
            System.out.println("=================================");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {

                case "1":
                    registro.showRegisterForm();
                    propietarioRepository.mostrarPropietarios();
                    break;

                case "2":
                    // Aquí se ejecuta el registro del restaurante
                    registroRestaurante.mostrarFormulario();
                    break;

                case "3":
                    registroPlato.mostrarFormulario();
                    break;

                case "4":
                    registroPlato.modificarPlato();
                    break;

                case "5":
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}





