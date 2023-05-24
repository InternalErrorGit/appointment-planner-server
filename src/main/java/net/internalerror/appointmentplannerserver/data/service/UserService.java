package net.internalerror.appointmentplannerserver.data.service;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.appointmentplannerserver.MutationState;
import net.internalerror.appointmentplannerserver.data.AuthToken;
import net.internalerror.appointmentplannerserver.data.User;
import net.internalerror.appointmentplannerserver.data.repo.UserRepository;
import net.internalerror.appointmentplannerserver.rest.model.LoginModel;
import net.internalerror.appointmentplannerserver.rest.model.RegisterModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService extends DatabaseEntityService<User, UserRepository> {

    private final AuthTokenService authTokenService;

    public UserService(UserRepository repository, AuthTokenService authTokenService) {
        super(repository);
        this.authTokenService = authTokenService;
    }

    public boolean isAuthorized(String token) {
        Optional<AuthToken> authToken = authTokenService.findByToken(token);
        return authToken.map(AuthToken::isValid).orElse(false);
    }

    public MutationState canRegister(RegisterModel registerModel) {

        if (repository.existsByUsername(registerModel.getUsername())) {
            return MutationState.OCCUPIED;
        }

        if (repository.existsByEmail(registerModel.getEmail())) {
            return MutationState.OCCUPIED;
        }


        return MutationState.MUTATION_VALID;
    }

    public void register(RegisterModel registerModel) {
        save(User.createUser(registerModel));
    }

    public Optional<User> getUser(LoginModel loginModel) {
        return repository.findByUsernameAndHashedPassword(loginModel.getUsername(), loginModel.getPassword());
    }

    public String login(User user) {
        log.info("Creating new auth token");
        String token = UUID.randomUUID().toString();

        authTokenService.removeUserToken(user);

        AuthToken authToken = new AuthToken(user, token, LocalDateTime.now().plusHours(2L));
        authTokenService.save(authToken);
        return authToken.getToken();
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
