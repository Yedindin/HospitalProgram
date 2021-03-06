package com.handong.oodp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.Adapter.Print;
import com.handong.oodp.Adapter.TitlePrint;
import com.handong.oodp.Builder.Patient;
import com.handong.oodp.ChainOfResponsibility.Request;
import com.handong.oodp.ChainOfResponsibility.TaskAdd;
import com.handong.oodp.ChainOfResponsibility.TaskDel;
import com.handong.oodp.ChainOfResponsibility.TaskDisp;
import com.handong.oodp.ChainOfResponsibility.TaskWork;
import com.handong.oodp.Observer.PatientManage;
import com.handong.oodp.Observer.ViewAgeMinMax;
import com.handong.oodp.Observer.ViewPatientAll;
import com.handong.oodp.file.DRoundingFile;
import com.handong.oodp.file.File;
import com.handong.oodp.file.NRoundingFile;
import com.handong.oodp.file.PatientFile;
import com.handong.oodp.file.ScheduleFile;
import com.handong.oodp.file.SurgeryFile;
import com.handong.oodp.file.TaskFile;
import com.handong.oodp.file.UserFile;
import com.handong.oodp.file.load.PatientLoad;
import com.handong.oodp.file.load.SurgeryLoad;
import com.handong.oodp.file.load.TaskLoad;
import com.handong.oodp.file.load.DRoundingLoad;
import com.handong.oodp.file.load.NRoundingLoad;
import com.handong.oodp.file.load.UserLoad;
import com.handong.oodp.file.save.SurgerySave;
import com.handong.oodp.file.save.UserSave;
import com.handong.oodp.iterator.Iterator;
import com.handong.oodp.iterator.Surgery;
import com.handong.oodp.iterator.SurgeryList;
import com.handong.oodp.iterator.User;
import com.handong.oodp.iterator.UserList;
import com.handong.oodp.template.DRoundingWork;
import com.handong.oodp.template.NRoundingWork;
import com.handong.oodp.template.Work;
import com.handong.oodp.visitor.Doctor;
import com.handong.oodp.visitor.NA;
import com.handong.oodp.visitor.Nurse;
import com.handong.oodp.visitor.UserV;
import com.handong.oodp.visitor.UserVisitor;
import com.handong.oodp.visitor.Visitor;

import java.io.IOException;

public class HospitalProgram {

	public static List<List<List<String>>> task = new ArrayList<List<List<String>>>(3);

