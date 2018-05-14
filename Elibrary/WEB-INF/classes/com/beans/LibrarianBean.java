package com.beans;


//Librarian Bean. Contains getters and setters.
public class LibrarianBean {
private int id;
private String name,email,password;
private long mobile;
private int admin_id;
public LibrarianBean() {}

public LibrarianBean(int id, String name, String email, String password, long mobile, int admin_id) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
	this.admin_id=admin_id;
}
public LibrarianBean(String name, String email, String password, long mobile, int admin_id) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
	this.admin_id=admin_id;
}

public LibrarianBean(String name, String email, String password, long mobile) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
}

public LibrarianBean(int id, String name, String email, String password, long mobile) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.mobile = mobile;
}

public int getAdmin_id() {
	return admin_id;
}

public void setAdmin_id(int admin_id) {
	this.admin_id = admin_id;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public long getMobile() {
	return mobile;
}
public void setMobile(long mobile) {
	this.mobile = mobile;
}

}

