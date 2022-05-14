package com.handong.oodp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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
		PatientManage patientManage = new PatientManage();
		Printer printer = Printer.getPrinter(); //singleton
		String mainmenu = "**** 병원 관리 프로그램 ****\n1. 로그인\n2. 회원등록\n0. 종료 \n\n";
		String scanNumber = "원하는 메뉴의 번호를 입력하세요 : ";
		String inputOnlyNumber = "숫자만 입력하세요.\n\n";
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id = "", pw;
		String position = "fail";
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		List<List<String>> list = new ArrayList<List<String>>();
		List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);
		
		//환자 정보 불러오기
		List<Patient> patientList = new ArrayList<Patient>();
		patientList = patientManage.loadPatient();
		

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
		 * 리스트 출력 for (List<String> item : list) { printer.println(item.get(0) +
		 * "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) ); }
		 */
		while (true) {
			printer.print(mainmenu);
			printer.print(scanNumber);

			int state = 0;
			while (true) {
				try {
					state = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					sc = new Scanner(System.in);
					printer.print(inputOnlyNumber);
					printer.print(mainmenu);
					printer.print(scanNumber);
				}
			}

			if (state == 1) { // 로그인
				while (position == "fail") {
					printer.println("ID를 입력하세요.");
					id = sc.next();
					printer.println("패스워드를 입력하세요.");
					pw = sc.next();
					position = login.login(id, pw, list); // position 에 따라 다른 기능 구현
					if (position == "fail") {
						printer.println("아이디나 비밀번호를 잘못입력하셨습니다.");
						printer.println("다시 로그인해 주세요.");
					}
				}
				if (position.equals("manager")) {

					while (run) {
						printer.println("**** 병원 관리 프로그램 ****");
						printer.println("1. 고용관리");
						printer.println("2. 계정관리");
						printer.println("3. 일정관리");
						printer.println("0. 로그아웃\n\n");
						printer.print(scanNumber);

						while (true) {
							try {
								num1 = sc.nextInt();
								break;
							} catch (InputMismatchException e) {
								sc = new Scanner(System.in);
								printer.print(inputOnlyNumber);
								printer.println("**** 병원 관리 프로그램 ****");
								printer.println("1. 고용관리");
								printer.println("2. 계정관리");
								printer.println("3. 일정관리");
								printer.println("0. 로그아웃\n\n");
								printer.print(scanNumber);
							}
						}

						if (num1 == 0) { // 종료
							//run = false;
							position = "fail";
							printer.println("병원 관리 프로그램을 종료합니다.");
							break;
						} else if (num1 == 3) {
							while (true) {
								int schedule1 = 0;
								printer.println("\n\n****일정 관리****");
								printer.println("1. 전체 일정 확인");
								printer.println("2. 일정 추가");
								printer.println("3. 일정 삭제");
								printer.println("0. 이전으로 돌아가기");
								printer.print("원하는 메뉴의 번호를 입력하세요 : ");

								while (true) {
									try {
										schedule1 = sc.nextInt();
										break;
									} catch (InputMismatchException e) {
										sc = new Scanner(System.in);
										printer.print(inputOnlyNumber);

										printer.println("\n\n****일정 관리****");
										printer.println("1. 전체 일정 확인");
										printer.println("2. 일정 추가");
										printer.println("3. 일정 삭제");
										printer.println("0. 이전으로 돌아가기");
										printer.print("원하는 메뉴의 번호를 입력하세요 : ");
									}
								}

								if (schedule1 == 0) {
									break;
								} else if (schedule1 == 1) {
									work.displaySchedule(schedule);
								} else if (schedule1 == 2) {
									String name = login.getName(id, list);
									schedule = work.addWorkSchedule(name, position, list, schedule);
								} else if (schedule1 == 3) {
									String name = login.getName(id, list);
									schedule = work.deleteWorkSchedule(name, position, list, schedule);
								} else if (schedule1 == 0) {
									break;
								} else {
									printer.println("유효하지 않는 입력입니다.");
								}
							}

						} else if (num1 == 1) {// 고용관리
							while (true) {
								printer.println("\n\n**** 고용 관리 ****");
								printer.println("1. 의사/간호사 추가");
								printer.println("2. 의사/간호사 삭제");
								printer.println("0. 이전으로 돌아가기\n\n");
								printer.print("원하는 메뉴의 번호를 입력하세요 : ");

								while (true) {
									try {
										num2 = sc.nextInt();
										break;
									} catch (InputMismatchException e) {
										sc = new Scanner(System.in);
										printer.print(inputOnlyNumber);
										printer.println("**** 병원 관리 프로그램 ****");
										printer.println("1. 로그인");
										printer.println("2. 회원등록");
										printer.println("0. 종료\n\n");
										printer.print("원하는 메뉴의 번호를 입력하세요 : ");
									}
								}

								if (num2 == 1) {
									printer.println("\n\n**** 추가하기 ****");
									printer.println("1. 의사 추가");
									printer.println("2. 간호사 추가");
									printer.println("0. 처음으로 돌아가기\n\n");
									printer.print("원하는 메뉴의 번호를 입력하세요 : ");
									int num = 0;
									while (true) {
										try {
											num = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(inputOnlyNumber);

											printer.println("\n\n**** 추가하기 ****");
											printer.println("1. 의사 추가");
											printer.println("2. 간호사 추가");
											printer.println("0. 처음으로 돌아가기\n\n");
											printer.print("원하는 메뉴의 번호를 입력하세요 : ");
										}
									}

									if (num == 1) {
										User user = new User(list);
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
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

											printer.println("저장완료");

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
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
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

											printer.println("저장완료");

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
									printer.println("\n\n**** 삭제하기 ****");
									printer.println("1. 의사 삭제");
									printer.println("2. 간호사 삭제");
									printer.println("0. 처음으로 돌아가기\n\n");
									printer.print("원하는 메뉴의 번호를 입력하세요 : ");
									int num = 0;
									while (true) {
										try {
											num = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(inputOnlyNumber);

											printer.println("\n\n**** 삭제하기 ****");
											printer.println("1. 의사 삭제");
											printer.println("2. 간호사 삭제");
											printer.println("0. 처음으로 돌아가기\n\n");
											printer.print("원하는 메뉴의 번호를 입력하세요 : ");
										}
									}

									if (num == 1) {
										printer.println("삭제할 의사의 이름을 입력해 주세요.");
										User user = new User(list);
										list = emplmanage.deleteDoctor(sc.next());

									} else if (num == 2) {
										printer.println("삭제할 간호사의 이름을 입력해 주세요.");
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
							printer.println("\n\n**** 계정관리 ****");
							printer.println("1. 회원등록");
							printer.println("2. 회원수정");
							printer.println("3. 회원삭제");
							printer.println("0. 이전으로 돌아가기\n\n");
							printer.print("원하는 메뉴의 번호를 입력하세요 : ");
							int num = 0;
							while (true) {
								try {
									num = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(inputOnlyNumber);
									printer.println("\n\n**** 계정관리 ****");
									printer.println("1. 회원등록");
									printer.println("2. 회원수정");
									printer.println("3. 회원삭제");
									printer.println("0. 이전으로 돌아가기\n\n");
									printer.print("원하는 메뉴의 번호를 입력하세요 : ");
								}
							}
							if (num == 1) {
								printer.println("회원등록할 이름을 입력해 주세요.");
								User user = new User(list);
								list = user.addUser(sc.next());
								continue;
							} else if (num == 2) {
								printer.println("수정할 회원의 이름을 입력해 주세요.");
								User user = new User(list);
								list = user.editUser(sc.next());
							} else if (num == 3) {
								printer.println("삭제할 회원의 이름을 입력해 주세요.");
								User user = new User(list);
								list = user.deleteUser(sc.next());
							} else {
								continue;
							}

						} else {
							printer.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
							continue;
						}
					}

				} else {
					while (true) {
						printer.println("\n\n**** 매니저아님 ****");
						printer.println("1. 일정 관리");
						printer.println("2. 환자관리");
						printer.println("0. 로그아웃");
						printer.print("원하는 메뉴의 번호를 입력하세요 : ");

						int client_num = 0;
						while (true) {
							try {
								client_num = sc.nextInt();
								break;
							} catch (InputMismatchException e) {
								sc = new Scanner(System.in);
								printer.print(inputOnlyNumber);
								printer.println("\n\n**** 매니저아님 ****");
								printer.println("1. 일정 관리");
								printer.println("2. 환자관리");
								printer.println("0. 로그아웃");
								printer.print("원하는 메뉴의 번호를 입력하세요 : ");
							}
						}
						while (client_num == 1) {

							int schedule1 = 0;
							printer.println("\n\n****일정 관리****");
							printer.println("1. 전체 일정 확인");
							printer.println("2. 일정 추가");
							printer.println("3. 일정 삭제");
							printer.println("0. 뒤로가기");
							printer.print("원하는 메뉴의 번호를 입력하세요 : ");

							while (true) {
								try {
									schedule1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(inputOnlyNumber);

									printer.println("\n\n****일정 관리****");
									printer.println("1. 전체 일정 확인");
									printer.println("2. 일정 추가");
									printer.println("3. 일정 삭제");
									printer.println("0. 뒤로가기");
									printer.print("원하는 메뉴의 번호를 입력하세요 : ");
								}
							}

							if (schedule1 == 0) {
								break;
							} else if (schedule1 == 1) {
								work.displaySchedule(schedule);
							} else if (schedule1 == 2) {
								String name = login.getName(id, list);
								schedule = work.addWorkSchedule(name, position, list, schedule);
							} else if (schedule1 == 3) {
								String name = login.getName(id, list);
								schedule = work.deleteWorkSchedule(name, position, list, schedule);
							} else {
								printer.println("유효하지 않는 입력입니다.");
							}
						}
						while(client_num == 2) {
							int patient1 = 0;
							printer.println("\n\n****환자 관리****");
							printer.println("1. 전체 환자 확인");
							printer.println("2. 환자 추가");
							printer.println("3. 환자 삭제");
							printer.println("0. 뒤로가기");
							printer.print("원하는 메뉴의 번호를 입력하세요 : ");
							
							
							while (true) {
								try {
									patient1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(inputOnlyNumber);
									printer.println("\n\n****환자 관리****");
									printer.println("1. 전체 환자 확인");
									printer.println("2. 환자 추가");
									printer.println("3. 환자 삭제");
									printer.println("0. 뒤로가기");
									printer.print("원하는 메뉴의 번호를 입력하세요 : ");
								}
							}
							
							if (patient1 == 0) {
								break;
							}
							else if (patient1 == 1) {
								patientManage.displayPatientList(patientList);
							} 
							else if (patient1 == 2) {
								patientList = patientManage.addPatient(patientList);
							} 
							else if (patient1 == 3) {
								patientList = patientManage.removePatient(patientList);
							}
							else {
								printer.println("유효하지 않는 입력입니다.");
							}
							
						}
						if (client_num == 0) {
							position = "fail";
							break;
						} else {
							continue;
						}
					}
				}
			} else if (state == 2) {
				printer.println("성함을 입력해 주세요.");
				User user = new User(list);
				String name = sc.next();
				list = user.addUser(name);
			} else if (state == 0) {
				
				break;
			} else {
				printer.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
				continue;
			}
		}
	}

}
