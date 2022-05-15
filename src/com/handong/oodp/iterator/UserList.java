package com.handong.oodp.iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Printer;
import com.handong.oodp.file.File;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.save.UserSave;

public class UserList implements Aggregate{
	private List<User> userlist;
	
	public UserList(List<User> userlist) {
		this.userlist = userlist;
	}
	public UserList() {
		
	}
	
	public User getUserAt(int index) {
		return userlist.get(index);
	}
	public List<User> appendUser(User user1) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		//FileINnOUT fileinout = new FileINnOUT();
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());

		for (User user : userlist) {
			if(user1.getName().equals(user.getName()) && user.getID().equals("")) {
				printer.println("사용할 ID를 입력하세요.");
				user1.setID(sc.next());
				printer.println("사용할 패스워드를 입력하세요.");
				user1.setPW(sc.next());
				printer.println("회원등록이 완료되었습니다.");
				user.setName(user.getName());
				user.setID(user1.getID());
				user.setPW(user1.getPW());
				user.setDepartment(user.getDepartment());
				user.setPosition(user.getPosition());
				user.setAge(user.getAge());
				
				userfile.save(userlist);
				//fileinout.saveFile(userlist);
				return userlist;
			}
		}
		printer.println("등록 대상이 아닙니다.");
		return userlist;
		
	}
	public List<User> deleteUser(User user1) throws IOException {
		//FileINnOUT fileinout = new FileINnOUT();
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		Printer printer = Printer.getPrinter(); //singleton
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());
		int index = 0;
		for (User item : userlist ) {
			if(user1.getName().equals(item.getName()))
			{
				//printer.println(list.get(index));
				userlist.remove(index);
				
				printer.println("user 삭제 완료");
				userfile.save(userlist);
				//fileinout.saveFile(userlist);

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
		printer.println("존재하지 않는 회원입니다.");
		return userlist;

	}
	public int getLength() {
		return userlist.size();
	}
	@Override
	public Iterator iterator() {
		return new UserListIterator(this);
	}
}