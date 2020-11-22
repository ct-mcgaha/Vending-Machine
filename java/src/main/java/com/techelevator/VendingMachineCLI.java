package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String END_PROGRAM = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			END_PROGRAM };

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT,
			PURCHASE_MENU_FINISH_TRANSACTION };
	private static final String NO_BUTTON = "That's not an option.";
	public static List<VendingMachine> items = new ArrayList<>();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public VendingMachineCLI() {
	}

	Scanner userInput = new Scanner(System.in);
	int dollars = 0;
	int currentMoney = 0;
	BigDecimal cm = new BigDecimal("0.00");
	BigDecimal orderPrice = new BigDecimal("0.00");
	BigDecimal coinChange = new BigDecimal("0.00");
	BigDecimal quartersInADollar = new BigDecimal("4.00");
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public void run() throws IOException {
		items = readFile();
		try (vmLogger testLog = new vmLogger("purchaseLog.txt")) {
			boolean shouldLoop = true;
			while (shouldLoop) {
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					VendingMachine.displayItems();
				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					shouldLoop = false;
					while (!shouldLoop) {
						String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
						if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
							System.out.println("Please insert full dollar amounts: $1, $5, $10, or $20 >>> ");
							int dollars = Integer.parseInt(userInput.nextLine());
							currentMoney += dollars;
							BigDecimal bigDollars = new BigDecimal(dollars);
							cm = cm.add(bigDollars);
							System.out.println("Current Money: $" + cm);
							testLog.write(dateTime.format(now) + "   " + PURCHASE_MENU_FEED_MONEY + "   $" + bigDollars
									+ "  $" + cm);
						} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
							System.out.println("Please choose the slot number item you'd like to purchase: ");
							System.out.println(items);
							String keyChoice = userInput.nextLine().toUpperCase();
							boolean found = false;
							for (VendingMachine slot : items) {
								if (slot.getSlot().equals(keyChoice)) {
									found = true;
									if (slot.getQuantity() > 0) {
										cm = cm.subtract(slot.getPrice());
										if (cm.intValue() > 0) {
											System.out.println(slot.getName() + ", $" + slot.getPrice() + ", $" + cm
													+ ", " + slot.purchaseMessage());
											slot.setQuantity(slot.getQuantity() - 1);
											if (slot.getQuantity() == 0) {
												slot.setName(slot.getName() + " ***** SOLD OUT ***** ");
											}
											testLog.write(dateTime.format(now) + "   " + slot.getName() + " "
													+ slot.getSlot());
										} else {
											cm = cm.add(slot.getPrice());
											System.out.println("Not enough money!");
										}
									} else {
										System.out.println("Out Of Stock!");
									}
								}
							}
							if (!found) {
								System.out.println(NO_BUTTON);
							}
						} else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
							coinChange = (cm.subtract(orderPrice));
							testLog.write(dateTime.format(now) + "   CHANGE:   " + coinChange);
							int quarter = 0;
							BigDecimal bigQuarter = new BigDecimal("0.25");
							int dime = 0;
							BigDecimal bigDime = new BigDecimal("0.10");
							int nickel = 0;
							BigDecimal bigNickel = new BigDecimal("0.05");
							boolean changeLoop1 = true;
							boolean changeLoop2 = true;
							boolean changeLoop3 = true;
							while (changeLoop1) {
								int res = coinChange.compareTo(bigQuarter);
								if (res == 1) {
									coinChange = coinChange.subtract(bigQuarter);
									quarter++;
								}
								if (res == 0) {
									coinChange = coinChange.subtract(bigQuarter);
									quarter++;
									changeLoop1 = false;
								}
								if (res == -1) {
									changeLoop1 = false;
								}
							}
							while (changeLoop2) {
								int res2 = coinChange.compareTo(bigDime);
								if (res2 == 1) {
									coinChange = coinChange.subtract(bigDime);
									dime++;
								}
								if (res2 == 0) {
									coinChange = coinChange.subtract(bigDime);
									dime++;
									changeLoop2 = false;
								}
								if (res2 == -1) {
									changeLoop2 = false;
								}
							}
							while (changeLoop3) {
								int res3 = coinChange.compareTo(bigNickel);
								if (res3 == 1) {
									coinChange = coinChange.subtract(bigNickel);
									nickel++;
								}
								if (res3 == 0) {
									coinChange = coinChange.subtract(bigNickel);
									nickel++;
									changeLoop3 = false;
								}
								if (res3 == -1) {
									changeLoop3 = false;
								}
							}
							System.out.println("Thanks for using this ol' vending machine! \nYour change is: " + quarter
									+ " quarters " + dime + " dimes and " + nickel + " nickels");
							cm = BigDecimal.valueOf(0.00);
							shouldLoop = true;
						}
					}
				} else if (choice.equals(END_PROGRAM)) {
					System.out.println("Thank you!");
					System.exit(1);
				}
			}
		}
	}

	public List<VendingMachine> readFile() {
		List<VendingMachine> invList = new ArrayList<>();

		try {
			File inventory = new File("vendingmachine.csv");
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
				if (type.equals("Chip")) {
					Chips chips = new Chips(slot, name, price, 5);
					invList.add(chips);
				}
				if (type.equals("Gum")) {
					Gum gum = new Gum(slot, name, price, 5);
					invList.add(gum);
				}
				if (type.equals("Drink")) {
					Drinks drinks = new Drinks(slot, name, price, 5);
					invList.add(drinks);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		return invList;
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}