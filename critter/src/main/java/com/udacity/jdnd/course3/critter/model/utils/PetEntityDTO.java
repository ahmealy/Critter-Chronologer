package com.udacity.jdnd.course3.critter.model.utils;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.beans.BeanUtils;

public class PetEntityDTO {
    public static PetDTO convertEntityToPetDTO(Pet pet)
    {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    public static Pet convertPetDTOToEntity(PetDTO petDTO, CustomerService customerService)
    {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setOwner(customerService.getById(petDTO.getOwnerId()));
        return pet;
    }
}