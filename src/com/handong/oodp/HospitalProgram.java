package com.handong.oodp;

import java.util.Scanner;
import com.handong.oodp.*;

public class HospitalProgram {

	public static void main(String[] args) {

		EmploymentManagement emplmanage = new EmploymentManagement();

		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;

		while (run) {
			System.out.println("**** 병원 관리 프로그램 ****");
			System.out.println("1. 고용관리");
			System.out.println("2. 계정관리");
			System.out.println("3. 로그인");
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
						int num =sc.nextInt();
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
						int num =sc.nextInt();
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

}
