package com.handong.oodp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Adapter.Print;
import com.handong.oodp.Adapter.TitlePrint;
import com.handong.oodp.file.File;
import com.handong.oodp.file.PatientFile;
import com.handong.oodp.file.ScheduleFile;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.load.PatientLoad;
import com.handong.oodp.file.load.ScheduleLoad;
import com.handong.oodp.file.load.UserLoad;
import com.handong.oodp.file.save.UserSave;
import com.handong.oodp.iterator.Iterator;
import com.handong.oodp.iterator.User;
import com.handong.oodp.iterator.UserList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class HospitalProgram {

	public static void main(String[] args) throws IOException {
		//FileINnOUT fileinout = new FileINnOUT();
		Login login = new Login();
		PatientManage patientManage = new PatientManage();
		Work work = new Work();
		Printer printer = Printer.getPrinter(); // singleton
		Print p = new TitlePrint("병원 관리 프로그램");

		
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id = "", pw;
		String position = "fail";

		//List<List<String>> list = new ArrayList<List<String>>();
		
		Menu Menu = new Menu();
		
		// 환자 정보 불러오기
		File patientfile = new PatientFile("patientfile");
		patientfile.setLoadstrategy(new PatientLoad());
		List<Patient> patientList = new ArrayList<Patient>();
		patientList = (List<Patient>) patientfile.load();
		//patientList = patientManage.loadPatient();

		File userfile = new UserFile("userfile");
		userfile.setLoadstrategy(new UserLoad());
		userfile.setSavestrategy(new UserSave());
		List<User> userlist = new ArrayList<User>();
		userlist = (List<User>) userfile.load();
		//userlist = fileinout.loadFile();
		
//		BufferedReader bufferedReader = null;
//
//		try {
//			bufferedReader = Files.newBufferedReader(Paths.get("./data/userdata.csv"));
//			String line = "";
//
//			while ((line = bufferedReader.readLine()) != null) {
//				List<String> stringList = new ArrayList<>();
//				String stringArray[] = line.split(",");
//				stringList = Arrays.asList(stringArray);
//				list.add(stringList);
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		finally {
//			try {
//				assert bufferedReader != null;
//				bufferedReader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		EmploymentManagement emplmanage = new EmploymentManagement(userlist);
		File schedulefile = new ScheduleFile("schedulefile");
		schedulefile.setLoadstrategy(new ScheduleLoad());
		List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);
		schedule = (List<List<List<String>>>) schedulefile.load();
		
		
		//work.loadWorkSchedule(schedule);
		/*
		 * 리스트 출력 for (List<String> item : list) { printer.println(item.get(0) + "and" +
		 * item.get(1) + item.get(2) + item.get(3) + item.get(4) ); }
		 */
		while (true) {
			p.printTitle();
			printer.print(Menu.main);

			int state = 0;
			while (true) {
				try {
					state = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					sc = new Scanner(System.in);
					printer.print(Menu.Number);
					p.printTitle();
					printer.print(Menu.main);
				}
			}

			if (state == 1) { // 로그인
				while (position == "fail") {
					printer.println("ID를 입력하세요.");
					id = sc.next();
					printer.println("패스워드를 입력하세요.");
					pw = sc.next();
					position = login.login(id, pw, userlist); // position 에 따라 다른 기능 구현
					if (position == "fail") {
						printer.println("아이디나 비밀번호를 잘못입력하셨습니다.");
						printer.println("다시 로그인해 주세요.");
					}
				}
				if (position.equals("manager")) {

					while (run) {
						printer.print(Menu.Manager);
						while (true) {
							try {
								num1 = sc.nextInt();
								break;
							} catch (InputMismatchException e) {
								sc = new Scanner(System.in);
								printer.print(Menu.Number);
								printer.print(Menu.Manager);
							}
						}

						if (num1 == 0) { // 종료
							// run = false;
							position = "fail";
							printer.println("병원 관리 프로그램을 종료합니다.");
							break;
						} else if (num1 == 3) {
							while (true) {
								int schedule1 = 0;
								printer.print(Menu.Schedule);
								
								while (true) {
									try {
										schedule1 = sc.nextInt();
										break;
									} catch (InputMismatchException e) {
										sc = new Scanner(System.in);
										printer.print(Menu.Number);
										printer.print(Menu.Schedule);
									}
								}

								if (schedule1 == 0) {
									break;
								} else if (schedule1 == 1) {
									work.displaySchedule(schedule);
								} else if (schedule1 == 2) {
									String name = login.getName(id, userlist);
									schedule = work.addWorkSchedule(name, position, userlist, schedule);
								} else if (schedule1 == 3) {
									String name = login.getName(id, userlist);
									schedule = work.deleteWorkSchedule(name, position, userlist, schedule);
								} else if (schedule1 == 0) {
									break;
								} else {
									printer.println("유효하지 않는 입력입니다.");
								}
							}

						} else if (num1 == 1) {// 고용관리
							while (true) {
								printer.print(Menu.Employment);

								while (true) {
									try {
										num2 = sc.nextInt();
										break;
									} catch (InputMismatchException e) {
										sc = new Scanner(System.in);
										printer.print(Menu.Number);
										printer.print(Menu.Employment);
						//오류있던부분      //printer.print(inputOnlyNumber);
										//printer.println("**** 병원 관리 프로그램 ****");
										//printer.println("1. 로그인");
										//printer.println("2. 회원등록");
										//printer.println("0. 종료\n\n");
										//printer.print("원하는 메뉴의 번호를 입력하세요 : ");
									}
								}

								if (num2 == 1) {
									printer.print(Menu.Add);

									int num = 0;
									while (true) {
										try {
											num = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(Menu.Number);
											printer.print(Menu.Add);
										}
									}

									if (num == 1) {
										User user = new User(userlist);
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("Doctor");
										userlist = emplmanage.addDoctor(user);
										
										userfile.save(userlist);
										//fileinout.saveFile(userlist);
										

//										try {
//
//											StringBuffer data = new StringBuffer();
//											Charset.forName("UTF-8");
//											for (List<String> item : list) {
//
//												data.append(item.get(0) + "," + item.get(1) + "," + item.get(2) + ","
//														+ item.get(3) + "," + item.get(4) + "," + item.get(5) + "\n");
//											}
//											FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
//											outputStream.write(data.toString().getBytes());
//											outputStream.close();
//
//											printer.println("저장완료");
//
//										} catch (FileNotFoundException e) {
//											e.printStackTrace();
//										} catch (IOException e) {
//											e.printStackTrace();
//										} finally {
//											try {
//												if (br != null) {
//													br.close();
//												}
//											} catch (IOException e) {
//												e.printStackTrace();
//
//											}
//										}

									} else if (num == 2) {
										User user = new User(userlist);
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("Nurse");
										userlist = emplmanage.addNurse(user);

										userfile.save(userlist);
//										fileinout.saveFile(userlist);
//										try {
//											StringBuffer data = new StringBuffer();
//											Charset.forName("UTF-8");
//											for (List<String> item : list) {
//
//												data.append(item.get(0) + "," + item.get(1) + "," + item.get(2) + ","
//														+ item.get(3) + "," + item.get(4) + "," + item.get(5) + "\n");
//											}
//											FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
//											outputStream.write(data.toString().getBytes());
//											outputStream.close();
//
//											printer.println("저장완료");
//
//										} catch (FileNotFoundException e) {
//											e.printStackTrace();
//										} catch (IOException e) {
//											e.printStackTrace();
//										} finally {
//											try {
//												if (br != null) {
//													br.close();
//												}
//											} catch (IOException e) {
//												e.printStackTrace();
//
//											}
//										}
									} else {
										break;
									}
								} else if (num2 == 2) {
									printer.print(Menu.Delete);

									int num = 0;
									while (true) {
										try {
											num = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(Menu.Number);
											printer.print(Menu.Delete);

										}
									}

									if (num == 1) {
										printer.println("삭제할 의사의 이름을 입력해 주세요.");
										User user = new User(userlist);
										userlist = emplmanage.deleteDoctor(sc.next());

									} else if (num == 2) {
										printer.println("삭제할 간호사의 이름을 입력해 주세요.");
										User user = new User(userlist);
										userlist = emplmanage.deleteNurse(sc.next());
									} else {
										break;
									}
								} else {
									break;
								}
							}
						} else if (num1 == 2) {// 계정관리
							printer.print(Menu.Account);
							int num = 0;
							while (true) {
								try {
									num = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(Menu.Number);
									printer.print(Menu.Account);
								}
							}
							if (num == 1) {
								printer.println("회원등록할 이름을 입력해 주세요.");
								UserList user = new UserList(userlist);
								User u = new User();
								u.setName(sc.next());
								userlist = user.appendUser(u);
								continue;
							} else if (num == 2) {
								printer.println("수정할 회원의 이름을 입력해 주세요.");
								UserList ul = new UserList(userlist);
								userlist = ul.editUser(sc.next());
							} else if (num == 3) {
								printer.println("삭제할 회원의 이름을 입력해 주세요.");
								UserList user = new UserList(userlist);
								User u = new User();
								u.setName(sc.next());
								userlist = user.deleteUser(u);
							} else {
								continue;
							}

						}
						else if(num1==4) { // 전직원 조회(Iterator pattern)
							UserList ul = new UserList(userlist);
							printer.println("현재 직원은 총 " + ul.getLength() +"명 입니다.");
							Iterator it = ul.iterator();
							
							while(it.hasNext()) {
								User user = it.next();
								printer.println(user.toString());
							}
							
						}
						else {
							printer.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
							continue;
						}
					}

				} else {
					while (true) {
						printer.print(Menu.Employee);

						int client_num = 0;
						while (true) {
							try {
								client_num = sc.nextInt();
								break;
							} catch (InputMismatchException e) {
								sc = new Scanner(System.in);
								printer.print(Menu.Number);
								printer.print(Menu.Employee);
							}
						}
						while (client_num == 1) {

							int schedule1 = 0;
							printer.print(Menu.Schedule);

							while (true) {
								try {
									schedule1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(Menu.Number);
									printer.print(Menu.Schedule);
								}
							}

							if (schedule1 == 0) {
								break;
							} else if (schedule1 == 1) {
								work.displaySchedule(schedule);
							} else if (schedule1 == 2) {
								String name = login.getName(id, userlist);
								schedule = work.addWorkSchedule(name, position, userlist, schedule);
							} else if (schedule1 == 3) {
								String name = login.getName(id, userlist);
								schedule = work.deleteWorkSchedule(name, position, userlist, schedule);
							} else {
								printer.println("유효하지 않는 입력입니다.");
							}
						}
						while (client_num == 2) {
							int patient1 = 0;
							printer.print(Menu.Patient);

							while (true) {
								try {
									patient1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(Menu.Number);
									printer.print(Menu.Patient);
								}
							}

							if (patient1 == 0) {
								break;
							} else if (patient1 == 1) {
								patientManage.displayPatientList(patientList);
							} else if (patient1 == 2) {
								patientList = patientManage.addPatient(patientList);
							} else if (patient1 == 3) {
								patientList = patientManage.removePatient(patientList);
							} else {
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
				User user = new User(userlist);
				UserList u = new UserList(userlist);
				user.setName(sc.next());
				userlist = u.appendUser(user);
			} else if (state == 0) {

				break;
			} else {
				printer.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
				continue;
			}
		}
	}

}
