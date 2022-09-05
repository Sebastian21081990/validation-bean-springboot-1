package com.example.beanvalidation.controller;

import com.example.beanvalidation.entities.User;
import com.example.beanvalidation.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @RequestBody -> Permite extraer la entrada de datos del body deserializarlo y convertirlo en un objeto java
     * @Valid -> Se usa a nivel del método
     *           Instancia el validador y valida el parámetro
     *           Si la validación falla lanza una MethodArgumentNotValidException
     *           Se mapea con 400 Bad Request
     *
     * Para validar los parámetros de un método
     * @Validated -> Se agrega esta anotación a nivel de clase
     * @Positive -> Se agrega la validación a lado del parámetro
     * Si falla la validación lanza una excepción ConstraintViolationException
     */
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid User user){
        userRepository.save(user);
        return ResponseEntity.ok("User data is valid");
    }

    @GetMapping("{id}")
    public Optional<User> getUser(@PathVariable @Positive Long id){
        return userRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handlerConstraintViolation(ConstraintViolationException ex){

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            errors.put("message", error.getMessage());
            errors.put("path", (error.getPropertyPath()).toString());
        });

        return errors;

    }

}
