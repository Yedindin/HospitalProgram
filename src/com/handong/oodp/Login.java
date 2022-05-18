package com.handong.oodp;
import java.util.List;

import com.handong.oodp.iterator.User;
import com.handong.oodp.Singleton.Printer;

public class Login {
	
	public String login(String ID, String PW,List<User> list) {
		Printer printer = Printer.getPrinter(); //singleton
		for (User item : list) {
			if(ID.equals( item.getID()) && PW.equals( item.getPW()))
			{
				printer.println("로그인에 성공하셨습니다.");
				return item.getPosition();
			}
		}
		return "fail";
		
	}
	public String getName(String ID,List<User> list) {
		for (User item : list) {
			if(ID.equals( item.getID()))
			{
				return item.getName();
			}
		}
		return "fail";
	}
	
	public void logout() {
		Printer printer = Printer.getPrinter(); //singleton
		printer.println("로그아웃");
	}

}
