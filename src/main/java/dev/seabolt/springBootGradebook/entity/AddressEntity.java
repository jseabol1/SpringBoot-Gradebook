package dev.seabolt.springBootGradebook.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Address", schema = "dbo", catalog = "Gradebook")
public class AddressEntity {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String streetLine1;
    private String streetLine2;
    private String city;
    private String state;
    private int postalCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && postalCode == that.postalCode && streetLine1.equals(that.streetLine1) && Objects.equals(streetLine2, that.streetLine2) && city.equals(that.city) && state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetLine1, streetLine2, city, state, postalCode);
    }
}
