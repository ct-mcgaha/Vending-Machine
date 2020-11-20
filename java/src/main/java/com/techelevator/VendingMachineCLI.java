package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String END_PROGRAM = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, END_PROGRAM };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		List<VendingMachine> items = new ArrayList<>();
		items = readFile();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				// call displayItems() method
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				// call purchase() meth
			} else if (choice.equals(END_PROGRAM)) {
				System.out.println("Thank you!");
				System.exit(1);
			}
		}
	}
	
	
	public List<VendingMachine> readFile(){
		List<VendingMachine> invList = new ArrayList<>();
	
		try {		
			File inventory = new File ("vendingmachine.csv");
			Scanner fileScanner = new Scanner(inventory);
			while (fileScanner.hasNextLine()) {
				String invData = fileScanner.nextLine();
				String[] splitInvData = invData.split("\\|");
				String slot = splitInvData[0];
				String name = splitInvData[1];
				BigDecimal price = new BigDecimal(splitInvData[2]);
				String type = splitInvData[3];
				
				if (type.equals("Candy")) {
					Candy candy = new Candy(slot, name, price, 5);
					invList.add(candy);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}

		
		System.out.println(invList);  // for debugging purposes only

		return invList;
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}