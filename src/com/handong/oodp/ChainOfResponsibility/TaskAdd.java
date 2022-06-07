package com.handong.oodp.ChainOfResponsibility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.TaskFile;
import com.handong.oodp.file.File;
import com.handong.oodp.file.save.TaskSave;
import com.handong.oodp.file.save.NRoundingSave;
import com.handong.oodp.file.save.DRoundingSave;
import com.handong.oodp.iterator.User;
import com.handong.oodp.HospitalProgram;
import com.handong.oodp.Singleton.Printer;

public class TaskAdd extends TaskWork{
	
	private int request;
	public TaskAdd(int request) {
		super();
		this.request = request;
	}

	protected boolean resolve(Request request) throws IOException {
		List<List<List<String>>> task = new ArrayList<List<List<String>>>(3);
		task = HospitalProgram.task;
		
		if (request.getRnum() == 2) {
			Printer printer = Printer.getPrinter(); // singleton
			Scanner sc = new Scanner(System.in);
			File taskfile = new TaskFile("DRoundingfile");
			taskfile.setSavestrategy(new DRoundingSave());
			int i = 0, j = 0;
			List<List<List<String>>> schedule = request.getTask();
			if (request.getPosition().equals("manager")) {
				printer.println("넣고 싶은 유저를 입력하세요 (예시.홍길동)");
				String crew_name = sc.next();
				boolean isNoName = true;
				for (User item : request.getUserlist()) {
					if (crew_name.equals(item.getName())) {
						isNoName = false;
					}
				}
				if (isNoName) {
					printer.println("존재하지 않는 유저입니다.");
					return true;
				} else {
					printer.println("원하는 추가 시간을 입력하세요(예시. 월1).");
					String time = sc.next();
					printer.println(time);
					if (time.charAt(0) == '월')
						i = 0;
					else if (time.charAt(0) == '화')
						i = 1;
					else if (time.charAt(0) == '수')
						i = 2;
					else if (time.charAt(0) == '목')
						i = 3;
					else if (time.charAt(0) == '금')
						i = 4;
					else if (time.charAt(0) == '토')
						i = 5;
					else if (time.charAt(0) == '일')
						i = 6;
					else {
						printer.println("유효하지 않는 입력입니다.");
						return true;
					}

					if (time.charAt(1) == '1')
						j = 0;
					else if (time.charAt(1) == '2')
						j = 1;
					else if (time.charAt(1) == '3')
						j = 2;
					else {
						printer.println("유효하지 않는 입력입니다.");
						return true;
					}

					List<List<String>> week = schedule.get(j);
					List<String> temp = schedule.get(j).get(i);
					String[] people_list;
					people_list = temp.toArray(new String[temp.size() + 1]);
					people_list[temp.size()] = crew_name;
					week.set(i, Arrays.asList(people_list));
					schedule.set(j, week);
				}
			} else {
				printer.println("원하는 추가 시간을 입력하세요(예시. 월1).");
				String time = sc.next();
				printer.println(time);
				if (time.charAt(0) == '월')
					i = 0;
				else if (time.charAt(0) == '화')
					i = 1;
				else if (time.charAt(0) == '수')
					i = 2;
				else if (time.charAt(0) == '목')
					i = 3;
				else if (time.charAt(0) == '금')
					i = 4;
				else if (time.charAt(0) == '토')
					i = 5;
				else if (time.charAt(0) == '일')
					i = 6;
				else {
					printer.println("유효하지 않는 입력입니다.");
					return true;
				}

				if (time.charAt(1) == '1')
					j = 0;
				else if (time.charAt(1) == '2')
					j = 1;
				else if (time.charAt(1) == '3')
					j = 2;
				else {
					printer.println("유효하지 않는 입력입니다.");
					return true;
				}

				List<List<String>> week = schedule.get(j);
				List<String> temp = schedule.get(j).get(i);
				String[] people_list;
				people_list = temp.toArray(new String[temp.size() + 1]);
				people_list[temp.size()] = request.getName();
				week.set(i, Arrays.asList(people_list));
				schedule.set(j, week);
			} // not manager
			taskfile.save(task);
			printer.println("추가되었습니다.");
			return true;
		}
		return false;
		
	}

}