package com.hcl.mybank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.mybank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public List<Transaction> findByUserId(long uid,Pageable page);
}
