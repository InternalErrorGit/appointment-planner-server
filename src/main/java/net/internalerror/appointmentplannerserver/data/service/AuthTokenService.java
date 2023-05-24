package net.internalerror.appointmentplannerserver.data.service;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.appointmentplannerserver.data.AuthToken;
import net.internalerror.appointmentplannerserver.data.User;
import net.internalerror.appointmentplannerserver.data.repo.AuthTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthTokenService extends DatabaseEntityService<AuthToken, AuthTokenRepository> {
    public AuthTokenService(AuthTokenRepository repository) {
        super(repository);
    }

    public int removeExpiredTokens() {
        List<AuthToken> tokens = repository.findByValidUntilBefore(LocalDateTime.now());

        repository.deleteAll(tokens);

        return tokens.size();
    }

    public void removeUserToken(User user) {
        log.info("Removing tokens of user {}", user);
        repository.deleteAll(repository.findByUser(user));
    }

    public Optional<AuthToken> findByToken(String token) {
        return repository.findByToken(token);
    }
}
