package com.udacity.jdnd.course3.critter.model.utils;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeDTO;
import org.springframework.beans.BeanUtils;

public class EmployeeEntityDTO {
    public static EmployeeDTO convertEntityToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public static Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }
}
