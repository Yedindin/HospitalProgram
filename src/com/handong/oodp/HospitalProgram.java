package com.handong.oodp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class HospitalProgram {

	public static void main(String[] args) throws IOException {

		Login login = new Login();
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id = "", pw;
		String position = "fail";
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		List<List<String>> list = new ArrayList<List<String>>();
		List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);
		
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
		EmploymentManagement emplmanage = new EmploymentManagement(list);
		Work work = new Work();
		work.loadWorkSchedule(schedule);
		/*
		 * 리스트 출력 for (List<String> item : list) { System.out.println(item.get(0) +
		 * "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) ); }
		 */
		while(true) {

			System.out.println("**** 병원 관리 프로그램 ****");
			System.out.println("1. 로그인");
			System.out.println("2. 회원등록");
			System.out.println("0. 종료\n\n");
			System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
			
			int state = sc.nextInt();
			if (state == 1) { //로그인
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
						System.out.println("3. 일정관리");
						System.out.println("0. 종료\n\n");

						System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
						num1 = sc.nextInt();
						if (num1 == 0) { // 종료
							run = false;
							System.out.println("병원 관리 프로그램을 종료합니다.");
						}
						else if(num1 == 3) {
							int schedule1 = 0;
							System.out.println("\n\n****일정 관리****");
							System.out.println("1. 전체 일정 확인");
							System.out.println("2. 일정 추가");
							System.out.println("3. 일정 삭제");
							System.out.println("0. 뒤로가기");
							System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
							schedule1 = sc.nextInt();
							if(schedule1 == 0) {
								break;
							}
							else if(schedule1 == 1) {
								work.displaySchedule(schedule);
							}
							else if (schedule1 == 2) {
								String name = login.getName(id,list);
								schedule = work.addWorkSchedule(name,position,list,schedule);
							}
							else if (schedule1 == 3) {
								String name = login.getName(id,list);
								schedule = work.deleteWorkSchedule(name,position,list,schedule);
							}
							
						}
						else if (num1 == 1) {// 고용관리
							while (true) {
								System.out.println("\n\n**** 고용 관리 ****");
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
										User user = new User(list);
										System.out.println("이름을 입력해주세요.");
										user.setName(sc.next());
										System.out.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										System.out.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("Doctor");
										list = emplmanage.addDoctor(user);

										try {

											StringBuffer data = new StringBuffer();
											Charset.forName("UTF-8");
											for (List<String> item : list) {

												data.append(item.get(0) + "," + item.get(1) + "," + item.get(2) + ","
														+ item.get(3) + "," + item.get(4) + "," + item.get(5) + "\n");
											}
											FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
											outputStream.write(data.toString().getBytes());
											outputStream.close();

											System.out.println("저장완료");

										} catch (FileNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										} finally {
											try {
												if (br != null) {
													br.close();
												}
											} catch (IOException e) {
												e.printStackTrace();

											}
										}

									} else if (num == 2) {
										User user = new User(list);
										System.out.println("이름을 입력해주세요.");
										user.setName(sc.next());
										System.out.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										System.out.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("Nurse");
										list = emplmanage.addNurse(user);

										try {
											StringBuffer data = new StringBuffer();
											Charset.forName("UTF-8");
											for (List<String> item : list) {

												data.append(item.get(0) + "," + item.get(1) + "," + item.get(2) + ","
														+ item.get(3) + "," + item.get(4) + "," + item.get(5) + "\n");
											}
											FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
											outputStream.write(data.toString().getBytes());
											outputStream.close();

											System.out.println("저장완료");

										} catch (FileNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										} finally {
											try {
												if (br != null) {
													br.close();
												}
											} catch (IOException e) {
												e.printStackTrace();

											}
										}
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
										System.out.println("삭제할 의사의 이름을 입력해 주세요.");
										User user = new User(list);
										list = emplmanage.deleteDoctor(sc.next());
										
									} else if (num == 2) {
										System.out.println("삭제할 간호사의 이름을 입력해 주세요.");
										User user = new User(list);
										list = emplmanage.deleteNurse(sc.next());
									} else {
										break;
									}
								} else {
									break;
								}
							}
						} else if (num1 == 2) {// 계정관리
							System.out.println("\n\n**** 계정관리 ****");
							System.out.println("1. 회원등록");
							System.out.println("2. 회원삭제");
							System.out.println("0. 이전으로 돌아가기\n\n");
							System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
							int num = sc.nextInt();
							if (num == 1) {
								System.out.println("회원등록할 이름을 입력해 주세요.");
								User user = new User(list);
								list = user.addUser(sc.next());
								continue;
							} else if (num == 2) {
								System.out.println("삭제할 회원의 이름을 입력해 주세요.");
								User user = new User(list);
								list = user.deleteUser(sc.next());
							} else {
								continue;
							}
							
						} else { 
							System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
							continue;
						}
					}

				} else
				{
					while(true) {
						System.out.println("\n\n**** 매니저아님 ****");
						System.out.println("1. 일정 관리");
						System.out.println("0. 로그아웃");
						System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
						int client_num = sc.nextInt();
						while(client_num == 1) {
							
							int schedule1 = 0;
							System.out.println("\n\n****일정 관리****");
							System.out.println("1. 전체 일정 확인");
							System.out.println("2. 일정 추가");
							System.out.println("3. 일정 삭제");
							System.out.println("0. 뒤로가기");
							System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
							schedule1 = sc.nextInt();
							if(schedule1 == 0) {
								break;
							}
							else if(schedule1 == 1) {
								work.displaySchedule(schedule);
							}
							else if (schedule1 == 2) {
								String name = login.getName(id,list);
								schedule = work.addWorkSchedule(name,position,list,schedule);
							}
						}
						if(client_num == 0) {
							break;
						}
						else {
							continue;
						}
					}
				}
			}
			else if(state==2) {
				System.out.println("성함을 입력해 주세요.");
				User user = new User(list);
				String name = sc.next();
				list = user.addUser(name);
				
				
			}
			else if(state==0) {
				break;
			}else {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
				continue;
			}
		}
	}

}
