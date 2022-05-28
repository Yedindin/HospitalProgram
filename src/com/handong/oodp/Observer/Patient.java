package com.handong.oodp.Observer;

public class Patient {
	private String name = "";
	private int age;
	private String registrationNumber ="";
	private String detail = "";
	private int emergency;
	
	public Patient (String name, int age, String registrationNumber, String detail, int emergency) {
		this.setName(name);
		this.setAge(age);
		this.setRegistrationNumber(registrationNumber);
		this.setDetail(detail);
		this.setEmergency(emergency);
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
