package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.handong.oodp.Printer;

public class ScheduleLoad implements LoadStrategy{

	

	@Override
	public List<List<List<String>>> load() {
		
		List<List<List<String>>> schedule = new ArrayList<List<List<String>>>(3);
		Printer printer = Printer.getPrinter(); // singleton
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./data/applySchedule.csv"));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				List<List<String>> weektime_ = new ArrayList<List<String>>(7);
				List<String> stringList = new ArrayList<>();
				String[] work_1 = new String[7];
				work_1 = line.split(",");
				for (String item : work_1) {
					String[] peopleList = item.split("/");
					stringList = Arrays.asList(peopleList);
					weektime_.add(stringList);
				}
				schedule.add(weektime_);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
		
	}

}
