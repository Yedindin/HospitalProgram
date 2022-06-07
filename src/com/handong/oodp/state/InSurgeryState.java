package com.handong.oodp.state;

import com.handong.oodp.Singleton.Printer;

public class InSurgeryState implements State {
	Printer printer = Printer.getPrinter(); // singleton
	private SurgeryControl surgerycontrol;
	
	InSurgeryState(SurgeryControl surgerycontrol){
		this.surgerycontrol = surgerycontrol;
	}
	@Override
	public void readySurgery() {
		surgerycontrol.changeState(surgerycontrol.readySurgery);
	}

	@Override
	public void startSurgery() {
		// TODO Auto-generated method stub
		surgerycontrol.changeState(surgerycontrol.inSurgery);
	}

	@Override
	public void endSurgery() {
		surgerycontrol.changeState(surgerycontrol.endSurgery);
	}

	@Override
	public void printCurrent() {
		// TODO Auto-generated method stub
		printer.println("현재 수술 중 입니다.");

	}

	@Override
	public String getState() {
		return "수술중";
	}
	@Override
	public State getS() {
		// TODO Auto-generated method stub
		return this;
	}


}
