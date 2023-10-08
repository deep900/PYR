package com.pradheep.web.common.event;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ChurchEventPassContent implements PDFContent {

	private List<ParticipantInformation> input;

	private List<BufferedImage> QRCodes;

	private String title;

	public ChurchEventPassContent(List<ParticipantInformation> input, List<BufferedImage> QRCodes, String title) {
		setRenderContent(input, QRCodes, title);
	}

	@Override
	public PdfPTable getRenderedContent() {
		PdfPTable table = new PdfPTable(3);
		this.addTableHeader(table);
		try {
			this.addCustomRows(table, input, QRCodes);
		} catch (BadElementException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private void setCellStyle(PdfPCell pdfpCell) { 
		pdfpCell.setBorder(PdfPCell.BOX);
		pdfpCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfpCell.setBorderWidth(1);
		pdfpCell.setBorderWidthBottom(1);
		pdfpCell.setBorderWidthLeft(1);
		pdfpCell.setBorderWidthRight(1);
		pdfpCell.setBorderWidthTop(1);
		pdfpCell.setBorderColorRight(BaseColor.LIGHT_GRAY);
		pdfpCell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
		pdfpCell.setBorderColorTop(BaseColor.LIGHT_GRAY);
		pdfpCell.setBorderColorBottom(BaseColor.LIGHT_GRAY);
		pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);		
		pdfpCell.setUseBorderPadding(true);	
	}

	private void addTableHeader(PdfPTable table) {
		PdfPCell header = new PdfPCell(
				new Paragraph(1, title, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));
		header.setBackgroundColor(BaseColor.YELLOW);
		setCellStyle(header);
		header.setPadding(10);
		header.setNoWrap(true);
		header.setColspan(3);
		table.addCell(header);
	}

	private Image convertBuffImageToImg(BufferedImage bImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpeg", baos);
			byte[] bytes = baos.toByteArray();
			Jpeg jpgObj = new Jpeg(bytes);
			return jpgObj;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PdfPCell getRenderCell(String phrase) {
		PdfPCell textCell = new PdfPCell(new Phrase(phrase));
		setCellStyle(textCell);
		return textCell;
	}

	private void addCustomRows(PdfPTable table, List<ParticipantInformation> inputData, List<BufferedImage> images)
			throws URISyntaxException, BadElementException, IOException {
		AtomicInteger index = new AtomicInteger();
		inputData.forEach(participantInformation -> {
			String name = participantInformation.getParticipantName();
			String id = participantInformation.getId();
			table.addCell(getRenderCell(name));
			table.addCell(getRenderCell(id));
			if (images.size() > index.get()) {
				PdfPCell imageCell = new PdfPCell(convertBuffImageToImg(images.get(index.getAndIncrement())), true);
				setCellStyle(imageCell);
				table.addCell(imageCell);
			}
		});
	}

	@Override
	public void setRenderContent(List<ParticipantInformation> input, List<BufferedImage> QRCodes, String title) {
		this.QRCodes = QRCodes;
		this.input = input;
		this.title = title;
	}
}
