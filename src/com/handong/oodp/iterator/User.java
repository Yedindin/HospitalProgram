package com.handong.oodp.iterator;

import java.util.List;


public class User {

	private String name;
	private String ID;
	private String PW;
	private String department;
	private String position;
	private String age;
	private List<List<String>> list;
	private List<User> userlist;

//	public User(List<List<String>> list) {
//		this.list = list;
//	}
	public User(List<User> userlist) {
		this.userlist = userlist;
	}
	public User() {
		
	}
	
	/*public List<User> addUser(String name) throws IOException {
		Scanner sc = new Scanner(System.in);
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));

		for (User user : userlist) {
			if(name.equals(user.getName()) && user.getID().equals("")) {
				printer.println("사용할 ID를 입력하세요.");
				this.ID = sc.next();
				printer.println("사용할 패스워드를 입력하세요.");
				this.PW = sc.next();
				printer.println("회원등록이 완료되었습니다.");
				user.setName(user.getName());
				user.setID(this.ID);
				user.setPW(this.PW);
				user.setDepartment(user.getDepartment());
				user.setPosition(user.getPosition());
				user.setAge(user.getAge());
				
				
				return userlist;
			}
		}
//		for (List<String> item : list) {
//			if (name.equals(item.get(0)) && item.get(1).equals("")) {
//				printer.println("사용할 ID를 입력하세요.");
//				this.ID = sc.next();
//				printer.println("사용할 패스워드를 입력하세요.");
//				this.PW = sc.next();
//				printer.println("회원등록이 완료되었습니다.");
//				item.set(0, item.get(0));
//				item.set(1, this.ID);
//				item.set(2, this.PW);
//				item.set(3, item.get(3));
//				item.set(4, item.get(4));
//				item.set(5, item.get(5));
//				
//				
//				return list;
//			}
//		}
		printer.println("등록 대상이 아닙니다.");
		return userlist;

//		List<String> data = new ArrayList<>();
//		data.add(0,name);
//		data.add(3,department);
//		data.add(4,position);
//		data.add(5,age);
//		list.add(data);
//		for (List<String> item : list) { printer.println(item.get(0) + "and" +
//				 item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5) ); }
//		printer.println("user 추가 완료");
//		return list;
	}
*/
//	public void editUser(User user) {
//		printer.println("user 수정 완료");
//	}
//	public List<User> deleteUser(String name) throws IOException {
//		Printer printer = Printer.getPrinter(); //singleton
//		File userfile = new UserFile("userfile");
//		userfile.setSavestrategy(new UserSave());
//		int index = 0;
//		for (List<String> item : list ) {
//			if(item.contains(name))
//			{
//				//printer.println(list.get(index));
//				userlist.remove(index);
//				userfile.save(userlist);
//				printer.println("user 삭제 완료");
//				//fileinout.saveFile(userlist);

//				return userlist;
//			}
//			index++;
//		}
//		printer.println("존재하지 않는 회원입니다.");
//		return userlist;
//
//	}

	@Override
	public String toString() {
		return "[이름: " + name + ", ID: " + ID + ", 부서: " + department + ", 직급: "
				+ position + ", 나이: " + age + "]";
	}
	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPW() {
		return PW;
	}

	public String getDepartment() {
		return department;
	}

	public String getPosition() {
		return position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String string) {
		this.age = string;
	}

}
