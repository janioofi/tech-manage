package br.janioofi.techmanage.domain.repositories;

import br.janioofi.techmanage.domain.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
