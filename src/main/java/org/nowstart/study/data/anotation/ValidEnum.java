package org.nowstart.study.data.anotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidEnum.EnumValidator.class)
public @interface ValidEnum {

    String message() default "Invalid value. This is not permitted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum> enumClass();

    class EnumValidator implements ConstraintValidator<ValidEnum, String> {

        private ValidEnum annotation;

        @Override
        public void initialize(ValidEnum constraintAnnotation) {
            this.annotation = constraintAnnotation;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return this.annotation.enumClass().getEnumConstants() != null && Arrays.stream(this.annotation.enumClass().getEnumConstants()).anyMatch(enumValue -> value.equals(enumValue.toString()));
        }
    }
}
