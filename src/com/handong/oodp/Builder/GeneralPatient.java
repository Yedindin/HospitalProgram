package com.handong.oodp.Builder;

public class GeneralPatient extends Builder {



	@Override
	public void buildDetail(String detail) {
		// TODO Auto-generated method stub
		patient.setDetail(detail);
		
	}

	@Override
	public void buildEmergency() {
		// TODO Auto-generated method stub
		patient.setEmergency(0);
		
	}

	@Override
	public void buildName(String name) {
		// TODO Auto-generated method stub
		patient.setName(name);
		
	}

	@Override
	public void buildAge(int age) {
		// TODO Auto-generated method stub
		patient.setAge(age);
		
	}

	@Override
	public void buildregistrationNumber(String id) {
		// TODO Auto-generated method stub
		patient.setRegistrationNumber(id);
		
	}

	@Override
	public Patient getPatient() {
		// TODO Auto-generated method stub
		return patient;
	}
	

}
