/*
 * Ben Mighall
 * Project 4
 * CSCI 112
 * July 21st, 2016
 */

public class Customer {
	
	private String name;
	private String ccNum;
	
	public Customer(){
		name = "";
		ccNum = "";
	}
	
	public Customer(String name, String ccNum){
		this.name = name;
		this.ccNum = ccNum;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCCNum(String ccNum){
		this.ccNum = ccNum;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCCNum(){
		return ccNum;
	}
	
	public String toString(){
		String s = "Customer Name: " + name + "\nCredit Card Number: " + ccNum; //Added newline characters to enhance formatting
		return s;
	}

}
