package com.handong.oodp.file.load;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.handong.oodp.Singleton.Printer;
import com.handong.oodp.iterator.Surgery;
import com.handong.oodp.state.ReadySurgeryState;
import com.handong.oodp.state.State;
import com.handong.oodp.state.SurgeryControl;

public class SurgeryLoad implements LoadStrategy{
	
	@Override
	public List<Surgery> load() {
		
		List<Surgery> surgerylist = new ArrayList<Surgery>();
		Printer printer = Printer.getPrinter(); //singleton
			BufferedReader bufferedReader = null;

			try {
				bufferedReader = Files.newBufferedReader(Paths.get("./data/surgerydata.csv"));
				String line = "";

				while ((line = bufferedReader.readLine()) != null) {
					String onesurgery[] = line.split(",");
					
					Surgery surgery = new Surgery();
					SurgeryControl sct= new SurgeryControl(new ReadySurgeryState());
					
					State s = sct.findS(onesurgery[7]);
					sct = new SurgeryControl(s);
					
					surgery.setIndex(onesurgery[0]);
					surgery.setDate(onesurgery[1]);
					surgery.setTime(onesurgery[2]);
					surgery.setDepartment(onesurgery[3]);
					surgery.setDoctor(onesurgery[4]);
					surgery.setDetail(onesurgery[5]);
					surgery.setPatient(onesurgery[6]);
					surgery.setState(onesurgery[7]);
					surgery.setS(s);
					
					surgerylist.add(surgery);
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
			return surgerylist;
	}
}
