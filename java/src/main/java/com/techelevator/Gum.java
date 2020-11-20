package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachine {

	public Gum(String slot, String name, BigDecimal price, int quantity) {
		super(slot, name, price, quantity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String purchaseMessage() {
		return "Chew Chew, Yum!";
	}
}
