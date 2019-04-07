package residentevil.web.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (date == null){
            return false;
        }
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }
}
