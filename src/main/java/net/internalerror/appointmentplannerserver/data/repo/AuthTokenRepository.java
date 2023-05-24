package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.AuthToken;
import net.internalerror.appointmentplannerserver.data.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthTokenRepository extends DatabaseEntityRepository<AuthToken> {
    List<AuthToken> findByValidUntilBefore(LocalDateTime now);

    List<AuthToken> findByUser(User user);

    Optional<AuthToken> findByToken(String token);


}