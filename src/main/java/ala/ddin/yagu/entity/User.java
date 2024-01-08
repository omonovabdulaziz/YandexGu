package ala.ddin.yagu.entity;


import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.entity.tmp.AbsLongEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbsLongEntity implements UserDetails {
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private Integer smsCode;
    private String location;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private Status status;
    @OneToOne
    private Car car;
    private Boolean isEnabled;
    private Boolean isConfirmed;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
