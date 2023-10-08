package com.pradheep.web.common.event;

import java.io.File;
import java.util.List;

public interface WelcomeEmailAttachmentUtility {

	public File getWelcomeEmailAttachment(List<ParticipantInformation> participantInformation,String title,String fileName);	
}
