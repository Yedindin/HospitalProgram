package com.handong.oodp.Observer;

import java.util.ArrayList;
import java.util.List;
import com.handong.oodp.Builder.Patient;
import com.handong.oodp.Singleton.Printer;

public class ViewPatientAll implements Observer {
	private PatientManage patientManage;
	
	public ViewPatientAll (PatientManage patientManage) {
		this.patientManage = patientManage;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		List<Patient> patientList = patientManage.getPatients();
		displayPatientList(patientList);
	}
	
	public void displayPatientList(List<Patient> patientList) {
		Printer printer = Printer.getPrinter(); // singleton
		printer.println("\n\n****전체 환자 목록****");
		for (Patient i : patientList) {
			if(i.getEmergency() == 1) {
				printer.print("(응급)");
			}
			else {
				printer.print("(일반)");
			}
			printer.print("이름 : " + i.getName() + " ");
			printer.print("나이 : " + Integer.toString(i.getAge()) + " ");
			printer.print("주민번호 : " + i.getRegistrationNumber() + " ");
			printer.println("상세정보 : " + i.getDetail() + " ");
		}
	}

}