	public static void main(String[] args) throws IOException {
		Login login = new Login();
		PatientManage patientManage = new PatientManage();
		//Work work = new TaskWork();
		Printer printer = Printer.getPrinter(); // singleton
		Print p = new TitlePrint("?????? ?????? ????????????");
		Print f = new TitlePrint("??????????????? ???????????????.");

		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		int num1, num2 = 0;
		String id = "", pw;
		String position = "fail";

		Menu Menu = new Menu();

		// ?????? ?????? ????????????
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
		//userfile.save(userlist);

		File taskfile = new TaskFile("taskfile");
		taskfile.setLoadstrategy(new TaskLoad());
		task = (List<List<List<String>>>) taskfile.load();
		
		File droundingfile = new DRoundingFile("DRoundingfile");
		droundingfile.setLoadstrategy(new DRoundingLoad());
		List<List<List<String>>> drounding = new ArrayList<List<List<String>>>(3);
		drounding = (List<List<List<String>>>) droundingfile.load();
		
		File nroundingfile = new NRoundingFile("NRoundingLoadfile");
		nroundingfile.setLoadstrategy(new NRoundingLoad());
		List<List<List<String>>> nrounding = new ArrayList<List<List<String>>>(3);
		nrounding = (List<List<List<String>>>) nroundingfile.load();
		
		File surgeryfile = new SurgeryFile("surgeryfile");
		surgeryfile.setLoadstrategy(new SurgeryLoad());
		surgeryfile.setSavestrategy(new SurgerySave());
		List<Surgery> surgerylist = new ArrayList<Surgery>();
		surgerylist = (List<Surgery>) surgeryfile.load();
		
		File schedulefile4 = new ScheduleFile("schedulefile");
		patientManage.attach(viewPatientAll);
		patientManage.attach(viewPatientAge);
		/*
		 * ????????? ?????? for (List<String> item : list) { printer.println(item.get(0) + "and" +
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

			if (state == 1) { // ?????????
				while (position == "fail") {
					printer.println("ID??? ???????????????.");
					id = sc.next();
					printer.println("??????????????? ???????????????.");
					pw = sc.next();
					position = login.login(id, pw, userlist); // position ??? ?????? ?????? ?????? ??????
					if (position == "fail") {
						printer.println("???????????? ??????????????? ???????????????????????????.");
						printer.println("?????? ???????????? ?????????.");
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

						if (num1 == 0) { // ??????
							// run = false;
							position = "fail";
							printer.println("?????? ?????? ??????????????? ???????????????.");
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
										if (s2 == 0) break;
										TaskWork TDisp = new TaskDisp(s2);
										TaskWork TAdd = new TaskAdd(s2);
										TaskWork TDel = new TaskDel(s2);
										TDisp.setNext(TAdd).setNext(TDel);
										String name = login.getName(id, userlist);
										TDisp.work(new Request(s2, name, position, userlist, task));
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
											printer.println("???????????? ?????? ???????????????.");
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
											printer.println("???????????? ?????? ???????????????.");
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
											printer.println("???????????? ?????? ???????????????.");
										}
									}
								} else if (s1 == 0) {
									break;
								} else {
									printer.println("???????????? ?????? ???????????????.");
								}
							}

						} else if (num1 == 5) {
							while (true) {
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
									patientManage.setEmergencyPatient(); //???????????? ??????
								}
								else if (patient1 == 7) {
									patientManage.setGeneralPatient(); //???????????? ??????
								}
								else if (patient1 == 8) {
									patientManage.changePatient(); //?????? ?????? ??????
								}
								
								else {
									printer.println("???????????? ?????? ???????????????.");
								}

							}
						}

						else if (num1 == 1) {// ????????????
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
										// ?????????????????? //printer.print(inputOnlyNumber);
										// printer.println("**** ?????? ?????? ???????????? ****");
										// printer.println("1. ?????????");
										// printer.println("2. ????????????");
										// printer.println("0. ??????\n\n");
										// printer.print("????????? ????????? ????????? ??????????????? : ");
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
										printer.println("????????? ??????????????????.");
										user.setName(sc.next());
										printer.println("????????? ??????????????????.");
										user.setAge(sc.next());
										printer.println("????????? ??????????????????.");
										user.setDepartment(sc.next());
										user.setPosition("Doctor");
										UserV userv = new Doctor(userlist);
										userlist = userv.addUser(user);

										userfile.save(userlist);

									} else if (num == 2) {
										User user = new User(userlist);
										printer.println("????????? ??????????????????.");
										user.setName(sc.next());
										printer.println("????????? ??????????????????.");
										user.setAge(sc.next());
										printer.println("????????? ??????????????????.");
										user.setDepartment(sc.next());
										user.setPosition("Nurse");
										UserV userv = new Nurse(userlist);
										userlist = userv.addUser(user);

										userfile.save(userlist);
									} 
									else if(num == 3){
										User user = new User(userlist);
										printer.println("????????? ??????????????????.");
										user.setName(sc.next());
										printer.println("????????? ??????????????????.");
										user.setAge(sc.next());
										printer.println("????????? ??????????????????.");
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
										printer.println("????????? ????????? ????????? ????????? ?????????.");
										User user = new User(userlist);
										user.setName(sc.next());
										UserV userv = new Doctor(userlist);
										userlist = userv.deleteUser(user);

									} else if (num == 2) {
										printer.println("????????? ???????????? ????????? ????????? ?????????.");
										User user = new User(userlist);
										user.setName(sc.next());
										UserV userv = new Nurse(userlist);
										userlist = userv.deleteUser(user);
									}else if (num == 3) {
										printer.println("????????? ?????????????????? ????????? ????????? ?????????.");
										User user = new User(userlist);
										user.setName(sc.next());
										UserV userv = new NA(userlist);
										userlist = userv.deleteUser(user);
									}else {
										break;
									}
								} else if(num2==3){ //?????? ??? ?????? (position???)
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
									if(num==1) { //?????? ??? ??????
										UserV userv = new Doctor(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("??? ?????? ?????? " +total +"??? ?????????.");
									}else if(num==2) {//????????? ??? ??????
										UserV userv = new Nurse(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("??? ????????? ?????? " +total +"??? ?????????.");
									}else if(num==3) {//??????????????? ??? ??????
										UserV userv = new NA(userlist);
										Visitor visitor = new UserVisitor();
										int total = userv.accept(visitor);
										printer.print("??? ??????????????? ?????? " +total +"??? ?????????.");
									}else {
										break;
									}
								}else {
									break;
								}
							}
						} else if (num1 == 2) {// ????????????
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
								printer.println("??????????????? ????????? ????????? ?????????.");
								UserList user = new UserList(userlist);
								User u = new User();
								u.setName(sc.next());
								userlist = user.appendUser(u);
								continue;
							} else if (num == 2) {
								printer.println("????????? ????????? ????????? ????????? ?????????.");
								UserList ul = new UserList(userlist);
								userlist = ul.editUser(sc.next());
							} else if (num == 3) {
								printer.println("????????? ????????? ????????? ????????? ?????????.");
								UserList user = new UserList(userlist);
								User u = new User();
								u.setName(sc.next());
								userlist = user.deleteUser(u);
							} else {
								continue;
							}

						} else if (num1 == 4) { // ????????? ??????(Iterator pattern)
							UserList ul = new UserList(userlist);
							printer.println("?????? ????????? ??? " + ul.getLength() + "??? ?????????.");
							Iterator it = ul.iterator();

							while (it.hasNext()) {
								User user = (User) it.next();
								printer.println(user.toString());
							}

						} else {
							printer.println("????????? ???????????????. ?????? ??????????????????.\n\n");
							continue;
						}
					}

				} else { //????????????
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
									if (s2 == 0) break;
									TaskWork TDisp = new TaskDisp(s2);
									TaskWork TAdd = new TaskAdd(s2);
									TaskWork TDel = new TaskDel(s2);
									TDisp.setNext(TAdd).setNext(TDel);
									String name = login.getName(id, userlist);
									TDisp.work(new Request(s2, name, position, userlist, task));
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
										printer.println("???????????? ?????? ???????????????.");
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
										printer.println("???????????? ?????? ???????????????.");
									}
								}
							} else if (s1 == 0) {
								break;
							} else {
								printer.println("???????????? ?????? ???????????????.");
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
								patientManage.setEmergencyPatient(); //???????????? ??????
							}
							else if (patient1 == 7) {
								patientManage.setGeneralPatient(); //???????????? ??????
							}
							else if (patient1 == 8) {
								patientManage.changePatient(); //?????? ?????? ??????
							}
							
							else {
								printer.println("???????????? ?????? ???????????????.");
							}

						}
						while(client_num == 3) {//????????????
							int surgery1 = 0;

							printer.print(Menu.Surgery);
							SurgeryList surgery  = new SurgeryList(surgerylist);
							
							while (true) {
								try {
									surgery1 = sc.nextInt();
									break;
								} catch (InputMismatchException e) {
									sc = new Scanner(System.in);
									printer.print(Menu.Number);
									printer.print(Menu.Surgery);
								}
							}

							if(surgery1 ==1) { //?????? ??????
								sc = new Scanner(System.in);
								printer.print("????????? ?????? ????????? ??????????????????.");
								Surgery s = new Surgery();
								s = surgery.getSurgeryAt(sc.nextInt());
								
								if(s == null) {
									printer.println("???????????? ?????? ???????????????.");
								}else {
									printer.println(s.toString());
									printer.println("?????? ????????? ????????????????????????? (1:???????????? 2:????????? 3:????????? 0:??????)");
									String num = sc.next();
									if(num.equals("1")||num.equals("2")||num.equals("3")) {
										surgery.editSurgeryState(s.getIndex(), num);
									}else if(num.equals("0")) {
										continue;
									}
										
								}
								
								
							}else if(surgery1 == 2) { // ?????? ??????
								
								surgerylist = surgery.addSurgery();
								continue;
							}else if(surgery1 == 3) { //?????? ??????
								printer.println("????????? ?????? ????????? ????????? ?????????.");
								surgerylist = surgery.editSurgery(sc.next());
							}else if(surgery1 == 4) { // ?????? ??????
								printer.println("????????? ?????? ????????? ????????? ?????????.");
								Surgery s = new Surgery();
								s.setIndex(sc.next());
								surgerylist = surgery.deleteSurgery(s);
							}else if(surgery1 == 5) { //?????? ?????? ??????
								printer.println("?????? ????????? ????????? " + surgery.getLength() + "??? ?????????.");
								Iterator it = surgery.iterator();

								while (it.hasNext()) {
									Surgery sur = (Surgery) it.next();
									printer.println(sur.toString());
								}
								
							}else if(surgery1 == 0) { //???????????? ????????????
								break;
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
				printer.println("????????? ????????? ?????????.");
				User user = new User(userlist);
				UserList u = new UserList(userlist);
				user.setName(sc.next());
				userlist = u.appendUser(user);
			} else if (state == 0) {
				f.printEnd();
				break;
			} else {
				printer.println("????????? ???????????????. ?????? ??????????????????.\n\n");
				continue;
			}
		}
	}

}
