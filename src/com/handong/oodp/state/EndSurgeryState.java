package com.handong.oodp.state;

import com.handong.oodp.Singleton.Printer;

public class EndSurgeryState implements State {
	
	Printer printer = Printer.getPrinter(); //singleton

	@Override
	public void readySurgery() {
		// end 상태에서는 이용 불가

	}

	@Override
	public void startSurgery() {
		// end 상태에서는 이용 불가
	}

	@Override
	public void endSurgery() {
		// end 상태에서는 이용 불가
	}

	@Override
	public void printCurrent() {
		// TODO Auto-generated method stub
		printer.println("현재 수술종료 후 회복중입니다.");
	}

}
