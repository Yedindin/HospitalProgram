package com.handong.oodp.iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.file.File;
import com.handong.oodp.file.SurgeryFile;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.save.SurgerySave;
import com.handong.oodp.file.save.UserSave;

public class SurgeryList implements Aggregate{
	
	private List<Surgery> surgerylist;
	
	
	public SurgeryList(List<Surgery> surgerylist) {
		this.surgerylist = surgerylist;
	}
	public SurgeryList() {
		
	}
	
	public Surgery getSurgeryAt(int index) {
		return surgerylist.get(index);
	}
	public List<Surgery> addSurgery() throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/surgerydata.csv"), Charset.forName("UTF-8"));
		File surgeryfile = new SurgeryFile("surgeryfile");
		surgeryfile.setSavestrategy(new SurgerySave());
		Surgery surgery1 = new Surgery();
		
		int index = this.getLength()+1;
		surgery1.setIndex(Integer.toString(index));
		printer.println("수술날짜를 입력하세요.(형식 : yyyymmdd)");
		surgery1.setDate(sc.next());
		printer.println("수술시간을 입력하세요. (형식 : hh:mm)");
		surgery1.setTime(sc.next());
		printer.println("담당의를 입력하세요.");
		surgery1.setDoctor(sc.next());
		printer.println("담당의를 입력하세요.");
		surgery1.setDetail(sc.next());
		printer.println("환자이를을 입력하세요.");
		surgery1.setPatient(sc.next());
		printer.println("진료과를 입력하세요.");
		surgery1.setDepartment(sc.next());
		surgery1.setState("수술대기중");
		
		surgeryfile.save(surgerylist);
		
		return surgerylist;
		
	}
	public List<Surgery> deleteSurgery(Surgery surgery1) throws IOException {
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/surgerydata.csv"), Charset.forName("UTF-8"));
		File surgeryfile = new SurgeryFile("surgeryfile");
		surgeryfile.setSavestrategy(new SurgerySave());
		int index = 0;
		for (Surgery item : surgerylist ) {
			if(surgery1.getIndex().equals(item.getIndex()))
			{
				//printer.println(list.get(index));
				surgerylist.remove(index);
				
				printer.println("수술 삭제 완료");
				surgeryfile.save(surgerylist);

				return surgerylist;
			}
			index++;
		}
		printer.println("존재하지 않는 수술번호입니다.");
		return surgerylist;

	}
	public List<Surgery> editSurgery(String index) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		File surgeryfile = new SurgeryFile("surgeryfile");
		surgeryfile.setSavestrategy(new SurgerySave());
		for (Surgery surgery1 : surgerylist) {
			if(surgery1.getIndex().equals(index)) {
				printer.println("변경할 수술날짜를 입력하세요.(형식 : yyyymmdd)");
				surgery1.setDate(sc.next());
				printer.println("변경할 수술시간을 입력하세요. (형식 : hh:mm)");
				surgery1.setTime(sc.next());
				
				surgery1.setDoctor(surgery1.getDoctor());
				surgery1.setDetail(surgery1.getDetail());
				surgery1.setPatient(surgery1.getPatient());
				surgery1.setDepartment(surgery1.getDepartment());
				surgery1.setState(surgery1.getState());
				
				surgeryfile.save(surgerylist);
				return surgerylist;
			}
		}

		printer.println("존재하지 않는 수술번호입니다.");
		return surgerylist;
	}
	
	public int getLength() {
		return surgerylist.size();
	}
	@Override
	public Iterator iterator() {
		return new SurgeryListIterator(this);
	}

}
