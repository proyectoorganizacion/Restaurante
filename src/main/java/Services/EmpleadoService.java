package Services;

import Model.Propietario;
import Model.SesionUsuario;

public class EmpleadoService {

    public void crearEmpleado(String nombre, String identificacion) throws Exception {

        if (!SesionUsuario.getInstancia().estaAutenticado()) {
            throw new Exception("Debe iniciar sesión para realizar esta acción.");
        }

        Propietario usuarioActual = SesionUsuario.getInstancia().getUsuarioAutenticado();
        if (!"PROPIETARIO".equalsIgnoreCase(usuarioActual.getRole())) {
            throw new Exception("Acceso denegado: Solo los usuarios con rol PROPIETARIO pueden crear empleados.");
        }

        System.out.println("Empleado " + nombre + " registrado exitosamente.");
    }
}