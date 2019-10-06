package com.pradheep.dao.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.pradheep.dao.importclass.model.BibleVerseEngImport;

public class ImportVerseUtility {

	HashMap map = new HashMap();
	
	String files[] = new String[] { "1.json", "archieve.json", "archieve0.json", "archieve1.json", "archieve2.json",
			"archieve3.json", "archieve4.json", "archieve5.json", "archieve6_unused.json", "english_archieve.json",
			"EngVerses.json" };

	private void loadJsonFiles()  {
		File fout = new File("D:\\Workspace\\AG\\Web1\\src\\main\\resources\\final_output.txt");
		try {
			fout.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for (String file : files) {
				File f = new File("D:\\Workspace\\AG\\Web1\\src\\main\\resources\\"+ file);
				System.out.println("---------- Reading : " + f.getAbsolutePath() +"-----------");
				if(f.exists()){
					FileReader in = new FileReader(f);
					BufferedReader reader = new BufferedReader(in);
					String str = null;
					while((str = reader.readLine()) != null){
						if(str.contains("dailyVerseEng")){
							//System.out.println(str);
							String engVerse = str.substring(str.indexOf(":")+2, str.indexOf("chapter")-4);
							String engChapter = str.substring(str.lastIndexOf("chapter")+10,str.indexOf("date")-4);
							System.out.println(engVerse);
							System.out.println(engChapter);
							String date = str.substring(str.lastIndexOf(":")+1,str.length() -2);
							date = date.replace("\"", "");
							System.out.println(date);
							BibleVerse bv = null;
							if(map.containsKey(date)){
								bv = (BibleVerse) map.get(date);
							}
							else{
							 bv = new BibleVerse();
							}
							bv.setEngChapter(engChapter);
							bv.setEngVerse(engVerse);
							
							map.put(date, bv);
						}		
						else if(str.contains("dailyVerse")){
							//System.out.println(">> "+str);
							String tamVerse = str.substring(str.indexOf(":")+2, str.indexOf("chapter")-4);
							String tamChapter = str.substring(str.lastIndexOf("chapter")+10,str.indexOf("date")-4);
							System.out.println(tamVerse);
							System.out.println(tamChapter);
							String date = str.substring(str.lastIndexOf(":")+1,str.length() -2);
							date = date.replace("\"", "");
							System.out.println(date);
							BibleVerse bv = null;
							if(map.containsKey(date)){
								bv = (BibleVerse) map.get(date);
							}
							else{
							 bv = new BibleVerse();
							}
							bv.setVerse(tamVerse);
							bv.setChapter(tamChapter);
							
							map.put(date, bv);
						}
					}
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.println(map);
		FileOutputStream out;
		try {
			out = new FileOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(map);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException er){
			er.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ImportVerseUtility utility = new ImportVerseUtility();
		utility.loadJsonFiles();
	}

}
