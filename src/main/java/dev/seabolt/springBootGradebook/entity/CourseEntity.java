package dev.seabolt.springBootGradebook.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Course", schema = "dbo", catalog = "Gradebook")
public class CourseEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Date dateStart;
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "TeacherID", referencedColumnName = "ID", nullable = false)
    private TeacherEntity teacher;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateStart, dateEnd);
    }


}
