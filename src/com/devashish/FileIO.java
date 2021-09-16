package com.devashish;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Devashish Priyadarshi
 * This is a utility class for reading a binary file into list of integer array
 */
public class FileIO {
	/**
	 * reads a file and stores into a List of Integer
	 * @param filePath
	 * @return
	 */
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
	
	/**
	 * @param dirPath
	 * @return reads the files inside dirPath and creates map with key as filename and value as file content in the List<Integer> form 
	 */
	public static HashMap<String, List<Integer>> getFileNametoFileContentMap(String dirPath) {
		List<String> filesInsideDir = FileIO.listFilesPaths(dirPath);
		if(dirPath.contains("test")) {
			System.out.println("Test files: " + "" + filesInsideDir);	
		} else if(dirPath.contains("train")) {
			System.out.println("Training files: " + "" + filesInsideDir);	
		} 
		HashMap<String, List<Integer>> filesDataMap = new HashMap<String, List<Integer>>();
		filesDataMap.forEach((k, v) -> System.out.println(k + " size:" + v.size()));
		filesInsideDir.stream().forEach(fName -> filesDataMap.put(fName, FileIO.readFile(fName)));
		return filesDataMap;
	}
	
	/**
	 * @param dirStr
	 * Reads the directory 'dirStr' and returns the file names
	 * @return
	 */
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
