package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VendingMachineCLITest {

	@Test
	public void MAIN_MENU_option_1_selects_DISPLAY_ITEMS() {
		VendingMachineCLI display = new VendingMachineCLI();
		
		
		List<VendingMachine> expected = display.readFile();
		List<VendingMachine> actual = 
	}
	
	@Test
	public void feed_5_dollars_returns_500() {
		VendingMachineCLI feedMoney = new VendingMachineCLI();
		
	}
	
	@Test
	public void select_a1_returns_Potato_Crisps() {
		VendingMachineCLI potatoCrisps = new VendingMachineCLI();
		
	}
	
	@Test
	public void select_b1_returns_PotatoCrisps_false() {
		
	}
	
	@Test 
	public void finish_transaction_returns_change() {
		
	}
	
	@Test
	public void finish_transaction_returns_coins() {
		
	}

}
