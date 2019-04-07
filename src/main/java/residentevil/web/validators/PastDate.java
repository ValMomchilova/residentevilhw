package residentevil.web.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = PastDateValidator.class)
public @interface PastDate {
    String message() default "date must be past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
