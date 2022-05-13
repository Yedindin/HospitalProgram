package com.handong.oodp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class EmploymentManagement {
	private List<List<String>> list;
	
	
	public EmploymentManagement (List<List<String>> list) {
		this.list = list;
	}
	public List<List<String>> addDoctor(User user) {
		Printer printer = Printer.getPrinter(); //singleton
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
//		for (List<String> item : list) {
//			printer.println(item.get(0) + "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5));
//		}
		printer.println("의사 추가 완료");
		return list;

	}

	public List<List<String>> addNurse(User user) {
		Printer printer = Printer.getPrinter(); //singleton
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
		printer.println("간호사 추가 완료"); //singleton
		return list;
	}

//	public void editDoctor(User user) {
//		printer.println("의사 수정");
//	}
//	public void editNurse(User user) {
//		printer.println("간호사 수정");
//	}
	public List<List<String>> deleteDoctor(String name) throws IOException {
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		int index = 0;
		for (List<String> item : list ) {
			if(item.contains(name) && item.get(4).equals("Doctor"))
			{
				//printer.println(list.get(index));
				list.remove(index);
				
				printer.println("의사 삭제 완료");
				try {

					StringBuffer data = new StringBuffer();
					Charset.forName("UTF-8");
					for (List<String> item2 : list) {
						data.append(item2.get(0) + "," + item2.get(1) + "," + item2.get(2) + "," + item2.get(3) + ","
								+ item2.get(4) + "," + item2.get(5) + "\n");
					}
					FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
					outputStream.write(data.toString().getBytes());
					outputStream.close();

					printer.println("저장완료");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null) {
							br.close();
						}
					} catch (IOException e) {
						e.printStackTrace();

					}
				}
				return list;
			}
			index++;
		}
		printer.println("존재하지 않는 의사입니다.");
		return list;

	}

	public List<List<String>> deleteNurse(String name) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		Printer printer = Printer.getPrinter(); //singleton
		int index = 0;
		for (List<String> item : list ) {
			if(item.contains(name) && item.get(4).equals("Nurse"))
			{
				//printer.println(list.get(index));
				list.remove(index);
				
				printer.println("간호사 삭제 완료");
				try {

					StringBuffer data = new StringBuffer();
					Charset.forName("UTF-8");
					for (List<String> item2 : list) {
						data.append(item2.get(0) + "," + item2.get(1) + "," + item2.get(2) + "," + item2.get(3) + ","
								+ item2.get(4) + "," + item2.get(5) + "\n");
					}
					FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
					outputStream.write(data.toString().getBytes());
					outputStream.close();

					printer.println("저장완료");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null) {
							br.close();
						}
					} catch (IOException e) {
						e.printStackTrace();

					}
				}
				return list;
			}
			index++;
		}
		printer.println("존재하지 않는 간호사입니다.");
		return list;

	}
}
