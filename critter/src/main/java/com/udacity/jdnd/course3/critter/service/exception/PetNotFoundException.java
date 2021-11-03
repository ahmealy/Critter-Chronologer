package com.udacity.jdnd.course3.critter.service.exception;

import javax.persistence.EntityNotFoundException;

public class PetNotFoundException extends EntityNotFoundException {
    public PetNotFoundException(String message) {
        super(message);
    }
}