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
	
	public BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
	    ByteArrayOutputStream stream = QRCode
	      .from(barcodeText)
	      .withSize(250, 250)
	      .stream();
	    ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
	    return ImageIO.read(bis);
	}
	
	public void createImage(BufferedImage image,String targetFileName) {
		try {		 
		    File outputfile = new File(targetFileName);
		    ImageIO.write(image, "png", outputfile);		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/*public static void main(String args[]) {
		try {
			BufferedImage image = QRCodeGenerator.generateQRCodeImage("129|Pradheep|deep_90@gmail.com|84530859");
			QRCodeGenerator.createImage(image,"c:\\Roshan\\1.png");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}*/
}
