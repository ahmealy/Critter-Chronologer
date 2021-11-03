package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerRepository customerRepository;

    public Schedule save (Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAll () {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPet (Long petId) {
        Pet pet = petService.getPetById(petId);
        return scheduleRepository.findByPetsContaining(pet);
    }

    public List<Schedule> getSchedulesForEmployee (Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return scheduleRepository.findByEmployeesContaining(employee);
    }

    public List<Schedule> getSchedulesForCustomer (Long customerId){
        Customer customer = customerRepository.getOne(customerId);
        List<Schedule> schedules = scheduleRepository.findByPetsIn(customer.getPets());
        return schedules;
    }
}