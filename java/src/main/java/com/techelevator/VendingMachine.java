package com.techelevator;

import java.math.BigDecimal;



public abstract class VendingMachine {
	private String slot;
	private String name;
	private BigDecimal price;
	private int quantity;
	private String stock;
	
	public VendingMachine() {
		
	}
	
	public String getStock() {
		return stock;
	}
	
<<<<<<< HEAD
=======
	public VendingMachine() {
		
	}
	
>>>>>>> caec782721d1817dbbcc32fa4aebf117531d1bad
	public VendingMachine(String slot, String name, BigDecimal price, int quantity) {
		super();
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public VendingMachine(String slot, String name, BigDecimal price, int quantity, String stock) {
		super();
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		if (quantity < 0) {
			this.quantity = 0;
		}
	}
	
	public String getSlot() {
		return slot;
	}
	
	public String getName() {
		return name;
	}
	

	
	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getPrice() {
		return price;
	
	}
	
	@Override
	public String toString() {
		return "\r" + " " + slot +" * " + name + " * " + price + " * " + quantity;
	}
	
	public abstract String purchaseMessage();

	public void message() {
		System.out.println("You didn't get anything.");
	}

	public static void displayItems() {
		System.out.println(VendingMachineCLI.items);
	}
	
	public static void purchase() {
	}
	
	public static void feedMoney() {
}
}
