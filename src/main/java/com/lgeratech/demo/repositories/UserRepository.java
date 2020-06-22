package com.lgeratech.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lgeratech.demo.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findAll();
	List<User> findByName(String name);
}
