/*
 * Ben Mighall
 * Project 4
 * CSCI 112
 * July 21st, 2016
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
	
	private Customer customer;
	private ArrayList <Purchase> purchases;
	private String date;
	private DecimalFormat deci = new DecimalFormat("$#.00");
	
	//Empty constructor
	public Order(){
		customer = new Customer();
		purchases = new ArrayList<Purchase>();
		date = "1/1/2000";
	}
	
	//For initial data import (tokens.length == 1) and user-inputted new orders
	public Order(String orderDate){
		customer = new Customer();
		purchases = new ArrayList<Purchase>();
		date = orderDate;
	}
	
	public void setCustomerData(String customerName, String ccNum){ 
		customer.setName(customerName);
		customer.setCCNum(ccNum);
	}
	
	public void changeCustomerName(String customerName){
		customer.setName(customerName);
	}
	
	public void changeCustomerCC(String newCC){
		customer.setCCNum(newCC);
	}
	
	public void changePurchaseName(int index, String newName){
		purchases.get(index).setName(newName);
	}
	
	public void changePurchaseDescription(int index, String newDescription){
		purchases.get(index).setDescription(newDescription);
	}

	public void changePurchaseCost(int index, double newCost){
		purchases.get(index).setCost(newCost);
	}
	
	public void changeOrderDate(String date){
		this.date = date;
	}
	
	public void printPurchases(){ //For the section of the menu where users can select what changes they would like to make and to what part of the data
		for(int i = 0; i < purchases.size(); i++){
			System.out.println("Purchase #" + (i + 1) + ":");
			System.out.println(purchases.get(i).toString());
		}
	}
	
	public void basicPrint(){ //Creating this method to make it easier to output info for user menu selections
		System.out.println("Order Date: " + date);
		System.out.println(customer.toString());
	}
	
	//Decided to forego having total be a variable for the entire class and instead have it be a method function. 
	public void totalCost(){
		double total = 0.0;
		for (int i = 0; i < purchases.size(); i++){
			total += purchases.get(i).getCost();
		}
		System.out.println("Order Total: " + deci.format(total));
	}
	
	public void addPurchase(String name, String description, double cost){
		purchases.add(new Purchase(name, description, cost));
	}
	
	public void printData(){
		System.out.println("Order Date: " + date);
		System.out.println(customer.toString());
		for(int i = 0; i < purchases.size(); i++){
			System.out.println(purchases.get(i).toString());
		}
	}

}
