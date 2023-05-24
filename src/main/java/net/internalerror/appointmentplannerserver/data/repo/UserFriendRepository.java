package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.User;
import net.internalerror.appointmentplannerserver.data.UserFriend;

import java.util.List;
import java.util.Optional;

public interface UserFriendRepository extends DatabaseEntityRepository<UserFriend> {
    Optional<UserFriend> findByToken(String token);

    List<UserFriend> findByAuthor(User author);
}