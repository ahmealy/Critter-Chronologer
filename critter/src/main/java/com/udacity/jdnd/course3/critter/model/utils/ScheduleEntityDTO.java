package com.udacity.jdnd.course3.critter.model.utils;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ScheduleEntityDTO {
    public static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule, List<Long> employeeIds, List<Long> petsIds)
    {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petsIds);
        return scheduleDTO;
    }

    public static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO, List<Employee> employees, List<Pet> pets)
    {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return schedule;
    }
}
