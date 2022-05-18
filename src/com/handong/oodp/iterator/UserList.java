package com.handong.oodp.iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Singleton.Printer;
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

				return userlist;
			}
			index++;
		}
		printer.println("존재하지 않는 회원입니다.");
		return userlist;

	}
	public List<User> editUser(String name) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());
		for (User user1 : userlist) {
			if(user1.getName().equals(name)) {
				printer.println("변경할 ID를 입력하세요.");
				user1.setID(sc.next());
				printer.println("변경할 패스워드를 입력하세요.");
				user1.setPW(sc.next());
				printer.println("회원정보 변경이 완료되었습니다.");
				user1.setName(user1.getName());
				user1.setID(user1.getID());
				user1.setPW(user1.getPW());
				user1.setDepartment(user1.getDepartment());
				user1.setPosition(user1.getPosition());
				user1.setAge(user1.getAge());
				
				userfile.save(userlist);
				return userlist;
			}
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
