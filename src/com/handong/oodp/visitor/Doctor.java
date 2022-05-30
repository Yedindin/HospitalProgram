package com.handong.oodp.visitor;

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

public class Doctor implements UserV {
	
	private List<User> userlist;
	private int total =0;
	
	public Doctor(List<User> userlist) {
		this.userlist = userlist;
	}
	
	@Override
	public List<User> addUser(User user) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		User u = new User();
		u.setName(user.getName());
		u.setID("");
		u.setPW("");
		u.setDepartment(user.getDepartment());
		u.setPosition(user.getPosition());
		u.setAge(user.getAge());
		userlist.add(u);

		printer.println("의사 추가 완료");
		return userlist;
	}

	@Override
	public List<User> deleteUser(User user) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		File userfile = new UserFile("userfile");
		userfile.setSavestrategy(new UserSave());
		int index = 0;
		for (User item : userlist) {
			if (item.getName().equals(user.getName()) && item.getPosition().equals("Doctor")) {
				// printer.println(list.get(index));
				userlist.remove(index);
				userfile.save(userlist);
				printer.println("의사 삭제 완료");

				return userlist;
			}
			index++;
		}
		printer.println("존재하지 않는 의사입니다.");
		return userlist;
	}

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public int getTotal() {
		for (User user1 : userlist) {
			if(user1.getPosition().equals("Doctor")) {
				total++;
			}
		}
		return total;
	}

}
