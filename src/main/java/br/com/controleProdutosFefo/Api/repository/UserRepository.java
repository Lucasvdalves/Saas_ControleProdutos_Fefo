package br.com.controleProdutosFefo.Api.repository;

import br.com.controleProdutosFefo.Api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
