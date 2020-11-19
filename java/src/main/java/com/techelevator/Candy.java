package com.techelevator;

import java.io.FileNotFoundException;

public class Candy extends VendingMachine {

	public Candy() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String purchaseMessage() {
		return "Munch Munch, Yum!";
		
	}
}
