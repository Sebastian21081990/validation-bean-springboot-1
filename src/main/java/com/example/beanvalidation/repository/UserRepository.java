package com.example.beanvalidation.repository;

import com.example.beanvalidation.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
