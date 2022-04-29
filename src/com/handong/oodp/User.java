package com.handong.oodp;

public class User {

	private String name;
	private String ID;
	private String PW;
	private String department;
	private String position;
	
	public void addUser(User user) {
		System.out.println("user 추가 완료");
	}
//	public void editUser(User user) {
//		System.out.println("user 수정 완료");
//	}
	public void deleteUser(User user) {
		System.out.println("user 삭제 완료");
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
	
	
	
}
