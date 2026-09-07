import Model.Restaurante;
import Model.Propietario;
import Model.CrearPlato;
import Model.SesionUsuario;

import Services.PropietarioService;
import Services.RestauranteService;
import Services.CrearPlatoService;
import Services.EmpleadoService;
import Services.AuthService;

import repositories.PropietarioRepository;
import repositories.RestauranteRepository;
import repositories.CrearPlatoRepository;

import Views.RegistroPlato;
import Views.RegistroPropietario;
import Views.RegistroRestaurante;
import Views.RegistroEmpleado;
import Views.LoginView;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Repositories
        PropietarioRepository propietarioRepository = new PropietarioRepository();
        RestauranteRepository restauranteRepository = new RestauranteRepository();
        CrearPlatoRepository crearPlatoRepository = new CrearPlatoRepository();

        // Encriptar clave del Administrador inicial con BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode("1234");

        // Crear usuario ADMINISTRADOR inicial
        Propietario admin = new Propietario(
                "Admin",
                "Sistema",
                "1001",
                "+573000000000",
                LocalDate.of(1990, 1, 1),
                "admin@correo.com",
                passwordHash
        );
        admin.setRole("ADMINISTRADOR");
        propietarioRepository.guardarPropietario(admin);

        // Services
        PropietarioService service = new PropietarioService(propietarioRepository);
        RestauranteService service2 = new RestauranteService(restauranteRepository, propietarioRepository);
        CrearPlatoService service3 = new CrearPlatoService(crearPlatoRepository);
        EmpleadoService serviceEmpleado = new EmpleadoService();
        AuthService authService = new AuthService(propietarioRepository);

        // Views
        RegistroPropietario registro = new RegistroPropietario(service);
        RegistroRestaurante registroRestaurante = new RegistroRestaurante(service2);
        RegistroPlato registroPlato = new RegistroPlato(service3);
        RegistroEmpleado registroEmpleado = new RegistroEmpleado(serviceEmpleado);
        LoginView loginView = new LoginView(authService);

        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       SISTEMA DE RESTAURANTES");
            System.out.println("=================================");
            if (SesionUsuario.getInstancia().estaAutenticado()) {
                System.out.println("Usuario activo: " + SesionUsuario.getInstancia().getUsuarioAutenticado().getName() +
                        " [" + SesionUsuario.getInstancia().getUsuarioAutenticado().getRole() + "]");
            } else {
                System.out.println("Estado: Sin iniciar sesión");
            }
            System.out.println("---------------------------------");
            System.out.println("0. Iniciar sesión");
            System.out.println("1. Registrar propietario");
            System.out.println("2. Registrar restaurante");
            System.out.println("3. Crear Plato");
            System.out.println("4. Modificar Plato");
            System.out.println("5. Crear Empleado");
            System.out.println("6. Salir");
            System.out.println("=================================");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {

                case "0":
                    loginView.mostrarLogin();
                    break;

                case "1":
                    try {
                        registro.showRegisterForm();
                        propietarioRepository.mostrarPropietarios();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        registroRestaurante.mostrarFormulario();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        registroPlato.mostrarFormulario();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "4":
                    try {
                        registroPlato.modificarPlato();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "5":
                    try {
                        registroEmpleado.mostrarFormulario();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "6":
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}