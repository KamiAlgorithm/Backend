import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Usuario;

public class UsuarioTest {

    @Test
    public void testUsuarioValido() {
        Usuario u = new Usuario();
        u.setNombre("Test");
        u.setEmail("test@mail.com");

        assertNotNull(u.getNombre());
        assertNotNull(u.getEmail());
    }
}
