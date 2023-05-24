package net.internalerror.appointmentplannerserver.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "auth_token")
public class AuthToken extends DatabaseEntity {
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.REFRESH, optional = false, orphanRemoval = false)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Column(name = "token", nullable = false)
    protected String token;

    @Column(name = "valid_until")
    protected LocalDateTime validUntil;

    public boolean isValid() {
        return validUntil.isAfter(LocalDateTime.now());
    }

}