package com.pradheep.web.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class UTF8Converter {

	public static void main(String[] args) {
		UTF8Converter obj = new UTF8Converter();
		obj.readUTF8Data();
	}
	
	public void readUTF8Data(){
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("d:\\PYRWorkspace\\Utf8Sample1.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reader  reader  = new InputStreamReader(inputStream,Charset.forName("UTF-8"));
		StringBuffer buffer = new StringBuffer();
		int data;
		try {
			data = reader.read();
			while(data != -1){
			    char theChar = (char) data;
			    buffer.append(theChar);
			    data = reader.read();
			}
			System.out.println(buffer.toString());
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeToUtf8() {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("d:\\PYRWorkspace\\Utf8Sample1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
		try {
			writer.write("இஸ்ரவேலே, நீ உனக்குக் கேடுண்டாக்கிக்கொண்டாய்; ஆனாலும் என்னிடத்தில் உனக்குச் சகாயம் உண்டு.");
			writer.close();
			System.out.println("Written successfully..");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
