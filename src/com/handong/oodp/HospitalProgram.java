package com.handong.oodp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

public class HospitalProgram {

	public static void main(String[] args) {

		EmploymentManagement emplmanage = new EmploymentManagement();
		Login login = new Login();
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id, pw;
		String position = "fail";

		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = Files.newBufferedReader(Paths.get("./data/userdata.csv"));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				List<String> stringList = new ArrayList<>();
				String stringArray[] = line.split(",");
				stringList = Arrays.asList(stringArray);
				list.add(stringList);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				assert bufferedReader != null;
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * for (List<String> item : list) { System.out.println(item.get(0) + "and" +
		 * item.get(1) + item.get(2) + item.get(3) + item.get(4) ); }
		 */
		while (position == "fail") {
			System.out.println("ID를 입력하세요.");
			id = sc.next();
			System.out.println("패스워드를 입력하세요.");
			pw = sc.next();
			position = login.login(id, pw, list); // position 에 따라 다른 기능 구현
			if (position == "fail") {
				System.out.println("아이디나 비밀번호를 잘못입력하셨습니다.");
				System.out.println("다시 로그인해 주세요.");
			}
		}
		if (position.equals("manager")) {

			while (run) {
				System.out.println("**** 병원 관리 프로그램 ****");
				System.out.println("1. 고용관리");
				System.out.println("2. 계정관리");
				System.out.println("0. 종료\n\n");

				System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
				num1 = sc.nextInt();
				if (num1 == 0) { // 종료
					run = false;
					System.out.println("병원 관리 프로그램을 종료합니다.");
				} else if (num1 == 1) {// 고용관리
					while (true) {
						System.out.println("\n\n**** 계정 관리 ****");
						System.out.println("1. 의사/간호사 추가");
						System.out.println("2. 의사/간호사 삭제");
						System.out.println("0. 이전으로 돌아가기\n\n");
						System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
						num2 = sc.nextInt();

						if (num2 == 1) {
							System.out.println("\n\n**** 추가하기 ****");
							System.out.println("1. 의사 추가");
							System.out.println("2. 간호사 추가");
							System.out.println("0. 처음으로 돌아가기\n\n");
							System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
							int num = sc.nextInt();
							if (num == 1) {
								User user = new User();
								emplmanage.addDoctor(user);
							} else if (num == 2) {
								User user = new User();
								emplmanage.addNurse(user);
							} else {
								break;
							}
						} else if (num2 == 2) {
							System.out.println("\n\n**** 삭제하기 ****");
							System.out.println("1. 의사 삭제");
							System.out.println("2. 간호사 삭제");
							System.out.println("0. 처음으로 돌아가기\n\n");
							System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
							int num = sc.nextInt();
							if (num == 1) {
								User user = new User();
								emplmanage.deleteDoctor(user);
							} else if (num == 2) {
								User user = new User();
								emplmanage.deleteNurse(user);
							} else {
								break;
							}
						} else {
							break;
						}
					}
				} else if (num1 == 2) {// 계정관리

				} else if (num1 == 3) {// 로그인

				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
					continue;
				}
			}
			
		}
		else {
			System.out.println("매니저아님");
		}
	}

}
