package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachine {

	public Candy(String slot, String name, BigDecimal price, int quantity) {
		super(slot, name, price, quantity);
	}

	public Candy() {
	
	}
	@Override
	public String purchaseMessage() {
		return "Munch Munch, Yum!";
		
	}
}