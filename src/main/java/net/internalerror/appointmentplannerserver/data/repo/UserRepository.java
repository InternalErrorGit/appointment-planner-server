package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends DatabaseEntityRepository<User> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsernameAndHashedPassword(String username, String hashedPassword);

    Optional<User> findByUsername(String username);
}