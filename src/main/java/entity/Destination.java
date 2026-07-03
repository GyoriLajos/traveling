package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city_name", nullable = false)
    private String cityName;
    @Column(nullable = false)
    private String region;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int popularity;

}
