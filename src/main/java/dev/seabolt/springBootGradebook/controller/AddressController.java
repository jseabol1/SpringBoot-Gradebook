package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.AddressEntity;
import dev.seabolt.springBootGradebook.repo.AddressRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")
class AddressController extends ControllerBase<AddressEntity, AddressRepo> {

    AddressController(AddressRepo repository) {
        super(repository);
    }


    @GetMapping("/SearchByCity/{city}")
    List<AddressEntity> getByCity(@PathVariable String city) {

        List<AddressEntity> list = repository.findByCity(city);

        if (list.isEmpty()) {
            throw new RuntimeException("None found");
        }
        return list;
    }

    @PutMapping("/UpdateByID/{id}")
    AddressEntity updateByID(@RequestBody AddressEntity newAddress, @PathVariable Long id) {

        return repository.findById(id)
                .map(Address -> {
                    Address.setStreetLine1(newAddress.getStreetLine1());
                    Address.setStreetLine2(newAddress.getStreetLine2());
                    Address.setCity(newAddress.getCity());
                    Address.setState(newAddress.getState());
                    Address.setPostalCode(newAddress.getPostalCode());
                    return repository.save(Address);
                })
                .orElseThrow(() ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
