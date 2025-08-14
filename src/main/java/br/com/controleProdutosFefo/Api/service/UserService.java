package br.com.controleProdutosFefo.Api.service;

import br.com.controleProdutosFefo.Api.dto.UserRequestDTO;
import br.com.controleProdutosFefo.Api.dto.UserResponseDTO;
import br.com.controleProdutosFefo.Api.model.TipoUsuario;
import br.com.controleProdutosFefo.Api.model.UserModel;
import br.com.controleProdutosFefo.Api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRequestDTO dto) {

        UserModel userModel = new UserModel();
        userModel.setUsername(dto.getUsername());
        userModel.setEmail(dto.getEmail());

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        userModel.setPassword(encodedPassword);

        userModel.setTipoUsuario(TipoUsuario.ROLE_USER);

        UserModel savedUser = userRepository.save(userModel);

        return toDTO(savedUser);
    }

    private UserResponseDTO toDTO(UserModel user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getTipoUsuario().name()
        );
    }
}