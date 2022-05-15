package com.handong.oodp.file;

import java.io.IOException;

import com.handong.oodp.file.load.LoadStrategy;
import com.handong.oodp.file.save.SaveStrategy;

public abstract class File {
	private String name;
	private LoadStrategy loadstrategy;
	private SaveStrategy savestrategy;
	
	public File(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Object load() {
		return loadstrategy.load();
	}
	public void save(Object o) throws IOException {
		savestrategy.save(o);
	}
	public void setLoadstrategy(LoadStrategy loadstrategy) {
		this.loadstrategy = loadstrategy;
	}
	public void setSavestrategy(SaveStrategy savestrategy) {
		this.savestrategy = savestrategy;
	}

}
