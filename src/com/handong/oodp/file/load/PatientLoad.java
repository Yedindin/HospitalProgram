package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.handong.oodp.Observer.Patient;

public class PatientLoad implements LoadStrategy{

	@Override
	public List<Patient> load() {
		List<Patient> patientList = new ArrayList<Patient>();
        try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./data/Patient.csv"));
			String line = "";
	        int row_count = 0;
	         while ((line = bufferedReader.readLine()) != null) {
	        	 String[] patient_1 = line.split(",");
	        	 Patient temp = new Patient(patient_1[0],Integer.parseInt(patient_1[1]),patient_1[2],patient_1[3],Integer.parseInt(patient_1[4]));
	        	 patientList.add(temp);
	          }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientList;
	}

}
