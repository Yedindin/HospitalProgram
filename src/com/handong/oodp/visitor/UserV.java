package com.handong.oodp.visitor;

import java.io.IOException;
import java.util.List;

import com.handong.oodp.iterator.User;

public interface UserV {
	
	
	public List<User> addUser(User user) throws IOException;
	public List<User> deleteUser(User user) throws IOException;
	
	public int accept(Visitor visitor);
}
