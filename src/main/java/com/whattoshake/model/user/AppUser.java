package com.whattoshake.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppUser")
@Table(name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class AppUser extends AppUserDetails {
    @Column(name = "login", nullable = false, columnDefinition = "TEXT")
    private String login;
    @Column(name = "first_and_last_name", nullable = false, columnDefinition = "TEXT")
    private String firstAndLastName;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Override
    public String getUsername() {
        return getLogin();
    }
}
