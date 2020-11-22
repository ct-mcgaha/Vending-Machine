package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends VendingMachine {

	public Drinks(String slot, String name, BigDecimal price, int quantity) {
		super(slot, name, price, quantity);
		// TODO Auto-generated constructor stub
	}

	public Drinks() {
		
	}
	
	@Override
	public String purchaseMessage() {
		return "Glug Glug, Yum!";
	}

}
