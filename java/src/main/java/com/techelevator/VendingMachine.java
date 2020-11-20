package com.techelevator;

import java.math.BigDecimal;



public abstract class VendingMachine {
	private String slot;
	private String name;
	private BigDecimal price;
	private int quantity;
	
	public VendingMachine(String slot, String name, BigDecimal price, int quantity) {
		super();
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

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
	
	@Override
	public String toString() {
		return "\r" + " " + slot +" * " + name + " * " + price + " * " + quantity;
	}
	
	public abstract String purchaseMessage();

	public void message() {
		System.out.println("You didn't get anything.");
	}

	public static void displayItems() {
		// shows each item (slot, name, price, *updated* quantity)
		// shows *SOLDOUT*
		// choice to return to main menu
		System.out.println();
		System.out.println();
		System.exit(1);
	}
	
	public static void purchase() {
		// new menu (purchase Menu) that shows new options
		// Feed Money / Select Product / Finish Transaction
		// Feed Money - input money to total
		// Select Product - 
			// shows products available
			// *option for "does not exist"
			// "sold out"
			// dispense product to customer
				// prints name, cost, money remaining
		// have option for sold out == 0;
	}
	
	public static void feedMoney() {
//		System.out.print("Please insert full dollar amounts: $1, $5, $10, or $20");
//		int dollars = userInput.nextInt();
//		dollars += currentMoney;
//		System.out.println(currentMoney);
	}
}
