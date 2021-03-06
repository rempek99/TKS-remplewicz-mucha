package pl.lodz.p.it.repositoriesadapters.model_ent.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginEnt implements Serializable {

    private String login;
    private String password;
    private String role;

}
