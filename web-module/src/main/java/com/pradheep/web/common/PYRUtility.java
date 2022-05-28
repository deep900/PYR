package com.pradheep.web.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import org.apache.commons.lang3.StringEscapeUtils;

public class PYRUtility {

	public JTextArea tamilTextArea = new JTextArea(20, 10);
	public JTextArea unicodeTextArea = new JTextArea(20, 10);
	
	public static String getTodayDateFormatted(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
		Date today = new Date();
		return sdf.format(today);		
	}
	
	public static Date getNextDaySixAM(Integer offsetInSeconds){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 6);	
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		if(offsetInSeconds != null) {
			calendar.add(GregorianCalendar.SECOND, offsetInSeconds);
		}
		return calendar.getTime();
	}
	
	public static Date getThreeDaysFromNow(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 3);
		//calendar.add(GregorianCalendar.SECOND, 60); // For testing purpose will be reverted later.
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 6);	
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return calendar.getTime();
	}
	
	public static String getYesterdayDateFormatted(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
		Date today = new Date();
		GregorianCalendar gregorianCalender = new GregorianCalendar();		
		gregorianCalender.setTime(today);
		gregorianCalender.set(GregorianCalendar.HOUR, -24);
		return sdf.format(gregorianCalender.getTime());		
	}
	
	public static String getUnicodeCharacter(String input) {
		StringBuffer buff = new StringBuffer();
		if (input == null) {
			return "";
		}
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			String arg = "\\u" + Integer.toHexString(c | 0x10000).substring(1);
			buff.append(arg);
		}
		final String result = buff.toString();		
		return result;
	}
	
	public static String convertUnicodeToString(String input){
		final String val = StringEscapeUtils.unescapeJava(input);		
		return val;
	}
	
	private static void converFile(){
		String inputFile = new String("d:\\input.txt");
		String outputFile = new String("d:\\output_contry.txt");
		File inputFileObj = new File(inputFile);
		File outputFileObj = new File(outputFile);
		
		try {
			FileReader fileReader = new FileReader(inputFile);
			FileWriter fileWitter = new FileWriter(outputFileObj);
			String str = "";
			BufferedReader br = new BufferedReader(fileReader);
			BufferedWriter writ = new BufferedWriter(fileWitter);
			while((str=br.readLine()) != null){
				if(str.isEmpty() || str == null){
					continue;
				}
				System.out.println(str);
				String temp = str.substring(str.indexOf(">")+1, str.indexOf("</"));
				System.out.println(temp);
				String newStr = "<form:option value=\""+temp+"\">" + temp + "</form:option>";
				System.out.println(newStr);
				writ.write(newStr);
				
			}
			br.close();
			writ.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException err){
			
		}
		
	}
	
	public void makeUI(){
		JFrame jf = new JFrame();
		JPanel panel = new JPanel();		
	}
	
	public static void main(String[] args) {	
		//PYRUtility.converFile();
		/*PYRUtility utility = new PYRUtility();
		utility.createUI();*/
		
		convertFile(); 
		creationTest();
		System.exit(-1);
	}
	
	public static void convertFile(){
		JFileChooser jfc = new JFileChooser("C:\\Software_Development\\PYRWorkspace\\message_translations");
		jfc.showOpenDialog(new JFrame());
		StringBuffer buf = new StringBuffer();
		File f = jfc.getSelectedFile();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bReader = new BufferedReader(fileReader);
		String str = null;
		try {
			while((str = bReader.readLine()) != null){
				buf.append(str);
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		System.out.println(getUnicodeCharacter(buf.toString()));
	}
	
	public static void creationTest(){
		//String arg ="நம்மக்கு நியமிக்கப்பட்ட ஓட்டம்";
		//System.out.println(getUnicodeCharacter(arg));
	}
	
	private void createUI(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame jf = new JFrame();
		jf.setPreferredSize(new Dimension(750,650)); 
		jf.setSize(new Dimension(750,650)); 
		jf.setTitle("Unicode Converter V1");
		JPanel mainContainer = new JPanel();
		
		tamilTextArea.setBorder(BorderFactory.createEtchedBorder());

		unicodeTextArea.setBorder(BorderFactory.createLineBorder(Color.red));
		JButton btn = new JButton("Convert");
		btn.addActionListener(new MyClickListener());
		
		// Arrange them
		mainContainer.setLayout(new BorderLayout());
		Dimension preferred = new Dimension(300,100);
		tamilTextArea.setPreferredSize(preferred);
		unicodeTextArea.setPreferredSize(preferred);
		tamilTextArea.setSize(preferred);
		unicodeTextArea.setSize(preferred);
		
		mainContainer.setPreferredSize(new Dimension(720,600));
		mainContainer.setSize(new Dimension(720,600));
		
		mainContainer.add(tamilTextArea,BorderLayout.NORTH);
		mainContainer.add(new JSeparator());
		mainContainer.add(unicodeTextArea,BorderLayout.CENTER);
		mainContainer.add(btn, BorderLayout.SOUTH);
		
		jf.setContentPane(mainContainer);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
	}
	
	class MyClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {			
			System.out.println("Clicked the button");
			String text = tamilTextArea.getText();
			unicodeTextArea.setText(convertUnicodeToString(text));
		}
		
	}
	
	

}
