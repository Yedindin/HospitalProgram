package com.handong.oodp.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.File;
import com.handong.oodp.file.PatientFile;
import com.handong.oodp.file.save.PatientSave;
import com.handong.oodp.Builder.Builder;
import com.handong.oodp.Builder.Director;
import com.handong.oodp.Builder.EmergencyPatient;
import com.handong.oodp.Builder.GeneralPatient;
import com.handong.oodp.Builder.Patient;
import com.handong.oodp.Singleton.Printer;

public class PatientManage extends Subject {
	private List<Patient> patientList = new ArrayList<Patient>();

	public void removePatient() throws IOException {
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
				printer.println("삭제되었습니다.");
			} else {
				printer.println("존재하지 않는 환자입니다.");
			}
			break;
		}
		notifyObservers();
	}
	
	public void setEmergencyPatient() {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		String RegistrationNumber = "";
		File patientfile = new PatientFile("patientfile");
		patientfile.setSavestrategy(new PatientSave());

		while (true) {
			printer.println("응급으로 전환할 환자 주민번호를 입력하세요.(형식 = 123456-1234567) :");
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
				Patient temp = patientList.get(index);
				temp.setEmergency(1);
				try {
					patientfile.save(patientList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printer.println("변경되었습니다.");
			} else {
				printer.println("존재하지 않는 환자입니다.");
			}
			break;
		}
		notifyObservers();
	}
	
	public void setGeneralPatient() {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		String RegistrationNumber = "";
		File patientfile = new PatientFile("patientfile");
		patientfile.setSavestrategy(new PatientSave());

		while (true) {
			printer.println("일반으로 전환할 환자 주민번호를 입력하세요.(형식 = 123456-1234567) :");
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
				Patient temp = patientList.get(index);
				temp.setEmergency(0);
				try {
					patientfile.save(patientList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printer.println("변경되었습니다.");
			} else {
				printer.println("존재하지 않는 환자입니다.");
			}
			break;
		}
		notifyObservers();
	}
	public void addEPatient() throws IOException {
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
		sc.nextLine();
		String detail = sc.nextLine();
		Builder emergencyPatientBuilder = new EmergencyPatient();
		Director director = new Director(emergencyPatientBuilder);
		director.build(name,age,RegistrationNumber,detail);
		
		patientList.add(director.getPatient());
		patientfile.save(patientList);
		printer.println("추가되었습니다.");
		notifyObservers();
	}

	public void addPatient() throws IOException {
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
		sc.nextLine();
		String detail = sc.nextLine();
		Builder generalPatientBuilder = new GeneralPatient();
		Director director = new Director(generalPatientBuilder);
		director.build(name,age,RegistrationNumber,detail);
		
		patientList.add(director.getPatient());
		patientfile.save(patientList);
		printer.println("추가되었습니다.");
		notifyObservers();
	}

	public void displayPatientList() {
		notifyObservers();
	}

	public List<Patient> getPatients() {
		return patientList;
	}

	public void setPatients(List<Patient> patients) {
		this.patientList = patients;
	}

	public void changePatient() {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		String RegistrationNumber = "";
		File patientfile = new PatientFile("patientfile");
		patientfile.setSavestrategy(new PatientSave());

		while (true) {
			printer.println("정보를 변경할 환자 주민번호를 입력하세요.(형식 = 123456-1234567) :");
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
				Patient temp = patientList.get(index);
				printer.print("이름 : " + temp.getName() + " ");
				printer.print("나이 : " + Integer.toString(temp.getAge()) + " ");
				printer.println("상세정보 : " + temp.getDetail() + " ");
				
				
				printer.println("환자 이름을 입력하세요. :");
				String name = sc.nextLine();
				temp.setName(name);
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
				temp.setAge(age);
				printer.println("환자 상세정보를 입력하세요. :");
				sc.nextLine();
				String detail = sc.nextLine();
				temp.setDetail(detail);
				int emergency;
				while (true) {
					try {
						printer.println("응급환자 여부를 입력하세요. :(긴급 = 1)");
						emergency = sc.nextInt();
						break;
					} catch (InputMismatchException e) {
						sc = new Scanner(System.in);
						printer.println("숫자를 입력해주세요.");
					}
				}
				temp.setEmergency(emergency);
				
				try {
					patientfile.save(patientList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printer.println("변경되었습니다.");
			} else {
				printer.println("존재하지 않는 환자입니다.");
			}
			break;
		}
		notifyObservers();
	}
}
