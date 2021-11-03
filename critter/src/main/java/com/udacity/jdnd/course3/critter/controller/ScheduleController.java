package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.utils.ScheduleEntityDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = ScheduleEntityDTO.convertScheduleDTOToEntity(scheduleDTO, getEmployeesFromIds(scheduleDTO.getEmployeeIds()), getPetsFromIds(scheduleDTO.getPetIds()));
        return ScheduleEntityDTO.convertEntityToScheduleDTO(scheduleService.save(schedule), getIdsFromEmployee(schedule.getEmployees()), getIdsFromPets(schedule.getPets()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> schedulesDTOs = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getAll();
        for (Schedule schedule : schedules)
            schedulesDTOs.add(ScheduleEntityDTO.convertEntityToScheduleDTO(schedule, getIdsFromEmployee(schedule.getEmployees()), getIdsFromPets(schedule.getPets())));
        return schedulesDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> schedulesDTOs = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getSchedulesForPet(petId);
        for (Schedule schedule : schedules)
            schedulesDTOs.add(ScheduleEntityDTO.convertEntityToScheduleDTO(schedule, getIdsFromEmployee(schedule.getEmployees()), getIdsFromPets(schedule.getPets())));
        return schedulesDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> schedulesDTOs = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getSchedulesForEmployee(employeeId);
        for (Schedule schedule : schedules)
            schedulesDTOs.add(ScheduleEntityDTO.convertEntityToScheduleDTO(schedule, getIdsFromEmployee(schedule.getEmployees()), getIdsFromPets(schedule.getPets())));
        return schedulesDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> schedulesDTOs = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getSchedulesForCustomer(customerId);
        for (Schedule schedule : schedules)
            schedulesDTOs.add(ScheduleEntityDTO.convertEntityToScheduleDTO(schedule, getIdsFromEmployee(schedule.getEmployees()), getIdsFromPets(schedule.getPets())));
        return schedulesDTOs;
    }

    /**
     *
     * Helper Functions
     *
     */
    private List<Employee> getEmployeesFromIds(List<Long> employeesIds) {
        if(employeesIds == null) return null;
        List<Employee> employees = new ArrayList<>();
        for (Long id : employeesIds)
            employees.add(employeeService.getEmployeeById(id));
        return employees;
    }

    private List<Pet> getPetsFromIds(List<Long> petsIds) {
        if(petsIds == null) return null;
        List<Pet> pets = new ArrayList<>();
        for (Long id : petsIds)
            pets.add(petService.getPetById(id));
        return pets;
    }

    List<Long> getIdsFromEmployee(List<Employee> employees) {
        if(employees == null) return null;
        List<Long> ids = new ArrayList<>();
        for( Employee employee: employees)
            ids.add(employee.getId());
        return ids;
    }

    List<Long> getIdsFromPets(List<Pet> pets) {
        if(pets == null) return null;
        List<Long> ids = new ArrayList<>();
        for( Pet pet: pets)
            ids.add(pet.getId());
        return ids;
    }
}
