package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryDto implements Serializable {

    @Size(min = 3, max = 60)
    private String name;
    private String countryURI;
}
