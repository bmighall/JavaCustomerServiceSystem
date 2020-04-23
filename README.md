# SimpleCustomerServiceProgram
Creator: Ben Mighall

This is a simple "customer service rep assistant" written in Java to demonstrate aggregation and file input. It has four classes: Driver, Purchase, Order, and Customer; it also has a data file of customer/purchase data, "dataP4.txt". 

The Customer and Purchase classes are simple base classes that hold data for a given customer and a given purchase. The Order class is a slightly more complex intermediate class: it has a Customer object and an ArrayList of Purchase objects, and allows data to be changed or outputted. The Driver in turn has an ArrayList of Order objects that has its data placed into it from the dataP4.txt file, read in by a Scanner object. The program then enters a while loop "menu" that allows the user to create a new order, add additional items to an existing order or change its information, view existing order information, view all data, and exit the program when desired.

This code was written as part of coursework for University of Mississippi class CSCI 112 (Computer Science II).
