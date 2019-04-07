package residentevil.web.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FieldsEqualityValidator.class })
public @interface FieldsEquality {

    String message() default "Password and confirm password must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Name of the first field that will be compared.
     *
     @return name
     */
    String firstFieldName();

    /**
     * Name of the second field that will be compared.
     *
     @return name
     */
    String secondFieldName();

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    public @interface List {
        FieldsEquality[] value();
    }
}

