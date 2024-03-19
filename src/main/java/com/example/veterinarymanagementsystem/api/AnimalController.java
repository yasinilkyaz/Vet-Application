package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.example.veterinarymanagementsystem.dto.request.AnimalRequest;
import com.example.veterinarymanagementsystem.dto.response.AnimalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animal")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll(){return animalService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getById(@PathVariable("id") Long id){
        return animalService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse save(@RequestBody AnimalRequest animalRequest){
        return animalService.save(animalRequest);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable("id") Long id, @RequestBody AnimalRequest animalRequest){
        return animalService.update(id,animalRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        animalService.deleteById(id);
    }

    @GetMapping("/byOwnerName")
    public ResponseEntity<List<AnimalResponse>> getAnimalsByOwnerName(@RequestParam String ownerName) {
        try {
            List<AnimalResponse> animals = animalService.findByOwnerName(ownerName);
            return new ResponseEntity<>(animals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byName")
    public ResponseEntity<List<AnimalResponse>> getAnimalsByName(@RequestParam String name) {
        List<AnimalResponse> animals = animalService.findAnimalsByName(name);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<AnimalResponse>> getAnimalsByOwner(@RequestParam Long customerId) {
        List<AnimalResponse> animals = animalService.findAnimalsByOwner(customerId);
        return ResponseEntity.ok(animals);
    }
}
