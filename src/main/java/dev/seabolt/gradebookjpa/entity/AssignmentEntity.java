package dev.seabolt.gradebookjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
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

    @Column(name = "Name")
    private String name;
    @Column(name = "PointsAvailable")
    private int pointsAvailable;
    @Column(name = "DateAssigned")
    private Date dateAssigned;
    @Column(name = "DateDue")
    private Date dateDue;
    @Column(name = "IsRepeatable")
    private boolean isRepeatable;
    @Column(name = "HasBonusPoints")
    private boolean hasBonusPoints;
    @Column(name = "TotalAverage")
    private Integer totalAverage;
    @Column(name = "HighScoreAverage")
    private Integer highScoreAverage;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "ID", nullable = false)
    private CourseEntity courseByCourseId;

    @ManyToOne
    @JoinColumn(name = "AssignmentTypeID", referencedColumnName = "ID", nullable = false)
    private AssignmentTypeEntity assignmentTypeByAssignmentTypeId;




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
