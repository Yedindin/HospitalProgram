package com.handong.oodp.state;

import com.handong.oodp.Singleton.Printer;

public class EndSurgeryState implements State {
	
	Printer printer = Printer.getPrinter(); //singleton
	private SurgeryControl surgerycontrol;
	
	EndSurgeryState(SurgeryControl surgerycontrol){
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
		printer.println("현재 수술종료 후 회복중입니다.");
	}

	@Override
	public String getState() {
		return "회복중";
	}

	@Override
	public State getS() {
		// TODO Auto-generated method stub
		return this;
	}


}
