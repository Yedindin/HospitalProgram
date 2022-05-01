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
	
	
	public void displaySchedule(List<List<List<String>>> schedule) {
		System.out.println("\n\n****전체 일정****");
		System.out.println("	월		화		수		목		금		토		일");
		for(int i = 0; i<schedule.size();i++) {
			System.out.print(Integer.toString(i+1)); 
			for(int j = 0; j<schedule.get(i).size();j++) {
				String temp = "";
				for(int k = 0;k <schedule.get(i).get(j).size(); k++) {
					if(k == 0)
						temp+=(schedule.get(i).get(j).get(k));
					else temp+=(","+schedule.get(i).get(j).get(k));
				}
				System.out.print(temp+"|	");
			}
			System.out.println();
		}
		
	}
	
	public List<List<List<String>>> deleteWorkSchedule(String user, String position, List<List<String>> list,List<List<List<String>>> schedule) {
		Scanner sc = new Scanner(System.in);
		int i=0,j=0;
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

				if (time.charAt(0)=='월')
					i=0;
				else if (time.charAt(0)=='화')
					i=1;
				else if (time.charAt(0)=='수')
					i=2;
				else if (time.charAt(0)=='목')
					i=3;
				else if (time.charAt(0)=='금')
					i=4;
				else if (time.charAt(0)=='토')
					i=5;
				else if (time.charAt(0)=='일')
					i=6;
				if (time.charAt(1)=='1')
					j=0;
				else if (time.charAt(1)=='2')
					j=1;
				else if (time.charAt(1)=='3')
					j=2;
				
		         List<List<String>> week = schedule.get(j);
		         List<String> temp = schedule.get(j).get(i);
		         String[] people_list, new_list;
		         people_list = temp.toArray(new String[temp.size()]);
		         new_list = new String[temp.size()-1];
		         for(int i1=0; i1<temp.size(); i1++) {
		        	 if(crew_name.equals(people_list[i1])) {
		        		 if(i1==temp.size()-1) {
		        			 for(int i2=0; i2<temp.size()-1; i2++) {
		    		        	 new_list[i2] = people_list[i2];
		        			 }
		        		 }
		        		 else {
		        			 for(int i2=0; i2<temp.size()-1; i2++) {
		        				 if(i2<=i1) {
		        					 new_list[i2] = people_list[i2];
		        				 }
		        				 else {
		        					 new_list[i2] = people_list[i2-1];
		        				 }
		        			 }
		        		 }
		        	 }
		        	
		         }
		         week.set(i, Arrays.asList(new_list));
		         schedule.set(j, week);

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

			if (time.charAt(0)=='월')
				i=0;
			else if (time.charAt(0)=='화')
				i=1;
			else if (time.charAt(0)=='수')
				i=2;
			else if (time.charAt(0)=='목')
				i=3;
			else if (time.charAt(0)=='금')
				i=4;
			else if (time.charAt(0)=='토')
				i=5;
			else if (time.charAt(0)=='일')
				i=6;
			if (time.charAt(1)=='1')
				j=0;
			else if (time.charAt(1)=='2')
				j=1;
			else if (time.charAt(1)=='3')
				j=2;
			
	         List<List<String>> week = schedule.get(j);
	         List<String> temp = schedule.get(j).get(i);
	         String[] people_list, new_list;
	         people_list = temp.toArray(new String[temp.size()]);
	         new_list = new String[temp.size()-1];
	         for(int i1=0; i1<temp.size(); i1++) {
	        	 if(crew_name.equals(people_list[i1])) {
	        		 if(i1==temp.size()-1) {
	        			 for(int i2=0; i2<temp.size()-1; i2++) {
	    		        	 new_list[i2] = people_list[i2];
	        			 }
	        		 }
	        		 else {
	        			 for(int i2=0; i2<temp.size()-1; i2++) {
	        				 if(i2<=i1) {
	        					 new_list[i2] = people_list[i2];
	        				 }
	        				 else {
	        					 new_list[i2] = people_list[i2-1];
	        				 }
	        			 }
	        		 }
	        	 }
	        	
	         }
	         week.set(i, Arrays.asList(new_list));
	         schedule.set(j, week);
			// 삭제**
			// save
			// update
			System.out.println("삭제되었습니다.");
		}
		return schedule;
	}
	
	public List<List<List<String>>> addWorkSchedule(String user, String position, List<List<String>> list,List<List<List<String>>> schedule) {
		Scanner sc = new Scanner(System.in);
		int i=0,j=0;
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
					if (time.charAt(0)=='월')
						i=0;
					else if (time.charAt(0)=='화')
						i=1;
					else if (time.charAt(0)=='수')
						i=2;
					else if (time.charAt(0)=='목')
						i=3;
					else if (time.charAt(0)=='금')
						i=4;
					else if (time.charAt(0)=='토')
						i=5;
					else if (time.charAt(0)=='일')
						i=6;
					if (time.charAt(1)=='1')
						j=0;
					else if (time.charAt(1)=='2')
						j=1;
					else if (time.charAt(1)=='3')
						j=2;
					
			         List<List<String>> week = schedule.get(j);
			         List<String> temp = schedule.get(j).get(i);
			         String[] people_list;
			         people_list = temp.toArray(new String[temp.size()+1]);
			         people_list[temp.size()]=crew_name;
			         week.set(i, Arrays.asList(people_list));
			         schedule.set(j, week);
					// 추가**
					// save
				}
		}
		else {			
			System.out.println("원하는 추가 시간을 입력하세요(예시. 월1).");
			String time = sc.next();
			System.out.println(time);
			if (time.charAt(0)=='월')
				i=0;
			else if (time.charAt(0)=='화')
				i=1;
			else if (time.charAt(0)=='수')
				i=2;
			else if (time.charAt(0)=='목')
				i=3;
			else if (time.charAt(0)=='금')
				i=4;
			else if (time.charAt(0)=='토')
				i=5;
			else if (time.charAt(0)=='일')
				i=6;
			if (time.charAt(1)=='1')
				j=0;
			else if (time.charAt(1)=='2')
				j=1;
			else if (time.charAt(1)=='3')
				j=2;
			
	         List<List<String>> week = schedule.get(j);
	         List<String> temp = schedule.get(j).get(i);
	         String[] people_list;
	         people_list = temp.toArray(new String[temp.size()+1]);
	         people_list[temp.size()]=user;
	         week.set(i, Arrays.asList(people_list));
	         schedule.set(j, week);
			// 추가**
			// save
			// update
			
		}//not manager
		updateWorkSchedule(schedule);
		System.out.println("추가되었습니다.");
		return schedule;
	}
}
