package br.com.bookstore.security;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsVo implements Serializable {

    private String username;
    private String password;

}
