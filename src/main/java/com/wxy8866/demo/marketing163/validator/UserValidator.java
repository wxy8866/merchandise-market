package com.wxy8866.demo.marketing163.validator;

import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by wxy8866 on 2017/4/13.
 */
@Component
public class UserValidator implements Validator
{
    @Autowired
    private IPersonService personService;

    @Override
    public boolean supports(Class<?> aClass)
    {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        Person user = (Person) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
        {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (personService.findByUsername(user.getUsername()) != null)
        {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
        {
            errors.rejectValue("password", "Size.userForm.password");
        }

        /*if (!user.getPasswordConfirm().equals(user.getPassword()))
        {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }*/
    }
}
