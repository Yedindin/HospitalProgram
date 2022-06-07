package com.handong.oodp.ChainOfResponsibility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.iterator.User;

public class Request {
	private int request;
	private String name;
	private String position;
	private List<User> userlist = new ArrayList<User>();
	private List<List<List<String>>> task = new ArrayList<List<List<String>>>(3);
	public Request (int request, String name, String position, List<User> userlist, List<List<List<String>>> task) {
		this.request = request;
		this.name = name;
		this.position = position;
		this.userlist = userlist;
		this.task = task;
	}
	public int getRnum() {
		return request;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public List<User> getUserlist() {
		return userlist;
	}
	
	public List<List<List<String>>> getTask(){
		return task;
	}
}