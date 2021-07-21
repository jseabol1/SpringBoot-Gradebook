package dev.seabolt.springBootGradebook.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Student", schema = "dbo", catalog = "Gradebook")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private long id;


    private String major;

    @OneToOne
    @JoinColumn(name = "PersonID", referencedColumnName = "ID", nullable = false)
    private PersonEntity person;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id && major.equals(that.major) && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, major, person);
    }
}
