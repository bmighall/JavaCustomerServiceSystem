/*
 * Ben Mighall
 * Project 4
 * CSCI 112
 * July 21st, 2016
 */

import java.text.DecimalFormat;

public class Purchase {
	
	private String name;
	private String description;
	private double cost;
	private DecimalFormat deci = new DecimalFormat("$#.00");
	
	public Purchase(){
		name = "";
		description = "";
		cost = 0.0;
	}
	
	public Purchase(String name, String description, double cost){
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String toString(){
		String s = "Item Name: " + name + "\nItem Description: " + description + "\nCost: " + deci.format(cost);
		return s;
	}

}
