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
		return slot +"     " + name + "     " + price + "     " + quantity;
	}
	public abstract String purchaseMessage();

	public void message() {
		System.out.println("You didn't get anything.");
	}
}
