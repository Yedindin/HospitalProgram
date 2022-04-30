package com.handong.oodp;

import java.util.List;
import java.util.Scanner;

public class Work {
	
	private List[][] schedule = new List [7][3];
	
	
	public void updateWorkSchedule() {
		
		
	}
	
	public void displaySchedule() {
		System.out.println("\n\n****전체 일정****");
		System.out.println("	월			화			수			목			금			토			일");
		System.out.print("1"); //사람이름
		System.out.print("2");
		System.out.print("3");
		
	}
	
	public void addWorkSchedule(String user, String position, List<List<String>> list) {
		Scanner sc = new Scanner(System.in);
		if (position == "manager") {
//			for(;;) {
//				System.out.println("넣고 싶은 유저를 입력하세요 (예시.홍길동)");
//				String crew_name = sc.next();
////				for (List<String> item : list) {
////					if (name.equals(item.get(0))&&item.get(1).equals("")) {
////						System.out.println("사용할 ID를 입력하세요.");
////						this.ID = sc.next();
////						System.out.println("사용할 패스워드를 입력하세요.");
////						this.PW = sc.next();
////						System.out.println("회원등록이 완료되었습니다.");
////						item.set(1,this.ID);
////						item.set(2, this.PW);
////						return list;
////					}
////				}
////				if() {
////					
////				}
//				System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
//			}
//
//			System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
//			String time = sc.next();
//			System.out.println(time);
			System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
		}
		else {
			System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
			String time = sc.next();
			System.out.println(time);
		}
	}
	

}
