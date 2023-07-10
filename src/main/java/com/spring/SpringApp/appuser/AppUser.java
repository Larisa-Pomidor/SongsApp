package com.spring.SpringApp.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole role;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(@JsonProperty("name") String name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email,
                   @JsonProperty("role") AppUserRole role,
                   @JsonProperty("locked") Boolean locked,
                   @JsonProperty("enabled") Boolean enabled) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;
    }

    public AppUser(@JsonProperty("id") Integer id,
                   @JsonProperty("name") String name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email,
                   @JsonProperty("role") AppUserRole role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public AppUser(@JsonProperty("name") String name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email,
                   @JsonProperty("role") AppUserRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
