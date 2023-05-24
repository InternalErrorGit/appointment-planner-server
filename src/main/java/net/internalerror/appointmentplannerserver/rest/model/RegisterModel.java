package net.internalerror.appointmentplannerserver.rest.model;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterModel {

    @Min(value = 5, message = "Username must at least have two letters")
    @Max(value = 255, message = "Username cannot exceed the limit of 255")
    @NotBlank(message = "Username cannot be blank")
    private final String username;

    @Min(value = 3, message = "Firstname must at least have two letters")
    @Max(value = 255, message = "Firstname cannot exceed the limit of 255")
    @NotBlank(message = "Firstname cannot be blank")
    private final String firstname;

    @Min(value = 3, message = "Lastname must at least have two letters")
    @Max(value = 255, message = "Lastname cannot exceed the limit of 255")
    @NotBlank(message = "Lastname cannot be blank")
    private final String lastname;

    @NotBlank(message = "Password cannot be blank")
    private final String password;

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email cannot be blank")
    private final String email;

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("firstname", firstname);
        jsonObject.addProperty("lastname", lastname);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("email", email);
        return jsonObject;
    }


    public String toJsonString() {
        return toJsonObject().toString();
    }
}
