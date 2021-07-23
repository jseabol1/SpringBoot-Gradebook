package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<AddressEntity, Long>,
        PagingAndSortingRepository<AddressEntity, Long> {

    List<AddressEntity> findByCity(String city);

}
