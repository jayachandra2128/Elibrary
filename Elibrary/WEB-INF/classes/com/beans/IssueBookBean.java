package com.beans;

import java.sql.Date;

//Java Bean for Issuing books. Contains getters and setters.
public class IssueBookBean {
private String aileno;
private int studentid,librarianid;
private Date issueddate;
private String returnstatus;
public IssueBookBean() {}

public IssueBookBean(String aileno, int studentid, int librarianid) {
	super();
	this.aileno = aileno;
	this.studentid = studentid;
	
	this.librarianid=librarianid;
}

public String getReturnstatus() {
	return returnstatus;
}
public void setReturnstatus(String returnstatus) {
	this.returnstatus = returnstatus;
}
public Date getIssueddate() {
	return issueddate;
}
public void setIssueddate(Date issueddate) {
	this.issueddate = issueddate;
}
public String getAileno() {
	return aileno;
}
public void setAileno(String aileno) {
	this.aileno = aileno;
}
public int getStudentid() {
	return studentid;
}
public void setStudentid(int studentid) {
	this.studentid = studentid;
}
public int getLibrarianid() {
	return librarianid;
}
public void setLibrarianid(int librarianid) {
	this.librarianid = librarianid;
}


}
