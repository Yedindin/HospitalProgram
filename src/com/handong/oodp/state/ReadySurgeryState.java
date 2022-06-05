package com.handong.oodp.state;

import com.handong.oodp.Singleton.Printer;

public class ReadySurgeryState implements State {
	Printer printer = Printer.getPrinter(); // singleton
	private SurgeryControl surgerycontrol;

	@Override
	public void readySurgery() {
		// ready 상태에서는 이용불가
	}

	@Override
	public void startSurgery() {
		// TODO Auto-generated method stub
		surgerycontrol.changeState(surgerycontrol.inSurgeryState);
	}

	@Override
	public void endSurgery() {
		// ready 상태에서는 이용불가
	}

	@Override
	public void printCurrent() {
		// TODO Auto-generated method stub
		printer.println("현재 수술 대기중 입니다.");

	}

	@Override
	public String getState() {
		return "수술대기중";
	}
	@Override
	public State getS() {
		// TODO Auto-generated method stub
		return this;
	}

}
