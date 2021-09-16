package com.devashish;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Devashish Priyadarshi
 * This is a utility class for reading a binary file into list of integer array
 */
public class FileIO {
	public static List<Integer> readFile(String filePath) {
		List<Integer> data = new ArrayList<Integer>();
		try(FileInputStream fis = new FileInputStream(new File(filePath))) {
			do {
				Integer c = fis.read();
				data.add(c);
			} while(fis.available()>0);
		} catch(IOException ioe) {
			
		}
		return data;
	}
	
	public static ArrayList<String> listFilesPaths(String dirStr) {
		ArrayList<String> lst = new ArrayList<String>();
		File dir = new File(dirStr);
		if(dir.exists()) {
			File[] files = dir.listFiles();
			for(int i=0;i<files.length;i++) {
				File file = files[i];
				if(!file.isDirectory()) {
				    lst.add(dirStr + "/"+ file.getName());
				}
			}
		}
		return lst;
	}
}
