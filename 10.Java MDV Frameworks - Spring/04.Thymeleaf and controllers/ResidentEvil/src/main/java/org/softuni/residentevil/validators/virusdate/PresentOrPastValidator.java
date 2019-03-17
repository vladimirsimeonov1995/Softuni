package org.softuni.residentevil.validators.virusdate;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class PresentOrPastValidator implements ConstraintValidator<PresentOrPast, LocalDate> {

    @Override
    public boolean isValid(LocalDate date,
                           ConstraintValidatorContext constraintValidatorContext) {

        if(date == null){
            return true;
        }

        LocalDate today = LocalDate.now();
        return today.isAfter(date);
    }
}
