package com.handong.oodp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.Adapter.Print;
import com.handong.oodp.Adapter.TitlePrint;
import com.handong.oodp.Builder.Patient;
import com.handong.oodp.Observer.PatientManage;
import com.handong.oodp.Observer.ViewAgeMinMax;
import com.handong.oodp.Observer.ViewPatientAll;
import com.handong.oodp.file.File;
import com.handong.oodp.file.PatientFile;
import com.handong.oodp.file.ScheduleFile;
import com.handong.oodp.file.TaskFile;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.load.PatientLoad;
import com.handong.oodp.file.load.TaskLoad;
import com.handong.oodp.file.load.DRoundingLoad;
import com.handong.oodp.file.load.NRoundingLoad;
import com.handong.oodp.file.load.UserLoad;
import com.handong.oodp.file.save.UserSave;
import com.handong.oodp.iterator.Iterator;
import com.handong.oodp.iterator.User;
import com.handong.oodp.iterator.UserList;
import com.handong.oodp.template.DRoundingWork;
import com.handong.oodp.template.NRoundingWork;
import com.handong.oodp.template.TaskWork;
import com.handong.oodp.template.Work;
import com.handong.oodp.visitor.Doctor;
import com.handong.oodp.visitor.NA;
import com.handong.oodp.visitor.Nurse;
import com.handong.oodp.visitor.UserV;
import com.handong.oodp.visitor.UserVisitor;
import com.handong.oodp.visitor.Visitor;

import java.io.IOException;

public class HospitalProgram {

