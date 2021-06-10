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
public class BookDTO {
    @JsonProperty
    private String title;
    @JsonProperty
    private String author;
    @JsonProperty
    private int pages;
    @JsonProperty
    private boolean rented;
    @JsonProperty
    private String id;
}
