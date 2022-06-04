package com.handong.oodp.Builder;

public class Director {
	public Builder builder;
	
	public Director(Builder builder) {
		this.builder = builder;
	}
	
	public void build(String name, int age, String registrationNumber, String detail) {
		builder.AddPatient();
		builder.buildName(name);
		builder.buildAge(age);
		builder.buildregistrationNumber(registrationNumber);
		builder.buildDetail(detail);
		builder.buildEmergency();
	}
	
	public Patient getPatient() {
		return builder.getPatient();
	}

}