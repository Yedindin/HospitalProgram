package com.handong.oodp.file.save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.iterator.Surgery;

public class SurgerySave implements SaveStrategy {
	@Override
	public void save(Object list) throws IOException {
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/surgerydata.csv"), Charset.forName("UTF-8"));
		List<Surgery> surgerylist = (List<Surgery>) list;
		try {

			StringBuffer data = new StringBuffer();
			Charset.forName("UTF-8");
			for (Surgery surgery : surgerylist) {
				data.append(surgery.getIndex() + "," + surgery.getDate() + "," + surgery.getTime() + "," + surgery.getDepartment() + ","
						+ surgery.getDoctor() + "," + surgery.getDetail() + "," + surgery.getState().toString() + "\n");
			}
			FileOutputStream outputStream = new FileOutputStream("./data/surgerydata.csv");
			outputStream.write(data.toString().getBytes());
			outputStream.close();

			printer.println("저장완료");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}
