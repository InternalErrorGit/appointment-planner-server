package net.internalerror.appointmentplannerserver.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_friend")
public class UserFriend extends DatabaseEntity {
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    protected User author;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "friend_id")
    protected User friend;

    @Column(name = "token", nullable = false, unique = true)
    protected String token;


    public boolean isValid() {
        return getCreateDate().plusHours(24L).isAfter(LocalDateTime.now());
    }
}