package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SportDto implements Serializable {

    @Size(min = 3, max = 60)
    private String name;
    private String sportURI;
}
