package net.internalerror.appointmentplannerserver.rest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.appointmentplannerserver.data.service.UserService;
import net.internalerror.appointmentplannerserver.rest.controller.impl.UserControllerImpl;
import net.internalerror.appointmentplannerserver.rest.model.LoginModel;
import net.internalerror.appointmentplannerserver.rest.model.RegisterModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    private final UserControllerImpl controller;

    @PostMapping("/api/user/register")
    public String registerUser(@RequestBody RegisterModel registerModel) {
        return controller.registerUser(registerModel);
    }

    @PostMapping("/api/user/login")
    public String loginUser(@RequestBody LoginModel loginModel) {
        return controller.loginUser(loginModel);
    }

    @GetMapping("/api/user/add-friend")
    public String createFriendRequest(@RequestParam(name = "authToken") String authToken) {
        return controller.createFriendRequest(authToken);
    }

    @PostMapping("/api/user/accept-friend-request")
    public String acceptFriendRequest(@RequestParam(name = "authToken") String authToken, @RequestParam(name = "token") String token) {
        if (userService.isAuthorized(authToken)) {
            return controller.acceptFriendRequest(authToken, token);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized");
    }

    @GetMapping("/api/user/get-friends")
    public String getFriends(@RequestParam(name = "authToken") String authToken) {
        if (userService.isAuthorized(authToken)) {
            return controller.getFriends(authToken);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized");
    }


}
