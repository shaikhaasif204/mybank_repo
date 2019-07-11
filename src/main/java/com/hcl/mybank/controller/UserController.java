package com.hcl.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.model.Transaction;
import com.hcl.mybank.model.User;
import com.hcl.mybank.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping
	public String addUser(@RequestBody User user){
		String result = "Success";
		
		if(user.getPassword().equals(user.getConPassword())) {
			userService.save(user);		
		}else {
			result = "Fail";
		}
		
		return result;
	}
	@PostMapping("/login")
	public String login(@RequestBody User user){
		String result = "Success";
		
		User u = userService.login(user.getUsername(), user.getPassword());
		if(u == null) result="Fail";
		
		return result;
	}
	@PostMapping("/funds/{id}")
	public void editUser(@RequestBody Transaction trans,@PathVariable Long id){
		userService.transFund(trans, id);;
	}
	@GetMapping("/payees/{id}")
	public List<User> getAllPayees(@PathVariable Long id) {
		 return userService.getAllPayees(id);
	}
	@GetMapping("/trans/{id}")
	public List<Transaction> getAllTrans(@PathVariable Long id,Pageable page) {
		 return userService.getAllTransactions(id,page);
	}
}
