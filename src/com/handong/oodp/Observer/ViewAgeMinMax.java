package com.handong.oodp.Observer;

import java.util.List;

import com.handong.oodp.Singleton.Printer;

public class ViewAgeMinMax implements Observer {
	private PatientManage patientManage;
	
	public ViewAgeMinMax (PatientManage patientManage) {
		this.patientManage = patientManage;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		List<Patient> patientList = patientManage.getPatients();
		displayPatientAgeMinMax(patientList);
	}
	
	public void displayPatientAgeMinMax(List<Patient> patientList) {
		Printer printer = Printer.getPrinter(); // singleton
		printer.println("\n\n****환자 나이 최대 최소****");
		int min = 99999;
		int max = -1;
		for (Patient i : patientList) {
			if (min > i.getAge())
				min = i.getAge();
			if (max < i.getAge())
				max = i.getAge();
		}
		printer.println("환자 최소 나이 :"+Integer.toString(min));
		printer.println("환자 최대 나이 :"+Integer.toString(max));
	}

}
