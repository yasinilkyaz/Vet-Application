package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.example.veterinarymanagementsystem.dto.request.AnimalRequest;
import com.example.veterinarymanagementsystem.dto.response.AnimalResponse;
import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Customer;
import com.example.veterinarymanagementsystem.mapper.AnimalMapper;
import com.example.veterinarymanagementsystem.repository.AnimalDao;
import com.example.veterinarymanagementsystem.repository.CustomerDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalDao animalDao;
    private final CustomerDao customerDao;
    private final AnimalMapper animalMapper;


    public AnimalManager(AnimalDao animalDao, CustomerDao customerDao, AnimalMapper animalMapper) {
        this.animalDao = animalDao;
        this.customerDao = customerDao;
        this.animalMapper = animalMapper;
    }


    @Override
        public AnimalResponse save(AnimalRequest saveRequest) {
        Optional<Customer> isCustomerExist=customerDao.findById(saveRequest.getCustomerId());
        if (isCustomerExist.isEmpty()){
            throw new RuntimeException("Bu hayvanın sahibi sistemde bulunamadı.");
        }else {
            Customer customer=isCustomerExist.get();
            Optional<Animal> isAnimalExist=animalDao.findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomer(saveRequest.getName(),saveRequest.getSpecies(),saveRequest.getBreed(),saveRequest.getGender(),saveRequest.getColour(),saveRequest.getDateOfBirth(),customer);
            if (isAnimalExist.isEmpty()) {
                Animal animal=animalMapper.asEntity(saveRequest);
                animal.setCustomer(customer);
                animalDao.save(animal);
                return animalMapper.asOutput(animal);
            }

        }
            throw new RuntimeException("Bu hayvan daha önce sisteme kaydedilmiştir.");
        }

    @Override
    public List<AnimalResponse> findAll() {
        return animalMapper.asOutput(animalDao.findAll());
    }

    @Override
    public AnimalResponse getById(Long id) {
        return animalMapper.asOutput(animalDao.findById(id).orElseThrow(() ->new RuntimeException(id+" id li hayvan bulunamadı")));
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest saveRequest) {
        Optional<Customer> isCustomerExist = customerDao.findById(saveRequest.getCustomerId());
        if (isCustomerExist.isEmpty()) {
            throw new RuntimeException("Bu hayvanın sahibi sistemde bulunamadı.");
        } else {
            Customer customer = isCustomerExist.get();
            Optional<Animal> animalFromDB = animalDao.findById(id);

            if (animalFromDB.isEmpty()) {
                throw new RuntimeException("Güncellemeye çalıştığınız hayvan sistemde bulunamadı.");
            }

            Animal animal = animalFromDB.get();
            if (!animal.getName().equals(saveRequest.getName())
                    || !animal.getSpecies().equals(saveRequest.getSpecies())
                    || !animal.getBreed().equals(saveRequest.getBreed())
                    || !animal.getGender().equals(saveRequest.getGender())
                    || !animal.getColour().equals(saveRequest.getColour())
                    || !animal.getDateOfBirth().equals(saveRequest.getDateOfBirth())
                    || !animal.getCustomer().equals(customer)) {


                Optional<Animal> isAnimalExist = animalDao.findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomer(
                        saveRequest.getName(), saveRequest.getSpecies(), saveRequest.getBreed(),
                        saveRequest.getGender(), saveRequest.getColour(), saveRequest.getDateOfBirth(), customer);

                if (isAnimalExist.isPresent()) {
                    throw new RuntimeException("Bu hayvan daha önce sisteme kayıt olmuştur.");
                }

                animal.setName(saveRequest.getName());
                animal.setSpecies(saveRequest.getSpecies());
                animal.setBreed(saveRequest.getBreed());
                animal.setGender(saveRequest.getGender());
                animal.setColour(saveRequest.getColour());
                animal.setDateOfBirth(saveRequest.getDateOfBirth());
                animal.setCustomer(customer);

                return animalMapper.asOutput(animalDao.save(animal));
            } else {
                throw new RuntimeException("Bu hayvan mevcut sistemde bulunuyor.");

            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Animal> animalFromDB=animalDao.findById(id);
        if (animalFromDB.isPresent()){
            animalDao.delete(animalFromDB.get());
        }else {
            throw new RuntimeException(id + " id li Hayvan sistemde bulunamadı !!!");
        }
    }
    @Override
    public List<AnimalResponse> findByOwnerName(String ownerName) {
        List<Animal> animals = animalDao.findByCustomerName(ownerName);
        return animalMapper.asOutput(animals);
    }
    @Override
    public List<AnimalResponse> findAnimalsByName(String name) {
        List<Animal> animals = animalDao.findByName(name);
        return animalMapper.asOutput(animals);
    }

    @Override
    public List<AnimalResponse> findAnimalsByOwner(Long customerId) {
        Optional<Customer> customer = customerDao.findById(customerId);

        if (customer.isEmpty()) {
            throw new RuntimeException("Sistemde böyle bir hayvan sahibi bulunamadı.");
        }

        List<Animal> animals = animalDao.findByCustomer(customer.get());
        return animalMapper.asOutput(animals);
    }
}
