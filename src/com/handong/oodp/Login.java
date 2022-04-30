package com.handong.oodp;
import java.util.List;

public class Login {
	
	public String login(String ID, String PW,List<List<String>> list) {
		for (List<String> item : list) {
			if(ID.equals( item.get(1)) && PW.equals( item.get(2)))
			{
				System.out.println("로그인에 성공하셨습니다.");
				return item.get(4);
			}
		}
		return "fail";
		
	}
	public void logout() {
		System.out.println("로그아웃");
	}

}
