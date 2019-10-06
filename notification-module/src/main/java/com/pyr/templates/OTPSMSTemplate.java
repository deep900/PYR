/**
 * 
 */
package com.pyr.templates;

/**
 * @author pradheep.p
 *
 */
public class OTPSMSTemplate extends PyrTemplate {

	private String message;

	public OTPSMSTemplate(String arg) {
		this.message = arg;
	}

	@Override
	public String getFormatString() {
		return message;
	}

	@Override
	public int getType() {
		return TemplateType.SMS_OTP_TEMPLATE;
	}

}
