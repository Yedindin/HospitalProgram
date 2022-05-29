package com.handong.oodp.Adapter;

public class TitlePrint extends Title implements Print {
	public TitlePrint (String string) {
		super(string);
	}
	public void printHead() {
		showWithAster();
	}
	public void printEnd() {
		showWithDash();
	}
}
