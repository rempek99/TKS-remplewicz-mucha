package pl.lodz.p.it.topicmodels.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
