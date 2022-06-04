package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.handong.oodp.Builder.Builder;
import com.handong.oodp.Builder.Director;
import com.handong.oodp.Builder.EmergencyPatient;
import com.handong.oodp.Builder.GeneralPatient;
import com.handong.oodp.Builder.Patient;

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
	        	 if(Integer.parseInt(patient_1[4]) == 1) {
	        		 Builder emergencyPatient = new EmergencyPatient();
	        			Director director = new Director(emergencyPatient);
	        			director.build(patient_1[0],Integer.parseInt(patient_1[1]),patient_1[2],patient_1[3]);
	        			patientList.add(director.getPatient());
	        	 }
	        	 else {
	        		 Builder generalPatientBuilder = new GeneralPatient();
	        			Director director = new Director(generalPatientBuilder);
	        			director.build(patient_1[0],Integer.parseInt(patient_1[1]),patient_1[2],patient_1[3]);
	        			patientList.add(director.getPatient());
	        	 }
	          }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientList;
	}

}
