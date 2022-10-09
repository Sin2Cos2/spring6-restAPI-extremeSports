package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegionDto {

    private String name;
    private String regionURI;
    private String countryURI;
}
