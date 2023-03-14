package br.ebr.agenda.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentials {

    private String email;
    private String password;

}