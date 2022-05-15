package com.handong.oodp.iterator;


public class UserListIterator implements Iterator{
	private UserList userlist;
	private int index;
	
	public UserListIterator(UserList userlist) {
		super();
		this.userlist = userlist;
	}
	
	@Override
	public boolean hasNext() {
		return index < userlist.getLength() ? true : false;
	}
	
	@Override
	public User next() {
		User user = userlist.getUserAt(index);
		index++;
		return user;
	}
}
