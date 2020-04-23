/*
 * Ben Mighall
 * Project 4
 * CSCI 112
 * July 21st, 2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		ArrayList <Order> orders = new ArrayList <Order>();
		
		System.out.println("Hello, and welcome to the Sears Customer Service Assistant!");
		
		System.out.println("\nImporting data from customer file...\n");
		
		//Import file
		File file = new File("dataP4.txt");
		String[] tokens;
		Scanner importData = null;
		int numOrders = 0; //index for a given ArrayList is numOrders - 1
		try {
			importData = new Scanner(file);
			System.out.println("Customer data imported into program successfully!\n");
			while(importData.hasNextLine()){
				tokens = importData.nextLine().split(",");
				if(tokens.length == 1){
					//Date: one token, no delimiters
					orders.add(new Order(tokens[0]));
					numOrders++;
				}else if(tokens.length == 2){
					//Name/CC number: two tokens, one delimiter
					orders.get(numOrders - 1).setCustomerData(tokens[0], tokens[1]);
				}else if(tokens.length == 3){
					//Items: three tokens, two delimiters
					orders.get(numOrders - 1).addPurchase(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
				}
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("\nException: File not found. Data import failed.");
		}finally{
			importData.close();
		}
			
		int choice = 0;
		Scanner scan = new Scanner(System.in);
		
		while(choice != 6){
			System.out.println("Please choose from the following options by entering the number of your selection:\n");
			System.out.println("1. Create a new order");
			System.out.println("2. Add additional items to an order");
			System.out.println("3. Make changes to an existing order's information");
			System.out.println("4. View existing information for a specific order");
			System.out.println("5. View ALL saved order data in the system");
			System.out.println("6. Exit program");
			choice = scan.nextInt();
			scan.nextLine(); //Placed in here to solve issue with skipping waiting for user input, based on what I found on StackOverflow
			
			if(choice == 1){
				//creating new order from customer input
				System.out.println("\nTo enter a new order into the system, please enter the date that the order was started.");
				orders.add(new Order(scan.nextLine()));
				numOrders++;
				System.out.println("\nPlease enter the customer's name.");
				String newCustomer = scan.nextLine();
				System.out.println("\nPlease enter the customer's credit card number.");
				String newCC = scan.nextLine();
				orders.get(numOrders - 1).setCustomerData(newCustomer, newCC);
				System.out.println("\nPlease enter the number of purchases in the order.");
				int numPurchases = scan.nextInt();
				scan.nextLine(); //Placed in here to solve issue with skipping waiting for user input, based on what I found on StackOverflow
				for (int i = 0; i < numPurchases; i++){
					System.out.println("\nFor purchase #" + (i+1) + ", please enter the name of the item.");
					String newItem = scan.nextLine();
					System.out.println("\nFor purchase #" + (i+1) + ", please enter the description of the item.");
					String newDescription = scan.nextLine();
					System.out.println("\nFor purchase #" + (i+1) + ", please enter the cost of the item.");
					double newCost = scan.nextDouble();
					scan.nextLine(); //Placed in here to solve issue with skipping waiting for user input, based on what I found on StackOverflow
					orders.get(numOrders - 1).addPurchase(newItem, newDescription, newCost);
				}
				System.out.println("\nThank you. The following order has been registered in the system:\n");
				orders.get(numOrders - 1).printData();
				orders.get(numOrders - 1).totalCost();
				System.out.println();
			}else if(choice == 2){
				//Adding additional items to order
				System.out.println("\nTo add purchases to an order, please choose from the following "
						+ "orders by entering the order number of your selection.");
				for (int i = 0; i < numOrders; i++){
					System.out.println("Order #" + (i + 1) + ":");
					orders.get(i).basicPrint();
					System.out.println();
				}
				choice = scan.nextInt();
				if(choice > 0 && choice <= numOrders){
					System.out.println("How many additional purchases would you like to add to order #" + choice + "?");
					int numPurchases = scan.nextInt();
					scan.nextLine(); //Placed in here to solve issue with skipping waiting for user input, based on what I found on StackOverflow
					for (int i = 0; i < numPurchases; i++){
						System.out.println("\nFor purchase #" + (i+1) + ", please enter the name of the item.");
						String newItem = scan.nextLine();
						System.out.println("\nFor purchase #" + (i+1) + ", please enter the description of the item.");
						String newDescription = scan.nextLine();
						System.out.println("\nFor purchase #" + (i+1) + ", please enter the cost of the item.");
						double newCost = scan.nextDouble();
						scan.nextLine(); //Placed in here to solve issue with skipping waiting for user input, based on what I found on StackOverflow
						orders.get(choice - 1).addPurchase(newItem, newDescription, newCost);
					}
					System.out.println("\nThank you. Here is the modified order, after adding the new purchases:\n");
					orders.get(choice - 1).printData();
					orders.get(choice - 1).totalCost();
					System.out.println();
				}else{
					System.out.println("Sorry, that order number does not exist. Returning you to the main menu.\n");
				}
			}else if(choice == 3){
				//make changes to existing order's information
				choice = 1;
				while(choice == 1){
					System.out.println("\nTo make changes to a specific order, please choose from the following "
							+ "orders by entering the order number of your selection.");
					for (int i = 0; i < numOrders; i++){
						System.out.println("Order #" + (i + 1) + ":");
						orders.get(i).basicPrint();
						System.out.println();
					}
					int selection = scan.nextInt(); //index of the order is selection - 1
					if(selection > 0 && selection <= numOrders){
						System.out.println("\nTo make changes to order #" + selection + ", please choose from the following options "
								+ "by entering the number of your selection:\n");
						System.out.println("1. Change customer name on the order");
						System.out.println("2. Change customer credit card on the order");
						System.out.println("3. Change order date");
						System.out.println("4. Change purchase information, including name, description or cost of the item");
						choice = scan.nextInt();
						scan.nextLine();
						if(choice == 1){
							//change customer name on the order
							System.out.println("\nPlease enter the new name of the customer on this order:");
							String newName = scan.nextLine();
							orders.get(selection - 1).changeCustomerName(newName);
							System.out.println("\nThank you. Here is the modified order, after changing the customer name:\n");
							orders.get(selection - 1).printData();
							orders.get(selection - 1).totalCost();
							System.out.println();
						}else if (choice == 2){
							//change cc number on the order
							System.out.println("\nPlease enter the new credit card number for the customer on this order:");
							String newCC = scan.nextLine();
							orders.get(selection - 1).changeCustomerCC(newCC);
							System.out.println("\nThank you. Here is the modified order, after changing the customer's credit card:\n");
							orders.get(selection - 1).printData();
							orders.get(selection - 1).totalCost();
							System.out.println();
						}else if (choice == 3){
							//change order date
							System.out.println("\nPlease enter the new order date:");
							String newDate = scan.nextLine();
							orders.get(selection - 1).changeOrderDate(newDate);
							System.out.println("\nThank you. Here is the modified order, after changing the order date:\n");
							orders.get(selection - 1).printData();
							orders.get(selection - 1).totalCost();
							System.out.println();
						}else if (choice == 4){
							//change purchase info
							System.out.println("\nTo make changes to a specific purchase, please choose from the following "
									+ "purchases by entering the number of your selection.");
							orders.get(selection - 1).printPurchases();
							int purchaseNum = scan.nextInt() - 1;
							System.out.println("\nWhat changes would you like to make to this purchase?");
							System.out.println("1. Change purchase name");
							System.out.println("2. Change purchase description");
							System.out.println("3. Change purchase cost");
							choice = scan.nextInt();
							scan.nextLine();
							if(choice == 1){
								//change purchase name
								System.out.println("\nWhat would you like the purchase's new name to be?");
								orders.get(selection - 1).changePurchaseName(purchaseNum, scan.nextLine());
								System.out.println("\nThank you. Here is the modified order, after changing the purchase's name:\n");
								orders.get(selection - 1).printData();
								orders.get(selection - 1).totalCost();
								System.out.println();
							}else if(choice == 2){
								//change purchase description
								System.out.println("\nWhat would you like the purchase's new description to be?");
								orders.get(selection - 1).changePurchaseDescription(purchaseNum, scan.nextLine());
								System.out.println("\nThank you. Here is the modified order, after changing the purchase's description:\n");
								orders.get(selection - 1).printData();
								orders.get(selection - 1).totalCost();
								System.out.println();
							}else if(choice == 3){
								//change purchase cost
								System.out.println("\nWhat would you like the purchase's new cost to be?");
								orders.get(selection - 1).changePurchaseCost(purchaseNum, scan.nextDouble());
								System.out.println("\nThank you. Here is the modified order, after changing the purchase's cost:\n");
								orders.get(selection - 1).printData();
								orders.get(selection - 1).totalCost();
								System.out.println();
								
							}else{
								System.out.println("You have made an invalid selection.");
							}
							
						}else{
							System.out.println("Sorry, that is an invalid response.");
						}
						
						System.out.println("Would you like to continue changing order data, or would you like to return to the main menu?");
						System.out.println("Enter 1 to continue and 0 to return to the main menu.");
						choice = scan.nextInt();
						if (choice != 1 && choice != 0){
							System.out.println("That is an invalid selection. Returning you to the main menu.\n");
						}
					}else{
						System.out.println("\nSorry, that order number does not exist. Returning to the main menu.\n");
						choice = 0;
					}
				}
			}else if(choice == 4){
				System.out.println("\nTo view detailed information for a specific order, please choose from the following "
						+ "orders by entering the order number of your selection.");
				for (int i = 0; i < numOrders; i++){
					System.out.println("Order #" + (i + 1) + ":");
					orders.get(i).basicPrint();
					System.out.println();
				}
				choice = scan.nextInt();
				if(choice > 0 && choice <= numOrders){
					System.out.println("\nThank you. Here is the information for that specific order:\n");
					orders.get(choice - 1).printData();
					orders.get(choice - 1).totalCost();
					System.out.println();
				}else{
					System.out.println("Sorry, that order number does not exist. Returning you to the main menu.\n");
				}
			}else if(choice == 5){
				//print ALL saved data
				System.out.println("\nThe following is the entirety of the saved order information inside the system:\n");
				for (int i = 0; i < numOrders; i++){
					System.out.println("Order #" + (i + 1) + ":");
					orders.get(i).printData();
					orders.get(i).totalCost();
					System.out.println();
				}
			}else if(choice == 6){
				scan.close();
				
				System.out.println("\nThank you for using the Sears Virtual Customer Service Representative Assistant program! Goodbye!");
				//I like rhubarb pie and the variations of it that include other fruits
			}else{
				System.out.println("\nYou have made an invalid selection. Please try again.\n");
			}
		}
		
	}

}
