package com.handong.oodp.file.save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.handong.oodp.Observer.Patient;

import com.handong.oodp.Singleton.Printer;

public class PatientSave implements SaveStrategy {

	@Override
	public void save(Object o) {
		List<Patient> patientList = (List<Patient>) o;
		Printer printer = Printer.getPrinter(); // singleton
		String filePath = "./data/Patient.csv";
		String NEWLINE = System.lineSeparator();
		try {
			File file = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (Patient i : patientList) {
				String temp = i.getName() + "," + Integer.toString(i.getAge()) + "," + i.getRegistrationNumber() + ","
						+ i.getDetail() + "," + Integer.toString(i.getEmergency()) + NEWLINE;
				bw.write(temp);
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
