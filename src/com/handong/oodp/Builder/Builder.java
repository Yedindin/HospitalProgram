package com.handong.oodp.Builder;

public abstract class Builder {
	protected Patient patient;
	
	public void AddPatient() {
		patient = new Patient();
	}
	public abstract void buildName(String name);
	public abstract void buildAge(int age);
	public abstract void buildregistrationNumber(String id);
	public abstract void buildDetail(String detail);
	public abstract void buildEmergency();
	public abstract Patient getPatient();
}