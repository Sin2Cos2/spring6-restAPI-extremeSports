package sin2cos2.extremeSportRestAPI.api.v1.dtos;

import lombok.*;
import sin2cos2.extremeSportRestAPI.entities.Sport;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationTripURI extends TripURI implements Serializable {
    private String locationURI;

    @Builder
    public LocationTripURI(String tripURI, String locationURI) {
        super(tripURI);
        this.locationURI = locationURI;
    }
}
