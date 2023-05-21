package com.ewha.pudong.domain;

import com.ewha.pudong.config.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User extends BaseEntity{
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String username;

    @Column
    private String email;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String provider; //어떤 OAuth인지

    @Column
    private String providerId;

    @Builder
    public User(String username, String password, String email, String image, Role role, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String username, String image){
        this.username = username;
        this.image = image;

        return this;
    }
}
