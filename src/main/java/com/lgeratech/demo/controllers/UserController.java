package com.lgeratech.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lgeratech.demo.models.User;
import com.lgeratech.demo.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user){
		userRepository.save(user);
		return new ResponseEntity<String>("User has been added succesfully", HttpStatus.OK);
	}
	
	@GetMapping(value = "/retrieveUsers")
	public ResponseEntity<List<User>> retrieveUserList(){
		List<User> users = userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name){
		List<User> users = userRepository.findByName(name);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User userRequest){
		Optional<User> user = userRepository.findById(userRequest.getId());
		if(user.isPresent()) {
			userRepository.save(user.get());
			return new ResponseEntity<String>("User has been updated succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Can't Find User by id = " + userRequest.getId(), HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return new ResponseEntity<String>("User has been deleted succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Can't Find User by id = " +  id, HttpStatus.BAD_REQUEST);
	}
	
	

}
