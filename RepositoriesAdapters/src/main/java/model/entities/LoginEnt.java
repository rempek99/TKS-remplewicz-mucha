package model.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginEnt implements Serializable {

    private String login;
    private String password;
    private String role;

}
