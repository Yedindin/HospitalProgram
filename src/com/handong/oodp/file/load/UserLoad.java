package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.handong.oodp.Printer;
import com.handong.oodp.iterator.User;

public class UserLoad implements LoadStrategy{

	@Override
	public List<User> load() {
		//UserList userlist = new UserList(100);
		//List<List<String>> list = new ArrayList<List<String>>();
		List<User> userlist = new ArrayList<User>();
		Printer printer = Printer.getPrinter(); //singleton
			BufferedReader bufferedReader = null;

			try {
				bufferedReader = Files.newBufferedReader(Paths.get("./data/userdata.csv"));
				String line = "";

				while ((line = bufferedReader.readLine()) != null) {
					User user = new User();
					//List<String> stringList = new ArrayList<>();
					String oneuser[] = line.split(",");
					user.setName(oneuser[0]);
					user.setID(oneuser[1]);
					user.setPW(oneuser[2]);
					user.setDepartment(oneuser[3]);
					user.setPosition(oneuser[4]);
					user.setAge(oneuser[5]);
					//stringList = Arrays.asList(oneuser);
					//list.add(stringList);
					userlist.add(user);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			finally {
				try {
					assert bufferedReader != null;
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return userlist;
	}

}
