package com.handong.oodp.ChainOfResponsibility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.handong.oodp.file.DRoundingFile;
import com.handong.oodp.file.File;
import com.handong.oodp.file.save.TaskSave;
import com.handong.oodp.file.save.NRoundingSave;
import com.handong.oodp.file.save.DRoundingSave;
import com.handong.oodp.iterator.User;
import com.handong.oodp.template.Work;
import com.handong.oodp.HospitalProgram;
import com.handong.oodp.Singleton.Printer;

public class TaskDisp extends TaskWork{

	private int request;
	public TaskDisp(int request) {
		super();
		this.request = request;
	}
	
	protected boolean resolve(Request request) {
		List<List<List<String>>> task = new ArrayList<List<List<String>>>(3);
		task = HospitalProgram.task;
		if (request.getRnum() == 1) {
			Printer printer = Printer.getPrinter(); // singleton
			printer.println("\n\n****전체 일정****");
			printer.println("   월      화      수      목      금      토      일");
			for (int i = 0; i < task.size(); i++) {
				printer.print(Integer.toString(i + 1));
				for (int j = 0; j < task.get(i).size(); j++) {
					String temp = "";
					for (int k = 0; k < task.get(i).get(j).size(); k++) {
						if (k == 0)
							temp += (task.get(i).get(j).get(k));
						else
							temp += ("," + task.get(i).get(j).get(k));
					}
					printer.print(temp + "|   ");
				}
				printer.println("");
			}
			return true;
		}
		return false;
		
	}
}