package net.internalerror.appointmentplannerserver.rest.controller.impl;

import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.appointmentplannerserver.data.AuthToken;
import net.internalerror.appointmentplannerserver.data.User;
import net.internalerror.appointmentplannerserver.data.UserFriend;
import net.internalerror.appointmentplannerserver.data.service.AuthTokenService;
import net.internalerror.appointmentplannerserver.data.service.UserFriendService;
import net.internalerror.appointmentplannerserver.data.service.UserService;
import net.internalerror.appointmentplannerserver.rest.model.LoginModel;
import net.internalerror.appointmentplannerserver.rest.model.RegisterModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component("UserControllerImpl")
@AllArgsConstructor
public class UserControllerImpl {

    private final UserService userService;
    private final AuthTokenService authTokenService;
    private final UserFriendService userFriendService;

    public String registerUser(RegisterModel registerModel) {
        if (!userService.canRegister(registerModel).isError()) {
            userService.register(registerModel);
            return HttpStatus.OK.name();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Email already occupied");
        }
    }

    public String loginUser(LoginModel loginModel) {
        Optional<User> user = userService.getUser(loginModel);

        if (user.isPresent()) {
            return userService.login(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login failed");
        }
    }

    public String createFriendRequest(String authToken) {
        Optional<AuthToken> dbAuthToken = authTokenService.findByToken(authToken);
        if (dbAuthToken.isPresent()) {
            User user = dbAuthToken.get().getUser();
            return userFriendService.createFriendRequest(user);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Auth token found in database");
    }

    public String acceptFriendRequest(String authToken, String token) {
        if (userService.isAuthorized(authToken)) {
            Optional<AuthToken> dbAuthToken = authTokenService.findByToken(authToken);

            if (dbAuthToken.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Auth token found in database");
            }

            User user = dbAuthToken.get().getUser();

            Optional<UserFriend> userFriend = userFriendService.findByToken(token);

            if (userFriend.isPresent()) {
                if (userFriend.get().isValid()) {
                    userFriendService.accept(userFriend.get(), user);
                    return HttpStatus.OK.name();
                } else {
                    log.warn("Expired friend request");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token is expired");
                }
            } else {
                log.warn("No friend request with token {} found", token);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Friend Request found");
            }


        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized");
    }


    public String getFriends(String authToken) {
        Optional<AuthToken> dbAuthToken = authTokenService.findByToken(authToken);
        if (dbAuthToken.isPresent()) {
            User user = dbAuthToken.get().getUser();
            List<UserFriend> friends = userFriendService.findByAuthor(user);
            JsonArray array = new JsonArray();

            for (UserFriend friend : friends) {
                array.add(friend.getFriend().getUsername());
            }
            return array.toString();
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Auth token found in database");
    }
}
