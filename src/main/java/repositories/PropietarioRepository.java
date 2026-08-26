package repositories;

import Model.Propietario;

import java.util.ArrayList;
import java.util.List;

public class PropietarioRepository {
    private List<Propietario> propietarios = new ArrayList<>();

    public void guardarPropietario(Propietario propietario) {
        propietarios.add(propietario);
    }

    public void mostrarPropietarios() {

        for (Propietario propietario : propietarios) {

            System.out.println("=== Propietario ===");
            System.out.println("Name: " + propietario.getName());
            System.out.println("Lastname: " + propietario.getLastname());
            System.out.println("Identification: " + propietario.getIdentification());
            System.out.println("Phone: " + propietario.getPhone());
            System.out.println("Birthdate: " + propietario.getBirthdate());
            System.out.println("Email: " + propietario.getEmail());
            System.out.println("Password Hash: " + propietario.getPassword());
            System.out.println("Role: " + propietario.getRole());
        }
    }

    public List<Propietario> getPropietarios() {
        return propietarios;
    }
}
