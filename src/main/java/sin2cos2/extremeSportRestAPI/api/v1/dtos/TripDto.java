package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TripDto implements Serializable {

    @Size(max = 90)
    private String locationName;
    @Size(max = 60)
    private String sportName;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocationTripURI locationTripURI;
    private SportTripURI sportTripURI;
}

