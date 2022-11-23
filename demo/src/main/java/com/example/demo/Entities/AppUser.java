package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, updatable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isEnabled;

    private Boolean isNonExpired;

    @OneToOne(mappedBy = "appUser",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AppUserInformation userInformation;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(this.role.getAuthorities()).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
