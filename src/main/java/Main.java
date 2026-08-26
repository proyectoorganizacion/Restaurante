import Model.Propietario;
import Services.PropietarioService;
import repositories.PropietarioRepository;
import Views.RegistroPropietario;

public class Main {
    public static void main(String[] args) {

        PropietarioRepository propietarioRepository = new PropietarioRepository();

        PropietarioService service = new PropietarioService(propietarioRepository);
        RegistroPropietario registro = new RegistroPropietario(service);


        registro.showRegisterForm();

        propietarioRepository.mostrarPropietarios();
    }
}
