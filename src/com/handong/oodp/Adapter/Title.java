package com.handong.oodp.Adapter;

public class Title {
	private String string;
	public Title(String string) {
		this.string = string;
	}
	public void showWithAster() {
		System.out.println("****" + string + "****");
	}
}
