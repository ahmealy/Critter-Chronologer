package com.udacity.jdnd.course3.critter.model.utils;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CustomerEntityDTO {
    public static CustomerDTO convertEntityToCustomerDTO(Customer customer, List<Long> petsIds)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        customerDTO.setPetIds(petsIds);
        return customerDTO;
    }

    public static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO, List<Pet> pets)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setPets(pets);
        return customer;
    }
}
