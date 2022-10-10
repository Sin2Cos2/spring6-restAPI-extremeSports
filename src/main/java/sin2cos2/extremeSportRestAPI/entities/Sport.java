package sin2cos2.extremeSportRestAPI.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private Set<Trip> trips = new LinkedHashSet<>();
}