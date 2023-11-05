package br.com.fiap.basepet.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.basepet.user.User;

@Service
public class PetService {
    
    @Autowired
    PetRepository repository;

    public List<Pet> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var pet = repository.findById(id);
        if(pet.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Pet pet) {
        repository.save(pet);
    }

    public boolean catchPet(Long id, User user) {
        var optionalPet = repository.findById(id);

        if (optionalPet.isEmpty()) return false;

        var pet = optionalPet.get();

        if (pet.getUser() != null) return false;

        pet.setUser(user);

        repository.save(pet);
        return true;
        
    }

}
