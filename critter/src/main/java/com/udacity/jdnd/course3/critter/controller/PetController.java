package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetDTO;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.utils.PetEntityDTO;
import com.udacity.jdnd.course3.critter.model.utils.ScheduleEntityDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return PetEntityDTO.convertEntityToPetDTO(petService.save(PetEntityDTO.convertPetDTOToEntity(petDTO, customerService)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetEntityDTO.convertEntityToPetDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petsDTOs = new ArrayList<>();
        List<Pet> pets = petService.getAll();
        for (Pet pet : pets)
            petsDTOs.add(PetEntityDTO.convertEntityToPetDTO(pet));
        return petsDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petsDTOs = new ArrayList<>();
        List<Pet> pets = petService.getPetsByCustomerId(ownerId);
        for (Pet pet : pets)
            petsDTOs.add(PetEntityDTO.convertEntityToPetDTO(pet));
        return petsDTOs;
    }
}
