package kz.logitex.lab.animalCare360.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String manufacturer;

    @OneToMany(mappedBy = "medication")
    private Set<HealthRecord> healthRecords;

    @OneToMany(mappedBy = "medication")
    private Set<Treatment> treatments;
}