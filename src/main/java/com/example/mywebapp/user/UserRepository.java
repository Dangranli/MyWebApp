package com.example.mywebapp.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface UserRepository extends CrudRepository <User, Integer> {
    public Long countById(Integer id);
}
