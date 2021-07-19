package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<AddressEntity, Long> {

    List<AddressEntity> findByCity(String city);
}
