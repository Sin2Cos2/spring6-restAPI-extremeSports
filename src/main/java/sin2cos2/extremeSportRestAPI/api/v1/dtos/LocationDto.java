package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto {

    private String name;
    private String locationURI;
    private String regionURI;
    private String countryURI;
}

