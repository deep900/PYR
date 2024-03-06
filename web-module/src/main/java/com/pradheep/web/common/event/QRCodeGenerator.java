/**
 * 
 */
package com.pradheep.web.common.event;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.glxn.qrgen.javase.QRCode;

/**
 * @author Pradheep
 *
 */
public class QRCodeGenerator {
	
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
	    ByteArrayOutputStream stream = QRCode
	      .from(barcodeText)
	      .withSize(500, 500)
	      .stream();
	    ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
	    return ImageIO.read(bis);
	}
	
	public static void createImage(BufferedImage image,String targetFileName) {
		try {		 
		    File outputfile = new File(targetFileName);
		    ImageIO.write(image, "png", outputfile);		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		try {
			BufferedImage image = QRCodeGenerator.generateQRCodeImage("https://www.praiseyourredeemer.org//eventHost/register?eventId=jjpXbaOG39c=");
			QRCodeGenerator.createImage(image,"c:\\Roshan\\1\\event_500.png");
			System.out.println("Created successfully.");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
