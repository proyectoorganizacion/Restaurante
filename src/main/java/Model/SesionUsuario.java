package Model;

public class SesionUsuario {
    private static SesionUsuario instancia;
    private Propietario usuarioAutenticado;

    private SesionUsuario() {}

    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public void iniciarSesion(Propietario usuario) {
        this.usuarioAutenticado = usuario;
    }

    public void cerrarSesion() {
        this.usuarioAutenticado = null;
    }

    public Propietario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public boolean estaAutenticado() {
        return usuarioAutenticado != null;
    }
}