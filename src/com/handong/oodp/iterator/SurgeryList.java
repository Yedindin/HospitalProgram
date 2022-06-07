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
import com.handong.oodp.file.save.SurgerySave;
import com.handong.oodp.state.ReadySurgeryState;
import com.handong.oodp.state.State;
import com.handong.oodp.state.SurgeryControl;

public class SurgeryList implements Aggregate{
	
	private List<Surgery> surgerylist;
	
	
	public SurgeryList(List<Surgery> surgerylist) {
		this.surgerylist = surgerylist;
	}
	public SurgeryList() {
		
	}
	public Surgery getSurgeryat(int index) {
		return surgerylist.get(index);
	}
	public Surgery getSurgeryAt(int index) {
		for (Surgery item : surgerylist ) {
			if(Integer.toString(index).equals(item.getIndex()))
			{
				return item;
			}
		}
		return null;
	}
	public List<Surgery> addSurgery() throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/surgerydata.csv"), Charset.forName("UTF-8"));
		File surgeryfile = new SurgeryFile("surgeryfile");
		surgeryfile.setSavestrategy(new SurgerySave());
		Surgery surgery1 = new Surgery();
		
		SurgeryControl sct = new SurgeryControl();
		State s = sct.findS("수술대기중");
		String state = s.getState();
		int index = this.getLength()+1;
		surgery1.setIndex(Integer.toString(index));
		printer.println("수술날짜를 입력하세요.(형식 : yyyymmdd)");
		surgery1.setDate(sc.next());
		printer.println("수술시간을 입력하세요. (형식 : hh:mm)");
		surgery1.setTime(sc.next());
		printer.println("담당의를 입력하세요.");
		surgery1.setDoctor(sc.next());
		printer.println("수술세부내용을 입력하세요.");
		surgery1.setDetail(sc.next());
		printer.println("환자이름을 입력하세요.");
		surgery1.setPatient(sc.next());
		printer.println("진료과를 입력하세요.");
		surgery1.setDepartment(sc.next());
		surgery1.setState(state);
		surgery1.setS(s);		
		
		surgerylist.add(surgery1);
		
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
				surgery1.setS(surgery1.getS());
				
				surgeryfile.save(surgerylist);
				return surgerylist;
			}
		}

		printer.println("존재하지 않는 수술번호입니다.");
		return surgerylist;
	}
	public List<Surgery> editSurgeryState(String index, String num) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		File surgeryfile = new SurgeryFile("surgeryfile");
		
		
		surgeryfile.setSavestrategy(new SurgerySave());
		for (Surgery surgery1 : surgerylist) {
			if(surgery1.getIndex().equals(index)) {
				SurgeryControl sct = new SurgeryControl(surgery1.getS());
				
				sct.printCurrent();
				surgery1.setS(sct.getNextState(num));
				
				if(num.equals("1")) {
					sct.readSurgery();
				}else if (num.equals("2")) {
					sct.inSurgery();
				}else if (num.equals("3")) {
					sct.endSurgery();
				}
				printer.println("수술 상태가 변경되었습니다.");
				
				
				sct.printCurrent();
				
				surgery1.setState(sct.getState());
				
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
