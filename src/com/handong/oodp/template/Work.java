package com.handong.oodp.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.File;
import com.handong.oodp.file.ScheduleFile;
import com.handong.oodp.iterator.User;

import com.handong.oodp.Singleton.Printer;

public abstract class Work {
	public abstract void displaySchedule(List<List<List<String>>> schedule);

	public abstract List<List<List<String>>> deleteWorkSchedule(String user, String position, List<User> userlist,
			List<List<List<String>>> schedule) throws IOException;

	public abstract List<List<List<String>>> addWorkSchedule(String user, String position, List<User> userlist,
			List<List<List<String>>> schedule) throws IOException;
}