package net.internalerror.appointmentplannerserver.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import net.internalerror.appointmentplannerserver.rest.model.RegisterModel;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"hashedPassword"})
@Entity
@Table(name = "user")
public class User extends DatabaseEntity {
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "username", nullable = false, unique = true)
    protected String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @NotBlank(message = "Firstname cannot be empty")
    @Column(name = "firstname", nullable = false)
    protected String firstname;

    @NotBlank(message = "Lastname cannot be empty")
    @Column(name = "lastname", nullable = false)
    protected String lastname;

    @Column(name = "hashed_password", nullable = false)
    protected String hashedPassword;

    @Column(name = "request_change_password", unique = true)
    protected String requestChangePassword;

    public static User createUser(RegisterModel registerModel) {
        User user = new User();

        user.setUsername(registerModel.getUsername());
        user.setFirstname(registerModel.getFirstname());
        user.setLastname(registerModel.getLastname());
        user.setEmail(registerModel.getEmail());
        user.setHashedPassword(registerModel.getPassword());

        return user;
    }

}