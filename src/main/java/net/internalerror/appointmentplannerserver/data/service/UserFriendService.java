package net.internalerror.appointmentplannerserver.data.service;

import net.internalerror.appointmentplannerserver.data.User;
import net.internalerror.appointmentplannerserver.data.UserFriend;
import net.internalerror.appointmentplannerserver.data.repo.UserFriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserFriendService extends DatabaseEntityService<UserFriend, UserFriendRepository> {
    public UserFriendService(UserFriendRepository repository) {
        super(repository);
    }

    public String createFriendRequest(User user) {
        UserFriend userFriend = new UserFriend();

        userFriend.setToken(UUID.randomUUID().toString());
        userFriend.setAuthor(user);

        return save(userFriend).getToken();
    }

    public Optional<UserFriend> findByToken(String token) {
        return repository.findByToken(token);
    }

    public void accept(UserFriend userFriend, User user) {
        userFriend.setFriend(user);
        save(userFriend);
    }

    public List<UserFriend> findByAuthor(User user){
        return repository.findByAuthor(user);
    }
}
