package com.handong.oodp.iterator;

import com.handong.oodp.state.State;

public class Surgery {
	private String index;
	private String date;
	private String time;
	private String department;
	private String doctor;
	private String detail;
	private String patient;
	private String state;
	private State s;
	
	public String getIndex() {
		return index;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getDepartment() {
		return department;
	}
	public String getDoctor() {
		return doctor;
	}
	public String getDetail() {
		return detail;
	}
	public String getState() {
		return state;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setDoctor(String doctor) {
		this.doctor =doctor;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public State getS() {
		return s;
	}
	public void setS(State s) {
		this.s = s;
	}
	@Override
	public String toString() {
		return "---수술번호 : " + index + "---\n수술 날짜 : " + date + "\n수술시작시간 :" + time + "\n진료과 : " + department
				+ "\n집도담당의 : " + doctor + "\n세부내용 : " + detail + "\n환자 : " + patient + "\n현재 상태 : " + state + "\n---------------";
	}

}
