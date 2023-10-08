/**
 * 
 */
package com.pradheep.web.common.event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Pradheep
 *
 */
@Component
public class PDFGenerator {
	
	public File generatePDF(PDFContent content,String fileName) {
		File outputFile = null;
		try {
			System.out.println("Printing the file name:" + fileName);
			outputFile = File.createTempFile(fileName.substring(0, 10).replace(" ", "_"), ".pdf");
			System.out.println("Generating PDF: " + outputFile.getAbsolutePath());
			Document document = new Document();
			document.setPageSize(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(outputFile));
			document.open();
			document.add(content.getRenderedContent());
			document.close();			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return outputFile;
	}	
}
