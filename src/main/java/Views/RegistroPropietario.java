package Views;

import Model.Propietario;
import Services.PropietarioService;

//Poner fechas de cualquier forma mas comodo
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RegistroPropietario {

    private PropietarioService propietarioService;
    private Scanner scanner;

    public RegistroPropietario(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
        this.scanner = new Scanner(System.in);
    }

    //Formulario de registro
    public void showRegisterForm() {

        System.out.println("=== Register Owner ===");

        String name = readRequiredText("Name: ");

        String lastname = readRequiredText("Lastname: ");

        String identification = readIdentification("Identification: ");

        String phone = readPhone("Phone: ");

        LocalDate birthdate = readBirthdate();

        String email = readEmail("Email: ");

        String password = readRequiredText("Password: ");

        Propietario propietario = new Propietario(
                name,
                lastname,
                identification,
                phone,
                birthdate,
                email,
                password
        );

        propietarioService.registrarPropietario(propietario);
    }

    //Validacion para no dejar espacios en blanco
    private String readRequiredText(String message){
        while (true){
            System.out.print(message);

            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("This field is required.");
                continue;
            }
            return input;
        }
    }

    private String readEmail(String s) {

        while (true) {

            System.out.print("Email: ");
            String email = scanner.nextLine();

            if (email.isBlank()) {
                System.out.println("Email is required.");
                continue;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Invalid email format.");
                continue;
            }

            return email;
        }
    }
    //Validacion para Telefono
    private String readPhone(String s) {
        while (true) {
            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            if (phone.isBlank()) {
                System.out.println("Phone is required.");
                continue;
            }

            if(phone.length() > 13){
                System.out.println("Phone must contain a maximum of 13 characters");
                continue;
            }

            if (!phone.matches("^\\+?\\d+$")) {
                System.out.println(
                        "Phone must contain only numbers and an optional + at the beginning");
            }
            return phone;
        }
    }

    //Validacion para cc solo numeros
    private String readIdentification(String s) {
        while (true) {
            System.out.print("Identification: ");
            String identification = scanner.nextLine();

            if (identification.isBlank()) {
                System.out.println("identification is required.");
                continue;
            }

            if (!identification.matches("\\d+")) {
                System.out.println("Identification must contain only numbers");
            }
            return identification;
        }
    }

    private LocalDate readBirthdate() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {

            System.out.print("Birthdate (dd/MM/yyyy): ");
            String birthdateInput = scanner.nextLine();

            if (birthdateInput.isBlank()) {
                System.out.println("Birthdate is required.");
                continue;
            }

            try {

                LocalDate birthdate =
                        LocalDate.parse(birthdateInput, formatter);

                // Validar que no sea una fecha futura
                if (birthdate.isAfter(LocalDate.now())) {
                    System.out.println(
                            "Birthdate cannot be in the future."
                    );
                    continue;
                }

                // Validar que tenga al menos 18 años
                if (Period.between(
                        birthdate,
                        LocalDate.now()
                ).getYears() < 18) {

                    System.out.println(
                            "The owner must be at least 18 years old."
                    );
                    continue;
                }

                return birthdate;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date. Use the format dd/MM/yyyy."
                );
            }
        }
    }
}