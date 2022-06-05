package com.handong.oodp.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.File;
import com.handong.oodp.file.TaskFile;
import com.handong.oodp.file.save.TaskSave;
import com.handong.oodp.file.save.NRoundingSave;
import com.handong.oodp.file.save.DRoundingSave;
import com.handong.oodp.iterator.User;

import com.handong.oodp.Singleton.Printer;

public class TaskWork extends Work{

	private List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);

	public void displaySchedule(List<List<List<String>>> schedule) {
		Printer printer = Printer.getPrinter(); // singleton
		printer.println("\n\n****전체 일정****");
		printer.println("   월      화      수      목      금      토      일");
		for (int i = 0; i < schedule.size(); i++) {
			printer.print(Integer.toString(i + 1));
			for (int j = 0; j < schedule.get(i).size(); j++) {
				String temp = "";
				for (int k = 0; k < schedule.get(i).get(j).size(); k++) {
					if (k == 0)
						temp += (schedule.get(i).get(j).get(k));
					else
						temp += ("," + schedule.get(i).get(j).get(k));
				}
				printer.print(temp + "|   ");
			}
			printer.println("");
		}
	}

	public List<List<List<String>>> deleteWorkSchedule(String user, String position, List<User> userlist,
			List<List<List<String>>> schedule) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		File taskfile = new TaskFile("taskfile");
		taskfile.setSavestrategy(new TaskSave());
		int i = 0, j = 0;
		// display
		if (position.equals("manager")) {
			printer.println("삭제하고 싶은 스케줄의 유저를 입력하세요 (예시.홍길동)");
			String crew_name = sc.next();
			boolean isNoName = true;
			for (User item : userlist) {
				if (crew_name.equals(item.getName())) {
					isNoName = false;
				}
			}
			if (isNoName) {
				printer.println("존재하지 않는 유저입니다.");
				return schedule;
			} else {
				printer.println("원하는 삭제 시간을 입력하세요(예시. 월1).");
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
					//printer.println("유효하지 않는 입력입니다.");
					return schedule;
				}

				if (time.charAt(1) == '1')
					j = 0;
				else if (time.charAt(1) == '2')
					j = 1;
				else if (time.charAt(1) == '3')
					j = 2;
				else {
					//printer.println("유효하지 않는 입력입니다.");
					return schedule;
				}

				Boolean flag = true;
				for (int k = 0; k < schedule.get(j).get(i).size(); k++) {
					if (crew_name.equals(schedule.get(j).get(i).get(k))) {
						printer.println(schedule.get(j).get(i).get(k));
						flag = false;
						break;
					}
				}
				if (flag) {
					//printer.println("유효하지 않는 입력입니다.");
					return schedule;
				} else {
					List<List<String>> week = schedule.get(j);
					List<String> temp = schedule.get(j).get(i);
					String[] people_list, new_list;
					people_list = temp.toArray(new String[temp.size()]);
					new_list = new String[temp.size() - 1];
					for (int i1 = 0; i1 < temp.size(); i1++) {
						if (crew_name.equals(people_list[i1])) {
							if (i1 == temp.size() - 1) {
								for (int i2 = 0; i2 < temp.size() - 1; i2++) {
									new_list[i2] = people_list[i2];
								}
							} else {
								for (int i2 = 0; i2 < temp.size() - 1; i2++) {
									if (i2 <= i1) {
										new_list[i2] = people_list[i2];
									} else {
										new_list[i2] = people_list[i2 - 1];
									}
								}
							}
						}
					}
					week.set(i, Arrays.asList(new_list));
					schedule.set(j, week);
					printer.println("삭제되었습니다.");
				}
			}
		} else {
			printer.println("원하는 삭제 시간을 입력하세요(예시. 월1).");
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
				//printer.println("유효하지 않는 입력입니다.");
				return schedule;
			}

			if (time.charAt(1) == '1')
				j = 0;
			else if (time.charAt(1) == '2')
				j = 1;
			else if (time.charAt(1) == '3')
				j = 2;
			else {
				//printer.println("유효하지 않는 입력입니다.");
				return schedule;
			}
			Boolean flag = true;
			for (int k = 0; k < schedule.get(j).get(i).size(); k++) {
				if (user.equals(schedule.get(j).get(i).get(k))) {
					//printer.println("유효하지 않는 입력입니다.");
					flag = false;
					break;
				}
			}
			if (flag) {
				//printer.println("유효하지 않는 입력입니다.");
				return schedule;
			} else {
				List<List<String>> week = schedule.get(j);
				List<String> temp = schedule.get(j).get(i);
				String[] people_list, new_list;
				people_list = temp.toArray(new String[temp.size()]);
				new_list = new String[temp.size() - 1];
				for (int i1 = 0; i1 < temp.size(); i1++) {
					if (user.equals(people_list[i1])) {
						if (i1 == temp.size() - 1) {
							for (int i2 = 0; i2 < temp.size() - 1; i2++) {
								new_list[i2] = people_list[i2];
							}
						} else {
							for (int i2 = 0; i2 < temp.size() - 1; i2++) {
								if (i2 <= i1) {
									new_list[i2] = people_list[i2];
								} else {
									new_list[i2] = people_list[i2 - 1];
								}
							}
						}
					}
				}
				week.set(i, Arrays.asList(new_list));
				schedule.set(j, week);
				printer.println("삭제되었습니다.");
			}
		}
		taskfile.save(schedule);
		return schedule;
	}

	public List<List<List<String>>> addWorkSchedule(String user, String position, List<User> userlist,
			List<List<List<String>>> schedule) throws IOException {
		Printer printer = Printer.getPrinter(); // singleton
		Scanner sc = new Scanner(System.in);
		File taskfile = new TaskFile("taskfile");
		taskfile.setSavestrategy(new TaskSave());
		int i = 0, j = 0;
		if (position.equals("manager")) {
			printer.println("넣고 싶은 유저를 입력하세요 (예시.홍길동)");
			String crew_name = sc.next();
			boolean isNoName = true;
			for (User item : userlist) {
				if (crew_name.equals(item.getName())) {
					isNoName = false;
				}
			}
			if (isNoName) {
				printer.println("존재하지 않는 유저입니다.");
				return schedule;
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
					//printer.println("유효하지 않는 입력입니다.");
					return schedule;
				}

				if (time.charAt(1) == '1')
					j = 0;
				else if (time.charAt(1) == '2')
					j = 1;
				else if (time.charAt(1) == '3')
					j = 2;
				else {
					//printer.println("유효하지 않는 입력입니다.");
					return schedule;
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
				//printer.println("유효하지 않는 입력입니다.");
				return schedule;
			}

			if (time.charAt(1) == '1')
				j = 0;
			else if (time.charAt(1) == '2')
				j = 1;
			else if (time.charAt(1) == '3')
				j = 2;
			else {
				//printer.println("유효하지 않는 입력입니다.");
				return schedule;
			}

			List<List<String>> week = schedule.get(j);
			List<String> temp = schedule.get(j).get(i);
			String[] people_list;
			people_list = temp.toArray(new String[temp.size() + 1]);
			people_list[temp.size()] = user;
			week.set(i, Arrays.asList(people_list));
			schedule.set(j, week);
		} // not manager
		taskfile.save(schedule);
		printer.println("추가되었습니다.");
		return schedule;
	}
}