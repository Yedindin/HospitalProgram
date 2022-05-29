package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.handong.oodp.Singleton.Printer;

public class ShiftLoad implements LoadStrategy{

	

	@Override
	public List<List<String>> load() {
		
		List<List<String>> schedule = new ArrayList<List<String>>(3);
		Printer printer = Printer.getPrinter(); // singleton
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./data/applyShift.csv"));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				List<String> worktime_ = new ArrayList<String>(3);
				String[] work_1 = new String[3];
				work_1 = line.split(",");
				worktime_ = Arrays.asList(work_1);
				schedule.add(worktime_);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
		
	}

}
