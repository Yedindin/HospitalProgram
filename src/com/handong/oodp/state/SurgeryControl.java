package com.handong.oodp.state;

public class SurgeryControl {

	final State inSurgery;
	final State readySurgery;
	final State endSurgery;

	private State currentState;

	public SurgeryControl() {
		this.inSurgery = new InSurgeryState(this);
		this.readySurgery = new ReadySurgeryState(this);
		this.endSurgery = new EndSurgeryState(this);
	}

	public SurgeryControl(State state) {
		this.inSurgery = new InSurgeryState(this);
		this.readySurgery = new ReadySurgeryState(this);
		this.endSurgery = new EndSurgeryState(this);

		this.currentState = state;
	}

	public State getNextState(String num) {
		State next = null;

		if (num.equals("1"))
			next = readySurgery;
		else if (num.equals("2"))
			next = inSurgery;
		else if (num.equals("3"))
			next = endSurgery;
		return next;
	}

	public void changeState(State nextState) {
		this.currentState = nextState;
	}

	public void inSurgery() {
		this.currentState = inSurgery;
		this.currentState.startSurgery();
	}

	public void readSurgery() {
		this.currentState = readySurgery;
		this.currentState.readySurgery();

	}

	public void endSurgery() {
		this.currentState = endSurgery;
		this.currentState.endSurgery();

	}

	public void printCurrent() {
		this.currentState.printCurrent();
	}

	public String getState() {
		return this.currentState.getState();
	}

	public State findS(String s) {
		if (s.equals("수술대기중")) {
			this.currentState = this.readySurgery;
		} else if (s.equals("수술중")) {
			this.currentState = this.inSurgery;
		} else {
			this.currentState = this.endSurgery;
		}
		return currentState;
	}

	public State getS() {
		return this.currentState.getS();
	}

}
