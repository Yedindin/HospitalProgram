package com.handong.oodp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.file.File;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.save.UserSave;
import com.handong.oodp.iterator.User;

public class EmploymentManagement {
	// private List<List<String>> list;
	private List<User> userlist;

	public EmploymentManagement(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<User> addDoctor(User user) {
		Printer printer = Printer.getPrinter(); // singleton
		// List<String> data = new ArrayList<>();
		User u = new User();
		u.setName(user.getName());
		u.setID("");
		u.setPW("");
		u.setDepartment(user.getDepartment());
		u.setPosition(user.getPosition());
		u.setAge(user.getAge());
		userlist.add(u);
//		for (List<String> item : list) {
//			printer.println(item.get(0) + "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5));
//		}
		printer.println("의사 추가 완료");
		return userlist;

	}

	public List<User> addNurse(User user) {
		Printer printer = Printer.getPrinter(); // singleton
		// List<String> data = new ArrayList<>();
		User u = new User();
		u.setName(user.getName());
		u.setID("");
		u.setPW("");
		u.setDepartment(user.getDepartment());
		u.setPosition(user.getPosition());
		u.setAge(user.getAge());
		userlist.add(u);

		printer.println("간호사 추가 완료"); // singleton
		return userlist;
	}

//	public void editDoctor(User user) {
//		printer.println("의사 수정");
//	}
//	public void editNurse(User user) {
//		printer.println("간호사 수정");
//	}
	public List<User> deleteDoctor(String name) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		// FileINnOUT fileinout = new FileINnOUT();
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());
		int index = 0;
		for (User item : userlist) {
			if (item.getName().equals(name) && item.getPosition().equals("Doctor")) {
				// printer.println(list.get(index));
				userlist.remove(index);
				userfile.save(userlist);
				printer.println("의사 삭제 완료");
				// fileinout.saveFile(userlist);

//				try {
//
//					StringBuffer data = new StringBuffer();
//					Charset.forName("UTF-8");
//					for (List<String> item2 : list) {
//						data.append(item2.get(0) + "," + item2.get(1) + "," + item2.get(2) + "," + item2.get(3) + ","
//								+ item2.get(4) + "," + item2.get(5) + "\n");
//					}
//					FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
//					outputStream.write(data.toString().getBytes());
//					outputStream.close();
//
//					printer.println("저장완료");
//
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						if (br != null) {
//							br.close();
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//
//					}
//				}
				return userlist;
			}
			index++;
		}
		printer.println("존재하지 않는 의사입니다.");
		return userlist;

	}

	public List<User> deleteNurse(String name) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		Printer printer = Printer.getPrinter(); // singleton
		// FileINnOUT fileinout = new FileINnOUT();
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());
		int index = 0;
		for (User item : userlist) {
			if (item.getName().equals(name) && item.getPosition().equals("Nurse")) {
				// printer.println(list.get(index));
				userlist.remove(index);
				userfile.save(userlist);

				printer.println("간호사 삭제 완료");
				// fileinout.saveFile(userlist);

//				try {
//
//					StringBuffer data = new StringBuffer();
//					Charset.forName("UTF-8");
//					for (List<String> item2 : list) {
//						data.append(item2.get(0) + "," + item2.get(1) + "," + item2.get(2) + "," + item2.get(3) + ","
//								+ item2.get(4) + "," + item2.get(5) + "\n");
//					}
//					FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
//					outputStream.write(data.toString().getBytes());
//					outputStream.close();
//
//					printer.println("저장완료");
//
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						if (br != null) {
//							br.close();
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//
//					}
//				}
				return userlist;
			}
			index++;
		}
		printer.println("존재하지 않는 간호사입니다.");
		return userlist;

	}
}
