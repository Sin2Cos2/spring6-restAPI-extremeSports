package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SportTripURI extends TripURI implements Serializable {
    private String sportURI;

    @Builder
    public SportTripURI(String tripURI, String sportURI) {
        super(tripURI);
        this.sportURI = sportURI;
    }
}
