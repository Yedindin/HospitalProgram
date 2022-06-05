package com.handong.oodp.state;

import com.handong.oodp.Singleton.Printer;

public class InSurgeryState implements State {
	Printer printer = Printer.getPrinter(); // singleton
	private final SurgeryControl surgerycontrol = new SurgeryControl();
	@Override
	public void readySurgery() {
		// in 상태에서는 이용 불가
	}

	@Override
	public void startSurgery() {
		// in 상태에서는 이용 불가
	}

	@Override
	public void endSurgery() {
		// TODO Auto-generated method stub
		surgerycontrol.changeState(surgerycontrol.endSurgeryState);
	}

	@Override
	public void printCurrent() {
		// TODO Auto-generated method stub
		printer.println("현재 수술 중 입니다.");

	}

}
