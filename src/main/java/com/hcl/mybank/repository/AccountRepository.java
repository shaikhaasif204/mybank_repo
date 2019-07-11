package com.hcl.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.mybank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	public Account findByUserId(long uid);
}
