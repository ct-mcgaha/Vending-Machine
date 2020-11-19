package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class VendingMachine {
	private String slot;
	private String name;
	private BigDecimal price;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getSlot() {
		return slot;
	}
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public VendingMachine() throws FileNotFoundException {
	List<String> invList = new ArrayList<>();
	try {		
		File inventory = new File ("vendingmachine.csv");
		Scanner fileScanner = new Scanner(inventory);
		while (fileScanner.hasNextLine()) {
			String invData = fileScanner.nextLine();
			invList.add(invData);
		}
	} catch (FileNotFoundException e) {
		System.out.println("file not found");
	}

	
	
	//Slot - A1, A2, etc.
	//Name
	//Price
	//Quantity
	
	List<String> candy = new ArrayList<>();
	List<String> chips = new ArrayList<>();
	List<String> drinks = new ArrayList<>();
	List<String> gum = new ArrayList<>();
	
	for (int i = 0; i < invList.size(); i++) {
		if (invList.get(0) == "A") {
		}
}
	}
	public abstract String purchaseMessage();

	public void message() {
		System.out.println("You didn't get anything.");
	}
}
}