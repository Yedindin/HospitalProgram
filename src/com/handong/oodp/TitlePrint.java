package com.handong.oodp;

public class TitlePrint extends Title implements Print {
	public TitlePrint (String string) {
		super(string);
	}
	public void printTitle() {
		showWithAster();
	}
}
