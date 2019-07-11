package com.hcl.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="acc_id")
	private long accId;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="ref_acc_id")
	private long refAccId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRefAccId() {
		return refAccId;
	}

	public void setRefAccId(long refAccId) {
		this.refAccId = refAccId;
	}
	
	

}
