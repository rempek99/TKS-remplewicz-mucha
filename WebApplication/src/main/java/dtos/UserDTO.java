package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String roleOfUser;
    @JsonProperty
    private boolean isActive;
    @JsonProperty
    private String id;
    @JsonProperty
    private String login;
    @JsonProperty
    private String password;
}
