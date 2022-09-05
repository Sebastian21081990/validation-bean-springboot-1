package com.example.beanvalidation.bootstrap;

import com.example.beanvalidation.entities.User;
import com.example.beanvalidation.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * La clase DataLoader escucha el ContextRefreshedEvent que se levanta cuando el ApplicationContext es inicializado
 */

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User user_a = new User();
        user_a.setFirstName("Jane");
        user_a.setLastName("Doe");
        user_a.setEmail("jdoe@gmail.com");
        user_a.setMobilePhone("202-555-1234");
        user_a.setCommPreference("email");
        userRepository.save(user_a);

        User user_b = new User();
        user_b.setFirstName("Jack");
        user_b.setLastName("Frost");
        user_b.setEmail("jfrost@gmail.com");
        user_b.setMobilePhone("202-555-5678");
        user_b.setCommPreference("email");
        userRepository.save(user_b);

    }

}
