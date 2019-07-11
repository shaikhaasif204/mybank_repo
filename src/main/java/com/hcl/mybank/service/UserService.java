package com.hcl.mybank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.mybank.model.Account;
import com.hcl.mybank.model.Transaction;
import com.hcl.mybank.model.User;
import com.hcl.mybank.repository.AccountRepository;
import com.hcl.mybank.repository.TransactionRepository;
import com.hcl.mybank.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accRepository;
	
	@Autowired
	TransactionRepository transRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<User> getAllPayees(long id) {
		return userRepository.findPayees(id);
	}

	public List<Transaction> getAllTransactions(long id,Pageable page) {
		return transRepository.findByUserId(id,page);
	}

	public User getUserById(long id) {
		Optional<User> empOp = userRepository.findById(id);
		return empOp.isPresent() ? empOp.get() : null;
	}

	public User login(String username,String password) {
		User user = userRepository.login(username,password);
		return user;
	}
	
	public void save(User user) {
		User u = userRepository.save(user);
		Account acc = new Account();
		acc.setType("SAVING");
		acc.setBalance(1000d);
		acc.setUserId(u.getId());
		accRepository.save(acc);
	}

	public void transFund(Transaction trans,long userId) {
		
		trans.setUserId(userId);
		Account fromAccnt = accRepository.findByUserId(userId);
		Optional<Account> toAccntOp = accRepository.findById(trans.getRefAccId());
		if(!toAccntOp.isPresent()) throw new IllegalArgumentException("Account is not found");
		Account toAccnt = toAccntOp.get();
		double minBal = fromAccnt.getBalance() - trans.getAmount();
		double addBal = toAccnt.getBalance() + trans.getAmount();
		fromAccnt.setBalance(minBal);
		toAccnt.setBalance(addBal);
		transRepository.save(trans);
	}
	
	

	
}
