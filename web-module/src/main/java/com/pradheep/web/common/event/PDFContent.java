/**
 * 
 */
package com.pradheep.web.common.event;

import java.awt.image.BufferedImage;
import java.util.List;

import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author Pradheep
 *
 */
public interface PDFContent {
	
	public PdfPTable getRenderedContent();
	
	public void setRenderContent(List<ParticipantInformation> input,List<BufferedImage> QRCode,String title);

}
