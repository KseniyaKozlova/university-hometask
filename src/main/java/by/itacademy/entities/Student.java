package by.itacademy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static by.itacademy.utils.Constants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor

@Entity
@Table(name = STUDENT)
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = NAME)
    private String name;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = SPECIALIZATION_ID)
    @ToString.Exclude
    private Specialization specialization;
}
