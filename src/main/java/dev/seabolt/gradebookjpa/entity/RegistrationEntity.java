package dev.seabolt.gradebookjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Registration", schema = "dbo", catalog = "Gradebook")
public class RegistrationEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "ID", nullable = false)
    private CourseEntity courseByCourseId;

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "ID", nullable = false)
    private StudentEntity studentByStudentId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationEntity that = (RegistrationEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
