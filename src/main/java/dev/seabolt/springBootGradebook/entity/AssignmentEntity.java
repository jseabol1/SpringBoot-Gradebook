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
@Table(name = "Assignment", schema = "dbo", catalog = "Gradebook")
public class AssignmentEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int pointsAvailable;
    private Date dateAssigned;
    private Date dateDue;
    private boolean isRepeatable;
    private boolean hasBonusPoints;
    private Integer totalAverage;
    private Integer highScoreAverage;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "ID", nullable = false)
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "AssignmentTypeID", referencedColumnName = "ID", nullable = false)
    private AssignmentTypeEntity assignmentType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentEntity that = (AssignmentEntity) o;
        return id == that.id && pointsAvailable == that.pointsAvailable && isRepeatable == that.isRepeatable && hasBonusPoints == that.hasBonusPoints && Objects.equals(name, that.name) && Objects.equals(dateAssigned, that.dateAssigned) && Objects.equals(dateDue, that.dateDue) && Objects.equals(totalAverage, that.totalAverage) && Objects.equals(highScoreAverage, that.highScoreAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pointsAvailable, dateAssigned, dateDue, isRepeatable, hasBonusPoints, totalAverage, highScoreAverage);
    }

}
