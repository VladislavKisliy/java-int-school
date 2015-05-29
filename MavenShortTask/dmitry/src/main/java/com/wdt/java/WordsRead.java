package com.wdt.java;

import java.io.*;
import java.util.*; 

public class WordsRead {
	
	private static String readFile(String filename) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (filename));
	    String line = null;
	    StringBuilder sb = new StringBuilder();
	    while((line=reader.readLine()) != null ) {
	        sb.append(line+" ");	        
	    }	    
	    reader.close();	    
	    return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("serksfbklrbfjke");
		String filename = "C:/Work Docs/39script.sql";
		String filenameDup = "C:/Work Docs/duplicates.txt";
		String filenameUnique = "C:/Work Docs/unique.txt";
		
		StringBuilder sbDup = new StringBuilder();
		StringBuilder sbUnique = new StringBuilder();
		
		String filestring = readFile(filename).trim().replaceAll("[,.!?:;*\"/=()-]"," ");
		List<String> words = Arrays.asList(filestring.split("\\s+"));
		Set<String> uniqueWords = new HashSet<String>(words);
		
		Integer freq;
		Integer cnt_dup = 0;		
		for (String s : uniqueWords){
			sbUnique.append(s + "\n");
			freq = Collections.frequency(words, s); 
			if (freq > 1){
				cnt_dup++;
				sbDup.append(s + " : " + freq + "\n");
			}				
		}
		sbUnique.append("We have "+uniqueWords.size()+" unique words here!");
		sbDup.append("We have "+cnt_dup+" duplicates here!");
		
		PrintWriter pwUnique = new PrintWriter(new File(filenameUnique).getAbsoluteFile());
		pwUnique.print(sbUnique.toString());
		pwUnique.close();
		
		PrintWriter pwDup = new PrintWriter(new File(filenameDup).getAbsoluteFile());
		pwDup.print(sbDup.toString());
		pwDup.close();
	}
}
