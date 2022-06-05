package com.handong.oodp.state;

public class SurgeryControl {

	final State inSurgeryState;
	final State readySurgeryState;
	final State endSurgeryState;

	private State currentState;

	public SurgeryControl() {
		this.inSurgeryState = new InSurgeryState();
		this.readySurgeryState = new ReadySurgeryState();
		this.endSurgeryState = new EndSurgeryState();

		this.currentState = this.readySurgeryState;
	}

	public void changeState(State nextState) {
		this.currentState = nextState;
	}

	public void inSurgery() {
		this.currentState = inSurgeryState;
	}

	public void readSurgery() {
		this.currentState = readySurgeryState;
	}

	public void endSurgery() {
		this.currentState = endSurgeryState;
	}

	public void printCurrent() {
		this.currentState.printCurrent();
	}
}
