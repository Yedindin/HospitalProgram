package com.handong.oodp.visitor;

public interface Visitor {
	int visit(Doctor doctor);
	int visit(Nurse nurse);
	int visit(NA na);
}
