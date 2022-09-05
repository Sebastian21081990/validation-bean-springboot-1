package com.example.beanvalidation.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Target({ElementType.FIELD}) -> Declara que el constraint puede ser usado en campos de clases
 * @Retention(RetentionPolicy.RUNTIME) -> Indica que se trata de una anotación en tiempo de ejecución
 * @Constraint(validatedBy -> CommPreferenceValidator.class)
 * @Documented
 * String message() -> Este método retorna el mensaje de error cuando la validación falla
 * Class<?>[] groups() -> Este método permite la especificación de métodos de validación
 * Class<? extends Payload>[] payload() -> Este método se usa para enviar información de metadatos
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommPreferenceValidator.class)
@Documented
public @interface CommPreference {

    String message() default "Communication preference must be email or mobilePhone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
