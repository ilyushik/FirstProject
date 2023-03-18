package org.example.util;

import org.example.DAO.PersonDao;
import org.example.Model.Person;
import org.springframework.validation.Errors;

public class Validator implements org.springframework.validation.Validator {
    private final PersonDao personDao;
    public Validator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDao.findByName(((Person) target).getName()).isPresent()) {
            errors.rejectValue("name", "", "The person with such name was created");
        }
    }
}
