package com.ileiwe.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role authority;

    public Authority(Role role){
        this.authority = role;
    }

}
