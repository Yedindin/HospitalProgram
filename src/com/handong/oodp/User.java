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
import java.util.Scanner;

public class User {

	private String name;
	private String ID;
	private String PW;
	private String department;
	private String position;
	private String age;
	private List<List<String>> list;

	public User(List<List<String>> list) {
		this.list = list;
	}
	
	public List<List<String>> addUser(String name) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		for (List<String> item : list) {
			if (name.equals(item.get(0)) && item.get(1).equals("")) {
				printer.println("사용할 ID를 입력하세요.");
				this.ID = sc.next();
				printer.println("사용할 패스워드를 입력하세요.");
				this.PW = sc.next();
				printer.println("회원등록이 완료되었습니다.");
				item.set(0, item.get(0));
				item.set(1, this.ID);
				item.set(2, this.PW);
				item.set(3, item.get(3));
				item.set(4, item.get(4));
				item.set(5, item.get(5));

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
		}
		printer.println("등록 대상이 아닙니다.");
		return list;
	}
		
		public List<List<String>> editUser(String name) throws IOException {
			Scanner sc = new Scanner(System.in);
			BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
			Printer printer = Printer.getPrinter(); //singleton
			for (List<String> item : list ) {
				if(item.contains(name))
				{
					printer.println("ID : " + item.get(1));
					printer.println("PW : " + item.get(2));
					printer.println("수정할 ID를 입력하세요.");
					this.ID = sc.next();
					printer.println("수정할 패스워드를 입력하세요.");
					this.PW = sc.next();
					printer.println("회원정보수정이 완료되었습니다.");
					item.set(0, item.get(0));
					item.set(1, this.ID);
					item.set(2, this.PW);
					item.set(3, item.get(3));
					item.set(4, item.get(4));
					item.set(5, item.get(5));
					
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
					printer.println(name + "user 수정 완료");
					return list;
				}
			}
			printer.println("존재하지 않는 회원입니다.");
			return list;

//		List<String> data = new ArrayList<>();
//		data.add(0,name);
//		data.add(3,department);
//		data.add(4,position);
//		data.add(5,age);
//		list.add(data);
//		for (List<String> item : list) { printer.println(item.get(0) + "and" +
//				 item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5) ); }
//		printer.println("user 추가 완료");
//		return list;
	}

//	public void editUser(User user) {
//		printer.println("user 수정 완료");
//	}
	public List<List<String>> deleteUser(String name) throws IOException {
		
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		Printer printer = Printer.getPrinter(); //singleton
		int index = 0;
		for (List<String> item : list ) {
			if(item.contains(name))
			{
				//printer.println(list.get(index));
				list.remove(index);
				
				printer.println("user 삭제 완료");
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
		printer.println("존재하지 않는 회원입니다.");
		return list;

	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPW() {
		return PW;
	}

	public String getDepartment() {
		return department;
	}

	public String getPosition() {
		return position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String string) {
		this.age = string;
	}

}