/**
 * 
 */
package com.pradheep.web.common.event;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradheep.dao.config.ApplicationLogger;

/**
 * @author Pradheep
 *
 */
@Component
public class ChurchEventWelcomeEmail implements WelcomeEmailAttachmentUtility {

	@Autowired
	private PDFGenerator pdfGenerator;

	private Logger logger;

	private QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();

	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(this.getClass());
		}
		return logger;
	}

	@Override
	public File getWelcomeEmailAttachment(List<ParticipantInformation> participantInformation, String title,String fileName) {
		try {
			getLogger().info("Preparing the attachment with QR Codes" + participantInformation.toString());
			List<BufferedImage> QRCodes = getQRCodesForParticipants(participantInformation);
			ChurchEventPassContent pdfContent = new ChurchEventPassContent(participantInformation, QRCodes, title);
			return pdfGenerator.generatePDF(pdfContent,fileName);
		} catch (Exception err) {
			getLogger().error("Error while preparing the email attachment", err);
			return null;
		}		
	}

	private List<BufferedImage> getQRCodesForParticipants(List<ParticipantInformation> participantInformation) {
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		participantInformation.forEach(participantInformationObj -> {
			try {
				images.add(qrCodeGenerator.generateQRCodeImage(participantInformationObj.getDelimitedString()));
			} catch (Exception err) {
				getLogger().error("Error while generating the QR Code", err);
			}
		});
		return images;
	}
}
