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
	}
	public SurgeryControl(State state) {
		this.inSurgeryState = new InSurgeryState();
		this.readySurgeryState = new ReadySurgeryState();
		this.endSurgeryState = new EndSurgeryState();

		this.currentState = state;
	}

	public State getNextState(State current) {
		State next = null;

		if (current.equals(readySurgeryState))
			next = inSurgeryState;
		else if (current.equals(inSurgeryState))
			next = endSurgeryState;
		else
			next = readySurgeryState;
		return next;
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

	public String getState() {
		return this.currentState.getState();
	}

	public State findS(String s) {
		if (s.equals("수술대기중")) {
			this.currentState = this.readySurgeryState;
		} else if (s.equals("수술중")) {
			this.currentState = this.inSurgeryState;
		} else {
			this.currentState = this.endSurgeryState;
		}
		return currentState;
	}

	public State getS() {
		return this.currentState.getS();
	}

}
