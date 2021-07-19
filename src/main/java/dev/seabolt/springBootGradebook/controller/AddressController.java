package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.AddressEntity;
import dev.seabolt.springBootGradebook.repo.AddressRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Address")
class AddressController {

    private final AddressRepo repository;

    AddressController(AddressRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<AddressEntity> list() {
        List<AddressEntity> list = new ArrayList<>();
        for(AddressEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    AddressEntity newAddress(@RequestBody AddressEntity newAddress) {
        return repository.save(newAddress);
    }


    @GetMapping("/GetByID/{id}")
    AddressEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("address not found"));
    }

    @GetMapping("/SearchByCity/{city}")
    List<AddressEntity> getByCity(@PathVariable String city) {

        List<AddressEntity> list = repository.findByCity(city);

        if(list.isEmpty()){
            throw new RuntimeException("None found");
        }
        return list;
    }

    @PutMapping("/UpdateByID/{id}")
    AddressEntity replaceAddress(@RequestBody AddressEntity newAddress, @PathVariable Long id) {

        return repository.findById(id)
                .map(Address -> {
                    Address.setStreetLine1(newAddress.getStreetLine1());
                    Address.setStreetLine2(newAddress.getStreetLine2());
                    Address.setCity(newAddress.getCity());
                    Address.setState(newAddress.getState());
                    Address.setPostalCode(newAddress.getPostalCode());
                    return repository.save(Address);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteAddress(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
