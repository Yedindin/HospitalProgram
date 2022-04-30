package com.handong.oodp;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private String ID;
	private String PW;
	private String department;
	private String position;
	private String age;
	private List<List<String>> list;
	
	
	public User (List<List<String>> list) {
		this.list = list;
	}

	public List<List<String>> addUser(User user) {
		List<String> data = new ArrayList<>();
		data.add(0,name);
		data.add(3,department);
		data.add(4,position);
		data.add(5,age);
		list.add(data);
		for (List<String> item : list) { System.out.println(item.get(0) + "and" +
				 item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5) ); }
		System.out.println("user 추가 완료");
		return list;
	}
//	public void editUser(User user) {
//		System.out.println("user 수정 완료");
//	}
	public List<List<String>> deleteUser(User user) {
		System.out.println("user 삭제 완료");
		return list;

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
	
	
}
