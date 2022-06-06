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
import com.handong.oodp.Singleton.Printer;

public abstract class TaskWork{

	private TaskWork next;
	Printer printer = Printer.getPrinter();
	
	public TaskWork setNext(TaskWork next) {
		this.next = next;
		return next;
	}
	
	public final void work(Request request) throws IOException {
		if (resolve(request)) {
		} else if (next != null) {
			next.work(request);
		} else {
			fail(request);
		}
	}
	
	protected abstract boolean resolve(Request request) throws IOException;
	protected void fail(Request request) {
		printer.println("유효하지 않는 입력입니다.");
	}
	

}