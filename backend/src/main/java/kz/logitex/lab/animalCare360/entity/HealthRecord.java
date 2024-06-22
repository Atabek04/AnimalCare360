package kz.logitex.lab.animalCare360.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "health_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String symptoms;

    private String diagnosis;

    private String procedure;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    private String notes;
}
