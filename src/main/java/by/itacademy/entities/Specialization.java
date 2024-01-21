package by.itacademy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static by.itacademy.utils.Constants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor

@Entity
@Table(name = SPECIALIZATION)
public class Specialization {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = NAME)
    private String name;

    @Column(name = DISCIPLINE_COUNT)
    private Integer disciplineCount;

    @OneToMany(mappedBy = SPECIALIZATION_FIELD, cascade = ALL, fetch = EAGER)
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();
}
