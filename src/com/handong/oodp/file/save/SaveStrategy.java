package com.handong.oodp.file.save;

import java.io.IOException;

public interface SaveStrategy {
	public void save(Object o) throws IOException;

}
