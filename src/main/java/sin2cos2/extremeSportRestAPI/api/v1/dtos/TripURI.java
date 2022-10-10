package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class TripURI implements Serializable {
    private String tripURI;
}
