package com.handong.oodp;

import java.util.ArrayList;
import java.util.List;


public class EmploymentManagement {
	private List<List<String>> list;
	
	public EmploymentManagement (List<List<String>> list) {
		this.list = list;
	}
	public List<List<String>> addDoctor(User user) {
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
		for (List<String> item : list) {
			System.out.println(item.get(0) + "and" + item.get(1) + item.get(2) + item.get(3) + item.get(4) + item.get(5));
		}
		System.out.println("의사 추가 완료");
		return list;

	}

	public List<List<String>> addNurse(User user) {
		List<String> data = new ArrayList<>();
		data.add(0, user.getName());
		data.add(1,"");
		data.add(2,"");
		data.add(3, user.getDepartment());
		data.add(4, user.getPosition());
		data.add(5, user.getAge());
		list.add(data);
		System.out.println("간호사 추가 완료");
		return list;
	}

//	public void editDoctor(User user) {
//		System.out.println("의사 수정");
//	}
//	public void editNurse(User user) {
//		System.out.println("간호사 수정");
//	}
	public List<List<String>> deleteDoctor(User user) {
		System.out.println("의사 삭제");
		return list;

	}

	public List<List<String>> deleteNurse(User user) {
		System.out.println("간호사 삭제");
		return list;

	}
}
