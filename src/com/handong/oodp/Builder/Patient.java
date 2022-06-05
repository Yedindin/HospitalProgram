package com.handong.oodp.Builder;

public class Patient {
	private String name;
	private int age;
	private String registrationNumber;
	private String detail;
	private int emergency;
	
	public Patient(String name,int age,String registrationNumber,String detail,int emergency){
		this.name = name;
		this.age = age;
		this.registrationNumber = registrationNumber;
		this.detail = detail;
		this.emergency =emergency;
	}
	public Patient(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getEmergency() {
		return emergency;
	}

	public void setEmergency(int emergency) {
		this.emergency = emergency;
	}
	
}
