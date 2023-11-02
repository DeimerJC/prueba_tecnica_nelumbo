package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.security.entity;

import java.util.Collection;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;

public class PrincipalUserEntity {//extends User 

	private static final long serialVersionUID = 570921669742286857L;
	
	private Long id;
    private String name;
    private String dni;

//    public PrincipalUserEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
//
//    public PrincipalUserEntity(Long id, String name, String dni, String userName, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.id = id;
//        this.name = name;
//        this.dni = dni;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
