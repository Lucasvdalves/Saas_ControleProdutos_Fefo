package br.com.controleProdutosFefo.Api;

import br.com.controleProdutosFefo.Api.model.TipoUsuario;
import br.com.controleProdutosFefo.Api.model.UserModel;
import br.com.controleProdutosFefo.Api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        criaUsuarioMaster();
    }

    private void criaUsuarioMaster() {
        Optional<UserModel> masterUser = userRepository.findByUsername("master");

        if (masterUser.isEmpty()) {
            UserModel userModel = new UserModel();
            userModel.setUsername("master");
            userModel.setEmail("master@usuario.com");
            userModel.setTipoUsuario(TipoUsuario.ROLE_MASTER);

            // Criptografa a senha para o usuário master
            String encodedPassword = passwordEncoder.encode("senha123");
            userModel.setPassword(encodedPassword);

            userRepository.save(userModel);
            System.out.println("Usuário master 'master' criado com sucesso!");
        } else {
            System.out.println("Usuário master já existe.");
        }
    }
}