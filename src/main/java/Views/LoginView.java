package Views;

import Services.AuthService;
import java.util.Scanner;

public class LoginView {
    private AuthService authService;

    public LoginView(AuthService authService) {
        this.authService = authService;
    }

    public boolean mostrarLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== INICIO DE SESIÓN ===");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        try {
            authService.login(correo, clave);
            System.out.println("¡Inicio de sesión exitoso!");
            return true;
        } catch (Exception e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            return false;
        }
    }
}