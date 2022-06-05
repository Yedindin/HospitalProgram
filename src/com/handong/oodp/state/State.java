package com.handong.oodp.state;

public interface State {
	void readySurgery();
	void startSurgery();
	void endSurgery();
	void printCurrent();
	
}
