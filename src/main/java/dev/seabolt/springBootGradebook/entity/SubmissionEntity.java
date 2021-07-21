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
@Table(name = "Submission", schema = "dbo", catalog = "Gradebook")
public class SubmissionEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PointsAwarded")
    private Integer pointsAwarded;
    @Column(name = "DateSubmitted")
    private Date dateSubmitted;
    @Column(name = "Comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "ID", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "AssignmentID", referencedColumnName = "ID", nullable = false)
    private AssignmentEntity assignment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionEntity that = (SubmissionEntity) o;
        return id == that.id && pointsAwarded.equals(that.pointsAwarded) && dateSubmitted.equals(that.dateSubmitted) && comment.equals(that.comment) && student.equals(that.student) && assignment.equals(that.assignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pointsAwarded, dateSubmitted, comment, student, assignment);
    }
}
