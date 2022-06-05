package com.handong.oodp.iterator;

public class SurgeryListIterator implements Iterator{
	
	
	private SurgeryList surgerylist;
	private int index;
	
	public SurgeryListIterator(SurgeryList surgerylist) {
		super();
		this.surgerylist = surgerylist;
	}
	@Override
	public boolean hasNext() {
		return index < surgerylist.getLength() ? true : false;
	}

	@Override
	public Object next() {
		Surgery surgery = surgerylist.getSurgeryat(index);
		index++;
		return surgery;
	}
	
}
