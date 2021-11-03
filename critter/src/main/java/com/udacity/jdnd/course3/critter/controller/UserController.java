package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.*;
import com.udacity.jdnd.course3.critter.model.utils.CustomerEntityDTO;
import com.udacity.jdnd.course3.critter.model.utils.EmployeeEntityDTO;
import com.udacity.jdnd.course3.critter.model.utils.PetEntityDTO;
import com.udacity.jdnd.course3.critter.model.utils.ScheduleEntityDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = CustomerEntityDTO.convertCustomerDTOToEntity(customerDTO, getPetsFromIds(customerDTO.getPetIds()));
        return CustomerEntityDTO.convertEntityToCustomerDTO(customerService.save(customer), getIdsFromPets(customer.getPets()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customersDTOs = new ArrayList<>();
        List<Customer> customers = customerService.getAll();
        for (Customer customer : customers)
            customersDTOs.add(CustomerEntityDTO.convertEntityToCustomerDTO(customer, getIdsFromPets(customer.getPets())));
        return customersDTOs;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return CustomerEntityDTO.convertEntityToCustomerDTO(customerService.getByPetId(petId), getIdsFromPets(customerService.getByPetId(petId).getPets()));

    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return EmployeeEntityDTO.convertEntityToEmployeeDTO(employeeService.save(EmployeeEntityDTO.convertEmployeeDTOToEntity(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeEntityDTO.convertEntityToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> employeesDTOs = new ArrayList<>();
        List<Employee> employees = employeeService.getAvailableEmployeesWithSkills(employeeDTO.getSkills(), employeeDTO.getDate());
        for (Employee employee : employees)
            employeesDTOs.add(EmployeeEntityDTO.convertEntityToEmployeeDTO(employee));
        return employeesDTOs;
    }

    /**
     * Helper Functions
     */
    private List<Pet> getPetsFromIds(List<Long> petIds) {
        if(petIds == null) return null;
        List<Pet> pets = new ArrayList<>();
        for (Long id : petIds)
            pets.add(petService.getPetById(id));
        return pets;
    }

    List<Long> getIdsFromPets(List<Pet> pets) {
        if(pets == null) return null;
        List<Long> ids = new ArrayList<>();
        for( Pet pet: pets)
            ids.add(pet.getId());
        return ids;
    }
}
