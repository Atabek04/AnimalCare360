package kz.logitex.lab.animalCare360.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    private String breed;
    private Integer age;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("animals")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private Set<HealthRecord> healthRecords;

    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private Set<Treatment> treatments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
