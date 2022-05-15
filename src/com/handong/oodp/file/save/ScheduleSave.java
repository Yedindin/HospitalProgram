package com.handong.oodp.file.save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.handong.oodp.Printer;

public class ScheduleSave implements SaveStrategy {

	@Override
	public void save(Object o) {
		Printer printer = Printer.getPrinter(); // singleton
		String filePath = "./data/applySchedule.csv";
		String NEWLINE = System.lineSeparator();
		List<List<List<String>>> schedule = (List<List<List<String>>>) o;
		try {
			File file = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < schedule.size(); i++) {
				for (int j = 0; j < schedule.get(i).size(); j++) {
					String temp = "";
					for (int k = 0; k < schedule.get(i).get(j).size(); k++)
						temp += (schedule.get(i).get(j).get(k) + "/");
					temp += ",";
					bw.write(temp);
				}
				bw.write(NEWLINE);
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
