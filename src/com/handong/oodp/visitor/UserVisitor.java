package com.handong.oodp.visitor;

public class UserVisitor implements Visitor{

	@Override
	public int visit(Doctor doctor) {
		int total = doctor.getTotal();
		return total;
	}

	@Override
	public int visit(Nurse nurse) {
		int total = nurse.getTotal();
		return total;
	}

	@Override
	public int visit(NA na) {
		int total = na.getTotal();
		return total;
	}

}
