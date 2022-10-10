package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto implements Serializable {

    private String name;
    private String locationURI;
    private String regionURI;
    private String countryURI;
}

