package com.handong.oodp;

//Singleton Pattern

public class Printer {

	private static Printer printer = new Printer();
	
	private Printer() {
		
	}
	
	public static Printer getPrinter() {
		if(printer == null) {
			printer = new Printer();
		}
		return printer;
	}
	public void print(String str) {
		System.out.print(str);
	}
	public void println(String str) {
		System.out.println(str);
	}
}
