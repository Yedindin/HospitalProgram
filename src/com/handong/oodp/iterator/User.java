package com.handong.oodp.iterator;

import java.util.List;

public class User {

	private String name;
	private String ID;
	private String PW;
	private String department;
	private String position;
	private String age;
	private List<User> userlist;

	public User(List<User> userlist) {
		this.userlist = userlist;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "[이름: " + name + ", ID: " + ID + ", 부서: " + department + ", 직급: " + position + ", 나이: " + age + "]";
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPW() {
		return PW;
	}

	public String getDepartment() {
		return department;
	}

	public String getPosition() {
		return position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String string) {
		this.age = string;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
}
