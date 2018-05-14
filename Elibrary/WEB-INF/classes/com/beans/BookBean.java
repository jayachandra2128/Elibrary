package com.beans;

//Book Bean. Contains getters and setters.
public class BookBean {
private String aileno,name,author,publisher;
private int quantity,issued;
public int getIssued() {
	return issued;
}
public void setIssued(int issued) {
	this.issued = issued;
}
public BookBean() {
	super();
	// TODO Auto-generated constructor stub
}
public BookBean(String aileno, String name, String author, String publisher, int quantity) {
	super();
	this.aileno = aileno;
	this.name = name;
	this.author = author;
	this.publisher = publisher;
	this.quantity = quantity;
}

public String getAileno() {
	return aileno;
}
public void setAileno(String aileno) {
	this.aileno = aileno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}


}

