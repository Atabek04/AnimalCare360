package kz.logitex.lab.animalCare360.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "health_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String symptoms;

    private String diagnosis;

    private String procedure;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @Override
    public int hashCode() {
        return Objects.hash(id, date, symptoms, diagnosis, procedure, notes);
    }
}
