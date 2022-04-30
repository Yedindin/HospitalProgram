package com.handong.oodp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Work {
	
	private List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);
	
	
	public List<List<List<String>>> loadWorkSchedule(List<List<List<String>>> schedule) {
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./data/applySchedule.csv"));
			String line = "";
			int row_count = 0;
			
			while ((line = bufferedReader.readLine()) != null) {
				List<List<String>> weektime_= new ArrayList<List<String>>(7);
				List<String> stringList = new ArrayList<>();
				String[] work_1 = new String[7];
				work_1 = line.split(",");
				for(String item : work_1) {
					String[] peopleList = item.split("/");
					stringList= Arrays.asList(peopleList);
					weektime_.add(stringList);
				}
				schedule.add(weektime_);
			}
//			for(int i = 0; i<schedule.size();i++)
//				for(int j = 0; j<schedule.get(i).size();j++) {
//					for(int k = 0;k <schedule.get(i).get(j).size(); k++)
//						System.out.print( Integer.toString(i) + Integer.toString(j) + schedule.get(i).get(j).get(k)+"|");
//					System.out.println();
//				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	public void updateWorkSchedule(List<List<List<String>>> schedule) {
		String filePath = "./data/applySchedule.csv";
		String NEWLINE = System.lineSeparator();
		try {
			File file = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i<schedule.size();i++) {
				for(int j = 0; j<schedule.get(i).size();j++) {
					String temp = "";
					for(int k = 0;k <schedule.get(i).get(j).size(); k++)
						temp+=(schedule.get(i).get(j).get(k)+"/");
					temp+=",";
					System.out.println(temp);
					bw.write(temp);
				}
				bw.write(NEWLINE);
			}
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void displaySchedule() {
		System.out.println("\n\n****전체 일정****");
		System.out.println("	월			화			수			목			금			토			일");
		System.out.print("1"); //사람이름
		System.out.print("2");
		System.out.print("3");
		
	}
	
	public List<List<List<String>>> deleteWorkSchedule(String user, String position, List<List<String>> list,List<List<List<String>>> schedule) {
		Scanner sc = new Scanner(System.in);
		//display
		if (position.equals("manager")) {
			System.out.println("삭제하고 싶은 스케줄의 유저를 입력하세요 (예시.홍길동)");
			String crew_name = sc.next();
			boolean isNoName = true;
			for (List<String> item : list) {
				if (crew_name.equals(item.get(0))) {
					isNoName = false;
				}
			}
			if(isNoName) {
				System.out.println("존재하지 않는 유저입니다.");
			}
			else {
				System.out.println("원하는 삭제 시간을 입력하세요(예시. 월1).");
				String time = sc.next();
				System.out.println(time);

				// 삭제**
				// save
				// update
				System.out.println("삭제되었습니다.");
			}
		}
		else {
			System.out.println("원하는 삭제 시간을 입력하세요(예시. 월1).");
			String time = sc.next();
			System.out.println(time);
			// 삭제**
			// save
			// update
			System.out.println("삭제되었습니다.");
		}
		return schedule;
	}
	
	public List<List<List<String>>> addWorkSchedule(String user, String position, List<List<String>> list,List<List<List<String>>> schedule) {
		Scanner sc = new Scanner(System.in);
		if (position.equals("manager")) {
				System.out.println("넣고 싶은 유저를 입력하세요 (예시.홍길동)");
				String crew_name = sc.next();
				boolean isNoName = true;
				for (List<String> item : list) {
					if (crew_name.equals(item.get(0))) {
						isNoName = false;
					}
				}
				if(isNoName) {
					System.out.println("존재하지 않는 유저입니다.");
				}
				else {
					System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
					String time = sc.next();
					System.out.println(time);

					// 추가**
					// save
					// update
					System.out.println("추가되었습니다.");
				}
		}
		else {
			System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
			String time = sc.next();
			System.out.println(time);
			// 추가**
			// save
			// update
			System.out.println("추가되었습니다.");
		}//not manager
		return schedule;
	}
}
