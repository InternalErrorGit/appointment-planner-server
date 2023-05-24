package net.internalerror.appointmentplannerserver.rest.model;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class LoginModel {

    @Min(value = 5, message = "Username must at least have two letters")
    @Max(value = 255, message = "Username cannot exceed the limit of 255")
    @NotBlank(message = "Username cannot be blank")
    private final String username;


    @NotBlank(message = "Password cannot be blank")
    private final String password;


    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        return jsonObject;
    }


    public String toJsonString() {
        return toJsonObject().toString();
    }
}
