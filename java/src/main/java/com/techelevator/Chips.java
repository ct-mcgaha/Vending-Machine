package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingMachine {
	

	public Chips(String slot, String name, BigDecimal price, int quantity) {
		super(slot, name, price, quantity);
			}

	public Chips() {
		
	}
	@Override
	public String purchaseMessage() {
		return "Crunch Crunch, Yum!";
	}

	
}
