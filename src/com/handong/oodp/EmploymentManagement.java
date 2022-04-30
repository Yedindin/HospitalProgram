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
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
		for (List<String> item : list) {
			System.out.println(item.get(0) + "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5));
		}
		System.out.println("의사 추가 완료");
		return list;

	}

	public List<List<String>> addNurse(User user) {
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
		System.out.println("간호사 추가 완료");
		return list;
	}

//	public void editDoctor(User user) {
//		System.out.println("의사 수정");
//	}
//	public void editNurse(User user) {
//		System.out.println("간호사 수정");
//	}
	public List<List<String>> deleteDoctor(String name) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		int index = 0;
		for (List<String> item : list ) {
			if(item.contains(name) && item.get(4).equals("Doctor"))
			{
				//System.out.println(list.get(index));
				list.remove(index);
				
				System.out.println("의사 삭제 완료");
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

					System.out.println("저장완료");

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
		System.out.println("존재하지 않는 의사입니다.");
		return list;

	}

	public List<List<String>> deleteNurse(String name) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		int index = 0;
		for (List<String> item : list ) {
			if(item.contains(name) && item.get(4).equals("Nurse"))
			{
				//System.out.println(list.get(index));
				list.remove(index);
				
				System.out.println("간호사 삭제 완료");
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

					System.out.println("저장완료");

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
		System.out.println("존재하지 않는 간호사입니다.");
		return list;

	}
}
