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
@Table(name = "Teacher", schema = "dbo", catalog = "Gradebook")
public class TeacherEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Rank")
    private String rank;
    @Column(name = "Department")
    private String department;

    @ManyToOne
    @JoinColumn(name = "PersonID", referencedColumnName = "ID", nullable = false)
    private PersonEntity person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id == that.id && rank.equals(that.rank) && department.equals(that.department) && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank, department, person);
    }
}