	public static void main(String[] args) throws IOException {
		Login login = new Login();
		PatientManage patientManage = new PatientManage();
		//Work work = new TaskWork();
		Printer printer = Printer.getPrinter(); // singleton
		Print p = new TitlePrint("병원 관리 프로그램");
		Print f = new TitlePrint("프로그램을 종료합니다.");

		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id = "", pw;
		String position = "fail";

		Menu Menu = new Menu();

		// 환자 정보 불러오기
		File patientfile = new PatientFile("patientfile");
		patientfile.setLoadstrategy(new PatientLoad());
		patientManage.setPatients((List<Patient>) patientfile.load());
		ViewPatientAll viewPatientAll = new ViewPatientAll(patientManage);
		ViewAgeMinMax viewPatientAge = new ViewAgeMinMax(patientManage);


		File userfile = new UserFile("userfile");
		userfile.setLoadstrategy(new UserLoad());
		userfile.setSavestrategy(new UserSave());
		List<User> userlist = new ArrayList<User>();
		userlist = (List<User>) userfile.load();
		userfile.save(userlist);

		File taskfile = new TaskFile("taskfile");
		taskfile.setLoadstrategy(new TaskLoad());
		List<List<List<String>>> task = new ArrayList<List<List<String>>>(3);
		task = (List<List<List<String>>>) taskfile.load();
		
		File droundingfile = new ScheduleFile("DRoundingfile");
		droundingfile.setLoadstrategy(new DRoundingLoad());
		List<List<List<String>>> drounding = new ArrayList<List<List<String>>>(3);
		drounding = (List<List<List<String>>>) droundingfile.load();
		
		File nroundingfile = new ScheduleFile("NRoundingLoadfile");
		nroundingfile.setLoadstrategy(new NRoundingLoad());
		List<List<List<String>>> nrounding = new ArrayList<List<List<String>>>(3);
		nrounding = (List<List<List<String>>>) nroundingfile.load();
		
		File schedulefile4 = new ScheduleFile("schedulefile");

		/*
		 * 리스트 출력 for (List<String> item : list) { printer.println(item.get(0) + "and" +
		 * item.get(1) + item.get(2) + item.get(3) + item.get(4) ); }
		 */
		while (true) {
			p.printHead();
			printer.print(Menu.main);

			int state = 0;
			while (true) {
				try {
					state = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					sc = new Scanner(System.in);
					printer.print(Menu.Number);
					p.printHead();
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
								int s1 = 0, s2 = 0;
								printer.print(Menu.Work);

								while (true) {
									try {
										s1 = sc.nextInt();
										break;
									} catch (InputMismatchException e) {
										sc = new Scanner(System.in);
										printer.print(Menu.Number);
										printer.print(Menu.Work);
									}
								}
								if (s1 == 0) {
									break;
								} else if (s1 == 1) {
									while (true) {
										Work work = new TaskWork();
										printer.print(Menu.Task);
										while (true) {
											try {
												s2 = sc.nextInt();
												break;
											} catch (InputMismatchException e) {
												sc = new Scanner(System.in);
												printer.print(Menu.Number);
												printer.print(Menu.Task);
											}
										}
										if (s2 == 0) {
											break;
										} else if (s2 == 1) {
											work.displaySchedule(task);
										} else if (s2 == 2) {
											String name = login.getName(id, userlist);
											task = work.addWorkSchedule(name, position, userlist, task);
										} else if (s2 == 3) {
											String name = login.getName(id, userlist);
											task = work.deleteWorkSchedule(name, position, userlist, task);
										} else if (s2 == 0) {
											break;
										} else {
											printer.println("유효하지 않는 입력입니다.");
										}
									}
								} else if (s1 == 2) {
									while (true) {
										Work work = new DRoundingWork();
										printer.print(Menu.DRounding);
										while (true) {
											try {
												s2 = sc.nextInt();
												break;
											} catch (InputMismatchException e) {
												sc = new Scanner(System.in);
												printer.print(Menu.Number);
												printer.print(Menu.DRounding);
											}
										}
										if (s2 == 0) {
											break;
										} else if (s2 == 1) {
											work.displaySchedule(drounding);
										} else if (s2 == 2) {
											String name = login.getName(id, userlist);
											drounding = work.addWorkSchedule(name, position, userlist, drounding);
										} else if (s2 == 3) {
											String name = login.getName(id, userlist);
											drounding = work.deleteWorkSchedule(name, position, userlist, drounding);
										} else if (s2 == 0) {
											break;
										} else {
											printer.println("유효하지 않는 입력입니다.");
										}
									}
								} else if (s1 == 3) {
									while (true) {
										Work work = new NRoundingWork();
										printer.print(Menu.NRounding);
										while (true) {
											try {
												s2 = sc.nextInt();
												break;
											} catch (InputMismatchException e) {
												sc = new Scanner(System.in);
												printer.print(Menu.Number);
												printer.print(Menu.NRounding);
											}
										}
										if (s2 == 0) {
											break;
										} else if (s2 == 1) {
											work.displaySchedule(nrounding);
										} else if (s2 == 2) {
											String name = login.getName(id, userlist);
											nrounding = work.addWorkSchedule(name, position, userlist, nrounding);
										} else if (s2 == 3) {
											String name = login.getName(id, userlist);
											nrounding = work.deleteWorkSchedule(name, position, userlist, nrounding);
										} else if (s2 == 0) {
											break;
										} else {
											printer.println("유효하지 않는 입력입니다.");
										}
									}
								} else if (s1 == 4) {
									while (true) {
										Work work = new NRoundingWork();
										printer.print(Menu.NRounding);
										while (true) {
											try {
												s2 = sc.nextInt();
												break;
											} catch (InputMismatchException e) {
												sc = new Scanner(System.in);
												printer.print(Menu.Number);
												printer.print(Menu.NRounding);
											}
										}
										if (s2 == 0) {
											break;
										} else if (s2 == 1) {
											work.displaySchedule(nrounding);
										} else if (s2 == 2) {
											String name = login.getName(id, userlist);
											nrounding = work.addWorkSchedule(name, position, userlist, nrounding);
										} else if (s2 == 3) {
											String name = login.getName(id, userlist);
											nrounding = work.deleteWorkSchedule(name, position, userlist, nrounding);
										} else if (s2 == 0) {
											break;
										} else {
											printer.println("유효하지 않는 입력입니다.");
										}
									}
								} else if (s1 == 0) {
									break;
								} else {
									printer.println("유효하지 않는 입력입니다.");
								}
							}

						} else if (num1 == 5) {
							while (true) {
								int patient1 = 0;
								printer.print(Menu.Patient);
								patientManage.attach(viewPatientAll);
								patientManage.attach(viewPatientAge);

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
									patientManage.detach(viewPatientAge);
									patientManage.displayPatientList();
									patientManage.attach(viewPatientAge);
								} else if (patient1 == 3) {
									patientManage.addPatient();
								}  else if (patient1 == 4) {
									patientManage.addEPatient();
								}
								else if (patient1 == 5) {
									patientManage.removePatient();
								} else if (patient1 == 2) {
									patientManage.detach(viewPatientAll);
									patientManage.displayPatientList();
									patientManage.attach(viewPatientAll);
								}else if (patient1 == 6) {
									patientManage.setEmergencyPatient(); //응급환자 전환
								}
								else if (patient1 == 7) {
									patientManage.setGeneralPatient(); //일반환자 전환
								}
								else if (patient1 == 8) {
									patientManage.changePatient(); //환자 정보 변경
								}
								
								else {
									printer.println("유효하지 않는 입력입니다.");
								}

							}
						}

						else if (num1 == 1) {// 고용관리
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
										// 오류있던부분 //printer.print(inputOnlyNumber);
										// printer.println("**** 병원 관리 프로그램 ****");
										// printer.println("1. 로그인");
										// printer.println("2. 회원등록");
										// printer.println("0. 종료\n\n");
										// printer.print("원하는 메뉴의 번호를 입력하세요 : ");
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
										UserV userv = new Doctor(userlist);
										userlist = userv.addUser(user);

										userfile.save(userlist);

									} else if (num == 2) {
										User user = new User(userlist);
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("Nurse");
										UserV userv = new Nurse(userlist);
										userlist = userv.addUser(user);

										userfile.save(userlist);
									} 
									else if(num == 3){
										User user = new User(userlist);
										printer.println("이름을 입력해주세요.");
										user.setName(sc.next());
										printer.println("나이를 입력해주세요.");
										user.setAge(sc.next());
										printer.println("부서를 입력해주세요.");
										user.setDepartment(sc.next());
										user.setPosition("NA");
										UserV userv = new NA(userlist);
										userlist = userv.addUser(user);

										userfile.save(userlist);
									}else {
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
										UserV userv = new Doctor(userlist);
										userlist = userv.deleteUser(user);

									} else if (num == 2) {
										printer.println("삭제할 간호사의 이름을 입력해 주세요.");
										User user = new User(userlist);
										UserV userv = new Nurse(userlist);
										userlist = userv.deleteUser(user);
									}else if (num == 3) {
										printer.println("삭제할 간호조무사의 이름을 입력해 주세요.");
										User user = new User(userlist);
										UserV userv = new NA(userlist);
										userlist = userv.deleteUser(user);
									}else {
										break;
									}
								} else if(num2==3){ //직원 수 조회 (position별)
									printer.print(Menu.EmplTotal);

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
									if(num==1) { //의사 수 조회
										UserV userv = new Doctor(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("총 의사 수는 " +total +"명 입니다.");
									}else if(num==2) {//간호사 수 조회
										UserV userv = new Nurse(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("총 간호사 수는 " +total +"명 입니다.");
									}else if(num==3) {//간호조무사 수 조회
										UserV userv = new NA(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("총 간호조무사 수는 " +total +"명 입니다.");
									}else {
										break;
									}
								}else {
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

						} else if (num1 == 4) { // 전직원 조회(Iterator pattern)
							UserList ul = new UserList(userlist);
							printer.println("현재 직원은 총 " + ul.getLength() + "명 입니다.");
							Iterator it = ul.iterator();

							while (it.hasNext()) {
								User user = (User) it.next();
								printer.println(user.toString());
							}

						} else {
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

							int s1 = 0, s2 = 0;
							printer.print(Menu.CWork);

							while (true) {
								try {
									s1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(Menu.Number);
									printer.print(Menu.CWork);
								}
							}
							if(s1 == 2 && position.equals("Nurse")) {
								printer.print(Menu.NotD);
								continue;
							}
							else if(s1 == 3 && position.equals("Doctor")) {
								printer.print(Menu.NotN);
								continue;
							}
							

							if (s1 == 0) {
								break;
							} else if (s1 == 1) {
								while (true) {
									Work work = new TaskWork();
									printer.print(Menu.Task);
									while (true) {
										try {
											s2 = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(Menu.Number);
											printer.print(Menu.Task);
										}
									}
									if (s2 == 0) {
										break;
									} else if (s2 == 1) {
										work.displaySchedule(task);
									} else if (s2 == 2) {
										String name = login.getName(id, userlist);
										task = work.addWorkSchedule(name, position, userlist, task);
									} else if (s2 == 3) {
										String name = login.getName(id, userlist);
										task = work.deleteWorkSchedule(name, position, userlist, task);
									} else if (s2 == 0) {
										break;
									} else {
										printer.println("유효하지 않는 입력입니다.");
									}
								}
							} else if (s1 == 2) {
								while (true) {
									Work work = new DRoundingWork();
									printer.print(Menu.DRounding);
									while (true) {
										try {
											s2 = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(Menu.Number);
											printer.print(Menu.DRounding);
										}
									}
									if (s2 == 0) {
										break;
									} else if (s2 == 1) {
										work.displaySchedule(drounding);
									} else if (s2 == 2) {
										String name = login.getName(id, userlist);
										drounding = work.addWorkSchedule(name, position, userlist, drounding);
									} else if (s2 == 3) {
										String name = login.getName(id, userlist);
										drounding = work.deleteWorkSchedule(name, position, userlist, drounding);
									} else if (s2 == 0) {
										break;
									} else {
										printer.println("유효하지 않는 입력입니다.");
									}
								}
							} else if (s1 == 3) {
								while (true) {
									Work work = new NRoundingWork();
									printer.print(Menu.NRounding);
									while (true) {
										try {
											s2 = sc.nextInt();
											break;
										} catch (InputMismatchException e) {
											sc = new Scanner(System.in);
											printer.print(Menu.Number);
											printer.print(Menu.NRounding);
										}
									}
									if (s2 == 0) {
										break;
									} else if (s2 == 1) {
										work.displaySchedule(nrounding);
									} else if (s2 == 2) {
										String name = login.getName(id, userlist);
										nrounding = work.addWorkSchedule(name, position, userlist, nrounding);
									} else if (s2 == 3) {
										String name = login.getName(id, userlist);
										nrounding = work.deleteWorkSchedule(name, position, userlist, nrounding);
									} else if (s2 == 0) {
										break;
									} else {
										printer.println("유효하지 않는 입력입니다.");
									}
								}
							} else if (s1 == 0) {
								break;
							} else {
								printer.println("유효하지 않는 입력입니다.");
							}
						}
						while (client_num == 2) {
							int patient1 = 0;
							printer.print(Menu.Patient);
							patientManage.attach(viewPatientAll);
							patientManage.attach(viewPatientAge);

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
								patientManage.detach(viewPatientAge);
								patientManage.displayPatientList();
								patientManage.attach(viewPatientAge);
							} else if (patient1 == 3) {
								patientManage.addPatient();
							}  else if (patient1 == 4) {
								patientManage.addEPatient();
							}
							else if (patient1 == 5) {
								patientManage.removePatient();
							} else if (patient1 == 2) {
								patientManage.detach(viewPatientAll);
								patientManage.displayPatientList();
								patientManage.attach(viewPatientAll);
							}else if (patient1 == 6) {
								patientManage.setEmergencyPatient(); //응급환자 전환
							}
							else if (patient1 == 7) {
								patientManage.setGeneralPatient(); //일반환자 전환
							}
							else if (patient1 == 8) {
								patientManage.changePatient(); //환자 정보 변경
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
				User user = new User(userlist);
				UserList u = new UserList(userlist);
				user.setName(sc.next());
				userlist = u.appendUser(user);
			} else if (state == 0) {
				f.printEnd();
				break;
			} else {
				printer.println("잘못된 입력입니다. 다시 입력해주세요.\n\n");
				continue;
			}
		}
	}

}
