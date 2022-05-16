package com.handong.oodp;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.File;
import com.handong.oodp.file.PatientFile;
import com.handong.oodp.file.save.PatientSave;


import com.handong.oodp.Singleton.Printer;

public class PatientManage {

//	public List<Patient> loadPatient() {
//		List<Patient> patientList = new ArrayList<Patient>();
//		try {
//			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./data/Patient.csv"));
//			String line = "";
//			int row_count = 0;
//			while ((line = bufferedReader.readLine()) != null) {
//				String[] patient_1 = line.split(",");
//				Patient temp = new Patient(patient_1[0], Integer.parseInt(patient_1[1]), patient_1[2], patient_1[3]);
//				patientList.add(temp);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return patientList;
//	}

	public List<Patient> removePatient(List<Patient> patientList) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		String RegistrationNumber = "";
		File patientfile = new PatientFile("patientfile");
		patientfile.setSavestrategy(new PatientSave());

		while (true) {
			printer.println("삭제할 환자 주민번호를 입력하세요.(형식 = 123456-1234567) :");
			RegistrationNumber = sc.next();
			if (RegistrationNumber.length() != 14 || RegistrationNumber.indexOf("-") != 6) {
				printer.println("유효하지 않은 입력입니다.");
				sc = new Scanner(System.in);
				continue;
			}
			boolean flag = false;
			int index = 0;
			for (Patient i : patientList) {
				if (i.getRegistrationNumber().equals(RegistrationNumber)) {
					flag = true;
					sc = new Scanner(System.in);
					break;
				}
				index++;
			}
			if (flag) {
				patientList.remove(index);
				patientfile.save(patientList);
				// updatePatientList(patientList);
				printer.println("삭제되었습니다.");
			} else {
				printer.println("존재하지 않는 환자입니다.");
			}
			break;
		}
		return patientList;
	}

	public List<Patient> addPatient(List<Patient> patientList) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		File patientfile = new PatientFile("patientfile");
		patientfile.setSavestrategy(new PatientSave());
		printer.println("환자 이름을 입력하세요. :");
		String name = sc.nextLine();
		int age = 0;
		while (true) {
			try {
				printer.println("환자 나이를 입력하세요. :");
				age = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				printer.println("숫자를 입력해주세요.");
			}
		}
		String RegistrationNumber = "";
		while (true) {
			printer.println("환자 주민번호를 입력하세요.(형식 = 123456-1234567) :");
			RegistrationNumber = sc.next();
			if (RegistrationNumber.length() != 14 || RegistrationNumber.indexOf("-") != 6) {
				printer.println("유효하지 않은 입력입니다.");
				sc = new Scanner(System.in);
				continue;
			}
			boolean flag = false;

			for (Patient i : patientList) {
				if (i.getRegistrationNumber().equals(RegistrationNumber)) {
					printer.println("이미 존재하는 환자입니다.");
					flag = true;
					sc = new Scanner(System.in);
					break;
				}
			}
			if (flag)
				continue;

			break;
		}
		printer.println("환자 상세정보를 입력하세요. :");
		String detail = sc.nextLine();
		patientList.add(new Patient(name, age, RegistrationNumber, detail));
		patientfile.save(patientList);
		//updatePatientList(patientList);
		printer.println("추가되었습니다.");
		return patientList;
	}

	public void displayPatientList(List<Patient> patientList) {
		Printer printer = Printer.getPrinter(); // singleton
		printer.println("\n\n****환자 목록****");
		for (Patient i : patientList) {
			printer.print("이름 : " + i.getName() + " ");
			printer.print("나이 : " + Integer.toString(i.getAge()) + " ");
			printer.print("주민번호 : " + i.getRegistrationNumber() + " ");
			printer.println("상세정보 : " + i.getDetail() + " ");
		}
	}

//	   public void updatePatientList(List<Patient> patientList) {
//		  Printer printer = Printer.getPrinter(); //singleton
//	      String filePath = "./data/Patient.csv";
//	      String NEWLINE = System.lineSeparator();
//	      try {
//	         File file = new File(filePath);
//	         BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//	         for(Patient i : patientList) {
//	        	 String temp = i.getName()+","+Integer.toString(i.getAge())+","+
//	         i.getRegistrationNumber()+","+i.getDetail()+NEWLINE;
//	        	 bw.write(temp);
//	         }
//	         bw.flush();
//	         bw.close();
//	      } catch (IOException e) {
//	         // TODO Auto-generated catch block
//	         e.printStackTrace();
//	      }
//	   }
}
