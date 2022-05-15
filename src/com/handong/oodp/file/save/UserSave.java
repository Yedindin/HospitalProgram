package com.handong.oodp.file.save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.handong.oodp.Printer;
import com.handong.oodp.iterator.User;

public class UserSave implements SaveStrategy{

	@Override
	public void save(Object list) throws IOException {
		Printer printer = Printer.getPrinter(); //singleton
		BufferedReader br = Files.newBufferedReader(Paths.get("./data/userdata.csv"), Charset.forName("UTF-8"));
		List<User> userlist = (List<User>) list;
		try {

			StringBuffer data = new StringBuffer();
			Charset.forName("UTF-8");
			for (User user : userlist) {
				data.append(user.getName() + "," + user.getID() + "," + user.getPW() + "," + user.getDepartment() + ","
						+ user.getPosition() + "," + user.getAge() + "\n");
			}
			FileOutputStream outputStream = new FileOutputStream("./data/userdata.csv");
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
