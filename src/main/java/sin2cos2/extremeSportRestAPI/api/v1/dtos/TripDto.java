package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TripDto implements Serializable {

    @Size(min = 3, max = 90)
    private String locationName;
    @Size(min = 3, max = 60)
    private String sportName;
    @DecimalMin("0")
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tripURI;
    private String locationURI;
    private String sportURI;
}

