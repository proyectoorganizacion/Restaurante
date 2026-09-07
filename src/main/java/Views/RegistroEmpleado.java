package Views;

import Services.EmpleadoService;
import java.util.Scanner;

public class RegistroEmpleado {
    private final EmpleadoService empleadoService;

    public RegistroEmpleado(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void mostrarFormulario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=================================");
        System.out.println("       CREAR EMPLEADO");
        System.out.println("=================================");

        System.out.print("Ingrese el Nombre del empleado: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el Documento/Identificación: ");
        String identificacion = scanner.nextLine().trim();

        try {
            empleadoService.crearEmpleado(nombre, identificacion);
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}